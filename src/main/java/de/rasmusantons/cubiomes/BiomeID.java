package de.rasmusantons.cubiomes;

import java.util.EnumSet;

/**
 * All BiomeIDs that cubiomes knows.
 * <p>
 * Note: Cubiomes defines some duplicate values for biomes with different names in some versions of Minecraft.
 * Here, those names are accessible with {@link BiomeID#getAltNames()}.
 * <p>
 * If running inside a Minecraft mod, the {@link BiomeID} can be translated to a Biome like this (using Mojang Mappings):
 * {@snippet :
 * public static Holder<Biome> biomeIDToBiome(RegistryAccess access, BiomeID biomeID) throws NoSuchElementException {
 *     Registry<Biome> registry = access.lookup(Registries.BIOME).orElseThrow();
 *     Optional<Holder.Reference<Biome>> biome = registry.get(ResourceLocation.withDefaultNamespace(biomeID.name()));
 *     if (biome.isPresent()) {
 *         return biome.get();
 *     } else {
 *         for (String altName : biomeID.getAltNames()) {
 *             if (!ResourceLocation.isValidPath(altName))
 *                 continue;
 *             biome = registry.get(ResourceLocation.withDefaultNamespace(altName));
 *             if (biome.isPresent())
 *                 return biome.get();
 *         }
 *     }
 *     throw new NoSuchElementException("No value present");
 * }
 * }
 * A Biome can be translated to a BiomeID like this:
 * {@snippet :
 * public static BiomeID biomeToBiomeID(Holder<Biome> biome) {
 *     String path = biome.unwrapKey().orElseThrow().location().getPath();
 *     try {
 *         return BiomeID.valueOf(path);
 *     } catch (IllegalArgumentException e) {
 *         for (BiomeID biomeID : BiomeID.values()) {
 *             if (Arrays.asList(biomeID.getAltNames()).contains(path))
 *                 return biomeID;
 *         }
 *     }
 *     throw new NoSuchElementException("No value present");
 * }
 * }
 */
public enum BiomeID {
    none(-1),
    ocean(0),
    plains(1),
    desert(2),
    mountains(3, "extremeHills", "windswept_hills"),
    forest(4),
    taiga(5),
    swamp(6, "swampland"),
    river(7),
    nether_wastes(8, "hell"),
    the_end(9, "sky"),
    frozen_ocean(10, "frozenOcean"),
    frozen_river(11, "frozenRiver"),
    snowy_tundra(12, "icePlains", "snowy_plains"),
    snowy_mountains(13, "iceMountains"),
    mushroom_fields(14, "mushroomIsland"),
    mushroom_field_shore(15, "mushroomIslandShore"),
    beach(16),
    desert_hills(17, "desertHills"),
    wooded_hills(18, "forestHills"),
    taiga_hills(19, "taigaHills"),
    mountain_edge(20, "extremeHillsEdge"),
    jungle(21),
    jungle_hills(22, "jungleHills"),
    jungle_edge(23, "jungleEdge", "sparse_jungle"),
    deep_ocean(24, "deepOcean"),
    stone_shore(25, "stoneBeach", "stony_shore"),
    snowy_beach(26, "coldBeach"),
    birch_forest(27, "birchForest"),
    birch_forest_hills(28, "birchForestHills"),
    dark_forest(29, "roofedForest"),
    snowy_taiga(30, "coldTaiga"),
    snowy_taiga_hills(31, "coldTaigaHills"),
    giant_tree_taiga(32, "megaTaiga", "old_growth_pine_taiga"),
    giant_tree_taiga_hills(33, "megaTaigaHills"),
    wooded_mountains(34, "extremeHillsPlus", "windswept_forest"),
    savanna(35),
    savanna_plateau(36, "savannaPlateau"),
    badlands(37, "mesa"),
    wooded_badlands_plateau(38, "mesaPlateau_F", "wooded_badlands"),
    badlands_plateau(39, "mesaPlateau"),
    small_end_islands(40),
    end_midlands(41),
    end_highlands(42),
    end_barrens(43),
    warm_ocean(44, "warmOcean"),
    lukewarm_ocean(45, "lukewarmOcean"),
    cold_ocean(46, "coldOcean"),
    deep_warm_ocean(47, "warmDeepOcean"),
    deep_lukewarm_ocean(48, "lukewarmDeepOcean"),
    deep_cold_ocean(49, "coldDeepOcean"),
    deep_frozen_ocean(50, "frozenDeepOcean"),

    // Alpha 1.2 - Beta 1.7
    seasonal_forest(51),
    rainforest(52),
    shrubland(53),

    the_void(127),

    // mutated variants
    sunflower_plains(plains.getValue() + 128),
    desert_lakes(desert.getValue() + 128),
    gravelly_mountains(mountains.getValue() + 128, "windswept_gravelly_hills"),
    flower_forest(forest.getValue() + 128),
    taiga_mountains(taiga.getValue() + 128),
    swamp_hills(swamp.getValue() + 128),
    ice_spikes(snowy_tundra.getValue() + 128),
    modified_jungle(jungle.getValue() + 128),
    modified_jungle_edge(jungle_edge.getValue() + 128),
    tall_birch_forest(birch_forest.getValue() + 128, "old_growth_birch_forest"),
    tall_birch_hills(birch_forest_hills.getValue() + 128),
    dark_forest_hills(dark_forest.getValue() + 128),
    snowy_taiga_mountains(snowy_taiga.getValue() + 128),
    giant_spruce_taiga(giant_tree_taiga.getValue() + 128, "old_growth_spruce_taiga"),
    giant_spruce_taiga_hills(giant_tree_taiga_hills.getValue() + 128),
    modified_gravelly_mountains(wooded_mountains.getValue() + 128),
    shattered_savanna(savanna.getValue() + 128, "windswept_savanna"),
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

    // 1.19
    deep_dark(183),
    mangrove_swamp(184),

    // 1.20
    cherry_grove(185),

    // 1.21 Winter Drop
    pale_garden(186);


    private final int value;
    private final String[] altNames;

    BiomeID(int value, String... altNames) {
        this.value = value;
        this.altNames = altNames;
    }

    /**
     * {@return cubiomes' internal id of this biome}
     */
    public int getValue() {
        return value;
    }

    /**
     * {@return alternative names for this biome}
     */
    public String[] getAltNames() {
        return altNames;
    }

    /**
     * {@return the BiomeID for a given internal id}
     */
    public static BiomeID fromValue(int value) {
        for (BiomeID v : EnumSet.allOf(BiomeID.class)) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
