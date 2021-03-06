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
 * Please note that this implementation roughly repeats every 2*{@link Integer#MAX_VALUE} values.
 *
 * @author Lukas Mansour
 */
public final class ValueNoiseGenerator extends NoiseGenerator<ValueNoiseResult> {
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
    @NotNull
    public ValueNoiseResult evaluateNoise(double x, double y) {
        x *= frequency;
        y *= frequency;
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY)
        };
        double[] vals = new double[]{
            evaluateCoord2D((int) iX, (int) iY),
            evaluateCoord2D((int) iX + 1, (int) iY),
            evaluateCoord2D((int) iX, (int) iY + 1),
            evaluateCoord2D((int) iX + 1, (int) iY + 1)
        };
        return new ValueNoiseResult(interpolation.lerp(fractals, vals));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY),
            fadeFunction.fade(z - iZ)
        };
        double[] vals = new double[]{
            evaluateCoord3D(iX, iY, iZ),
            evaluateCoord3D(iX + 1, iY, iZ),
            evaluateCoord3D(iX, iY + 1, iZ),
            evaluateCoord3D(iX + 1, iY + 1, iZ),
            evaluateCoord3D(iX, iY, iZ + 1),
            evaluateCoord3D(iX + 1, iY, iZ + 1),
            evaluateCoord3D(iX, iY + 1, iZ + 1),
            evaluateCoord3D(iX + 1, iY + 1, iZ + 1)
        };
        return new ValueNoiseResult(interpolation.lerp(fractals, vals));
    }

    @Override
    @NotNull
    public ValueNoiseResult evaluateNoise(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY),
            fadeFunction.fade(z - iZ),
            fadeFunction.fade(w - iW)
        };
        double[] vals = new double[]{
            evaluateCoord4D(iX, iY, iZ, iW),
            evaluateCoord4D(iX + 1, iY, iZ, iW),
            evaluateCoord4D(iX, iY + 1, iZ, iW),
            evaluateCoord4D(iX + 1, iY + 1, iZ, iW),

            evaluateCoord4D(iX, iY, iZ + 1, iW),
            evaluateCoord4D(iX + 1, iY, iZ + 1, iW),
            evaluateCoord4D(iX, iY + 1, iZ + 1, iW),
            evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW),

            evaluateCoord4D(iX, iY, iZ, iW + 1),
            evaluateCoord4D(iX + 1, iY, iZ, iW + 1),
            evaluateCoord4D(iX, iY + 1, iZ, iW + 1),
            evaluateCoord4D(iX + 1, iY + 1, iZ, iW + 1),

            evaluateCoord4D(iX, iY, iZ + 1, iW + 1),
            evaluateCoord4D(iX + 1, iY, iZ + 1, iW + 1),
            evaluateCoord4D(iX, iY + 1, iZ + 1, iW + 1),
            evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW + 1)
        };
        return new ValueNoiseResult(interpolation.lerp(fractals, vals));
    }

    private double evaluateCoord2D(long x, long y) {
        int n = (((int) seed) ^ (X_PRIME * ((int) x)));
        n ^= Y_PRIME * y;

        return (n * n * n * 60493) / 2147483648.0;
    }

    private double evaluateCoord3D(long x, long y, long z) {
        int n = (((int) seed) ^ (X_PRIME * ((int) x)));
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;

        return (n * n * n * 60493) / 2147483648.0;
    }

    private double evaluateCoord4D(long x, long y, long z, long w) {
        int n = (((int) seed) ^ (X_PRIME * ((int) x)));
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;
        n ^= W_PRIME * w;

        return (n * n * n * 60493) / 2147483648.0;
    }

}
