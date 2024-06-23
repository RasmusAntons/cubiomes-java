package de.rasmusantons.cubiomes;

public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator(Biomes.MCVersion.MC_1_21, 0);
        generator.applySeed(0, 0);
        System.out.printf("mc: %d\n", generator.state.getInt(0));
        System.out.printf("dim: %d\n", generator.state.getInt(1));
        System.out.printf("biome at 0,0 is %d\n", generator.getBiomeAt(1, 0, 63, 0));
    }
}
