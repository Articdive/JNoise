package de.articdive.jnoise.generators.noisegen.pattern;

import de.articdive.jnoise.core.api.noisegen.NoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jspecify.annotations.NullMarked;

/**
 * A noise generator that returns a concentric spheres (centered on the origin) extending infinitely.
 * Returns 0.0 when on a circle, and 1.0 when between two circles.
 *
 * @author Articdive
 */
@NullMarked
public final class SphereNoiseGenerator implements NoiseGenerator {
    private SphereNoiseGenerator() {

    }

    @Override
    public double evaluateNoise(double x) {
        final double distFromCenter = Math.sqrt(x * x);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = Math.ceil(distFromCenter) - distFromCenter;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    @Override
    public double evaluateNoise(double x, double y) {
        final double distFromCenter = Math.sqrt(x * x + y * y);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = Math.ceil(distFromCenter) - distFromCenter;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        final double distFromCenter = Math.sqrt(x * x + y * y + z * z);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = 1.0 - distFromSmallerSphere;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        final double distFromCenter = Math.sqrt(x * x + y * y + z * z + w * w);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = 1.0 - distFromSmallerSphere;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    /**
     * Gets a {@link SphereNoiseBuilder} to build a {@link SphereNoiseGenerator}.
     *
     * @return {@link SphereNoiseBuilder}.
     */
    public static SphereNoiseBuilder newBuilder() {
        return new SphereNoiseBuilder();
    }

    /**
     * Builder for the {@link SphereNoiseGenerator}.
     */
    @NullMarked
    public static final class SphereNoiseBuilder implements NoiseSourceBuilder {

        private SphereNoiseBuilder() {

        }

        @Override
        public SphereNoiseGenerator build() {
            return new SphereNoiseGenerator();
        }
    }
}
