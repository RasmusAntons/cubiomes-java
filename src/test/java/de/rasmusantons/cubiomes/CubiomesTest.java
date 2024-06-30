package de.rasmusantons.cubiomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubiomesTest {
    private static final long TEST_SEED = 1234567890L;

    @Test
    void getBiomeAt() {
        Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, TEST_SEED);
        assertEquals(cubiomes.getBiomeAt(1, 0, 0, 0), BiomeID.lush_caves);
        assertEquals(cubiomes.getBiomeAt(1, 0, 63, 0), BiomeID.jungle);
        assertEquals(cubiomes.getBiomeAt(1, -150, 63, 150), BiomeID.tall_birch_forest);
    }

    @Test
    void genBiomes() {
        Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, TEST_SEED);
        Range r = new Range(1, -105, -1681, 2, 2, -29, 2);
        BiomeID[][][] biomeIDs = cubiomes.genBiomes(r);
        BiomeID[][][] expected = {
                {
                        {BiomeID.deep_dark, BiomeID.deep_dark},
                        {BiomeID.deep_dark, BiomeID.deep_dark}
                },
                {
                        {BiomeID.forest, BiomeID.forest},
                        {BiomeID.forest, BiomeID.stone_shore}
                }
        };
        for (int dy = 0; dy < r.sy(); ++dy) {
            for (int dz = 0; dz < r.sz(); ++dz) {
                for (int dx = 0; dx < r.sx(); ++dx) {
                    assertEquals(biomeIDs[dy][dz][dx], expected[dy][dz][dx]);
                }
            }
        }
    }

    @Test
    void genBiomes2D() {
        Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, 0L);
        BiomeID[][] biomeIDs = cubiomes.genBiomes2D(new Range(1, -1, -1, 2, 2, 15));
        for (BiomeID[] sliceZ : biomeIDs) {
            for (BiomeID biomeID : sliceZ) {
                assertEquals(biomeID, BiomeID.river);
            }
        }
    }

    @Test
    void getStructurePos() {
        Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
        Pos pos = cubiomes.getStructurePos(StructureType.Trial_Chambers, TEST_SEED, 0, 0);
        assertNotNull(pos);
        assertEquals(pos.x(), 256);
        assertEquals(pos.z(), 224);
    }

    @Test
    void isViableStructurePos() {
        Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, TEST_SEED);
        boolean viable = cubiomes.isViableStructurePos(StructureType.Desert_Pyramid, -336, -2496);
        assertTrue(viable);
    }

    @Test
    void checkForBiomes() {
        Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
        Range r = new Range(1, -1580, 2395, 2, 2, 63, 1);
        BiomeFilter cherryGroveFilter = BiomeFilter.Builder.with().allOf(BiomeID.cherry_grove).build();
        boolean hasCherryGrove = cubiomes.checkForBiomes(r, Dimension.DIM_OVERWORLD, TEST_SEED, cherryGroveFilter);
        assertTrue(hasCherryGrove);
        BiomeFilter badlandsFilter = BiomeFilter.Builder.with().allOf(BiomeID.badlands).build();
        boolean hasBadlands = cubiomes.checkForBiomes(r, Dimension.DIM_OVERWORLD, TEST_SEED, badlandsFilter);
        assertFalse(hasBadlands);
    }
}
