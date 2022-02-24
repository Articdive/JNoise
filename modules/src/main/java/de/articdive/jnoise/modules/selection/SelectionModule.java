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

package de.articdive.jnoise.modules.selection;

import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

public final class SelectionModule implements NoiseModule {
    private final NoiseSource a;
    private final NoiseSource b;
    private final NoiseSource controlSource;
    private final double boundary;

    private SelectionModule(
        @NotNull NoiseSource a,
        @NotNull NoiseSource b,
        @NotNull NoiseSource controlSource,
        double boundary
    ) {
        this.a = a;
        this.b = b;
        this.controlSource = controlSource;
        this.boundary = boundary;
    }

    @Override
    public double evaluateNoise(double x) {
        if (controlSource.evaluateNoise(x) >= boundary) {
            return a.evaluateNoise(x);
        } else {
            return b.evaluateNoise(x);
        }
    }

    @Override
    public double evaluateNoise(double x, double y) {
        if (controlSource.evaluateNoise(x, y) >= boundary) {
            return a.evaluateNoise(x, y);
        } else {
            return b.evaluateNoise(x, y);
        }
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        if (controlSource.evaluateNoise(x, y, z) >= boundary) {
            return a.evaluateNoise(x, y, z);
        } else {
            return b.evaluateNoise(x, y, z);
        }
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        if (controlSource.evaluateNoise(x, y, z, w) >= boundary) {
            return a.evaluateNoise(x, y, z, w);
        } else {
            return b.evaluateNoise(x, y, z, w);
        }
    }

    @NotNull
    public static SelectionModuleBuilder newBuilder() {
        return new SelectionModuleBuilder();
    }

    public static final class SelectionModuleBuilder implements NoiseSourceBuilder {
        private NoiseSource a;
        private NoiseSource b;
        private NoiseSource controlSource;
        private double boundary = 0;

        private SelectionModuleBuilder() {
        }

        /**
         * Sets the first noise source for the {@link SelectionModule}.
         *
         * @param noiseSource the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setA(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            this.a = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link SelectionModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setA(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            this.a = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the second noise source for the {@link SelectionModule}.
         *
         * @param noiseSource the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setB(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.b = noiseSource;
            return this;
        }

        /**
         * Sets the second noise source for the {@link SelectionModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setB(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.b = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the control noise source for the {@link SelectionModule}.
         *
         * @param noiseSource the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setControl(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.controlSource = noiseSource;
            return this;
        }

        /**
         * Sets the control noise source for the {@link SelectionModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setControl(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.controlSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the boundary for the {@link SelectionModule}.
         *
         * @param boundary the new boundary for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        @NotNull
        public SelectionModuleBuilder setBoundary(double boundary) {
            this.boundary = boundary;
            return this;
        }


        @NotNull
        public SelectionModule build() {
            if (a == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            if (b == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            if (controlSource == null) {
                throw new IllegalArgumentException("Control noise source cannot be null.");
            }
            return new SelectionModule(a, b, controlSource, boundary);
        }
    }
}
