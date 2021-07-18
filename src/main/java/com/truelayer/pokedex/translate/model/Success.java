package com.truelayer.pokedex.translate.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class Success {
    private Integer total;

    public Success() {
    }

    public Success(
            final Integer total
    ) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Success that = (Success) o;
        return Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }

    @Override
    public String toString() {
        return String.format("Success{total=%d}", total);
    }
}
