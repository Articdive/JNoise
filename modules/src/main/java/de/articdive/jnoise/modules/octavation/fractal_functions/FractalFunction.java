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

package de.articdive.jnoise.modules.octavation.fractal_functions;

/**
 * @author Articdive
 */
@FunctionalInterface
public interface FractalFunction {
    // FBM is actually the base layout for the OctavationModule
    FractalFunction FBM = a -> a;

    FractalFunction BILLOW = a -> Math.abs(a) * 2 - 1;

    FractalFunction RIDGED_MULTI = a -> 1 - Math.abs(a);

    /**
     * Applies the fractalization step to a noise output.
     *
     * @param a value (noise output) to fractalize.
     * @return a fractalized value for a.
     */
    double fractalize(double a);
}