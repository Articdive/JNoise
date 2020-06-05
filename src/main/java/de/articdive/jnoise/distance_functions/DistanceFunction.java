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

package de.articdive.jnoise.distance_functions;

/**
 * @author Articdive
 */
public interface DistanceFunction {
    /**
     * Gets the distance from a 2D point to another 2D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param y0 Y-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @param y1 Y-Coordinate of the second point.
     * @return a positive distance between P(x0, y0) and Q(x1, y1)
     */
    double distance(double x0, double y0, double x1, double y1);

    /**
     * Gets the distance from a 3D point to another 3D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param y0 Y-Coordinate of the first point.
     * @param z0 Z-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @param y1 Y-Coordinate of the second point.
     * @param z1 Z-Coordinate of the second point.
     * @return a positive distance between (x0, y0, z0) and (x1, y1, z1)
     */
    double distance(double x0, double y0, double z0, double x1, double y1, double z1);

    /**
     * Gets the distance from a 4D point to another 4D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param y0 Y-Coordinate of the first point.
     * @param z0 Z-Coordinate of the first point.
     * @param w0 W-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @param y1 Y-Coordinate of the second point.
     * @param z1 Z-Coordinate of the second point.
     * @param w1 W-Coordinate of the second point.
     * @return a positive distance between (x0, y0, z0, w0) and (x1, y1, z1, w1)
     */
    double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1);
}
