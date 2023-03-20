package de.articdive.jnoise.generators.noisegen.random.gaussian;

import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.core.util.HashUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * There are no bounds on this implementation (Asymptotes on both sides of a gaussian distribution).
 * It is recommended to use a clamp.
 *
 * @author Articdive
 */
public final class GaussianWhiteNoiseGenerator implements SeededNoiseGenerator {
    private final long seed;
    private final double mean;
    private final double stddev;

    private GaussianWhiteNoiseGenerator(long seed, double mean, double stddev) {
        this.seed = seed;
        this.mean = mean;
        this.stddev = stddev;
    }

    public double evaluateNoise(double x, long seed) {
        long iX = (long) Math.floor(x);

        Random random = new Random(HashUtil.hash1D(seed, iX));

        return random.nextGaussian(mean, stddev);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);

        Random random = new Random(HashUtil.hash2D(seed, iX, iY));

        return random.nextGaussian(mean, stddev);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);

        Random random = new Random(HashUtil.hash3D(seed, iX, iY, iZ));

        return random.nextGaussian(mean, stddev);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);

        Random random = new Random(HashUtil.hash4D(seed, iX, iY, iZ, iW));

        return random.nextGaussian(mean, stddev);
    }

    @Override
    public double evaluateNoise(double x) {
        return evaluateNoise(x, seed);
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return evaluateNoise(x, y, seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return evaluateNoise(x, y, z, seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return evaluateNoise(x, y, z, w, seed);
    }

    @Override
    public long getSeed() {
        return seed;
    }

    @NotNull
    public static GaussianWhiteNoiseGenerator.GaussianWhiteNoiseBuilder newBuilder() {
        return new GaussianWhiteNoiseBuilder();
    }

    public static final class GaussianWhiteNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;

        private double mean = 0.0;

        private double stddev = 1 / 3.0;

        private GaussianWhiteNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link GaussianWhiteNoiseGenerator}.
         *
         * @param seed the new seed for the {@link GaussianWhiteNoiseGenerator}.
         * @return {@link GaussianWhiteNoiseBuilder} this
         */
        @NotNull
        public GaussianWhiteNoiseGenerator.GaussianWhiteNoiseBuilder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        /**
         * Sets the mean for the gaussian distribution in the {@link GaussianWhiteNoiseGenerator}
         *
         * @param mean the new mean for the {@link GaussianWhiteNoiseGenerator}
         * @return {@link GaussianWhiteNoiseBuilder} this
         */
        public GaussianWhiteNoiseBuilder setMean(double mean) {
            this.mean = mean;
            return this;
        }

        /**
         * Sets the standard deviation for the gaussian distribution in the {@link GaussianWhiteNoiseGenerator}
         *
         * @param stddev the new standard deviation for the {@link GaussianWhiteNoiseGenerator}
         * @return {@link GaussianWhiteNoiseBuilder} this
         */
        public GaussianWhiteNoiseBuilder setStandardDeviation(double stddev) {
            this.stddev = stddev;
            return this;
        }

        @Override
        @NotNull
        public GaussianWhiteNoiseGenerator build() {
            return new GaussianWhiteNoiseGenerator(seed, mean, stddev);
        }
    }
}
