package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.TranslationRequest;
import com.truelayer.pokedex.translate.model.TranslationResponse;
import org.springframework.web.client.RestTemplate;

public class TranslationClientImpl implements TranslationClient {

    private final TranslateProps props;
    private final RestTemplate restTemplate;
    private final String translation;

    public TranslationClientImpl(
            final TranslateProps props,
            final RestTemplate restTemplate,
            final String translation
    ) {
        this.props = props;
        this.restTemplate = restTemplate;
        this.translation = translation;
    }

    @Override
    public String translate(final String text) {
            final String url = String.format(props.getUrl(), translation);
            return restTemplate.postForEntity(url, new TranslationRequest(text), TranslationResponse.class)
                    .getBody().getContents().getTranslated();
    }
}
