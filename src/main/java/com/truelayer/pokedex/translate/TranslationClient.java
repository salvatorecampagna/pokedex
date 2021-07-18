package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.Translation;

public interface TranslationClient {
    Translation translate(String text);
}
