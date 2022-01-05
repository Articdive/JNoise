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

package de.articdive.jnoise.simplex_variants;

public enum Simplex4DVariant {
    /**
     * Standard 4D OpenSimplex noise.
     */
    CLASSIC,
    /**
     * 4D OpenSimplex noise, with XY and ZW forming orthogonal triangular-based planes.
     * Recommended for 3D terrain, where X and Y (or Z and W) are horizontal.
     * Recommended for noise(x, y, sin(time), cos(time)) trick.
     */
    XY_BEFORE_ZW,
    /**
     * 4D OpenSimplex noise, with XZ and YW forming orthogonal triangular-based planes.
     * Recommended for 3D terrain, where X and Z (or Y and W) are horizontal.
     */
    XZ_BEFORE_YW,
    /**
     * 4D OpenSimplex noise, with XYZ oriented like 3D_Classic,
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     */
    XYZ_BEFORE_W
}
