package de.articdive.jnoise.modules.octavation;

import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.modules.octavation.fractal_functions.FractalFunction;
import org.jetbrains.annotations.NotNull;

public final class OctavationModule implements NoiseModule {
    private final NoiseSource noiseSource;
    private final int octaves;
    private final double persistence;
    private final double lacunarity;
    private final FractalFunction fractalFunction;
    private final boolean incrementSeed;
    // Special values
    private final double fractalBounding;

    private OctavationModule(
        @NotNull NoiseSource noiseSource,
        int octaves,
        double persistence,
        double lacunarity,
        @NotNull FractalFunction fractalFunction,
        boolean incrementSeed
    ) {
        this.noiseSource = noiseSource;
        this.octaves = octaves;
        this.persistence = persistence;
        this.lacunarity = lacunarity;
        this.fractalFunction = fractalFunction;
        this.incrementSeed = incrementSeed;
        double fractalBounding = 0;
        double amplitude = 1;
        for (int i = 0; i < this.octaves; i++) {
            fractalBounding += (amplitude *= this.persistence);
        }
        this.fractalBounding = fractalBounding;
    }

    @Override
    public double evaluateNoise(double x) {
        double amplitude = this.persistence;
        double output;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            output = fractalFunction.fractalize(sng.evaluateNoise(x, seed)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(sng.evaluateNoise(x *= lacunarity, ++seed)) * (amplitude *= this.persistence);
            }
        } else {
            output = fractalFunction.fractalize(noiseSource.evaluateNoise(x)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(noiseSource.evaluateNoise(x *= lacunarity)) * (amplitude *= this.persistence);
            }
        }
        return output / fractalBounding;
    }

    @Override
    public double evaluateNoise(double x, double y) {
        double amplitude = this.persistence;
        double output;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            output = fractalFunction.fractalize(sng.evaluateNoise(x, y, seed)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(sng.evaluateNoise(x *= lacunarity, y *= lacunarity, ++seed)) * (amplitude *= this.persistence);
            }
        } else {
            output = fractalFunction.fractalize(noiseSource.evaluateNoise(x, y)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(noiseSource.evaluateNoise(x *= lacunarity, y *= lacunarity)) * (amplitude *= this.persistence);
            }
        }
        return output / fractalBounding;
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        double amplitude = this.persistence;
        double output;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            output = fractalFunction.fractalize(sng.evaluateNoise(x, y, z, seed)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(sng.evaluateNoise(x *= lacunarity, y *= lacunarity, z *= lacunarity, ++seed)) * (amplitude *= this.persistence);
            }
        } else {
            output = fractalFunction.fractalize(noiseSource.evaluateNoise(x, y, z)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(noiseSource.evaluateNoise(x *= lacunarity, y *= lacunarity, z *= lacunarity)) * (amplitude *= this.persistence);
            }
        }
        return output / fractalBounding;
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        double amplitude = this.persistence;
        double output;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            output = fractalFunction.fractalize(sng.evaluateNoise(x, y, z, w, seed)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(sng.evaluateNoise(x *= lacunarity, y *= lacunarity, z *= lacunarity, w *= lacunarity, ++seed)) * (amplitude *= this.persistence);
            }
        } else {
            output = fractalFunction.fractalize(noiseSource.evaluateNoise(x, y, z, w)) * amplitude;
            for (int i = 1; i < this.octaves; i++) {
                output += fractalFunction.fractalize(noiseSource.evaluateNoise(x *= lacunarity, y *= lacunarity, z *= lacunarity, w *= lacunarity)) * (amplitude *= this.persistence);
            }
        }
        return output / fractalBounding;
    }

    @NotNull
    public static OctavationModuleBuilder newBuilder() {
        return new OctavationModuleBuilder();
    }

    public static final class OctavationModuleBuilder implements NoiseSourceBuilder {
        private NoiseSource noiseSource;
        private int octaves = 1;
        private double persistence = 1;
        private double lacunarity = 1;
        private FractalFunction fractalFunction = FractalFunction.FBM;
        private boolean incrementSeed = false;

        private OctavationModuleBuilder() {
        }

        /**
         * Sets the noise source for the {@link OctavationModule}.
         *
         * @param noiseSource the new noise source for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setNoiseSource(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Noise source cannot be null.");
            }
            this.noiseSource = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link OctavationModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setNoiseSource(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Noise source cannot be null.");
            }
            this.noiseSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the amount of octaves for the {@link OctavationModule}.
         *
         * @param octaves the new amount of octaves for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setOctaves(int octaves) {
            if (octaves <= 0) {
                throw new IllegalArgumentException("The amount of octaves must be a non-zero positive integer.");
            }
            this.octaves = octaves;
            return this;
        }

        /**
         * Sets the persistence for the {@link OctavationModule}.
         *
         * @param persistence the new persistence for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setPersistence(double persistence) {
            if (persistence <= 0) {
                throw new IllegalArgumentException("Persistence must be a non-zero positive value.");
            }
            this.persistence = persistence;
            return this;
        }

        /**
         * Sets the lacunarity for the {@link OctavationModule}.
         *
         * @param lacunarity the new lacunarity for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setLacunarity(double lacunarity) {
            if (lacunarity <= 0) {
                throw new IllegalArgumentException("Lacunarity must be a non-zero positive value.");
            }
            this.lacunarity = lacunarity;
            return this;
        }

        /**
         * Sets the FractalFunction for the {@link OctavationModule}.
         *
         * @param fractalFunction the new {@link FractalFunction} for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setFractalFunction(FractalFunction fractalFunction) {
            if (fractalFunction == null) {
                throw new IllegalArgumentException("Fractal function cannot be null.");
            }
            this.fractalFunction = fractalFunction;
            return this;
        }

        /**
         * Should the Noise Generator increment the seed with each octave?
         *
         * @param incrementSeed true if seed should increment each octave for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        @NotNull
        public OctavationModuleBuilder setIncrementSeed(boolean incrementSeed) {
            this.incrementSeed = incrementSeed;
            return this;
        }

        @NotNull
        public OctavationModule build() {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Noise source has not been defined.");
            }
            if (incrementSeed && !(noiseSource instanceof SeededNoiseGenerator)) {
                throw new IllegalArgumentException("Noise source does not have a seed, hence incrementSeed cannot be true!");
            }
            return new OctavationModule(noiseSource, octaves, persistence, lacunarity, fractalFunction, incrementSeed);
        }
    }
}