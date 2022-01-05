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

package de.articdive.jnoise.modules.octavation;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.module.NoiseModuleBuilder;
import de.articdive.jnoise.fractal_functions.FractalFunction;
import org.jetbrains.annotations.NotNull;

public final class OctavationModuleBuilder implements NoiseModuleBuilder<OctavationModuleBuilder> {
    private int octaves = 1;
    private double persistence = 1;
    private double lacunarity = 1;
    private FractalFunction fractalFunction = FractalFunction.FBM;
    private boolean incrementSeed = true;

    OctavationModuleBuilder() {

    }

    /**
     * Sets the amount of octaves for the {@link OctavationModule}.
     *
     * @param octaves the new amount of octaves for the {@link OctavationModule}.
     * @return {@link OctavationModuleBuilder} this
     */
    @NotNull
    public OctavationModuleBuilder setOctaves(int octaves) {
        if (octaves <= 0) {
            throw new IllegalArgumentException("The amount of octaves must be a non-zero positive integer.");
        }
        this.octaves = octaves;
        return this;
    }

    /**
     * Sets the persistence for the {@link OctavationModule}.
     *
     * @param persistence the new persistence for the {@link OctavationModule}.
     * @return {@link OctavationModuleBuilder} this
     */
    @NotNull
    public OctavationModuleBuilder setPersistence(double persistence) {
        if (persistence <= 0) {
            throw new IllegalArgumentException("Persistence must be a non-zero positive value.");
        }
        this.persistence = persistence;
        return this;
    }

    /**
     * Sets the lacunarity for the {@link OctavationModule}.
     *
     * @param lacunarity the new lacunarity for the {@link OctavationModule}.
     * @return {@link OctavationModuleBuilder} this
     */
    @NotNull
    public OctavationModuleBuilder setLacunarity(double lacunarity) {
        if (lacunarity <= 0) {
            throw new IllegalArgumentException("Lacunarity must be a non-zero positive value.");
        }
        this.lacunarity = lacunarity;
        return this;
    }

    /**
     * Sets the FractalFunction for the {@link OctavationModule}.
     *
     * @param fractalFunction the new {@link FractalFunction} for the {@link OctavationModule}.
     * @return {@link OctavationModuleBuilder} this
     */
    @NotNull
    public OctavationModuleBuilder setFractalFunction(@NotNull FractalFunction fractalFunction) {
        this.fractalFunction = fractalFunction;
        return this;
    }

    /**
     * Should the Noise Generator increment the seed with each octave?
     *
     * @param incrementSeed true if seed should increment each octave for the {@link OctavationModule}.
     * @return {@link OctavationModuleBuilder} this
     */
    @NotNull
    public OctavationModuleBuilder setIncrementSeed(boolean incrementSeed) {
        this.incrementSeed = incrementSeed;
        return this;
    }

    @Override
    public OctavationModule apply(@NotNull NoiseGenerator<?> noiseGenerator) {
        return new OctavationModule(noiseGenerator, octaves, persistence, lacunarity, fractalFunction, incrementSeed);
    }
}