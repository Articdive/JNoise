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

package de.articdive.jnoise.api;

/**
 * @author Lukas Mansour
 */
public interface Interpolation {

    /**
     * Interpolates a value between two known values.
     *
     * @param x Position for the data to be interpolated.
     * @param a The first known value.
     * @param b The second known value.
     * @return an interpolated value for x.
     */
    double lerp(double x, double a, double b);

    /**
     * Interpolates between an unknown number of values.
     * The amount of positions repsents the dimension
     * The amount of values must be 2^(amount of position).
     *
     * @param positions A list of positions starting with the first stage position and going up in order.
     * @param values    A list of values starting with the first stage values and going up in order.
     * @return an interpolated value between all the given positions.
     */
    default double lerp(double[] positions, double[] values) {
        if (values.length != 1 << positions.length) {
            throw new IllegalArgumentException("The amount of values must be 2^(amount of fractals).");
        }
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < ((1 << positions.length) - i); j += 2) {
                values[j / 2] = lerp(positions[i], values[j], values[j + 1]);
            }
        }
        // This should only have 1 element, the final value!
        return values[0];
    }
}
