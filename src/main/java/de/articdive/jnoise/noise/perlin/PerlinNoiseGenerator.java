/*
 * JNoise
 * Copyright (C) 2021 Articdive
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

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.fade_functions.FadeFunction;
import de.articdive.jnoise.interpolation.Interpolation;
import de.articdive.jnoise.util.HashUtil;
import de.articdive.jnoise.util.vectors.Vector1D;
import de.articdive.jnoise.util.vectors.Vector2D;
import de.articdive.jnoise.util.vectors.Vector3D;
import de.articdive.jnoise.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

/**
 * Based on Ken Perlin's implementation of Perlin Noise.
 * The bounds of this Perlin Noise implementation are: [-sqrt(n/2), sqrt(n/2)] (n being the dimension).
 *
 * @author Articdive
 */
public final class PerlinNoiseGenerator implements NoiseGenerator<PerlinNoiseResult> {
    private static final Vector1D[] VECTOR_1D = new Vector1D[]{
        new Vector1D(1), new Vector1D(-1)
    };
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

    private final long seed;
    private final Interpolation interpolation;
    private final FadeFunction fadeFunction;
    private final double frequency;

    PerlinNoiseGenerator(long seed, @NotNull Interpolation interpolation, @NotNull FadeFunction fadeFunction, double frequency) {
        this.seed = seed;
        this.interpolation = interpolation;
        this.fadeFunction = fadeFunction;
        this.frequency = frequency;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x *= frequency);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        // Vector1D distanceVector = new Vector2D(x - iX);
        x -= iX;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
        };
        double[] dots = new double[]{
            // (VECTOR_1D.length - 1) = 1.
            new Vector1D(x).dot(VECTOR_1D[HashUtil.hash1D(seed, iX) & 1]),
            new Vector1D(x - 1).dot(VECTOR_1D[HashUtil.hash1D(seed, iX + 1) & 1])
        };

        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        // Vector2D distanceVector = new Vector2D(x - iX,y - iY);
        x -= iX;
        y -= iY;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
            fadeFunction.fade(y)
        };
        double[] dots = new double[]{
            // (VECTOR_2D.length - 1) = 7.
            new Vector2D(x, y).dot(VECTOR_2D[HashUtil.hash2D(seed, iX, iY) & 7]),
            new Vector2D(x - 1, y).dot(VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY) & 7]),
            new Vector2D(x, y - 1).dot(VECTOR_2D[HashUtil.hash2D(seed, iX, iY + 1) & 7]),
            new Vector2D(x - 1, y - 1).dot(VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY + 1) & 7])
        };

        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        long iZ = (long) Math.floor(z *= frequency);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
            fadeFunction.fade(y),
            fadeFunction.fade(z)
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
        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        long iZ = (long) Math.floor(z *= frequency);
        long iW = (long) Math.floor(w *= frequency);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        w -= iW;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
            fadeFunction.fade(y),
            fadeFunction.fade(z),
            fadeFunction.fade(w)
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
        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x) {
        return evaluateNoise(x, seed);
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return evaluateNoise(x, y, seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return evaluateNoise(x, y, z, seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return evaluateNoise(x, y, z, w, seed);
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, long seed) {
        return new PerlinNoiseResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, double y, long seed) {
        return new PerlinNoiseResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, double y, double z, long seed) {
        return new PerlinNoiseResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        return new PerlinNoiseResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x) {
        return new PerlinNoiseResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, double y) {
        return new PerlinNoiseResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, double y, double z) {
        return new PerlinNoiseResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public PerlinNoiseResult evaluateNoiseResult(double x, double y, double z, double w) {
        return new PerlinNoiseResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    public long getSeed() {
        return seed;
    }
}
