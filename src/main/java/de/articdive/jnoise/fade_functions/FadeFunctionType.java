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

package de.articdive.jnoise.fade_functions;

/**
 * @author Lukas Mansour
 */
public enum FadeFunctionType implements FadeFunction {
    NONE {
        @Override
        public double fade(double t) {
            return t;
        }
    },
    SMOOTHSTEP {
        @Override
        public double fade(double t) {
            return t * t * (3 - 2 * t); // -(2t^3) + 3t^2
        }
    },
    IMPROVED_PERLIN_NOISE {
        @Override
        public double fade(double t) {
            return t * t * t * (t * (t * 6 - 15) + 10); // 6t^5 - (15t^4) + 10t^3
        }
    }
}
