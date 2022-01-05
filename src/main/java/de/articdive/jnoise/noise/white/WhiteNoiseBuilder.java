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

import de.articdive.jnoise.api.NoiseBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class WhiteNoiseBuilder extends NoiseBuilder<WhiteNoiseResult, WhiteNoiseBuilder> {
    private double frequency = 1.00;

    /**
     * Sets the frequency for the {@link WhiteNoiseGenerator}.
     *
     * @param frequency the new frequency for the {@link WhiteNoiseGenerator}.
     * @return {@link WhiteNoiseBuilder} this
     */
    @NotNull
    public WhiteNoiseBuilder setFrequency(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be a non-zero positive value.");
        }
        this.frequency = frequency;
        return this;
    }

    @Override
    protected @NotNull WhiteNoiseGenerator createGenerator() {
        return new WhiteNoiseGenerator(seed, frequency);
    }
}
