/*
 * JNoise
 * Copyright (C) 2021 Articdive (Lukas Mansour)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.articdive.jnoise.noise.perlin;

import de.articdive.jnoise.interpolation.Interpolation;
import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.util.HashUtil;
import de.articdive.jnoise.util.vectors.Vector2D;
import de.articdive.jnoise.util.vectors.Vector3D;
import de.articdive.jnoise.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

/**
 * Based on Ken Perlin's implementation of Perlin Noise.
 * The bounds of this Perlin Noise implementation are: [-sqrt(n/2), sqrt(n/2)] (n being the dimension).
 *
 * @author Lukas Mansour
 */
public final class PerlinNoiseGenerator extends NoiseGenerator<PerlinNoiseResult> {
    private static final Vector2D[] VECTOR_2D = new Vector2D[]{
        new Vector2D(1, 1), new Vector2D(-1, 1), new Vector2D(1, -1), new Vector2D(-1, -1),

        new Vector2D(0, 1), new Vector2D(0, -1), new Vector2D(1, 0), new Vector2D(-1, 0)
    };
    private static final Vector3D[] VECTOR_3D = new Vector3D[]{
        new Vector3D(1, 1, 1), new Vector3D(-1, 1, 1),
        new Vector3D(1, -1, 1), new Vector3D(-1, -1, 1),
        new Vector3D(1, 1, -1), new Vector3D(-1, 1, -1),
        new Vector3D(1, -1, -1), new Vector3D(-1, -1, -1),

        new Vector3D(0, 1, 1), new Vector3D(0, -1, 1), new Vector3D(0, 1, -1), new Vector3D(0, -1, -1),
        new Vector3D(1, 0, 1), new Vector3D(-1, 0, 1), new Vector3D(1, 0, -1), new Vector3D(-1, 0, -1),
        new Vector3D(1, 1, 0), new Vector3D(-1, 1, 0), new Vector3D(1, -1, 0), new Vector3D(-1, -1, 0)
    };
    private static final Vector4D[] VECTOR_4D = new Vector4D[]{
        new Vector4D(1, 1, 1, 1), new Vector4D(1, 1, -1, 1),
        new Vector4D(1, -1, 1, 1), new Vector4D(1, -1, -1, 1),
        new Vector4D(-1, 1, 1, 1), new Vector4D(-1, 1, -1, 1),
        new Vector4D(-1, -1, 1, 1), new Vector4D(-1, -1, -1, 1),
        new Vector4D(1, 1, 1, -1), new Vector4D(1, 1, -1, -1),
        new Vector4D(1, -1, 1, -1), new Vector4D(1, -1, -1, -1),
        new Vector4D(-1, 1, 1, -1), new Vector4D(-1, 1, -1, -1),
        new Vector4D(-1, -1, 1, -1), new Vector4D(-1, -1, -1, -1),

        new Vector4D(0, 1, 1, 1), new Vector4D(0, 1, -1, 1),
        new Vector4D(0, -1, 1, 1), new Vector4D(0, -1, -1, 1),
        new Vector4D(0, 1, 1, -1), new Vector4D(0, 1, -1, -1),
        new Vector4D(0, -1, 1, -1), new Vector4D(0, -1, -1, -1),
        new Vector4D(1, 0, 1, 1), new Vector4D(1, 0, -1, 1),
        new Vector4D(-1, 0, 1, 1), new Vector4D(-1, 0, -1, 1),
        new Vector4D(1, 0, 1, -1), new Vector4D(1, 0, -1, -1),
        new Vector4D(-1, 0, 1, -1), new Vector4D(-1, 0, -1, -1),
        new Vector4D(1, 1, 0, 1), new Vector4D(1, -1, 0, 1),
        new Vector4D(-1, 1, 0, 1), new Vector4D(-1, -1, 0, 1),
        new Vector4D(1, 1, 0, -1), new Vector4D(1, -1, 0, -1),
        new Vector4D(-1, 1, 0, -1), new Vector4D(-1, -1, 0, -1),
        new Vector4D(1, 1, 1, 0), new Vector4D(1, -1, 1, 0),
        new Vector4D(-1, 1, 1, 0), new Vector4D(-1, -1, 1, 0),
        new Vector4D(1, 1, -1, 0), new Vector4D(1, -1, -1, 0),
        new Vector4D(-1, 1, -1, 0), new Vector4D(-1, -1, -1, 0)
    };

