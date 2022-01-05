/*
 * JNoise
 * Copyright (C) 2021-2022 Articdive
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

package de.articdive.jnoise.noise.value;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.fade_functions.FadeFunction;
import de.articdive.jnoise.interpolation.Interpolation;
import org.jetbrains.annotations.NotNull;

import static de.articdive.jnoise.util.HashUtil.W_PRIME;
import static de.articdive.jnoise.util.HashUtil.X_PRIME;
import static de.articdive.jnoise.util.HashUtil.Y_PRIME;
import static de.articdive.jnoise.util.HashUtil.Z_PRIME;

/**
 * The bounds of this Value Noise implementation are: [-1, 1].
 *
 * @author Articdive
 */
public final class ValueNoiseGenerator implements NoiseGenerator<ValueNoiseResult> {
    private final long seed;
    private final Interpolation interpolation;
    private final FadeFunction fadeFunction;
    private final double frequency;

    ValueNoiseGenerator(long seed, @NotNull Interpolation interpolation, @NotNull FadeFunction fadeFunction, double frequency) {
        this.seed = seed;
        this.interpolation = interpolation;
        this.fadeFunction = fadeFunction;
        this.frequency = frequency;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
        };
        double[] vals = new double[]{
            evaluateCoord1D((int) iX, seed),
            evaluateCoord1D((int) iX + 1, seed),
        };
        return interpolation.lerp(fractals, vals);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY)
        };
        double[] vals = new double[]{
            evaluateCoord2D((int) iX, (int) iY, seed),
            evaluateCoord2D((int) iX + 1, (int) iY, seed),
            evaluateCoord2D((int) iX, (int) iY + 1, seed),
            evaluateCoord2D((int) iX + 1, (int) iY + 1, seed)
        };
        return interpolation.lerp(fractals, vals);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        long iZ = (long) Math.floor(z *= frequency);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY),
            fadeFunction.fade(z - iZ)
        };
        double[] vals = new double[]{
            evaluateCoord3D(iX, iY, iZ, seed),
            evaluateCoord3D(iX + 1, iY, iZ, seed),
            evaluateCoord3D(iX, iY + 1, iZ, seed),
            evaluateCoord3D(iX + 1, iY + 1, iZ, seed),
            evaluateCoord3D(iX, iY, iZ + 1, seed),
            evaluateCoord3D(iX + 1, iY, iZ + 1, seed),
            evaluateCoord3D(iX, iY + 1, iZ + 1, seed),
            evaluateCoord3D(iX + 1, iY + 1, iZ + 1, seed)
        };
        return interpolation.lerp(fractals, vals);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        long iZ = (long) Math.floor(z *= frequency);
        long iW = (long) Math.floor(w *= frequency);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY),
            fadeFunction.fade(z - iZ),
            fadeFunction.fade(w - iW)
        };
        double[] vals = new double[]{
            evaluateCoord4D(iX, iY, iZ, iW, seed),
            evaluateCoord4D(iX + 1, iY, iZ, iW, seed),
            evaluateCoord4D(iX, iY + 1, iZ, iW, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ, iW, seed),

            evaluateCoord4D(iX, iY, iZ + 1, iW, seed),
            evaluateCoord4D(iX + 1, iY, iZ + 1, iW, seed),
            evaluateCoord4D(iX, iY + 1, iZ + 1, iW, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW, seed),

            evaluateCoord4D(iX, iY, iZ, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY, iZ, iW + 1, seed),
            evaluateCoord4D(iX, iY + 1, iZ, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ, iW + 1, seed),

            evaluateCoord4D(iX, iY, iZ + 1, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY, iZ + 1, iW + 1, seed),
            evaluateCoord4D(iX, iY + 1, iZ + 1, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW + 1, seed)
        };
        return interpolation.lerp(fractals, vals);
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
    public ValueNoiseResult evaluateNoiseResult(double x, long seed) {
        return new ValueNoiseResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x, double y, long seed) {
        return new ValueNoiseResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x, double y, double z, long seed) {
        return new ValueNoiseResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        return new ValueNoiseResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x) {
        return new ValueNoiseResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x, double y) {
        return new ValueNoiseResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x, double y, double z) {
        return new ValueNoiseResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoiseResult(double x, double y, double z, double w) {
        return new ValueNoiseResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    public long getSeed() {
        return seed;
    }

    private static double evaluateCoord1D(long x, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));

        return (n * n * n * 60493) / 2147483648.0;
    }

    private static double evaluateCoord2D(long x, long y, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));
        n ^= Y_PRIME * y;

        return (n * n * n * 60493) / 2147483648.0;
    }

    private static double evaluateCoord3D(long x, long y, long z, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;

        return (n * n * n * 60493) / 2147483648.0;
    }

    private static double evaluateCoord4D(long x, long y, long z, long w, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;
        n ^= W_PRIME * w;

        return (n * n * n * 60493) / 2147483648.0;
    }
}
