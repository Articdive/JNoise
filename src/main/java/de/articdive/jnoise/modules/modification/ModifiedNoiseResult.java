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

import de.articdive.jnoise.api.NoiseResult;

/**
 * This class wraps the result of modified Noise.
 *
 * @author Articdive
 */
public record ModifiedNoiseResult(double pureValue) implements NoiseResult {
    @Override
    public double getPureValue() {
        return pureValue;
    }
}