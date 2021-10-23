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

package de.articdive.jnoise.api;

import org.jetbrains.annotations.NotNull;

/**
 * @param <NR> {@link NoiseResult} class
 * @author Articdive
 */
public interface NoiseGenerator<NR extends NoiseResult> {

    double evaluateNoise(double x, long seed);

    double evaluateNoise(double x, double y, long seed);

    double evaluateNoise(double x, double y, double z, long seed);

    double evaluateNoise(double x, double y, double z, double w, long seed);

    double evaluateNoise(double x);

    double evaluateNoise(double x, double y);

    double evaluateNoise(double x, double y, double z);

    double evaluateNoise(double x, double y, double z, double w);

    @NotNull
    NR evaluateNoiseResult(double x, long seed);

    @NotNull
    NR evaluateNoiseResult(double x, double y, long seed);

    @NotNull
    NR evaluateNoiseResult(double x, double y, double z, long seed);

    @NotNull
    NR evaluateNoiseResult(double x, double y, double z, double w, long seed);

    @NotNull
    NR evaluateNoiseResult(double x);

    @NotNull
    NR evaluateNoiseResult(double x, double y);

    @NotNull
    NR evaluateNoiseResult(double x, double y, double z);

    @NotNull
    NR evaluateNoiseResult(double x, double y, double z, double w);


    long getSeed();
}
