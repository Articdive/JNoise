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

package de.articdive.jnoise.modules.combination;

import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

public final class CombinationModule implements NoiseModule {
    private final NoiseSource a;
    private final NoiseSource b;
    private final Combiner combiner;

    private CombinationModule(
        @NotNull NoiseSource a,
        @NotNull NoiseSource b,
        @NotNull Combiner combiner
    ) {
        this.a = a;
        this.b = b;
        this.combiner = combiner;
    }

    @Override
    public double evaluateNoise(double x) {
        return combiner.combine(a.evaluateNoise(x), b.evaluateNoise(x));
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return combiner.combine(a.evaluateNoise(x, y), b.evaluateNoise(x, y));
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return combiner.combine(a.evaluateNoise(x, y, z), b.evaluateNoise(x, y, z));
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return combiner.combine(a.evaluateNoise(x, y, z, w), b.evaluateNoise(x, y, z, w));
    }

    @NotNull
    public static CombinationModuleBuilder newBuilder() {
        return new CombinationModuleBuilder();
    }

    public static final class CombinationModuleBuilder implements NoiseSourceBuilder {
        private NoiseSource a;
        private NoiseSource b;
        private Combiner combiner = Combiner.ADD;

        private CombinationModuleBuilder() {
        }

        /**
         * Sets the first noise source for the {@link CombinationModule}.
         *
         * @param noiseSource the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        @NotNull
        public CombinationModuleBuilder setA(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            this.a = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link CombinationModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        @NotNull
        public CombinationModuleBuilder setA(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            this.a = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the second noise source for the {@link CombinationModule}.
         *
         * @param noiseSource the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        @NotNull
        public CombinationModuleBuilder setB(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.b = noiseSource;
            return this;
        }

        /**
         * Sets the second noise source for the {@link CombinationModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        @NotNull
        public CombinationModuleBuilder setB(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.b = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the combiner for the {@link CombinationModule}.
         *
         * @param combiner the new {@link Combiner} for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        @NotNull
        public CombinationModuleBuilder setCombiner(Combiner combiner) {
            if (combiner == null) {
                throw new IllegalArgumentException("Combiner cannot be null.");
            }
            this.combiner = combiner;
            return this;
        }


        @NotNull
        public CombinationModule build() {
            if (a == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            if (b == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            return new CombinationModule(a, b, combiner);
        }
    }
}