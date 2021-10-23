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

package de.articdive.jnoise.api.module;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * @author Articdive
 */
public interface NoiseModuleBuilder<M extends NoiseModuleBuilder<M>> extends Function<@NotNull NoiseGenerator<?>, @NotNull NoiseModule> {
    /**
     * Builds the {@link NoiseModule} for your {@link JNoise} instance.
     * Note: You need not include the build() method, as addModule() accepts the Builder.
     * This is intentional behaviour.
     *
     * @return {@link M} this
     */
    default M build() {
        return (M) this;
    }
}
