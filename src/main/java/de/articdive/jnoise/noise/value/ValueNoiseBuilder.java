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

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.api.builders.Seeded;
import de.articdive.jnoise.fade_functions.FadeFunction;
import de.articdive.jnoise.fade_functions.FadeFunctionType;
import de.articdive.jnoise.interpolation.Interpolation;
import de.articdive.jnoise.interpolation.InterpolationType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Mansour
 */
public final class ValueNoiseBuilder extends NoiseBuilder implements Seeded<ValueNoiseBuilder> {
    private long seed = 1729;
    private double frequency = 1.00;
    private Interpolation interpolation = InterpolationType.LINEAR;
    private FadeFunction fadeFunction = FadeFunctionType.NONE;

    /**
     * Sets the seed for the {@link ValueNoiseGenerator}.
     *
     * @param seed the new seed for the {@link ValueNoiseGenerator}.
     * @return {@link ValueNoiseBuilder} this
     */
    @Override
    @NotNull
    public ValueNoiseBuilder setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    /**
     * Sets the frequency for the {@link ValueNoiseGenerator}.
     *
     * @param frequency the new frequency for the {@link ValueNoiseGenerator}.
     * @return {@link ValueNoiseBuilder} this
     */
    @NotNull
    public ValueNoiseBuilder setFrequency(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be a non-zero positive value.");
        }
        this.frequency = frequency;
        return this;
    }

    /**
     * Sets the Interpolation for the {@link ValueNoiseGenerator}
     *
     * @param interpolation The new {@link Interpolation} for the {@link ValueNoiseGenerator}
     * @return {@link ValueNoiseBuilder} this
     */
    @NotNull
    public ValueNoiseBuilder setInterpolation(@NotNull Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    /**
     * Sets the FadeFunction for the {@link ValueNoiseGenerator}.
     *
     * @param fadeFunction the new {@link FadeFunction} for the {@link ValueNoiseGenerator}.
     * @return {@link ValueNoiseBuilder} this
     */
    @NotNull
    public ValueNoiseBuilder setFadeFunction(@NotNull FadeFunction fadeFunction) {
        this.fadeFunction = fadeFunction;
        return this;
    }

    @Override
    @NotNull
    public JNoise build() {
        return JNoise.build(new ValueNoiseGenerator(seed, interpolation, fadeFunction, frequency));
    }

    public static long getSeed(ValueNoiseBuilder builder) {
        return builder.seed;
    }
}
