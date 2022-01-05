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

package de.articdive.jnoise.api;

/**
 * This interface denotes the NoiseResult class, which is used to wrap the results of an entire noise generation step.
 * Useful when there are multiple forms of a result, i.e. not only a mathematical value but e.g. the closest feature point.
 *
 * @author Articdive
 */
public interface NoiseResult {
    /**
     * @return double denoting the pure mathematical value of the noise result.
     */
    double getPureValue();
}