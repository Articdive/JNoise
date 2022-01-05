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

import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.fade_functions.FadeFunction;
import de.articdive.jnoise.interpolation.Interpolation;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class ValueNoiseBuilder extends NoiseBuilder<ValueNoiseResult, ValueNoiseBuilder> {
    private double frequency = 1.00;
    private Interpolation interpolation = Interpolation.LINEAR;
    private FadeFunction fadeFunction = FadeFunction.NONE;

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
    protected ValueNoiseGenerator createGenerator() {
        return new ValueNoiseGenerator(seed, interpolation, fadeFunction, frequency);
    }
}
