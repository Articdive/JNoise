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

package de.articdive.jnoise;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.NoiseResult;
import de.articdive.jnoise.api.module.NoiseModule;
import de.articdive.jnoise.api.module.NoiseModuleBuilder;
import de.articdive.jnoise.noise.opensimplex.FastSimplexBuilder;
import de.articdive.jnoise.noise.opensimplex.SuperSimplexBuilder;
import de.articdive.jnoise.noise.perlin.PerlinNoiseBuilder;
import de.articdive.jnoise.noise.value.ValueNoiseBuilder;
import de.articdive.jnoise.noise.white.WhiteNoiseBuilder;
import de.articdive.jnoise.noise.worley.WorleyNoiseBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main entrypoint class for JNoise.
 */
public final class JNoise {
    private final NoiseGenerator<? extends NoiseResult> noiseGenerator;
    private final List<NoiseModule> noiseModules = new ArrayList<>();

    private JNoise(@NotNull NoiseGenerator<? extends NoiseResult> noiseGenerator, List<NoiseModuleBuilder<?>> noiseModuleBuilders) {
        this.noiseGenerator = noiseGenerator;
        for (NoiseModuleBuilder<?> builder : noiseModuleBuilders) {
            noiseModules.add(builder.apply(noiseGenerator));
        }
    }

    /**
     * Evaluates noise at a 1D point.
     *
     * @param x The x value of the point.
     * @return A double representing the noise at the point (x), its bounds are noise-type dependant!
     */
    public double getNoise(double x) {
        double noise = noiseGenerator.evaluateNoise(x);
        for (NoiseModule noiseModule : noiseModules) {
            noise = noiseModule.apply1D(noise, x);
        }
        return noise;
    }

    /**
     * Evaluates the noise at a 2D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @return A double representing the noise at the point (x,y), its bounds are noise-type dependant!
     */
    public double getNoise(double x, double y) {
        double noise = noiseGenerator.evaluateNoise(x, y);
        for (NoiseModule noiseModule : noiseModules) {
            noise = noiseModule.apply2D(noise, x, y);
        }
        return noise;
    }

    /**
     * Evaluates the noise at a 3D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @param z The z value of the point
     * @return A double representing the noise at the point (x,y,z), its bounds are noise-type dependant!
     */
    public double getNoise(double x, double y, double z) {
        double noise = noiseGenerator.evaluateNoise(x, y, z);
        for (NoiseModule noiseModule : noiseModules) {
            noise = noiseModule.apply3D(noise, x, y, z);
        }
        return noise;
    }

    /**
     * Evaluates the noise at a 4D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @param z The z value of the point
     * @param w The w value of the point
     * @return A double representing the noise at the point (x,y,z,w), its bounds are noise-type dependant!
     */
    public double getNoise(double x, double y, double z, double w) {
        double noise = noiseGenerator.evaluateNoise(x, y, z, w);
        for (NoiseModule noiseModule : noiseModules) {
            noise = noiseModule.apply4D(noise, x, y, z, w);
        }
        return noise;
    }

    /**
     * Evaluates noise at a 1D point.
     *
     * @param x The x value of the point.
     * @return A {@link NoiseResult} representing the noise at the point (x), its bounds are noise-type dependant!
     */
    public NoiseResult getNoiseResult(double x) {
        NoiseResult result = noiseGenerator.evaluateNoiseResult(x);
        for (NoiseModule noiseModule : noiseModules) {
            result = noiseModule.apply1D(result, x);
        }
        return result;
    }

    /**
     * Evaluates the noise at a 2D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @return A {@link NoiseResult} representing the noise at the point (x,y), its bounds are noise-type dependant!
     */
    @NotNull
    public NoiseResult getNoiseResult(double x, double y) {
        NoiseResult result = noiseGenerator.evaluateNoiseResult(x, y);
        for (NoiseModule noiseModule : noiseModules) {
            result = noiseModule.apply2D(result, x, y);
        }
        return result;
    }

    /**
     * Evaluates the noise at a 3D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @param z The z value of the point
     * @return A {@link NoiseResult} representing the noise at the point (x,y,z), its bounds are noise-type dependant!
     */
    @NotNull
    public NoiseResult getNoiseResult(double x, double y, double z) {
        NoiseResult result = noiseGenerator.evaluateNoiseResult(x, y, z);
        for (NoiseModule noiseModule : noiseModules) {
            result = noiseModule.apply3D(result, x, y, z);
        }
        return result;
    }

    /**
     * Evaluates the noise at a 4D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @param z The z value of the point
     * @param w The w value of the point
     * @return A {@link NoiseResult} representing the noise at the point (x,y,z,w), its bounds are noise-type dependant!
     */
    @NotNull
    public NoiseResult getNoiseResult(double x, double y, double z, double w) {
        NoiseResult result = noiseGenerator.evaluateNoiseResult(x, y, z, w);
        for (NoiseModule noiseModule : noiseModules) {
            result = noiseModule.apply4D(result, x, y, z, w);
        }
        return result;
    }

    @NotNull
    public static JNoiseBuilder newBuilder() {
        return new JNoiseBuilder();
    }

    public static final class JNoiseBuilder {
        private JNoiseBuilder() {
        }

        @NotNull
        public PerlinNoiseBuilder perlin() {
            return new PerlinNoiseBuilder();
        }

        @NotNull
        public WhiteNoiseBuilder white() {
            return new WhiteNoiseBuilder();
        }

        @NotNull
        public FastSimplexBuilder fastSimplex() {
            return new FastSimplexBuilder();
        }

        @NotNull
        public SuperSimplexBuilder superSimplex() {
            return new SuperSimplexBuilder();
        }

        @NotNull
        public ValueNoiseBuilder value() {
            return new ValueNoiseBuilder();
        }

        @NotNull
        public WorleyNoiseBuilder worley() {
            return new WorleyNoiseBuilder();
        }

        @NotNull
        public static JNoise build(@NotNull NoiseGenerator<? extends NoiseResult> generator, List<NoiseModuleBuilder<?>> noiseModules) {
            return new JNoise(generator, noiseModules);
        }
    }
}
