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

package de.articdive.jnoise.modules.combination;

/**
 * @author Articdive
 */
@FunctionalInterface
public interface Combiner {
    Combiner ADD = Double::sum;
    Combiner MULTIPLY = (a, b) -> a * b;
    Combiner MAX = Math::max;
    Combiner MIN = Math::min;
    Combiner POW = Math::pow;
    
    double combine(double a, double b);
}
