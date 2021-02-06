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

package de.articdive.jnoise.noise.white;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.util.HashUtil;

/**
 * The bounds of this White Noise implementation are: {0.0, 1.1}.
 *
 * @author Lukas Mansour
 */
public final class WhiteNoiseGenerator extends NoiseGenerator<WhiteNoiseResult> {
    private final double[] output = new double[]{0.0, 1.0};

    WhiteNoiseGenerator(long seed) {
        super(seed);
    }

    @Override
    public WhiteNoiseResult evaluateNoise(double x, double y) {
        return new WhiteNoiseResult(output[HashUtil.hash2D(seed, Double.hashCode(x), Double.hashCode(y)) & 1]);
    }

    @Override
    public WhiteNoiseResult evaluateNoise(double x, double y, double z) {
        return new WhiteNoiseResult(output[
            HashUtil.hash3D(seed, Double.hashCode(x), Double.hashCode(y), Double.hashCode(z)) & 1]);
    }

    @Override
    public WhiteNoiseResult evaluateNoise(double x, double y, double z, double w) {
        return new WhiteNoiseResult(output[
            HashUtil.hash4D(seed, Double.hashCode(x), Double.hashCode(y), Double.hashCode(z), Double.hashCode(w)) & 1]);
    }
}
