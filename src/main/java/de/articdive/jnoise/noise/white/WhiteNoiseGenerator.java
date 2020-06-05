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

package de.articdive.jnoise.noise.white;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.util.HashUtil;

/**
 * The bounds of this White Noise implementation are: {0.0, 1.1}.
 *
 * @author Lukas Mansour
 */
public final class WhiteNoiseGenerator extends NoiseGenerator {
    private final double[] output = new double[]{0.0, 1.0};
    
    WhiteNoiseGenerator(int seed) {
        super(seed);
    }
    
    @Override
    public double evaluateNoise(double x) {
        return output[HashUtil.hash1D(seed, Double.toHexString(x).hashCode()) & 1];
    }
    
    @Override
    public double evaluateNoise(double x, double y) {
        return output[HashUtil.hash2D(seed, Double.toHexString(x).hashCode(), Double.toHexString(y).hashCode()) & 1];
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z) {
        return output[HashUtil.hash3D(seed, Double.toHexString(x).hashCode(), Double.toHexString(y).hashCode(), Double.toHexString(z).hashCode()) & 1];
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return output[HashUtil.hash4D(seed, Double.toHexString(x).hashCode(), Double.toHexString(y).hashCode(), Double.toHexString(z).hashCode(), Double.toHexString(w).hashCode()) & 1];
    }
}
