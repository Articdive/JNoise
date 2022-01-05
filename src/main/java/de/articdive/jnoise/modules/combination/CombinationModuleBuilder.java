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

package de.articdive.jnoise.modules.combination;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.module.NoiseModuleBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class CombinationModuleBuilder implements NoiseModuleBuilder<CombinationModuleBuilder> {
    private Combiner combiner = Combiner.ADD;
    private JNoise extNoise;

    CombinationModuleBuilder() {
    }

    /**
     * Sets the combiner for the {@link CombinationModule}.
     *
     * @param combiner the new {@link Combiner} for the {@link CombinationModule}.
     * @return {@link CombinationModuleBuilder} this
     */
    @NotNull
    public CombinationModuleBuilder setCombiner(@NotNull Combiner combiner) {
        this.combiner = combiner;
        return this;
    }

    /**
     * Sets the noise that will be combined with the main noise source.
     *
     * @param extNoise the {@link JNoise} generator that should be combined.
     * @return {@link CombinationModuleBuilder} this
     */
    @NotNull
    public CombinationModuleBuilder setExtNoise(@NotNull JNoise extNoise) {
        this.extNoise = extNoise;
        return this;
    }

    @Override
    public CombinationModule apply(@NotNull NoiseGenerator<?> noiseGenerator) {
        return new CombinationModule(noiseGenerator, combiner, extNoise);
    }
}