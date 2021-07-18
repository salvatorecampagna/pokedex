package com.truelayer.pokedex.translate;

import org.springframework.stereotype.Service;

@Service
public interface TranslationClientProvider {
    TranslationClient get(String habitat, Boolean isLegendary);
}
