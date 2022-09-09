package de.articdive.jnoise.generators.noisegen.opensimplex;

import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.generators.noise_parameters.simplex_variants.Simplex2DVariant;
import de.articdive.jnoise.generators.noise_parameters.simplex_variants.Simplex3DVariant;
import de.articdive.jnoise.generators.noise_parameters.simplex_variants.Simplex4DVariant;
import org.jetbrains.annotations.NotNull;

/**
 * Uses KdotJPG's the super variant of OpenSimplex2 Noise located at <a href=https://github.com/KdotJPG/OpenSimplex2>https://github.com/KdotJPG/OpenSimplex2</a>.
 * The bounds of SuperOpenSimplex2 Noise are: [-1, 1].
 *
 * @author Articdive
 */
public final class SuperSimplexNoiseGenerator implements SeededNoiseGenerator {
    private final long seed;
    private final Simplex2DVariant variant2D;
    private final Simplex3DVariant variant3D;
    private final Simplex4DVariant variant4D;

    private SuperSimplexNoiseGenerator(
        long seed,
        @NotNull Simplex2DVariant variant2D,
        @NotNull Simplex3DVariant variant3D,
        @NotNull Simplex4DVariant variant4D
    ) {
        this.seed = seed;
        this.variant2D = variant2D;
        this.variant3D = variant3D;
        this.variant4D = variant4D;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        return OpenSimplex2S.noise2(seed, x, 1.0);
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        switch (variant2D) {
            case IMPROVE_X:
                return OpenSimplex2S.noise2_ImproveX(seed, x, y);
            case CLASSIC:
                return OpenSimplex2S.noise2(seed, x, y);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        switch (variant3D) {
            case IMPROVE_XY:
                return OpenSimplex2S.noise3_ImproveXY(seed, x, y, z);
            case IMPROVE_XZ:
                return OpenSimplex2S.noise3_ImproveXZ(seed, x, y, z);
            case CLASSIC:
                return OpenSimplex2S.noise3_Fallback(seed, x, y, z);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        switch (variant4D) {
            case IMPROVE_XY_IMPROVE_ZW:
                return OpenSimplex2S.noise4_ImproveXY_ImproveZW(seed, x, y, z, w);
            case IMPROVE_XYZ_IMPROVE_XY:
                return OpenSimplex2S.noise4_ImproveXYZ_ImproveXY(seed, x, y, z, w);
            case IMPROVE_XYZ_IMPROVE_XZ:
                return OpenSimplex2S.noise4_ImproveXYZ_ImproveXZ(seed, x, y, z, w);
            case IMRPOVE_XYZ:
                return OpenSimplex2S.noise4_ImproveXYZ(seed, x, y, z, w);
            case CLASSIC:
                return OpenSimplex2S.noise4_Fallback(seed, x, y, z, w);
            default:
                throw new IllegalArgumentException();
        }
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
    public static SuperSimplexNoiseBuilder newBuilder() {
        return new SuperSimplexNoiseBuilder();
    }

    public static final class SuperSimplexNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;
        private Simplex2DVariant variant2D = Simplex2DVariant.CLASSIC;
        private Simplex3DVariant variant3D = Simplex3DVariant.CLASSIC;
        private Simplex4DVariant variant4D = Simplex4DVariant.CLASSIC;

        private SuperSimplexNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link SuperSimplexNoiseGenerator}.
         *
         * @param seed the new seed for the {@link SuperSimplexNoiseGenerator}.
         * @return {@link SuperSimplexNoiseBuilder} this
         */
        @NotNull
        public SuperSimplexNoiseBuilder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        /**
         * Sets the variant used for 2D OpenSimplex noise
         *
         * @param variant2D the new {@link Simplex2DVariant} for the {@link SuperSimplexNoiseGenerator}.
         * @return {@link SuperSimplexNoiseBuilder} this
         */
        @NotNull
        public SuperSimplexNoiseBuilder setVariant2D(Simplex2DVariant variant2D) {
            if (variant2D == null) {
                throw new IllegalArgumentException("Simplex 2D Variant cannot be null.");
            }
            this.variant2D = variant2D;
            return this;
        }

        /**
         * Sets the variant used for 3D OpenSimplex noise
         *
         * @param variant3D the new {@link Simplex3DVariant} for the {@link SuperSimplexNoiseGenerator}.
         * @return {@link SuperSimplexNoiseBuilder} this
         */
        @NotNull
        public SuperSimplexNoiseBuilder setVariant3D(Simplex3DVariant variant3D) {
            if (variant3D == null) {
                throw new IllegalArgumentException("Simplex 3D Variant cannot be null.");
            }
            this.variant3D = variant3D;
            return this;
        }

        /**
         * Sets the variant used for 4D OpenSimplex noise
         *
         * @param variant4D the new {@link Simplex4DVariant} for the {@link SuperSimplexNoiseGenerator}.
         * @return {@link SuperSimplexNoiseBuilder} this
         */
        @NotNull
        public SuperSimplexNoiseBuilder setVariant4D(Simplex4DVariant variant4D) {
            if (variant4D == null) {
                throw new IllegalArgumentException("Simplex 4D Variant cannot be null.");
            }
            this.variant4D = variant4D;
            return this;
        }

        @Override
        @NotNull
        public SuperSimplexNoiseGenerator build() {
            return new SuperSimplexNoiseGenerator(seed, variant2D, variant3D, variant4D);
        }
    }
}
