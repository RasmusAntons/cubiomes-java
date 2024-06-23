package de.rasmusantons.cubiomes;

public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator(MCVersion.MC_1_21);
        generator.applySeed(Dimension.DIM_OVERWORLD, 0);
        System.out.printf("biome at 0,0 is %s\n", generator.getBiomeAt(1, 0, 63, 0));
        BiomeID[][] biomeIDs = generator.genBiomes2D(new Range(1, -1, -1, 2, 2, 15));
        for (BiomeID[] sliceZ : biomeIDs) {
            for (BiomeID biomeID : sliceZ) {
                System.out.printf("biome: %s\n", biomeID);
            }
        }
    }
}
