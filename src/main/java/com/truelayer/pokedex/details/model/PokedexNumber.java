package com.truelayer.pokedex.details.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PokedexNumber{
    @JsonProperty("entry_number")
    public int entryNumber;
    public Pokedex pokedex;

    public int getEntryNumber() {
        return entryNumber;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokedexNumber that = (PokedexNumber) o;
        return entryNumber == that.entryNumber && Objects.equals(pokedex, that.pokedex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryNumber, pokedex);
    }

    @Override
    public String toString() {
        return String.format("PokedexNumber{entryNumber=%d, pokedex=%s}", entryNumber, pokedex);
    }
}