    private final Interpolation interpolation;
    private final double frequency;

    PerlinNoiseGenerator(long seed, @NotNull Interpolation interpolation, double frequency) {
        super(seed);
        this.interpolation = interpolation;
        this.frequency = frequency;
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoise(double x, double y) {
        x *= frequency;
        y *= frequency;
        // Find unit square
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        // Vector2D distanceVector = new Vector2D(x - iX,y - iY);
        x -= Math.floor(x);
        y -= Math.floor(y);
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fade(x),
            fade(y)
        };
        double[] dots = new double[]{
            // (VECTOR_2D.length - 1) = 7.
            new Vector2D(x, y).dot(VECTOR_2D[HashUtil.hash2D(seed, iX, iY) & 7]),
            new Vector2D(x - 1, y).dot(VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY) & 7]),
            new Vector2D(x, y - 1).dot(VECTOR_2D[HashUtil.hash2D(seed, iX, iY + 1) & 7]),
            new Vector2D(x - 1, y - 1).dot(VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY + 1) & 7])
        };

        return new PerlinNoiseResult(interpolation.lerp(fractals, dots));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        // Find unit square
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fade(x),
            fade(y),
            fade(z)
        };
        double[] dots = new double[]{
            // (VECTOR_3D.length - 1) = 19.
            new Vector3D(x, y, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY, iZ) & 19]),
            new Vector3D(x - 1, y, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY, iZ) & 19]),
            new Vector3D(x, y - 1, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY + 1, iZ) & 19]),
            new Vector3D(x - 1, y - 1, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY + 1, iZ) & 19]),
            new Vector3D(x, y, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY, iZ + 1) & 19]),
            new Vector3D(x - 1, y, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY, iZ + 1) & 19]),
            new Vector3D(x, y - 1, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY + 1, iZ + 1) & 19]),
            new Vector3D(x - 1, y - 1, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY + 1, iZ + 1) & 19])
        };
        return new PerlinNoiseResult(interpolation.lerp(fractals, dots));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoise(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        // Find unit square
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        w -= iW;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fade(x),
            fade(y),
            fade(z),
            fade(w)
        };
        double[] dots = new double[]{
            // (VECTOR_4D.length - 1) = 19.
            new Vector4D(x, y, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ, iW) & 47]),
            new Vector4D(x - 1, y, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ, iW) & 47]),
            new Vector4D(x, y - 1, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ, iW) & 47]),
            new Vector4D(x - 1, y - 1, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW) & 47]),
            new Vector4D(x, y, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ + 1, iW) & 47]),
            new Vector4D(x - 1, y, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW) & 47]),
            new Vector4D(x, y - 1, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW) & 47]),
            new Vector4D(x - 1, y - 1, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW) & 47]),
            new Vector4D(x, y, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ, iW + 1) & 47]),
            new Vector4D(x - 1, y, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ, iW + 1) & 47]),
            new Vector4D(x, y - 1, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ, iW + 1) & 47]),
            new Vector4D(x - 1, y - 1, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW + 1) & 47]),
            new Vector4D(x, y, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ + 1, iW + 1) & 47]),
            new Vector4D(x - 1, y, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW + 1) & 47]),
            new Vector4D(x, y - 1, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW + 1) & 47]),
            new Vector4D(x - 1, y - 1, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW + 1) & 47])
        };
        return new PerlinNoiseResult(interpolation.lerp(fractals, dots));
    }

    /**
     * Fade method used to remove interpolation artifacts.
     *
     * @param t value to fade.
     * @return faded t value.
     */
    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }
}