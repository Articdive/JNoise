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

package de.articdive.jnoise.noise.octaved;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Mansour
 */
public final class OctaveNoiseBuilder extends NoiseBuilder<OctaveNoiseBuilder> {
    private JNoise noise;
    private int octaves = 1;
    private double persistence = 1;
    private double lacunarity = 1;
    
    @Override
    @NotNull
    protected OctaveNoiseBuilder self() {
        return this;
    }
    
    /**
     * Sets the noise that will be octavated.
     * .
     *
     * @return {@link OctaveNoiseGenerator} this
     */
    @NotNull
    public OctaveNoiseBuilder setNoise(@NotNull JNoise noise) {
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
    
    
    @Override
    @NotNull
    public JNoise build() {
        return JNoise.build(new OctaveNoiseGenerator(seed, noise, octaves, persistence, lacunarity));
    }
}
