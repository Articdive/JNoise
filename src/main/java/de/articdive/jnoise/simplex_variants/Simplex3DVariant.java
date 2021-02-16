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

package de.articdive.jnoise.simplex_variants;

/**
 * @author Lukas Mansour
 */
public enum Simplex3DVariant {
    /**
     * Standard (equivilant) 3D OpenSimplex noise.
     * Use XYBeforeZ or XZBeforeY instead, wherever appropriate.
     */
    CLASSIC,
    /**
     * Better visual isotropy in (X, Y).
     * Recommended for 3D terrain and time-varied animations.
     * The Z coordinate should always be the "different" coordinate in your use case.
     * If Y is vertical in world coordinates, use XYBeforeZ(x, z, Y) or use XZBeforeY.
     * If Z is vertical in world coordinates, use XYBeforeZ(x, y, Z).
     * For a time varied animation, call XYBeforeZ(x, y, T).
     */
    XY_BEFORE_Z,
    /**
     * Better visual isotropy in (X, Z).
     * Recommended for 3D terrain and time-varied animations.
     * The Y coordinate should always be the "different" coordinate in your use case.
     * If Y is vertical in world coordinates, call XZBeforeY(x, Y, z).
     * If Z is vertical in world coordinates, call XZBeforeY(x, Z, y) or use XYBeforeZ.
     * For a time varied animation, call XZBeforeY(x, T, y) or use XYBeforeZ.
     */
    XZ_BEFORE_Y
}
