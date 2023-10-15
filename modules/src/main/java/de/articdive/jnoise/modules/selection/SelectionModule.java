package de.articdive.jnoise.modules.selection;

import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class SelectionModule implements NoiseModule {
    private final NoiseSource a;
    private final NoiseSource b;
    private final NoiseSource controlSource;
    private final double boundary;

    private SelectionModule(
        NoiseSource a,
        NoiseSource b,
        NoiseSource controlSource,
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

    public static SelectionModuleBuilder newBuilder() {
        return new SelectionModuleBuilder();
    }

    @NullMarked
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
        public SelectionModuleBuilder setA(NoiseSource noiseSource) {
            this.a = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link SelectionModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        public SelectionModuleBuilder setA(NoiseSourceBuilder noiseSourceBuilder) {
            this.a = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the second noise source for the {@link SelectionModule}.
         *
         * @param noiseSource the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        public SelectionModuleBuilder setB(NoiseSource noiseSource) {
            this.b = noiseSource;
            return this;
        }

        /**
         * Sets the second noise source for the {@link SelectionModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        public SelectionModuleBuilder setB(NoiseSourceBuilder noiseSourceBuilder) {
            this.b = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the control noise source for the {@link SelectionModule}.
         *
         * @param noiseSource the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        public SelectionModuleBuilder setControl(NoiseSource noiseSource) {
            this.controlSource = noiseSource;
            return this;
        }

        /**
         * Sets the control noise source for the {@link SelectionModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        public SelectionModuleBuilder setControl(NoiseSourceBuilder noiseSourceBuilder) {
            this.controlSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the boundary for the {@link SelectionModule}.
         *
         * @param boundary the new boundary for the {@link SelectionModule}.
         * @return {@link SelectionModuleBuilder} this
         */
        public SelectionModuleBuilder setBoundary(double boundary) {
            this.boundary = boundary;
            return this;
        }

        @Override
        public SelectionModule build() {
            if (a == null) {
                throw new IllegalArgumentException("First noise source must be defined.");
            }
            if (b == null) {
                throw new IllegalArgumentException("Second noise source must be defined.");
            }
            if (controlSource == null) {
                throw new IllegalArgumentException("Control noise source must be defined.");
            }
            return new SelectionModule(a, b, controlSource, boundary);
        }
    }
}
