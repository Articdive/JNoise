/*
 * JNoise
 * Copyright (C) 2020-2022 Articdive
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

package de.articdive.jnoise.core.api.noisegen;

import org.jetbrains.annotations.NotNull;

/**
 * Interface that denotes a {@link SeededNoiseGenerator}, which can additionally evaluate a {@link NoiseResult} at a location.
 *
 * @param <NR> {@link NoiseResult} class
 * @author Articdive
 */
public interface SeededExplicitNoiseGenerator<NR extends NoiseResult> extends ExplicitNoiseGenerator<NR>, SeededNoiseGenerator {
    @NotNull
    NR evaluateNoiseResult(double x, long seed);

    @NotNull
    NR evaluateNoiseResult(double x, double y, long seed);

    @NotNull
    NR evaluateNoiseResult(double x, double y, double z, long seed);

    @NotNull
    NR evaluateNoiseResult(double x, double y, double z, double w, long seed);
}
