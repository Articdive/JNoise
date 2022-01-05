/*
 * JNoise
 * Copyright (C) 2021-2022 Articdive
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
 * @author Articdive
 */
@Deprecated
public enum FadeFunctionType implements FadeFunction {
    /**
     * @deprecated Use {@link FadeFunction#NONE}
     */
    @Deprecated(forRemoval = true)
    NONE {
        @Override
        public double fade(double t) {
            return t;
        }
    },
    /**
     * @deprecated Use {@link FadeFunction#SMOOTHSTEP}
     */
    @Deprecated(forRemoval = true)
    SMOOTHSTEP {
        @Override
        public double fade(double t) {
            return t * t * (3 - 2 * t); // f(t) = -(2t^3) + 3t^2
        }
    },
    /**
     * @deprecated Use {@link FadeFunction#IMPROVED_PERLIN_NOISE}
     */
    @Deprecated(forRemoval = true)
    IMPROVED_PERLIN_NOISE {
        @Override
        public double fade(double t) {
            return t * t * t * (t * (t * 6 - 15) + 10); // f(t) = 6t^5 - (15t^4) + 10t^3
        }
    }
}
