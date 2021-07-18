package com.trustlayer.pokedex.translate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.trustlayer.pokedex.auth.ApiKeyProvider;
import com.trustlayer.pokedex.translate.model.TranslationRequest;
import com.trustlayer.pokedex.translate.model.TranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TranslateClientImpl implements TranslationClient {

    @Autowired
    private ApiKeyProvider apiKeyProvider;

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.funtranslations.com/translate/%s.json";

    public TranslateClientImpl(
            final RestTemplateBuilder restTemplateBuilder
    ) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private HttpHeaders buildHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        if (!apiKeyProvider.get().getValid()) {
            // Return empty headers
            return headers;
        }
        headers.add("X-Funtranslations-Api-Secret", apiKeyProvider.get().getApiKey());
        return headers;
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "translateFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
            }
    )
    public String translate(final String text, final String translation) {
        final String url = String.format(URL, translation);
        final ResponseEntity<TranslationResponse> response = restTemplate.postForEntity(
                url, new TranslationRequest(text), TranslationResponse.class, buildHeaders()
        );
        final HttpStatus statusCode = response.getStatusCode();
        if (!statusCode.is2xxSuccessful()) {
            throw new TranslationServiceException(
                    String.format("Error while invoking the translations service. Status code: %s", statusCode)
            );
        }

        return response.getBody().getContents().getText();
    }

    private String translateFallback(final String text, final String translation) {
        return text;
    }
}
