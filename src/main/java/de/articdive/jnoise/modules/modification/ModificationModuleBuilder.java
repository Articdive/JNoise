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

package de.articdive.jnoise.modules.modification;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.module.NoiseModuleBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public final class ModificationModuleBuilder implements NoiseModuleBuilder<ModificationModuleBuilder> {
    private Modifier modifier = Modifier.ABS;

    ModificationModuleBuilder() {
    }

    /**
     * Sets the combiner for the {@link ModificationModule}.
     *
     * @param modifier the new {@link Modifier} for the {@link ModificationModule}.
     * @return {@link ModificationModuleBuilder} this
     */
    @NotNull
    public ModificationModuleBuilder setModifier(@NotNull Modifier modifier) {
        this.modifier = modifier;
        return this;
    }

    @Override
    public ModificationModule apply(@NotNull NoiseGenerator<?> noiseGenerator) {
        return new ModificationModule(noiseGenerator, modifier);
    }
}