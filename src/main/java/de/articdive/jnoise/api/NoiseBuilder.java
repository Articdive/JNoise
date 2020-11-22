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

package de.articdive.jnoise.api;

import de.articdive.jnoise.JNoise;
import org.jetbrains.annotations.NotNull;

/**
 * @param <SELF> The Class of the extending class.
 * @author Lukas Mansour
 */
public abstract class NoiseBuilder<SELF extends NoiseBuilder<SELF>> {
    protected long seed = 1729;

    /**
     * Sets the seed of the to-be-generated noise-generator.
     *
     * @param seed an integer to seed the noise-generator.
     * @return {@link NoiseBuilder}
     */
    @NotNull
    public SELF setSeed(long seed) {
        this.seed = seed;
        return self();
    }

    /**
     * For default methods to return the correct builder this method must be defined.
     *
     * @return The builder.
     */
    @NotNull
    protected abstract SELF self();

    /**
     * Generates the noise-generator
     *
     * @return {@link JNoise}
     */
    @NotNull
    public abstract JNoise build();

}
