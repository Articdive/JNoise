package de.articdive.jnoise.modules.blend;

import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

public final class BlendModule implements NoiseModule {
    private final NoiseSource a;
    private final NoiseSource b;
    private final NoiseSource controlSource;
    private final Interpolation interpolation;

    private BlendModule(
        @NotNull NoiseSource a,
        @NotNull NoiseSource b,
        @NotNull NoiseSource controlSource,
        Interpolation interpolation
    ) {
        this.a = a;
        this.b = b;
        this.controlSource = controlSource;
        this.interpolation = interpolation;
    }

    @Override
    public double evaluateNoise(double x) {
        return interpolation.lerp(controlSource.evaluateNoise(x), a.evaluateNoise(x), b.evaluateNoise(x));
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return interpolation.lerp(controlSource.evaluateNoise(x, y), a.evaluateNoise(x, y), b.evaluateNoise(x, y));
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return interpolation.lerp(controlSource.evaluateNoise(x, y, z), a.evaluateNoise(x, y, z), b.evaluateNoise(x, y, z));
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return interpolation.lerp(controlSource.evaluateNoise(x, y, z, w), a.evaluateNoise(x, y, z, w), b.evaluateNoise(x, y, z, w));
    }

    @NotNull
    public static BlendModuleBuilder newBuilder() {
        return new BlendModuleBuilder();
    }

    public static final class BlendModuleBuilder implements NoiseSourceBuilder {
        private NoiseSource a;
        private NoiseSource b;
        private NoiseSource controlSource;
        private Interpolation interpolation = Interpolation.LINEAR;

        private BlendModuleBuilder() {
        }

        /**
         * Sets the first noise source for the {@link BlendModule}.
         *
         * @param noiseSource the new noise source for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setA(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            this.a = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link BlendModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setA(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            this.a = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the second noise source for the {@link BlendModule}.
         *
         * @param noiseSource the new noise source for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setB(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.b = noiseSource;
            return this;
        }

        /**
         * Sets the second noise source for the {@link BlendModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setB(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.b = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the control noise source for the {@link BlendModule}.
         *
         * @param noiseSource the new noise source for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setControl(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.controlSource = noiseSource;
            return this;
        }

        /**
         * Sets the control noise source for the {@link BlendModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setControl(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            this.controlSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the Interpolation for the {@link BlendModule}.
         *
         * @param interpolation The new {@link Interpolation} for the {@link BlendModule}.
         * @return {@link BlendModuleBuilder} this
         */
        @NotNull
        public BlendModuleBuilder setInterpolation(Interpolation interpolation) {
            if (interpolation == null) {
                throw new IllegalArgumentException("Interpolation cannot be null.");
            }
            this.interpolation = interpolation;
            return this;
        }


        @NotNull
        public BlendModule build() {
            if (a == null) {
                throw new IllegalArgumentException("First noise source cannot be null.");
            }
            if (b == null) {
                throw new IllegalArgumentException("Second noise source cannot be null.");
            }
            if (controlSource == null) {
                throw new IllegalArgumentException("Control noise source cannot be null.");
            }
            return new BlendModule(a, b, controlSource, interpolation);
        }
    }
}
