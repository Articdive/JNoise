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

package de.articdive.jnoise.generators.noise_parameters.distance_functions;

/**
 * Interesting distance function that will be a bit more expensive to compute (especially for large values of p)
 * Also to note: p = 1 is the same as Manhattan and p = 2 is the same as EuclidianSquared, so don't use this for p = 1 and p = 2!
 *
 * @author Articdive
 */
public final class MinkowskiDistance implements DistanceFunction {
    private final long p;

    public MinkowskiDistance(long p) {
        this.p = p;
    }

    @Override
    public double distance(double x0, double x1) {
        return Math.abs(x0 - x1);
    }

    @Override
    public double distance(double x0, double y0, double x1, double y1) {
        return Math.pow(
            Math.pow(Math.abs(x0 - x1), p) + Math.pow(Math.abs(y0 - y1), p),
            1.0 / p);
    }

    @Override
    public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
        return Math.pow(
            Math.pow(Math.abs(x0 - x1), p) + Math.pow(Math.abs(y0 - y1), p) + Math.pow(Math.abs(z0 - z1), p),
            1.0 / p);
    }

    @Override
    public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
        return Math.pow(
            Math.pow(Math.abs(x0 - x1), p) + Math.pow(Math.abs(y0 - y1), p) + Math.pow(Math.abs(z0 - z1), p)
                + Math.pow(Math.abs(w0 - w1), p),
            1.0 / p);
    }
}
