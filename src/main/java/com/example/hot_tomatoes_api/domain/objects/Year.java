package com.example.hot_tomatoes_api.domain.objects;


public record Year(int value) {
    public Year {
        if (value < 1900 || value > 2100) {
            throw new IllegalArgumentException("Year must be between 1900 and 2100");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year = (Year) o;
        return value == year.value;
    }

    public int compareTo(Year year) {
        return this.value - year.value;
    }
}
