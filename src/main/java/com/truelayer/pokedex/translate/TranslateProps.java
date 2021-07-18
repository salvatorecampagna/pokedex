package com.truelayer.pokedex.translate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(prefix = "service.pokemon.translate")
@Validated
public class TranslateProps {

    @NotBlank(message = "service.pokemon.translate.url must not be blank")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("TranslateProps{url='%s'}", url);
    }
}
