/*
 * JNoise
 * Copyright (C) 2020 Articdive (Lukas Mansour)
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

package de.articdive.jnoise.noise.worley;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.DistanceFunction;
import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.distance_functions.DistanceFunctionType;
import org.jetbrains.annotations.NotNull;

import java.util.function.LongFunction;

/**
 * @author Lukas Mansour
 */
public final class WorleyNoiseBuilder extends NoiseBuilder<WorleyNoiseBuilder> {
    private double frequency = 1.00;
    private DistanceFunction distanceFunction = DistanceFunctionType.EUCLIDEAN_SQUARED;
    private LongFunction<Integer> fpAmountFunction = i -> 1;
    private int n = 1;

    @NotNull
    @Override
    protected WorleyNoiseBuilder self() {
        return this;
    }

    /**
     * Sets the frequency for the {@link WorleyNoiseBuilder}.
     *
     * @param frequency the new frequency for the {@link WorleyNoiseBuilder}.
     * @return {@link WorleyNoiseBuilder} this
     */
    @NotNull
    public WorleyNoiseBuilder setFrequency(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be a non-zero positive value.");
        }
        this.frequency = frequency;
        return this;
    }

    /**
     * Sets the distance function for the {@link WorleyNoiseGenerator}.
     *
     * @param distanceFunction The new {@link DistanceFunction} for the {@link WorleyNoiseGenerator}.
     * @return {@link WorleyNoiseBuilder} this
     */
    @NotNull
    public WorleyNoiseBuilder setDistanceFunction(@NotNull DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
        return this;
    }

    /**
     * Sets the function supplying the amount of feature points in a unit section.
     * Increasing this number can add more "features" to one area.
     * The values are clamped between 1 and 10 for performance reasons!
     *
     * @param fpAmountFunction The new (feature point amount function) for the {@link WorleyNoiseGenerator}
     * @return {@link WorleyNoiseBuilder} this
     */
    @NotNull
    public WorleyNoiseBuilder setFeaturePointAmountFunction(@NotNull LongFunction<Integer> fpAmountFunction) {
        this.fpAmountFunction = fpAmountFunction;
        return this;
    }

    @Override
    @NotNull
    public JNoise build() {
        return JNoise.build(new WorleyNoiseGenerator(seed, frequency, distanceFunction, fpAmountFunction));
    }
}
