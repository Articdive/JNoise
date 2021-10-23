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
import de.articdive.jnoise.api.module.NoiseModuleBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Articdive
 */
public abstract class NoiseBuilder<NR extends NoiseResult, B extends NoiseBuilder<? extends NoiseResult, B>> {
    private final List<NoiseModuleBuilder<?>> noiseModules = new ArrayList<>();
    protected long seed = 1729;

    @NotNull
    public B addModule(@NotNull NoiseModuleBuilder<?> module) {
        noiseModules.add(module);
        return (B) this;
    }

    /**
     * Sets the seed for the {@link NoiseGenerator}.
     *
     * @param seed the new seed for the {@link NoiseGenerator}.
     * @return {@link B} this
     */
    public @NotNull B setSeed(long seed) {
        this.seed = seed;
        return (B) this;
    }

    /**
     * Generates the noise generator.
     *
     * @return {@link JNoise}
     */
    @NotNull
    public final JNoise build() {
        return JNoise.JNoiseBuilder.build(createGenerator(), noiseModules);
    }

    @NotNull
    protected abstract NoiseGenerator<NR> createGenerator();
}
