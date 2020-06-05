/*
 * JNoise
 * Copyright (C) 2021 Articdive
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

import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public abstract class NoiseGenerator<R extends NoiseResult> {

    protected NoiseGenerator() {
    }

    /**
     * Evaluates the noise at a 2D point.
     *
     * @param x The x value of the point.
     * @param y The y value of the point.
     * @return A value representing the noise at the point (x,y), its bounds are noise-type dependant!
     */
    @NotNull
    public abstract R evaluateNoise(double x, double y);

    /**
     * Evaluates the noise at a 3D point.
     *
     * @param x The x value of the point.
     * @param y The y value of the point.
     * @param z The z value of the point.
     * @return A value representing the noise at the point (x,y,z), its bounds are noise-type dependant!
     */
    @NotNull
    public abstract R evaluateNoise(double x, double y, double z);

    /**
     * Evaluates the noise at a 4D point.
     *
     * @param x The x value of the point.
     * @param y The y value of the point.
     * @param z The z value of the point.
     * @param w The w value of the point.
     * @return A value representing the noise at the point (x,y,z,w), its bounds are noise-type dependant!
     */
    @NotNull
    public abstract R evaluateNoise(double x, double y, double z, double w);
}
