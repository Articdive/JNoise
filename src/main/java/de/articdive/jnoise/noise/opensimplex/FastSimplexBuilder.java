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

package de.articdive.jnoise.noise.opensimplex;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.simplex_variants.Simplex2DVariant;
import de.articdive.jnoise.simplex_variants.Simplex3DVariant;
import de.articdive.jnoise.simplex_variants.Simplex4DVariant;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Mansour
 */
public final class FastSimplexBuilder extends NoiseBuilder {
    private long seed = 1729;
    private double frequency = 1.0;
    private Simplex2DVariant variant2D = Simplex2DVariant.CLASSIC;
    private Simplex3DVariant variant3D = Simplex3DVariant.CLASSIC;
    private Simplex4DVariant variant4D = Simplex4DVariant.CLASSIC;

    /**
     * Sets the seed for the {@link FastSimplexGenerator}.
     *
     * @param seed the new seed for the {@link FastSimplexGenerator}.
     * @return {@link FastSimplexBuilder} this
     */
    @NotNull
    public FastSimplexBuilder setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    /**
     * Sets the frequency for the {@link FastSimplexGenerator}.
     *
     * @param frequency the new frequency for the {@link FastSimplexGenerator}.
     * @return {@link FastSimplexBuilder} this
     */
    @NotNull
    public FastSimplexBuilder setFrequency(double frequency) {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Frequency must be a non-zero positive value.");
        }
        this.frequency = frequency;
        return this;
    }

    /**
     * Sets the variant used for 2D OpenSimplex noise
     *
     * @param variant2D the new {@link Simplex2DVariant} for the {@link FastSimplexGenerator}.
     * @return {@link FastSimplexBuilder} this
     */
    @NotNull
    public FastSimplexBuilder setVariant2D(@NotNull Simplex2DVariant variant2D) {
        this.variant2D = variant2D;
        return this;
    }

    /**
     * Sets the variant used for 3D OpenSimplex noise
     *
     * @param variant3D the new {@link Simplex3DVariant} for the {@link FastSimplexGenerator}.
     * @return {@link FastSimplexBuilder} this
     */
    @NotNull
    public FastSimplexBuilder setVariant3D(@NotNull Simplex3DVariant variant3D) {
        this.variant3D = variant3D;
        return this;
    }

    /**
     * Sets the variant used for 4D OpenSimplex noise
     *
     * @param variant4D the new {@link Simplex4DVariant} for the {@link FastSimplexGenerator}.
     * @return {@link FastSimplexBuilder} this
     */
    @NotNull
    public FastSimplexBuilder setVariant4D(@NotNull Simplex4DVariant variant4D) {
        this.variant4D = variant4D;
        return this;
    }

    @Override
    @NotNull
    public JNoise build() {
        return JNoise.build(new FastSimplexGenerator(seed, frequency, variant2D, variant3D, variant4D));
    }
}
