/*
 * JNoise
 * Copyright (C) 2020-2022 Articdive
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

package de.articdive.jnoise.core.util.vectors;

import org.jetbrains.annotations.NotNull;

/**
 * Record that denotes a mathematical vector with an X component.
 *
 * @author Articdive
 */
public record Vector1D(double x) implements Vector {

    /**
     * Calculates the dot product of this vector with another 1D vector.
     *
     * @param vector1D {@link Vector1D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(@NotNull Vector1D vector1D) {
        return (x * vector1D.x);
    }

    @Override
    public double getX() {
        return x;
    }
}
