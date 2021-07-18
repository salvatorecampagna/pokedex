package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.Translation;
import com.truelayer.pokedex.translate.model.TranslationRequest;
import com.truelayer.pokedex.translate.model.TranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Translation translate(final String text) {
            final String url = String.format(props.getUrl(), translation);
            final TranslationResponse translationResponse = restTemplate.postForEntity(
                    url, new TranslationRequest(text), TranslationResponse.class).getBody();
            return new Translation(
                    translationResponse.getContents().getTranslated(),
                    translationResponse.getContents().getText(),
                    translationResponse.getContents().getTranslation()
            );
    }

    @Override
    public String getTranslation() {
        return translation;
    }
}
