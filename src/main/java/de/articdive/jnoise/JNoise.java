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

package de.articdive.jnoise;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.NoiseResult;
import de.articdive.jnoise.noise.octaved.OctaveNoiseBuilder;
import de.articdive.jnoise.noise.opensimplex.OpenSimplexBuilder;
import de.articdive.jnoise.noise.perlin.PerlinNoiseBuilder;
import de.articdive.jnoise.noise.value.ValueNoiseBuilder;
import de.articdive.jnoise.noise.white.WhiteNoiseBuilder;
import de.articdive.jnoise.noise.worley.WorleyNoiseBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Mansour
 */
public final class JNoise {
    private final NoiseGenerator<?> noiseGenerator;

    private JNoise(@NotNull NoiseGenerator<?> noiseGenerator) {
        this.noiseGenerator = noiseGenerator;
    }

    /**
     * Evaluates the noise at a 2D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @return A value representing the noise at the point (x,y), its bounds are noise-type dependant!
     */
    public double getNoise(double x, double y) {
        return noiseGenerator.evaluateNoise(x, y).getNoiseValue();
    }

    /**
     * Evaluates the noise at a 3D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @param z The z value of the point
     * @return A value representing the noise at the point (x,y,z), its bounds are noise-type dependant!
     */
    public double getNoise(double x, double y, double z) {
        return noiseGenerator.evaluateNoise(x, y, z).getNoiseValue();
    }

    /**
     * Evaluates the noise at a 4D point.
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @param z The z value of the point
     * @param w The w value of the point
     * @return A value representing the noise at the point (x,y,z,w), its bounds are noise-type dependant!
     */
    public double getNoise(double x, double y, double z, double w) {
        return noiseGenerator.evaluateNoise(x, y, z, w).getNoiseValue();
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
        return noiseGenerator.evaluateNoise(x, y);
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
        return noiseGenerator.evaluateNoise(x, y, z);
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
        return noiseGenerator.evaluateNoise(x, y, z, w);
    }

    @NotNull
    public static JNoise build(@NotNull NoiseGenerator<?> generator) {
        return new JNoise(generator);
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
        public OpenSimplexBuilder openSimplex() {
            return new OpenSimplexBuilder();
        }

        @NotNull
        public ValueNoiseBuilder value() {
            return new ValueNoiseBuilder();
        }

        @NotNull
        public OctaveNoiseBuilder octavated() {
            return new OctaveNoiseBuilder();
        }

        @NotNull
        public WorleyNoiseBuilder worley() {
            return new WorleyNoiseBuilder();
        }
    }
}
