package de.articdive.jnoise.generators.noisegen.perlin;

import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.core.util.HashUtil;
import de.articdive.jnoise.core.util.vectors.Vector1D;
import de.articdive.jnoise.core.util.vectors.Vector2D;
import de.articdive.jnoise.core.util.vectors.Vector3D;
import de.articdive.jnoise.core.util.vectors.Vector4D;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noise_parameters.interpolation.Interpolation;
import org.jetbrains.annotations.NotNull;

/**
 * Based on Ken Perlin's implementation of Perlin Noise.
 * The bounds of this Perlin Noise implementation are: [-sqrt(n/2), sqrt(n/2)] (n being the dimension).
 *
 * @author Articdive
 */
public final class PerlinNoiseGenerator implements SeededNoiseGenerator {
    private static final Vector1D[] VECTOR_1D = new Vector1D[]{
        new Vector1D(1), new Vector1D(-1)
    };
    private static final Vector2D[] VECTOR_2D = new Vector2D[]{
        new Vector2D(1, 1), new Vector2D(-1, 1), new Vector2D(1, -1), new Vector2D(-1, -1),

        new Vector2D(0, 1), new Vector2D(0, -1), new Vector2D(1, 0), new Vector2D(-1, 0)
    };
    private static final Vector3D[] VECTOR_3D = new Vector3D[]{
        new Vector3D(1, 1, 1), new Vector3D(-1, 1, 1),
        new Vector3D(1, -1, 1), new Vector3D(-1, -1, 1),
        new Vector3D(1, 1, -1), new Vector3D(-1, 1, -1),
        new Vector3D(1, -1, -1), new Vector3D(-1, -1, -1),

        new Vector3D(0, 1, 1), new Vector3D(0, -1, 1), new Vector3D(0, 1, -1), new Vector3D(0, -1, -1),
        new Vector3D(1, 0, 1), new Vector3D(-1, 0, 1), new Vector3D(1, 0, -1), new Vector3D(-1, 0, -1),
        new Vector3D(1, 1, 0), new Vector3D(-1, 1, 0), new Vector3D(1, -1, 0), new Vector3D(-1, -1, 0)
    };
    private static final Vector4D[] VECTOR_4D = new Vector4D[]{
        new Vector4D(1, 1, 1, 1), new Vector4D(1, 1, -1, 1),
        new Vector4D(1, -1, 1, 1), new Vector4D(1, -1, -1, 1),
        new Vector4D(-1, 1, 1, 1), new Vector4D(-1, 1, -1, 1),
        new Vector4D(-1, -1, 1, 1), new Vector4D(-1, -1, -1, 1),
        new Vector4D(1, 1, 1, -1), new Vector4D(1, 1, -1, -1),
        new Vector4D(1, -1, 1, -1), new Vector4D(1, -1, -1, -1),
        new Vector4D(-1, 1, 1, -1), new Vector4D(-1, 1, -1, -1),
        new Vector4D(-1, -1, 1, -1), new Vector4D(-1, -1, -1, -1),

        new Vector4D(0, 1, 1, 1), new Vector4D(0, 1, -1, 1),
        new Vector4D(0, -1, 1, 1), new Vector4D(0, -1, -1, 1),
        new Vector4D(0, 1, 1, -1), new Vector4D(0, 1, -1, -1),
        new Vector4D(0, -1, 1, -1), new Vector4D(0, -1, -1, -1),
        new Vector4D(1, 0, 1, 1), new Vector4D(1, 0, -1, 1),
        new Vector4D(-1, 0, 1, 1), new Vector4D(-1, 0, -1, 1),
        new Vector4D(1, 0, 1, -1), new Vector4D(1, 0, -1, -1),
        new Vector4D(-1, 0, 1, -1), new Vector4D(-1, 0, -1, -1),
        new Vector4D(1, 1, 0, 1), new Vector4D(1, -1, 0, 1),
        new Vector4D(-1, 1, 0, 1), new Vector4D(-1, -1, 0, 1),
        new Vector4D(1, 1, 0, -1), new Vector4D(1, -1, 0, -1),
        new Vector4D(-1, 1, 0, -1), new Vector4D(-1, -1, 0, -1),
        new Vector4D(1, 1, 1, 0), new Vector4D(1, -1, 1, 0),
        new Vector4D(-1, 1, 1, 0), new Vector4D(-1, -1, 1, 0),
        new Vector4D(1, 1, -1, 0), new Vector4D(1, -1, -1, 0),
        new Vector4D(-1, 1, -1, 0), new Vector4D(-1, -1, -1, 0)
    };

    private final long seed;
    private final Interpolation interpolation;
    private final FadeFunction fadeFunction;

