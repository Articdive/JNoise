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

package de.articdive.jnoise.noise.white;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Mansour
 */
public final class WhiteNoiseBuilder extends NoiseBuilder<WhiteNoiseBuilder> {
    @NotNull
    @Override
    protected WhiteNoiseBuilder self() {
        return this;
    }

    @Override
    @NotNull
    public JNoise build() {
        return JNoise.build(new WhiteNoiseGenerator(seed));
    }
}
