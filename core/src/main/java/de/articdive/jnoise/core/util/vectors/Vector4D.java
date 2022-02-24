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
 * Record that denotes a mathematical vector with an X, Y, Z and W component.
 *
 * @author Articdive
 */
public record Vector4D(double x, double y, double z, double w) implements Vector {

    /**
     * Calculates the dot product of this vector with another 4D vector.
     *
     * @param vector4D {@link Vector4D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(@NotNull Vector4D vector4D) {
        return (x * vector4D.x) + (y * vector4D.y) + (z * vector4D.z) + (w * vector4D.w);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public double getW() {
        return w;
    }
}