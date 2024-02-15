package de.articdive.jnoise.generators.noisegen.perlin;

import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.core.util.HashUtil;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import org.jspecify.annotations.NullMarked;

import static de.articdive.jnoise.core.util.MathUtil.dot2D;
import static de.articdive.jnoise.core.util.MathUtil.dot3D;
import static de.articdive.jnoise.core.util.MathUtil.dot4D;

/**
 * Based on Ken Perlin's implementation of Perlin Noise.
 * The bounds of this Perlin Noise implementation are: [-sqrt(n/2), sqrt(n/2)] (n being the dimension).
 *
 * @author Articdive
 */
@NullMarked
public final class PerlinNoiseGenerator implements SeededNoiseGenerator {
    private static final double[] VECTOR_1D = new double[]{
        1, -1
    };
    private static final double[][] VECTOR_2D = new double[][]{
        new double[]{1, 1}, new double[]{-1, 1}, new double[]{1, -1}, new double[]{-1, -1},

        new double[]{0, 1}, new double[]{0, -1}, new double[]{1, 0}, new double[]{-1, 0}
    };
    private static final double[][] VECTOR_3D = new double[][]{
        new double[]{1, 1, 1}, new double[]{-1, 1, 1},
        new double[]{1, -1, 1}, new double[]{-1, -1, 1},
        new double[]{1, 1, -1}, new double[]{-1, 1, -1},
        new double[]{1, -1, -1}, new double[]{-1, -1, -1},

        new double[]{0, 1, 1}, new double[]{0, -1, 1}, new double[]{0, 1, -1}, new double[]{0, -1, -1},
        new double[]{1, 0, 1}, new double[]{-1, 0, 1}, new double[]{1, 0, -1}, new double[]{-1, 0, -1},
        new double[]{1, 1, 0}, new double[]{-1, 1, 0}, new double[]{1, -1, 0}, new double[]{-1, -1, 0}
    };
    private static final double[][] VECTOR_4D = new double[][]{
        new double[]{1, 1, 1, 1}, new double[]{1, 1, -1, 1},
        new double[]{1, -1, 1, 1}, new double[]{1, -1, -1, 1},
        new double[]{-1, 1, 1, 1}, new double[]{-1, 1, -1, 1},
        new double[]{-1, -1, 1, 1}, new double[]{-1, -1, -1, 1},
        new double[]{1, 1, 1, -1}, new double[]{1, 1, -1, -1},
        new double[]{1, -1, 1, -1}, new double[]{1, -1, -1, -1},
        new double[]{-1, 1, 1, -1}, new double[]{-1, 1, -1, -1},
        new double[]{-1, -1, 1, -1}, new double[]{-1, -1, -1, -1},

        new double[]{0, 1, 1, 1}, new double[]{0, 1, -1, 1},
        new double[]{0, -1, 1, 1}, new double[]{0, -1, -1, 1},
        new double[]{0, 1, 1, -1}, new double[]{0, 1, -1, -1},
        new double[]{0, -1, 1, -1}, new double[]{0, -1, -1, -1},
        new double[]{1, 0, 1, 1}, new double[]{1, 0, -1, 1},
        new double[]{-1, 0, 1, 1}, new double[]{-1, 0, -1, 1},
        new double[]{1, 0, 1, -1}, new double[]{1, 0, -1, -1},
        new double[]{-1, 0, 1, -1}, new double[]{-1, 0, -1, -1},
        new double[]{1, 1, 0, 1}, new double[]{1, -1, 0, 1},
        new double[]{-1, 1, 0, 1}, new double[]{-1, -1, 0, 1},
        new double[]{1, 1, 0, -1}, new double[]{1, -1, 0, -1},
        new double[]{-1, 1, 0, -1}, new double[]{-1, -1, 0, -1},
        new double[]{1, 1, 1, 0}, new double[]{1, -1, 1, 0},
        new double[]{-1, 1, 1, 0}, new double[]{-1, -1, 1, 0},
        new double[]{1, 1, -1, 0}, new double[]{1, -1, -1, 0},
        new double[]{-1, 1, -1, 0}, new double[]{-1, -1, -1, 0}
    };

    private final long seed;
    private final Interpolation interpolation;
    private final FadeFunction fadeFunction;

