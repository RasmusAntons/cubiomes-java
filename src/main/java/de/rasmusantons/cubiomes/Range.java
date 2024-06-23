package de.rasmusantons.cubiomes;

public record Range(int scale, int x, int z, int sx, int sz, int y, int sy) {
    public Range(int scale, int x, int z, int sx, int sz, int y) {
        this(scale, x, z, sx, sz, y, 1);
    }
}
