package de.articdive.jnoise.generators.noisegen.random.white;

import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

import static de.articdive.jnoise.core.util.HashUtil.W_PRIME;
import static de.articdive.jnoise.core.util.HashUtil.X_PRIME;
import static de.articdive.jnoise.core.util.HashUtil.Y_PRIME;
import static de.articdive.jnoise.core.util.HashUtil.Z_PRIME;

/**
 * The bounds of this White Noise implementation are: [-1, 1].
 *
 * @author Articdive
 */
public final class WhiteNoiseGenerator implements SeededNoiseGenerator {
    private final long seed;

    private WhiteNoiseGenerator(long seed) {
        this.seed = seed;
    }

    public double evaluateNoise(double x, long seed) {
        return evaluateCoord1D((long) Math.floor(x), seed);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        return evaluateCoord2D((long) Math.floor(x), (long) Math.floor(y), seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        return evaluateCoord3D((long) Math.floor(x), (long) Math.floor(y), (long) Math.floor(z), seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        return evaluateCoord4D((long) Math.floor(x), (long) Math.floor(y), (long) Math.floor(z), (long) Math.floor(w), seed);
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

    private static double evaluateCoord1D(long x, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));

        return (n * n * n * 60493) / 2147483648.0;
    }

    private static double evaluateCoord2D(long x, long y, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));
        n ^= Y_PRIME * y;

        return (n * n * n * 60493) / 2147483648.0;
    }

    private static double evaluateCoord3D(long x, long y, long z, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;

        return (n * n * n * 60493) / 2147483648.0;
    }

    private static double evaluateCoord4D(long x, long y, long z, long w, long seed) {
        int n = (int) ((seed) ^ (X_PRIME * (x)));
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;
        n ^= W_PRIME * w;

        return (n * n * n * 60493) / 2147483648.0;
    }

    /**
     * Gets a {@link WhiteNoiseBuilder} to build a {@link WhiteNoiseGenerator}.
     *
     * @return {@link WhiteNoiseBuilder}.
     */
    @NotNull
    public static WhiteNoiseBuilder newBuilder() {
        return new WhiteNoiseBuilder();
    }

    /**
     * Builder for the {@link WhiteNoiseGenerator}.
     */
    public static final class WhiteNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;

        private WhiteNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link WhiteNoiseGenerator}.
         *
         * @param seed the new seed for the {@link WhiteNoiseGenerator}.
         * @return {@link WhiteNoiseBuilder} this
         */
        @NotNull
        public WhiteNoiseBuilder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        @Override
        @NotNull
        public WhiteNoiseGenerator build() {
            return new WhiteNoiseGenerator(seed);
        }
    }
}