    private PerlinNoiseGenerator(long seed, @NotNull Interpolation interpolation, @NotNull FadeFunction fadeFunction) {
        this.seed = seed;
        this.interpolation = interpolation;
        this.fadeFunction = fadeFunction;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        // Vector1D distanceVector = new Vector2D(x - iX);
        x -= iX;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
        };
        double[] dots = new double[]{
            // (VECTOR_1D.length - 1) = 1.
            new Vector1D(x).dot(VECTOR_1D[HashUtil.hash1D(seed, iX) & 1]),
            new Vector1D(x - 1).dot(VECTOR_1D[HashUtil.hash1D(seed, iX + 1) & 1])
        };

        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        // Vector2D distanceVector = new Vector2D(x - iX,y - iY);
        x -= iX;
        y -= iY;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
            fadeFunction.fade(y)
        };
        double[] dots = new double[]{
            // (VECTOR_2D.length - 1) = 7.
            new Vector2D(x, y).dot(VECTOR_2D[HashUtil.hash2D(seed, iX, iY) & 7]),
            new Vector2D(x - 1, y).dot(VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY) & 7]),
            new Vector2D(x, y - 1).dot(VECTOR_2D[HashUtil.hash2D(seed, iX, iY + 1) & 7]),
            new Vector2D(x - 1, y - 1).dot(VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY + 1) & 7])
        };

        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
            fadeFunction.fade(y),
            fadeFunction.fade(z)
        };
        double[] dots = new double[]{
            // (VECTOR_3D.length - 1) = 19.
            new Vector3D(x, y, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY, iZ) & 19]),
            new Vector3D(x - 1, y, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY, iZ) & 19]),
            new Vector3D(x, y - 1, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY + 1, iZ) & 19]),
            new Vector3D(x - 1, y - 1, z)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY + 1, iZ) & 19]),
            new Vector3D(x, y, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY, iZ + 1) & 19]),
            new Vector3D(x - 1, y, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY, iZ + 1) & 19]),
            new Vector3D(x, y - 1, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX, iY + 1, iZ + 1) & 19]),
            new Vector3D(x - 1, y - 1, z - 1)
                .dot(VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY + 1, iZ + 1) & 19])
        };
        return interpolation.lerp(fractals, dots);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        // Find unit square
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        w -= iW;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        double[] fractals = new double[]{
            fadeFunction.fade(x),
            fadeFunction.fade(y),
            fadeFunction.fade(z),
            fadeFunction.fade(w)
        };
        double[] dots = new double[]{
            // (VECTOR_4D.length - 1) = 19.
            new Vector4D(x, y, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ, iW) & 47]),
            new Vector4D(x - 1, y, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ, iW) & 47]),
            new Vector4D(x, y - 1, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ, iW) & 47]),
            new Vector4D(x - 1, y - 1, z, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW) & 47]),
            new Vector4D(x, y, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ + 1, iW) & 47]),
            new Vector4D(x - 1, y, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW) & 47]),
            new Vector4D(x, y - 1, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW) & 47]),
            new Vector4D(x - 1, y - 1, z - 1, w)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW) & 47]),
            new Vector4D(x, y, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ, iW + 1) & 47]),
            new Vector4D(x - 1, y, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ, iW + 1) & 47]),
            new Vector4D(x, y - 1, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ, iW + 1) & 47]),
            new Vector4D(x - 1, y - 1, z, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW + 1) & 47]),
            new Vector4D(x, y, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ + 1, iW + 1) & 47]),
            new Vector4D(x - 1, y, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW + 1) & 47]),
            new Vector4D(x, y - 1, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW + 1) & 47]),
            new Vector4D(x - 1, y - 1, z - 1, w - 1)
                .dot(VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW + 1) & 47])
        };
        return interpolation.lerp(fractals, dots);
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
    public static PerlinNoiseBuilder newBuilder() {
        return new PerlinNoiseBuilder();
    }

    public static final class PerlinNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;
        private Interpolation interpolation = Interpolation.LINEAR;
        private FadeFunction fadeFunction = FadeFunction.QUINTIC_POLY;

        private PerlinNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link PerlinNoiseGenerator}.
         *
         * @param seed the new seed for the {@link PerlinNoiseGenerator}.
         * @return {@link PerlinNoiseBuilder} this
         */
        @NotNull
        public PerlinNoiseBuilder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        /**
         * Sets the Interpolation for the {@link PerlinNoiseGenerator}.
         *
         * @param interpolation The new {@link Interpolation} for the {@link PerlinNoiseGenerator}.
         * @return {@link PerlinNoiseBuilder} this
         */
        @NotNull
        public PerlinNoiseBuilder setInterpolation(Interpolation interpolation) {
            if (interpolation == null) {
                throw new IllegalArgumentException("Interpolation cannot be null.");
            }
            this.interpolation = interpolation;
            return this;
        }

        /**
         * Sets the FadeFunction for the {@link PerlinNoiseGenerator}.
         *
         * @param fadeFunction the new {@link FadeFunction} for the {@link PerlinNoiseGenerator}.
         * @return {@link PerlinNoiseBuilder} this
         */
        @NotNull
        public PerlinNoiseBuilder setFadeFunction(FadeFunction fadeFunction) {
            if (fadeFunction == null) {
                throw new IllegalArgumentException("Fade function cannot be null.");
            }
            this.fadeFunction = fadeFunction;
            return this;
        }

        @Override
        @NotNull
        public PerlinNoiseGenerator build() {
            return new PerlinNoiseGenerator(seed, interpolation, fadeFunction);
        }
    }
}
