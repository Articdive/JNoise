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

package de.articdive.jnoise.modules.octavation;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.NoiseResult;
import de.articdive.jnoise.api.module.NoiseModule;
import de.articdive.jnoise.fractal_functions.FractalFunction;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public class OctavationModule extends NoiseModule {
    private final int octaves;
    private final double persistence;
    private final double lacunarity;
    private final FractalFunction fractalFunction;
    private final boolean incrementSeed;
    // Special values
    private final double fractalBounding;

    OctavationModule(
        @NotNull NoiseGenerator<?> noiseGenerator,
        int octaves,
        double persistence,
        double lacunarity,
        @NotNull FractalFunction fractalFunction,
        boolean incrementSeed
    ) {
        super(noiseGenerator);
        this.octaves = octaves;
        this.persistence = persistence;
        this.lacunarity = lacunarity;
        this.fractalFunction = fractalFunction;
        this.incrementSeed = incrementSeed;
        double fractalBounding = 0;
        double amplitude = 1;
        for (int i = 0; i < this.octaves; i++) {
            fractalBounding += amplitude;
        }
        this.fractalBounding = fractalBounding;
    }

    @Override
    public double apply1D(double noise, double x) {
        double amplitude = this.persistence;
        double output = fractalFunction.fractalize(noise) * amplitude;

        long seed = noiseGenerator.getSeed();
        // One iteration has already happend (we want to use the received noise in the var "noise"!)
        for (int i = 1; i < this.octaves; i++) {
            x *= lacunarity;
            if (incrementSeed) {
                seed += 1;
            }
            amplitude *= this.persistence;
            // Frequency is already inlcuded in the NoiseGenerator!
            output += fractalFunction.fractalize(noiseGenerator.evaluateNoise(x, seed)) * amplitude;
        }
        return output / fractalBounding;
    }

    @Override
    @NotNull
    public NoiseResult apply1D(@NotNull NoiseResult noiseResult, double x) {
        return new OctavatedNoiseResult(apply1D(noiseResult.getPureValue(), x));
    }

    @Override
    public double apply2D(double noise, double x, double y) {
        double amplitude = this.persistence;
        double output = fractalFunction.fractalize(noise) * amplitude;

        long seed = noiseGenerator.getSeed();
        // One iteration has already happend (we want to use the received noise in the var "noise"!)
        for (int i = 1; i < this.octaves; i++) {
            x *= lacunarity;
            y *= lacunarity;
            if (incrementSeed) {
                seed += 1;
            }
            amplitude *= this.persistence;
            // Frequency is already inlcuded in the NoiseGenerator!
            output += fractalFunction.fractalize(noiseGenerator.evaluateNoise(x, y, seed)) * amplitude;
        }
        return output / fractalBounding;
    }

    @Override
    @NotNull
    public NoiseResult apply2D(@NotNull NoiseResult noiseResult, double x, double y) {
        return new OctavatedNoiseResult(apply2D(noiseResult.getPureValue(), x, y));
    }

    @Override
    public double apply3D(double noise, double x, double y, double z) {
        double amplitude = this.persistence;
        double output = fractalFunction.fractalize(noise) * amplitude;

        long seed = noiseGenerator.getSeed();
        // One iteration has already happend (we want to use the received noise in the var "noise"!)
        for (int i = 1; i < this.octaves; i++) {
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            if (incrementSeed) {
                seed += 1;
            }
            amplitude *= this.persistence;
            // Frequency is already inlcuded in the NoiseGenerator!
            output += fractalFunction.fractalize(noiseGenerator.evaluateNoise(x, y, z, seed)) * amplitude;
        }
        return output / fractalBounding;
    }

    @Override
    @NotNull
    public NoiseResult apply3D(@NotNull NoiseResult noiseResult, double x, double y, double z) {
        return new OctavatedNoiseResult(apply3D(noiseResult.getPureValue(), x, y, z));
    }

    @Override
    public double apply4D(double noise, double x, double y, double z, double w) {
        double amplitude = this.persistence;
        double output = fractalFunction.fractalize(noise) * amplitude;

        long seed = noiseGenerator.getSeed();
        // One iteration has already happend (we want to use the received noise in the var "noise"!)
        for (int i = 1; i < this.octaves; i++) {
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
            if (incrementSeed) {
                seed += 1;
            }
            amplitude *= this.persistence;
            // Frequency is already inlcuded in the NoiseGenerator!
            output += fractalFunction.fractalize(noiseGenerator.evaluateNoise(x, y, z, w, seed)) * amplitude;
        }
        return output / fractalBounding;
    }

    @Override
    @NotNull
    public NoiseResult apply4D(@NotNull NoiseResult noiseResult, double x, double y, double z, double w) {
        return new OctavatedNoiseResult(apply4D(noiseResult.getPureValue(), x, y, z, w));
    }

    @NotNull
    public static OctavationModuleBuilder newBuilder() {
        return new OctavationModuleBuilder();
    }
}
