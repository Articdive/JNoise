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

package de.articdive.jnoise.noise.combined;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.Operator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The bounds of this combined noise implementation depends on the amount of noise and the operator.
 *
 * @author Lukas Mansour
 */
public final class CombinedNoiseGenerator extends NoiseGenerator {
    private final List<JNoise> noises;
    private final Operator operator;

    CombinedNoiseGenerator(long seed, @NotNull List<JNoise> noises, @NotNull Operator operator) {
        super(seed);
        this.noises = noises;
        this.operator = operator;
    }

    @Override
    public double evaluateNoise(double x, double y) {
        double output = noises.get(0).getNoise(x, y);
        for (int i = 1; i < noises.size(); i++) {
            output = operator.combine(output, noises.get(i).getNoise(x, y));
        }
        return output;
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        double output = noises.get(0).getNoise(x, y, z);
        for (int i = 1; i < noises.size(); i++) {
            output = operator.combine(output, noises.get(i).getNoise(x, y, z));
        }
        return output;
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        double output = noises.get(0).getNoise(x, y, z, w);
        for (int i = 1; i < noises.size(); i++) {
            output = operator.combine(output, noises.get(i).getNoise(x, y, z, w));
        }
        return output;
    }
}
