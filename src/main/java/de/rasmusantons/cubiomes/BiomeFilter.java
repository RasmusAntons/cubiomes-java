package de.rasmusantons.cubiomes;

public record BiomeFilter(BiomeID[] required, BiomeID[] excluded, BiomeID[] matchany) {
    public static class Builder {
        BiomeID[] required = new BiomeID[0];
        BiomeID[] excluded = new BiomeID[0];
        BiomeID[] matchany = new BiomeID[0];

        public static Builder with() {
            return new Builder();
        }

        public Builder allOf(BiomeID... biomes) {
            required = biomes;
            return this;
        }

        public Builder noneOf(BiomeID... biomes) {
            excluded = biomes;
            return this;
        }

        public Builder anyOf(BiomeID... biomes) {
            matchany = biomes;
            return this;
        }

        public BiomeFilter build() {
            return new BiomeFilter(required, excluded, matchany);
        }
    }
}
