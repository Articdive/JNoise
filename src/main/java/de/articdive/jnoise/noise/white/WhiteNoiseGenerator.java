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

package de.articdive.jnoise.noise.white;

import de.articdive.jnoise.api.NoiseGenerator;
import org.jetbrains.annotations.NotNull;

import static de.articdive.jnoise.util.HashUtil.W_PRIME;
import static de.articdive.jnoise.util.HashUtil.X_PRIME;
import static de.articdive.jnoise.util.HashUtil.Y_PRIME;
import static de.articdive.jnoise.util.HashUtil.Z_PRIME;

/**
 * The bounds of this White Noise implementation are: [-1, 1].
 *
 * @author Articdive
 */
public final class WhiteNoiseGenerator implements NoiseGenerator<WhiteNoiseResult> {
    private final long seed;
    private final double frequency;

    WhiteNoiseGenerator(long seed, double frequency) {
        this.seed = seed;
        this.frequency = frequency;
    }

    public double evaluateNoise(double x, long seed) {
        return evaluateCoord1D((long) Math.floor(x * frequency), seed);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        return evaluateCoord2D((long) Math.floor(x * frequency), (long) Math.floor(y * frequency), seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        return evaluateCoord3D((long) Math.floor(x * frequency), (long) Math.floor(y * frequency), (long) Math.floor(z * frequency), seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        return evaluateCoord4D((long) Math.floor(x * frequency), (long) Math.floor(y * frequency), (long) Math.floor(z * frequency), (long) Math.floor(w * frequency), seed);
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
    public WhiteNoiseResult evaluateNoiseResult(double x, long seed) {
        return new WhiteNoiseResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x, double y, long seed) {
        return new WhiteNoiseResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x, double y, double z, long seed) {
        return new WhiteNoiseResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        return new WhiteNoiseResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x) {
        return new WhiteNoiseResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x, double y) {
        return new WhiteNoiseResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x, double y, double z) {
        return new WhiteNoiseResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public WhiteNoiseResult evaluateNoiseResult(double x, double y, double z, double w) {
        return new WhiteNoiseResult(evaluateNoise(x, y, z, w, seed));
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
