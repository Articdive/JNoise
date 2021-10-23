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

package de.articdive.jnoise.fractal_functions;

/**
 * @author Articdive
 */
public enum FractalFunctionType implements FractalFunction {
    FBM {
        @Override
        public double fractalize(double a) {
            return a; // FBM is actually the base layout for the OctavationModule
        }
    },
    BILLOW {
        @Override
        public double fractalize(double a) {
            return Math.abs(a) * 2 - 1;
        }
    },
    RIDGED_MULTI {
        @Override
        public double fractalize(double a) {
            return 1 - Math.abs(a);
        }
    }

}
