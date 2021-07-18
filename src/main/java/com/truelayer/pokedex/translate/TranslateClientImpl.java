package com.truelayer.pokedex.translate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.truelayer.pokedex.translate.model.TranslationRequest;
import com.truelayer.pokedex.translate.model.TranslationResponse;
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
    private TranslateProps props;

    private final RestTemplate restTemplate;
    private final String translation;

    public TranslateClientImpl(
            final RestTemplate restTemplate,
            final String translation
    ) {
        this.restTemplate = restTemplate;
        this.translation = translation;
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "translateFallback",
            threadPoolKey = "pokemon-translate",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public String translate(final String text) {
        final String url = String.format(props.getUrl(), translation);
        final ResponseEntity<TranslationResponse> response = restTemplate.postForEntity(
                url, new TranslationRequest(text), TranslationResponse.class
        );
        final HttpStatus statusCode = response.getStatusCode();
        if (!statusCode.is2xxSuccessful()) {
            throw new TranslationServiceException(
                    String.format("Error while invoking the translations service. Status code: %s", statusCode)
            );
        }

        return response.getBody().getContents().getText();
    }

    private String translateFallback(final String text) {
        return text;
    }
}
