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

package de.articdive.jnoise.api;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.builders.Seeded;
import de.articdive.jnoise.noise.opensimplex.FastSimplexBuilder;
import de.articdive.jnoise.noise.opensimplex.SuperSimplexBuilder;
import de.articdive.jnoise.noise.perlin.PerlinNoiseBuilder;
import de.articdive.jnoise.noise.value.ValueNoiseBuilder;
import de.articdive.jnoise.noise.white.WhiteNoiseBuilder;
import de.articdive.jnoise.noise.worley.WorleyNoiseBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public abstract class NoiseBuilder {

    /**
     * Generates the noise-generator
     *
     * @return {@link JNoise}
     */
    @NotNull
    public abstract JNoise build();

    public static Long getSeed(NoiseBuilder builder) {
        if (!(builder instanceof Seeded)) {
            return null;
        } else if (builder instanceof WorleyNoiseBuilder) {
            return WorleyNoiseBuilder.getSeed((WorleyNoiseBuilder) builder);
        } else if (builder instanceof SuperSimplexBuilder) {
            return SuperSimplexBuilder.getSeed((SuperSimplexBuilder) builder);
        } else if (builder instanceof FastSimplexBuilder) {
            return FastSimplexBuilder.getSeed((FastSimplexBuilder) builder);
        } else if (builder instanceof PerlinNoiseBuilder) {
            return PerlinNoiseBuilder.getSeed((PerlinNoiseBuilder) builder);
        } else if (builder instanceof ValueNoiseBuilder) {
            return ValueNoiseBuilder.getSeed((ValueNoiseBuilder) builder);
        } else if (builder instanceof WhiteNoiseBuilder) {
            return WhiteNoiseBuilder.getSeed((WhiteNoiseBuilder) builder);
        }
        return null;
    }

}
