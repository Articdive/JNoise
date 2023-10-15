package de.articdive.jnoise.generators.noisegen.constant;

import de.articdive.jnoise.core.api.noisegen.NoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jspecify.annotations.NullMarked;

/**
 * A noise generator that always returns the constant specified.
 *
 * @author Articdive
 */
@NullMarked
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
    public static ConstantNoiseBuilder newBuilder() {
        return new ConstantNoiseBuilder();
    }

    /**
     * Builder for the {@link ConstantNoiseGenerator}.
     */
    @NullMarked
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
        public ConstantNoiseBuilder setConstant(double constant) {
            this.constant = constant;
            return this;
        }

        @Override
        public ConstantNoiseGenerator build() {
            return new ConstantNoiseGenerator(constant);
        }
    }
}
