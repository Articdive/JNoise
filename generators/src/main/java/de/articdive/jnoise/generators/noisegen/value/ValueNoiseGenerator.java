package de.articdive.jnoise.generators.noisegen.value;

import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noise_parameters.interpolation.Interpolation;
import org.jetbrains.annotations.NotNull;

import static de.articdive.jnoise.core.util.HashUtil.W_PRIME;
import static de.articdive.jnoise.core.util.HashUtil.X_PRIME;
import static de.articdive.jnoise.core.util.HashUtil.Y_PRIME;
import static de.articdive.jnoise.core.util.HashUtil.Z_PRIME;

/**
 * The bounds of this Value Noise implementation are: [-1, 1].
 *
 * @author Articdive
 */
public final class ValueNoiseGenerator implements SeededNoiseGenerator {
    private final long seed;
    private final Interpolation interpolation;
    private final FadeFunction fadeFunction;

    private ValueNoiseGenerator(long seed, @NotNull Interpolation interpolation, @NotNull FadeFunction fadeFunction) {
        this.seed = seed;
        this.interpolation = interpolation;
        this.fadeFunction = fadeFunction;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        long iX = (long) Math.floor(x);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
        };
        double[] vals = new double[]{
            evaluateCoord1D((int) iX, seed),
            evaluateCoord1D((int) iX + 1, seed),
        };
        return interpolation.lerp(fractals, vals);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY)
        };
        double[] vals = new double[]{
            evaluateCoord2D((int) iX, (int) iY, seed),
            evaluateCoord2D((int) iX + 1, (int) iY, seed),
            evaluateCoord2D((int) iX, (int) iY + 1, seed),
            evaluateCoord2D((int) iX + 1, (int) iY + 1, seed)
        };
        return interpolation.lerp(fractals, vals);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY),
            fadeFunction.fade(z - iZ)
        };
        double[] vals = new double[]{
            evaluateCoord3D(iX, iY, iZ, seed),
            evaluateCoord3D(iX + 1, iY, iZ, seed),
            evaluateCoord3D(iX, iY + 1, iZ, seed),
            evaluateCoord3D(iX + 1, iY + 1, iZ, seed),
            evaluateCoord3D(iX, iY, iZ + 1, seed),
            evaluateCoord3D(iX + 1, iY, iZ + 1, seed),
            evaluateCoord3D(iX, iY + 1, iZ + 1, seed),
            evaluateCoord3D(iX + 1, iY + 1, iZ + 1, seed)
        };
        return interpolation.lerp(fractals, vals);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        double[] fractals = new double[]{
            fadeFunction.fade(x - iX),
            fadeFunction.fade(y - iY),
            fadeFunction.fade(z - iZ),
            fadeFunction.fade(w - iW)
        };
        double[] vals = new double[]{
            evaluateCoord4D(iX, iY, iZ, iW, seed),
            evaluateCoord4D(iX + 1, iY, iZ, iW, seed),
            evaluateCoord4D(iX, iY + 1, iZ, iW, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ, iW, seed),

            evaluateCoord4D(iX, iY, iZ + 1, iW, seed),
            evaluateCoord4D(iX + 1, iY, iZ + 1, iW, seed),
            evaluateCoord4D(iX, iY + 1, iZ + 1, iW, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW, seed),

            evaluateCoord4D(iX, iY, iZ, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY, iZ, iW + 1, seed),
            evaluateCoord4D(iX, iY + 1, iZ, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ, iW + 1, seed),

            evaluateCoord4D(iX, iY, iZ + 1, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY, iZ + 1, iW + 1, seed),
            evaluateCoord4D(iX, iY + 1, iZ + 1, iW + 1, seed),
            evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW + 1, seed)
        };
        return interpolation.lerp(fractals, vals);
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

    @NotNull
    public static ValueNoiseBuilder newBuilder() {
        return new ValueNoiseBuilder();
    }

    public static final class ValueNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;
        private Interpolation interpolation = Interpolation.LINEAR;
        private FadeFunction fadeFunction = FadeFunction.IMPROVED_PERLIN_NOISE;

        private ValueNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link ValueNoiseGenerator}.
         *
         * @param seed the new seed for the {@link ValueNoiseGenerator}.
         * @return {@link ValueNoiseBuilder} this
         */
        @NotNull
        public ValueNoiseBuilder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        /**
         * Sets the Interpolation for the {@link ValueNoiseGenerator}.
         *
         * @param interpolation The new {@link Interpolation} for the {@link ValueNoiseGenerator}.
         * @return {@link ValueNoiseBuilder} this
         */
        @NotNull
        public ValueNoiseBuilder setInterpolation(Interpolation interpolation) {
            if (interpolation == null) {
                throw new IllegalArgumentException("Interpolation cannot be null.");
            }
            this.interpolation = interpolation;
            return this;
        }

        /**
         * Sets the FadeFunction for the {@link ValueNoiseGenerator}.
         *
         * @param fadeFunction the new {@link FadeFunction} for the {@link ValueNoiseGenerator}.
         * @return {@link ValueNoiseBuilder} this
         */
        @NotNull
        public ValueNoiseBuilder setFadeFunction(FadeFunction fadeFunction) {
            if (fadeFunction == null) {
                throw new IllegalArgumentException("Fade function cannot be null.");
            }
            this.fadeFunction = fadeFunction;
            return this;
        }

        @Override
        @NotNull
        public ValueNoiseGenerator build() {
            return new ValueNoiseGenerator(seed, interpolation, fadeFunction);
        }
    }
}
