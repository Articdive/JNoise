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

package de.articdive.jnoise.interpolation;

/**
 * @author Lukas Mansour
 */
public enum InterpolationType implements Interpolation {
    LINEAR {
        @Override
        public double lerp(double x, double a, double b) {
            return a + x * (b - a);
        }
    },
    QUADRATIC {
        @Override
        public double lerp(double x, double a, double b) {
            return a + (b - a) * x * x;
        }
    },
    CUBIC {
        @Override
        public double lerp(double x, double a, double b) {
            return a + (b - a) * x * x * x;
        }
    },
    QUARTIC {
        @Override
        public double lerp(double x, double a, double b) {
            return a + (b - a) * x * x * x * x;
        }
    },
    COSINE {
        @Override
        public double lerp(double x, double a, double b) {
            return a + ((1.0 - Math.cos(x * Math.PI)) / 2.0) * (b - a);
        }
    },
}
