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

package de.articdive.jnoise.generators.noise_parameters.simplex_variants;

/**
 * Enum for the different variants of OpenSimplex2 4D Noise.
 *
 * @author Articdive
 */
public enum Simplex4DVariant {
    /**
     * 4D OpenSimplex2 noise, standard (fallback) lattice orientation.
     */
    CLASSIC,
    /**
     * 4D OpenSimplex2 noise, with XY and ZW forming orthogonal triangular-based planes.
     * Recommended for 3D terrain, where X and Y (or Z and W) are horizontal.
     * Recommended for noise(x, y, sin(time), cos(time)) trick.
     */
    IMPROVE_XY_IMPROVE_ZW,
    /**
     * 4D OpenSimplex2 noise, with XYZ oriented like noise3_ImproveXY
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     * in a space where Z is vertical
     */
    IMPROVE_XYZ_IMPROVE_XY,
    /**
     * 4D OpenSimplex2 noise, with XYZ oriented like noise3_ImproveXZ
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     * in a space where Y is vertical
     */
    IMPROVE_XYZ_IMPROVE_XZ,
    /**
     * 4D OpenSimplex2 noise, with XYZ oriented like noise3_Fallback
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     * where there isn't a clear distinction between horizontal and vertical
     */
    IMRPOVE_XYZ
}
