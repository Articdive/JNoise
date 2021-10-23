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

import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.fade_functions.FadeFunction;
import de.articdive.jnoise.fade_functions.FadeFunctionType;
import de.articdive.jnoise.interpolation.Interpolation;
import de.articdive.jnoise.interpolation.InterpolationType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class PerlinNoiseBuilder extends NoiseBuilder<PerlinNoiseResult, PerlinNoiseBuilder> {
    private double frequency = 1.00;
    private Interpolation interpolation = InterpolationType.LINEAR;
    private FadeFunction fadeFunction = FadeFunctionType.IMPROVED_PERLIN_NOISE;

    /**
     * Sets the frequency for the {@link PerlinNoiseGenerator}.
     *
     * @param frequency the new frequency for the {@link PerlinNoiseGenerator}.
     * @return {@link PerlinNoiseBuilder} this
     */
    @NotNull
    public PerlinNoiseBuilder setFrequency(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be a non-zero positive value.");
        }
        this.frequency = frequency;
        return this;
    }

    /**
     * Sets the Interpolation for the {@link PerlinNoiseGenerator}.
     *
     * @param interpolation The new {@link Interpolation} for the {@link PerlinNoiseGenerator}.
     * @return {@link PerlinNoiseBuilder} this
     */
    @NotNull
    public PerlinNoiseBuilder setInterpolation(@NotNull Interpolation interpolation) {
        this.interpolation = interpolation;
        return this;
    }

    /**
     * Sets the FadeFunction for the {@link PerlinNoiseGenerator}.
     *
     * @param fadeFunction the new {@link FadeFunction} for the {@link PerlinNoiseGenerator}.
     * @return {@link PerlinNoiseBuilder} this
     */
    @NotNull
    public PerlinNoiseBuilder setFadeFunction(@NotNull FadeFunction fadeFunction) {
        this.fadeFunction = fadeFunction;
        return this;
    }

    @Override
    @NotNull
    protected PerlinNoiseGenerator createGenerator() {
        return new PerlinNoiseGenerator(seed, interpolation, fadeFunction, frequency);
    }
}
