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

package de.articdive.jnoise.generators.noisegen.constant;

import de.articdive.jnoise.core.api.noisegen.NoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * A noise generator that always returns the constant specified.
 *
 * @author Articdive
 */
public final class ConstantNoiseGenerator implements NoiseGenerator {
    private final double constant;

    private ConstantNoiseGenerator(double constant) {
        this.constant = constant;
    }

    @Override
    public double evaluateNoise(double x) {
        return constant;
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return constant;
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return constant;
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return constant;
    }

    @NotNull
    public static ConstantNoiseBuilder newBuilder() {
        return new ConstantNoiseBuilder();
    }

    public static final class ConstantNoiseBuilder implements NoiseSourceBuilder {
        private double constant = 0;

        private ConstantNoiseBuilder() {

        }

        /**
         * Sets the result constant for the {@link ConstantNoiseGenerator}.
         *
         * @param constant the new result for the {@link ConstantNoiseGenerator}.
         * @return {@link ConstantNoiseBuilder} this
         */
        @NotNull
        public ConstantNoiseBuilder setConstant(double constant) {
            this.constant = constant;
            return this;
        }

        @Override
        @NotNull
        public ConstantNoiseGenerator build() {
            return new ConstantNoiseGenerator(constant);
        }
    }
}