    private PerlinNoiseGenerator(long seed, Interpolation interpolation, FadeFunction fadeFunction) {
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
            x * VECTOR_1D[HashUtil.hash1D(seed, iX) & 1],
            (x - 1) * VECTOR_1D[HashUtil.hash1D(seed, iX + 1) & 1]
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
            dot2D(new double[]{x, y}, VECTOR_2D[HashUtil.hash2D(seed, iX, iY) & 7]),
            dot2D(new double[]{x - 1, y}, VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY) & 7]),
            dot2D(new double[]{x, y - 1}, VECTOR_2D[HashUtil.hash2D(seed, iX, iY + 1) & 7]),
            dot2D(new double[]{x - 1, y - 1}, VECTOR_2D[HashUtil.hash2D(seed, iX + 1, iY + 1) & 7]),
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
            dot3D(new double[]{x, y, z}, VECTOR_3D[HashUtil.hash3D(seed, iX, iY, iZ) & 19]),
            dot3D(new double[]{x - 1, y, z}, VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY, iZ) & 19]),
            dot3D(new double[]{x, y - 1, z}, VECTOR_3D[HashUtil.hash3D(seed, iX, iY + 1, iZ) & 19]),
            dot3D(new double[]{x - 1, y - 1, z}, VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY + 1, iZ) & 19]),
            dot3D(new double[]{x, y, z - 1}, VECTOR_3D[HashUtil.hash3D(seed, iX, iY, iZ + 1) & 19]),
            dot3D(new double[]{x - 1, y, z - 1}, VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY, iZ + 1) & 19]),
            dot3D(new double[]{x, y - 1, z - 1}, VECTOR_3D[HashUtil.hash3D(seed, iX, iY + 1, iZ + 1) & 19]),
            dot3D(new double[]{x - 1, y - 1, z - 1}, VECTOR_3D[HashUtil.hash3D(seed, iX + 1, iY + 1, iZ + 1) & 19])
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
            dot4D(new double[]{x, y, z, w}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ, iW) & 47]),
            dot4D(new double[]{x - 1, y, z, w}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ, iW) & 47]),
            dot4D(new double[]{x, y - 1, z, w}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ, iW) & 47]),
            dot4D(new double[]{x - 1, y - 1, z, w}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW) & 47]),
            dot4D(new double[]{x, y, z - 1, w}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ + 1, iW) & 47]),
            dot4D(new double[]{x - 1, y, z - 1, w}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW) & 47]),
            dot4D(new double[]{x, y - 1, z - 1, w}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW) & 47]),
            dot4D(new double[]{x - 1, y - 1, z - 1, w}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW) & 47]),
            dot4D(new double[]{x, y, z, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ, iW + 1) & 47]),
            dot4D(new double[]{x - 1, y, z, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ, iW + 1) & 47]),
            dot4D(new double[]{x, y - 1, z, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ, iW + 1) & 47]),
            dot4D(new double[]{x - 1, y - 1, z, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW + 1) & 47]),
            dot4D(new double[]{x, y, z - 1, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY, iZ + 1, iW + 1) & 47]),
            dot4D(new double[]{x - 1, y, z - 1, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW + 1) & 47]),
            dot4D(new double[]{x, y - 1, z - 1, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW + 1) & 47]),
            dot4D(new double[]{x - 1, y - 1, z - 1, w - 1}, VECTOR_4D[HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW + 1) & 47])
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

    /**
     * Gets a {@link PerlinNoiseBuilder} to build a {@link PerlinNoiseGenerator}.
     *
     * @return {@link PerlinNoiseBuilder}.
     */
    public static PerlinNoiseBuilder newBuilder() {
        return new PerlinNoiseBuilder();
    }

    /**
     * Builder for the {@link PerlinNoiseGenerator}.
     */
    @NullMarked
    public static final class PerlinNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;
        private de.articdive.jnoise.core.api.functions.Interpolation interpolation = de.articdive.jnoise.core.api.functions.Interpolation.LINEAR;
        private FadeFunction fadeFunction = FadeFunction.QUINTIC_POLY;

        private PerlinNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link PerlinNoiseGenerator}.
         *
         * @param seed the new seed for the {@link PerlinNoiseGenerator}.
         * @return {@link PerlinNoiseBuilder} this
         */
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
        public PerlinNoiseBuilder setInterpolation(Interpolation interpolation) {
            this.interpolation = interpolation;
            return this;
        }

        /**
         * Sets the FadeFunction for the {@link PerlinNoiseGenerator}.
         *
         * @param fadeFunction the new {@link FadeFunction} for the {@link PerlinNoiseGenerator}.
         * @return {@link PerlinNoiseBuilder} this
         */
        public PerlinNoiseBuilder setFadeFunction(FadeFunction fadeFunction) {
            this.fadeFunction = fadeFunction;
            return this;
        }

        @Override
        public PerlinNoiseGenerator build() {
            return new PerlinNoiseGenerator(seed, interpolation, fadeFunction);
        }
    }
}
