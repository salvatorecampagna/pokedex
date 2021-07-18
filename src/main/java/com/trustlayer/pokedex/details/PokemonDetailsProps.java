package com.trustlayer.pokedex.details;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(prefix = "service.pokemon.details")
@Validated
public class PokemonDetailsProps {
    @NotBlank(message = "service.pokemon.details.url must not be blank")
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return String.format("PokemonDetailsProps{url='%s'}", url);
    }
}
