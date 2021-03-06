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

package de.articdive.jnoise.noise.octaved;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.fractal_functions.FractalFunction;
import de.articdive.jnoise.fractal_functions.FractalFunctionType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Mansour
 */
public final class OctaveNoiseBuilder extends NoiseBuilder {
    private NoiseBuilder noise;
    private int octaves = 1;
    private double persistence = 1;
    private double lacunarity = 1;
    private FractalFunction fractalFunction = FractalFunctionType.FBM;
    private boolean incrementSeed = true;

    /**
     * Sets the noise that will be octavated.
     *
     * @param noise the {@link JNoise} generator that should be octavated.
     * @return {@link OctaveNoiseGenerator} this
     */
    @NotNull
    public OctaveNoiseBuilder setNoise(@NotNull NoiseBuilder noise) {
        this.noise = noise;
        return this;
    }

    /**
     * Sets the amount of octaves for the {@link OctaveNoiseGenerator}.
     *
     * @param octaves the new amount of octaves for the {@link OctaveNoiseGenerator}.
     * @return {@link OctaveNoiseGenerator} this
     */
    @NotNull
    public OctaveNoiseBuilder setOctaves(int octaves) {
        if (octaves <= 0) {
            throw new IllegalArgumentException("The amount of octaves must be a non-zero positive integer.");
        }
        this.octaves = octaves;
        return this;
    }

    /**
     * Sets the persistence for the {@link OctaveNoiseGenerator}.
     *
     * @param persistence the new persistence for the {@link OctaveNoiseGenerator}.
     * @return {@link OctaveNoiseBuilder} this
     */
    @NotNull
    public OctaveNoiseBuilder setPersistence(double persistence) {
        if (persistence <= 0) {
            throw new IllegalArgumentException("Persistence must be a non-zero positive value.");
        }
        this.persistence = persistence;
        return this;
    }

    /**
     * Sets the lacunarity for the {@link OctaveNoiseGenerator}.
     *
     * @param lacunarity the new lacunarity for the {@link OctaveNoiseGenerator}.
     * @return {@link OctaveNoiseBuilder} this
     */
    @NotNull
    public OctaveNoiseBuilder setLacunarity(double lacunarity) {
        if (lacunarity <= 0) {
            throw new IllegalArgumentException("Lacunarity must be a non-zero positive value.");
        }
        this.lacunarity = lacunarity;
        return this;
    }

    /**
     * Sets the FractalFunction for the {@link OctaveNoiseGenerator}.
     *
     * @param fractalFunction the new {@link FractalFunction} for the {@link OctaveNoiseGenerator}.
     * @return {@link OctaveNoiseBuilder} this
     */
    @NotNull
    public OctaveNoiseBuilder setFractalFunction(@NotNull FractalFunction fractalFunction) {
        this.fractalFunction = fractalFunction;
        return this;
    }

    /**
     * Should the Noise Generator increment the seed with each octave?
     *
     * @param incrementSeed true if seed should increment each octave for the {@link OctaveNoiseGenerator}.
     * @return {@link OctaveNoiseBuilder} this
     */
    @NotNull
    public OctaveNoiseBuilder setIncrementSeed(boolean incrementSeed) {
        this.incrementSeed = incrementSeed;
        return this;
    }

    @Override
    @NotNull
    public JNoise build() {
        if (noise == null) {
            throw new NullPointerException("A noise implementation to octavate has not been specified!");
        }
        return JNoise.build(new OctaveNoiseGenerator(noise, octaves, persistence, lacunarity, fractalFunction, incrementSeed));
    }
}
