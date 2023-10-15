package de.articdive.jnoise.modules.octavation;

import de.articdive.jnoise.core.api.modules.NoiseModule;
import de.articdive.jnoise.core.api.noisegen.SeededNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.modules.octavation.fractal_functions.FractalFunction;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class OctavationModule implements NoiseModule {
    private final NoiseSource noiseSource;
    private final int octaves;
    private final double gain;
    private final double lacunarity;
    private final FractalFunction fractalFunction;
    private final boolean incrementSeed;
    // Special values
    private final double fractalBounding;

    private OctavationModule(
        NoiseSource noiseSource,
        int octaves,
        double gain,
        double lacunarity,
        FractalFunction fractalFunction,
        boolean incrementSeed
    ) {
        this.noiseSource = noiseSource;
        this.octaves = octaves;
        this.gain = gain;
        this.lacunarity = lacunarity;
        this.fractalFunction = fractalFunction;
        this.incrementSeed = incrementSeed;
        double fractalBounding = 0;
        double amplitude = this.gain;
        for (int i = 0; i < this.octaves; i++) {
            fractalBounding += amplitude;
            amplitude *= this.gain;
        }
        this.fractalBounding = fractalBounding;
    }

    @Override
    public double evaluateNoise(double x) {
        double amplitude = gain;
        double frequency = 1;
        double output = 0;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(sng.evaluateNoise(frequency * x, seed++));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        } else {
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(noiseSource.evaluateNoise(frequency * x));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        }
        return output / fractalBounding;
    }

    @Override
    public double evaluateNoise(double x, double y) {
        double amplitude = gain;
        double frequency = 1;
        double output = 0;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(sng.evaluateNoise(frequency * x, frequency * y, seed++));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        } else {
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(noiseSource.evaluateNoise(frequency * x, frequency * y));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        }
        return output / fractalBounding;
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        double amplitude = gain;
        double frequency = 1;
        double output = 0;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(sng.evaluateNoise(frequency * x, frequency * y, frequency * z, seed++));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        } else {
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(noiseSource.evaluateNoise(frequency * x, frequency * y, frequency * z));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        }
        return output / fractalBounding;
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        double amplitude = gain;
        double frequency = 1;
        double output = 0;
        if (incrementSeed) {
            SeededNoiseGenerator sng = (SeededNoiseGenerator) noiseSource;
            long seed = sng.getSeed();
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(sng.evaluateNoise(frequency * x, frequency * y, frequency * z, frequency * w, seed++));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        } else {
            for (int i = 0; i < octaves; i++) {
                output += amplitude * fractalFunction.fractalize(noiseSource.evaluateNoise(frequency * x, frequency * y, frequency * z, frequency * w));
                frequency *= lacunarity;
                amplitude *= gain;
            }
        }
        return output / fractalBounding;
    }

    public static OctavationModuleBuilder newBuilder() {
        return new OctavationModuleBuilder();
    }

    @NullMarked
    public static final class OctavationModuleBuilder implements NoiseSourceBuilder {
        private NoiseSource noiseSource;
        private int octaves = 4;
        private double gain = 0.5;
        private double lacunarity = 2;
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
        public OctavationModuleBuilder setNoiseSource(NoiseSource noiseSource) {
            this.noiseSource = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link OctavationModule}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        public OctavationModuleBuilder setNoiseSource(NoiseSourceBuilder noiseSourceBuilder) {
            this.noiseSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the amount of octaves for the {@link OctavationModule}.
         *
         * @param octaves the new amount of octaves for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
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
         * @deprecated Use {@link #setGain} - Internals have not changed.
         */
        @Deprecated
        public OctavationModuleBuilder setPersistence(double persistence) {
            return setGain(persistence);
        }

        /**
         * Sets the gain for the {@link OctavationModule}.
         *
         * @param gain the new gain for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        public OctavationModuleBuilder setGain(double gain) {
            if (gain <= 0) {
                throw new IllegalArgumentException("Gain must be a non-zero positive value.");
            }
            this.gain = gain;
            return this;
        }

        /**
         * Sets the lacunarity for the {@link OctavationModule}.
         *
         * @param lacunarity the new lacunarity for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
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
        public OctavationModuleBuilder setFractalFunction(FractalFunction fractalFunction) {
            this.fractalFunction = fractalFunction;
            return this;
        }

        /**
         * Should the Noise Generator increment the seed with each octave?
         *
         * @param incrementSeed true if seed should increment each octave for the {@link OctavationModule}.
         * @return {@link OctavationModuleBuilder} this
         */
        public OctavationModuleBuilder setIncrementSeed(boolean incrementSeed) {
            this.incrementSeed = incrementSeed;
            return this;
        }

        @Override
        public OctavationModule build() {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Noise source must be defined.");
            }
            if (incrementSeed && !(noiseSource instanceof SeededNoiseGenerator)) {
                throw new IllegalArgumentException("Noise source does not have a seed, hence incrementSeed cannot be true!");
            }
            return new OctavationModule(noiseSource, octaves, gain, lacunarity, fractalFunction, incrementSeed);
        }
    }
}