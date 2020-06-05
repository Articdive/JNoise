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

package de.articdive.jnoise.noise.octaved;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.builders.Seeded;
import de.articdive.jnoise.fractal_functions.FractalFunction;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class OctaveNoiseGenerator extends NoiseGenerator<OctaveNoiseResult> {
    private final @NotNull NoiseBuilder noiseBuilder;
    private final int octaves;
    private final double persistence;
    private final double lacunarity;
    private final FractalFunction fractalFunction;
    private final boolean incrementSeed;
    // Special values
    private final long seed;
    private final double fractalBounding;

    OctaveNoiseGenerator(
        @NotNull NoiseBuilder noiseBuilder,
        int octaves,
        double persistence,
        double lacunarity,
        @NotNull FractalFunction fractalFunction,
        boolean incrementSeed
    ) {
        this.noiseBuilder = noiseBuilder;
        this.octaves = octaves;
        this.persistence = persistence;
        this.lacunarity = lacunarity;
        this.fractalFunction = fractalFunction;
        this.incrementSeed = incrementSeed;
        seed = NoiseBuilder.getSeed(noiseBuilder);
        double fractalBounding = 0;
        double amplitude = 1;
        for (int i = 0; i < this.octaves; i++) {
            fractalBounding += amplitude;
        }
        this.fractalBounding = fractalBounding;
    }

    @Override
    @NotNull
    public OctaveNoiseResult evaluateNoise(double x, double y) {
        JNoise noise = noiseBuilder.build();
        double output = 0;
        double amplitude = 1;
        long seedIncrement = 0;
        for (int i = 0; i < this.octaves; i++) {
            if (noiseBuilder instanceof Seeded && incrementSeed) {
                noise = ((Seeded<?>) noiseBuilder).setSeed(seed + seedIncrement++).build();
            }
            // Frequency is already inlcuded in the NoiseGenerator!
            output += fractalFunction.fractalize(noise.getNoise(x, y)) * amplitude;

            amplitude *= this.persistence;
            x *= lacunarity;
            y *= lacunarity;
        }
        return new OctaveNoiseResult(output / fractalBounding);
    }

    @Override
    @NotNull
    public OctaveNoiseResult evaluateNoise(double x, double y, double z) {
        JNoise noise = noiseBuilder.build();
        double output = 0;
        double amplitude = 1;
        long seedIncrement = 0;
        for (int i = 0; i < this.octaves; i++) {
            if (noiseBuilder instanceof Seeded && incrementSeed) {
                noise = ((Seeded<?>) noiseBuilder).setSeed(seed + seedIncrement++).build();
            }

            output += fractalFunction.fractalize(noise.getNoise(x, y, z)) * amplitude;

            amplitude *= this.persistence;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
        }
        return new OctaveNoiseResult(output / fractalBounding);
    }

    @Override
    @NotNull
    public OctaveNoiseResult evaluateNoise(double x, double y, double z, double w) {
        JNoise noise = noiseBuilder.build();
        double output = 0;
        double amplitude = 1;
        long seedIncrement = 0;
        for (int i = 0; i < this.octaves; i++) {
            if (noiseBuilder instanceof Seeded && incrementSeed) {
                noise = ((Seeded<?>) noiseBuilder).setSeed(seed + seedIncrement++).build();
            }

            output += fractalFunction.fractalize(noise.getNoise(x, y, z, w)) * amplitude;

            amplitude *= this.persistence;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
        }
        return new OctaveNoiseResult(output / fractalBounding);
    }
}
