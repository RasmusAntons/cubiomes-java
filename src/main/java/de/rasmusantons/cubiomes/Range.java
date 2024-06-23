package de.rasmusantons.cubiomes;

/**
 * Defines an area or volume for the biome generation. It is given by a
 * position, size and scaling in the horizontal axes, and an optional
 * vertical range. The vertical scaling is equal to 1:1 iff scale == 1,
 * and 1:4 (default biome scale) in all other cases!
 *
 * @param scale Horizontal scale factor, should be one of 1, 4, 16, 64, or 256
 *              additionally a value of zero bypasses scaling and expects a
 *              manual generation entry layer.
 * @param x Lower end of the x-range (west face of the cube)
 * @param z Lower end of the z-range (north face of the cube)
 * @param sx Size along the x-axis
 * @param sz Size along the z-axis
 * @param y Lower and of the y-range (bottom face of the cube)
 * @param sy Vertical size
 */
public record Range(int scale, int x, int z, int sx, int sz, int y, int sy) {
    public Range(int scale, int x, int z, int sx, int sz, int y) {
        this(scale, x, z, sx, sz, y, 1);
    }
}
