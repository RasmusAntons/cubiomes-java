package de.rasmusantons.cubiomes;

import java.util.EnumSet;

public enum BiomeID {
    none(-1),
    ocean(0),
    plains(1),
    desert(2),
    mountains(3), extremeHills(mountains.getValue()),
    forest(4),
    taiga(5),
    swamp(6), swampland(swamp.getValue()),
    river(7),
    nether_wastes(8), hell(nether_wastes.getValue()),
    the_end(9), sky(the_end.getValue()),
    frozen_ocean(10), frozenOcean(frozen_ocean.getValue()),
    frozen_river(11), frozenRiver(frozen_river.getValue()),
    snowy_tundra(12), icePlains(snowy_tundra.getValue()),
    snowy_mountains(13), iceMountains(snowy_mountains.getValue()),
    mushroom_fields(14), mushroomIsland(15),
    mushroom_field_shore(15), mushroomIslandShore(mushroom_field_shore.getValue()),
    beach(16),
    desert_hills(17), desertHills(desert_hills.getValue()),
    wooded_hills(18), forestHills(wooded_hills.getValue()),
    taiga_hills(19), taigaHills(taiga_hills.getValue()),
    mountain_edge(20), extremeHillsEdge(mountain_edge.getValue()),
    jungle(21),
    jungle_hills(22), jungleHills(jungle_hills.getValue()),
    jungle_edge(23), jungleEdge(jungle_edge.getValue()),
    deep_ocean(24), deepOcean(deep_ocean.getValue()),
    stone_shore(25), stoneBeach(stone_shore.getValue()),
    snowy_beach(26), coldBeach(snowy_beach.getValue()),
    birch_forest(27), birchForest(birch_forest.getValue()),
    birch_forest_hills(28), birchForestHills(birch_forest_hills.getValue()),
    dark_forest(29), roofedForest(dark_forest.getValue()),
    snowy_taiga(30), coldTaiga(snowy_taiga.getValue()),
    snowy_taiga_hills(31), coldTaigaHills(snowy_taiga_hills.getValue()),
    giant_tree_taiga(32), megaTaiga(giant_tree_taiga.getValue()),
    giant_tree_taiga_hills(33), megaTaigaHills(giant_tree_taiga_hills.getValue()),
    wooded_mountains(34), extremeHillsPlus(wooded_mountains.getValue()),
    savanna(35),
    savanna_plateau(36), savannaPlateau(savanna_plateau.getValue()),
    badlands(37), mesa(badlands.getValue()),
    wooded_badlands_plateau(38), mesaPlateau_F(wooded_badlands_plateau.getValue()),
    badlands_plateau(39), mesaPlateau(badlands_plateau.getValue()),
    small_end_islands(40),
    end_midlands(41),
    end_highlands(42),
    end_barrens(43),
    warm_ocean(44), warmOcean(warm_ocean.getValue()),
    lukewarm_ocean(45), lukewarmOcean(lukewarm_ocean.getValue()),
    cold_ocean(46), coldOcean(cold_ocean.getValue()),
    deep_warm_ocean(47), warmDeepOcean(deep_warm_ocean.getValue()),
    deep_lukewarm_ocean(48), lukewarmDeepOcean(deep_lukewarm_ocean.getValue()),
    deep_cold_ocean(49), coldDeepOcean(deep_cold_ocean.getValue()),
    deep_frozen_ocean(50), frozenDeepOcean(deep_frozen_ocean.getValue()),

    // Alpha 1.2 - Beta 1.7
    seasonal_forest(51),
    rainforest(52),
    shrubland(53),

    the_void(127),

    // mutated variants
    sunflower_plains(plains.getValue() + 128),
    desert_lakes(desert.getValue() + 128),
    gravelly_mountains(mountains.getValue() + 128),
    flower_forest(forest.getValue() + 128),
    taiga_mountains(taiga.getValue() + 128),
    swamp_hills(swamp.getValue() + 128),
    ice_spikes(snowy_tundra.getValue() + 128),
    modified_jungle(jungle.getValue() + 128),
    modified_jungle_edge(jungle_edge.getValue() + 128),
    tall_birch_forest(birch_forest.getValue() + 128),
    tall_birch_hills(birch_forest_hills.getValue() + 128),
    dark_forest_hills(dark_forest.getValue() + 128),
    snowy_taiga_mountains(snowy_taiga.getValue() + 128),
    giant_spruce_taiga(giant_tree_taiga.getValue() + 128),
    giant_spruce_taiga_hills(giant_tree_taiga_hills.getValue() + 128),
    modified_gravelly_mountains(wooded_mountains.getValue() + 128),
    shattered_savanna(savanna.getValue() + 128),
    shattered_savanna_plateau(savanna_plateau.getValue() + 128),
    eroded_badlands(badlands.getValue() + 128),
    modified_wooded_badlands_plateau(wooded_badlands_plateau.getValue() + 128),
    modified_badlands_plateau(badlands_plateau.getValue() + 128),

    // 1.14
    bamboo_jungle(168),
    bamboo_jungle_hills(169),

    // 1.16
    soul_sand_valley(170),
    crimson_forest(171),
    warped_forest(172),
    basalt_deltas(173),

    // 1.17
    dripstone_caves(174),
    lush_caves(175),

    // 1.18
    meadow(177),
    grove(178),
    snowy_slopes(179),
    jagged_peaks(180),
    frozen_peaks(181),
    stony_peaks(182),
    old_growth_birch_forest(tall_birch_forest.getValue()),
    old_growth_pine_taiga(giant_tree_taiga.getValue()),
    old_growth_spruce_taiga(giant_spruce_taiga.getValue()),
    snowy_plains(snowy_tundra.getValue()),
    sparse_jungle(jungle_edge.getValue()),
    stony_shore(stone_shore.getValue()),
    windswept_hills(mountains.getValue()),
    windswept_forest(wooded_mountains.getValue()),
    windswept_gravelly_hills(gravelly_mountains.getValue()),
    windswept_savanna(shattered_savanna.getValue()),
    wooded_badlands(wooded_badlands_plateau.getValue()),

    // 1.19
    deep_dark(183),
    mangrove_swamp(184),

    // 1.20
    cherry_grove(185);


    private final int value;

    BiomeID(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BiomeID fromValue(int value) {
        for (BiomeID v : EnumSet.allOf(BiomeID.class)) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
