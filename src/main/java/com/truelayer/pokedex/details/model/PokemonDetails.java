package com.truelayer.pokedex.details.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class PokemonDetails {
    public int id;
    public String name;
    public int order;
    @JsonProperty("gender_rate")
    public int genderRate;
    @JsonProperty("capture_rate")
    public int captureRate;
    @JsonProperty("base_happiness")
    public int baseHappiness;
    @JsonProperty("is_baby")
    public boolean isBaby;
    @JsonProperty("is_legendary")
    public boolean isLegendary;
    @JsonProperty("is_mythical")
    public boolean isMythical;
    @JsonProperty("hatch_counter")
    public int hatchCounter;
    @JsonProperty("has_gender_differences")
    public boolean hasGenderDifferences;
    @JsonProperty("forms_switchable")
    public boolean formsSwitchable;
    @JsonProperty("growth_rate")
    public GrowthRate growthRate;
    @JsonProperty("pokedex_numbers")
    public List<PokedexNumber> pokedex_Numbers;
    @JsonProperty("egg_groups")
    public List<EggGroup> eggGroups;
    public Color color;
    public Shape shape;
    @JsonProperty("evolves_from_species")
    public EvolvesFromSpecies evolvesFromSpecies;
    @JsonProperty("evolution_chain")
    public EvolutionChain evolutionChain;
    public Habitat habitat;
    public Generation generation;
    public List<Name> names;
    @JsonProperty("flavor_text_entries")
    public List<FlavorTextEntry> flavorTextEntries;
    @JsonProperty("form_descriptions")
    public List<FormDescription> formDescriptions;
    public List<Genera> genera;
    public List<Variety> varieties;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public int getGenderRate() {
        return genderRate;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public int getBaseHappiness() {
        return baseHappiness;
    }

    public boolean isBaby() {
        return isBaby;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public boolean isMythical() {
        return isMythical;
    }

    public int getHatchCounter() {
        return hatchCounter;
    }

    public boolean isHasGenderDifferences() {
        return hasGenderDifferences;
    }

    public boolean isFormsSwitchable() {
        return formsSwitchable;
    }

    public GrowthRate getGrowthRate() {
        return growthRate;
    }

    public List<PokedexNumber> getPokedex_Numbers() {
        return pokedex_Numbers;
    }

    public List<EggGroup> getEggGroups() {
        return eggGroups;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public EvolvesFromSpecies getEvolvesFromSpecies() {
        return evolvesFromSpecies;
    }

    public EvolutionChain getEvolutionChain() {
        return evolutionChain;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public Generation getGeneration() {
        return generation;
    }

    public List<Name> getNames() {
        return names;
    }

    public List<FlavorTextEntry> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public List<FormDescription> getFormDescriptions() {
        return formDescriptions;
    }

    public List<Genera> getGenera() {
        return genera;
    }

    public List<Variety> getVarieties() {
        return varieties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonDetails that = (PokemonDetails) o;
        return id == that.id && order == that.order && genderRate == that.genderRate && captureRate == that.captureRate && baseHappiness == that.baseHappiness && isBaby == that.isBaby && isLegendary == that.isLegendary && isMythical == that.isMythical && hatchCounter == that.hatchCounter && hasGenderDifferences == that.hasGenderDifferences && formsSwitchable == that.formsSwitchable && Objects.equals(name, that.name) && Objects.equals(growthRate, that.growthRate) && Objects.equals(pokedex_Numbers, that.pokedex_Numbers) && Objects.equals(eggGroups, that.eggGroups) && Objects.equals(color, that.color) && Objects.equals(shape, that.shape) && Objects.equals(evolvesFromSpecies, that.evolvesFromSpecies) && Objects.equals(evolutionChain, that.evolutionChain) && Objects.equals(habitat, that.habitat) && Objects.equals(generation, that.generation) && Objects.equals(names, that.names) && Objects.equals(flavorTextEntries, that.flavorTextEntries) && Objects.equals(formDescriptions, that.formDescriptions) && Objects.equals(genera, that.genera) && Objects.equals(varieties, that.varieties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, name, order, genderRate, captureRate, baseHappiness, isBaby,
                isLegendary, isMythical, hatchCounter, hasGenderDifferences,
                formsSwitchable, growthRate, pokedex_Numbers, eggGroups,
                color, shape, evolvesFromSpecies, evolutionChain, habitat,
                generation, names, flavorTextEntries, formDescriptions, genera, varieties);
    }

    @Override
    public String toString() {
        return String.format(
                "PokemonSpecie{id=%d, name='%s', order=%d, genderRate=%d, captureRate=%d, " +
                        "baseHappiness=%d, isBaby=%s, isLegendary=%s, isMythical=%s, " +
                        "hatchCounter=%d, hasGenderDifferences=%s, formsSwitchable=%s, " +
                        "growthRate=%s, pokedex_Numbers=%s, eggGroups=%s, color=%s, shape=%s, " +
                        "evolvesFromSpecies=%s, evolutionChain=%s, habitat=%s, generation=%s, " +
                        "names=%s, flavorTextEntries=%s, formDescriptions=%s, genera=%s, varieties=%s}",
                id, name, order, genderRate, captureRate, baseHappiness, isBaby, isLegendary, isMythical,
                hatchCounter, hasGenderDifferences, formsSwitchable, growthRate, pokedex_Numbers, eggGroups,
                color, shape, evolvesFromSpecies, evolutionChain, habitat, generation, names, flavorTextEntries,
                formDescriptions, genera, varieties
        );
    }
}
