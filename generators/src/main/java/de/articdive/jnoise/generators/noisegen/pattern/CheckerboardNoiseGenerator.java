package de.articdive.jnoise.generators.noisegen.pattern;

import de.articdive.jnoise.core.api.noisegen.NoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * A noise generator that returns a checkerboard of unit-sized blocks alternating between 0.0 and 1.0.
 * Useful when combined with the scale transformer, as you can create blocks of any size.
 *
 * @author Articdive
 */
public final class CheckerboardNoiseGenerator implements NoiseGenerator {
    private CheckerboardNoiseGenerator() {

    }

    @Override
    public double evaluateNoise(double x) {
        long iX = (long) Math.floor(x);
        return (iX & 1) != 0 ? 0.0 : 1.0;
    }

    @Override
    public double evaluateNoise(double x, double y) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        return ((iX & 1) ^ (iY & 1)) != 0 ? 0.0 : 1.0;
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        return ((iX & 1) ^ (iY & 1) ^ (iZ & 1)) != 0 ? 0.0 : 1.0;
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        return ((iX & 1) ^ (iY & 1) ^ (iZ & 1) ^ (iW & 1)) != 0 ? 0.0 : 1.0;
    }

    @NotNull
    public static CheckerboardNoiseBuilder newBuilder() {
        return new CheckerboardNoiseBuilder();
    }

    public static final class CheckerboardNoiseBuilder implements NoiseSourceBuilder {

        private CheckerboardNoiseBuilder() {

        }

        @Override
        @NotNull
        public CheckerboardNoiseGenerator build() {
            return new CheckerboardNoiseGenerator();
        }
    }
}
