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
 * @author Articdive
 */
public enum Simplex3DVariant {
    /**
     * 3D OpenSimplex2 noise, fallback rotation option
     * Use {@link Simplex3DVariant#IMPROVE_XY} or {@link Simplex3DVariant#IMPROVE_XZ} instead, wherever appropriate.
     * They have less diagonal bias. This function's best use is as a fallback.
     */
    CLASSIC,
    /**
     * 3D OpenSimplex2 noise, with better visual isotropy in (X, Y).
     * Recommended for 3D terrain and time-varied animations.
     * The Z coordinate should always be the "different" coordinate in whatever your use case is.
     * If Z is vertical in world coordinates, use {@link Simplex3DVariant#IMPROVE_XZ}.
     */
    IMPROVE_XY,
    /**
     * 3D OpenSimplex2 noise, with better visual isotropy in (X, Z).
     * Recommended for 3D terrain and time-varied animations.
     * The Y coordinate should always be the "different" coordinate in whatever your use case is.
     * If Y is vertical in world coordinates, use {@link Simplex3DVariant#IMPROVE_XY}.
     */
    IMPROVE_XZ
}
