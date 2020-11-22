/*
 * JNoise
 * Copyright (C) 2020 Articdive (Lukas Mansour)
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

import de.articdive.jnoise.api.DistanceFunction;

/**
 * @author Lukas Mansour
 */
public enum DistanceFunctionType implements DistanceFunction {
    EUCLIDEAN {
        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, y0, x1, y1));
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, y0, z0, x1, y1, z1));
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, y0, z0, w0, x1, y1, z1, w1));
        }
    },
    EUCLIDEAN_SQUARED {
        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1) + (z0 - z1) * (z0 - z1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1) + (z0 - z1) * (z0 - z1) + (w0 - w1) * (w0 - w1);
        }
    },
    MANHATTAN {
        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return Math.abs(x0 - x1) + Math.abs(y0 - y1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return Math.abs(x0 - x1) + Math.abs(y0 - y1) + Math.abs(z0 - z1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return Math.abs(x0 - x1) + Math.abs(y0 - y1) + Math.abs(z0 - z1) + Math.abs(w0 - w1);
        }
    },
    CHEBYSHEV {
        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1));
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return Math.max(
                Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1)),
                Math.abs(z0 - z1)
            );
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return Math.max(
                Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1)),
                Math.max(Math.abs(z0 - z1), Math.abs(w0 - w1))
            );
        }
    },
}
