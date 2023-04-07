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

    /**
     * Gets a {@link ConstantNoiseBuilder} to build a {@link ConstantNoiseGenerator}.
     *
     * @return {@link ConstantNoiseBuilder}.
     */
    @NotNull
    public static ConstantNoiseBuilder newBuilder() {
        return new ConstantNoiseBuilder();
    }

    /**
     * Builder for the {@link ConstantNoiseGenerator}.
     */
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
