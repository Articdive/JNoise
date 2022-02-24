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

package de.articdive.jnoise.core.api.noisegen;

/**
 * Interface that denotes a seeded {@link NoiseGenerator}.
 * A seeded {@link NoiseGenerator} has reproducability and should return the same value for the same seed at the same location.
 *
 * @author Articdive
 */
public interface SeededNoiseGenerator extends NoiseGenerator {

    double evaluateNoise(double x, long seed);

    double evaluateNoise(double x, double y, long seed);

    double evaluateNoise(double x, double y, double z, long seed);

    double evaluateNoise(double x, double y, double z, double w, long seed);

    /**
     * Returns the seed of the seeded noise generator.
     *
     * @return seed value of the noise generator.
     */
    long getSeed();
}
