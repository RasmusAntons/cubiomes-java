package de.rasmusantons.cubiomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void getBiomeAt() {
        Generator generator = new Generator(MCVersion.MC_1_21);
        generator.applySeed(Dimension.DIM_OVERWORLD, 0);
        assertEquals(generator.getBiomeAt(1, 0, 63, 0), BiomeID.river);
        assertEquals(generator.getBiomeAt(1, 50, 63, 50), BiomeID.forest);
    }

    @Test
    void genBiomes() {
        Generator generator = new Generator(MCVersion.MC_1_21);
        generator.applySeed(Dimension.DIM_OVERWORLD, 0);
        BiomeID[][][] biomeIDs = generator.genBiomes(new Range(1, -1, -1, 2, 2, 15, 2));
        for (BiomeID[][] sliceY : biomeIDs) {
            for (BiomeID[] sliceZ : sliceY) {
                for (BiomeID biomeID : sliceZ) {
                    assertEquals(biomeID, BiomeID.river);
                }
            }
        }
    }

    @Test
    void genBiomes2D() {
        Generator generator = new Generator(MCVersion.MC_1_21);
        generator.applySeed(Dimension.DIM_OVERWORLD, 0);
        BiomeID[][] biomeIDs = generator.genBiomes2D(new Range(1, -1, -1, 2, 2, 15));
        for (BiomeID[] sliceZ : biomeIDs) {
            for (BiomeID biomeID : sliceZ) {
                assertEquals(biomeID, BiomeID.river);
            }
        }
    }
}
