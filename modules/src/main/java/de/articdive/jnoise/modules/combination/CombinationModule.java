package de.articdive.jnoise.modules.combination;

import de.articdive.jnoise.core.api.functions.Combiner;
import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class CombinationModule implements NoiseModule {
    private final NoiseSource a;
    private final NoiseSource b;
    private final Combiner combiner;

    private CombinationModule(
        NoiseSource a,
        NoiseSource b,
        Combiner combiner
    ) {
        this.a = a;
        this.b = b;
        this.combiner = combiner;
    }

    @Override
    public double evaluateNoise(double x) {
        return combiner.applyTo(a.evaluateNoise(x), b.evaluateNoise(x));
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return combiner.applyTo(a.evaluateNoise(x, y), b.evaluateNoise(x, y));
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return combiner.applyTo(a.evaluateNoise(x, y, z), b.evaluateNoise(x, y, z));
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return combiner.applyTo(a.evaluateNoise(x, y, z, w), b.evaluateNoise(x, y, z, w));
    }

    public static CombinationModuleBuilder newBuilder() {
        return new CombinationModuleBuilder();
    }

    @NullMarked
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
        public CombinationModuleBuilder setA(NoiseSource noiseSource) {
            this.a = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link CombinationModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        public CombinationModuleBuilder setA(NoiseSourceBuilder noiseSourceBuilder) {
            this.a = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the second noise source for the {@link CombinationModule}.
         *
         * @param noiseSource the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        public CombinationModuleBuilder setB(NoiseSource noiseSource) {
            this.b = noiseSource;
            return this;
        }

        /**
         * Sets the second noise source for the {@link CombinationModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        public CombinationModuleBuilder setB(NoiseSourceBuilder noiseSourceBuilder) {
            this.b = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the combiner for the {@link CombinationModule}.
         *
         * @param combiner the new {@link Combiner} for the {@link CombinationModule}.
         * @return {@link CombinationModuleBuilder} this
         */
        public CombinationModuleBuilder setCombiner(Combiner combiner) {
            this.combiner = combiner;
            return this;
        }

        @Override
        public CombinationModule build() {
            if (a == null) {
                throw new IllegalArgumentException("First noise source must be defined.");
            }
            if (b == null) {
                throw new IllegalArgumentException("Second noise source must be defined.");
            }
            return new CombinationModule(a, b, combiner);
        }
    }
}