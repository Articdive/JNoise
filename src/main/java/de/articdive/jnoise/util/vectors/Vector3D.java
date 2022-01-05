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

package de.articdive.jnoise.util.vectors;

import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public record Vector3D(double x, double y, double z) implements Vector {
    
    public double dot(@NotNull Vector3D vector3D) {
        return (x * vector3D.x) + (y * vector3D.y) + (z * vector3D.z);
    }
}
