package de.articdive.jnoise.generators.noisegen.pattern;

import de.articdive.jnoise.core.api.noisegen.NoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * A noise generator that returns a concentric cylinders (centered on the origin) extending infinitely.
 * Returns 1.0 when on a cylinder side, and 0.0 when perfectly between two cylinders.
 * Please note the cylinders top and bottom (infinite) are facing the last specified axis.
 * E.g. for 3D, the cylinder is lying on it's side on the x-y plane (you see concentric circles), it's top/bottom is facing the z-axis.
 * Hence the x-z plane will be the cylinders on their sides.
 *
 * @author Articdive
 */
public final class CylinderNoiseGenerator implements NoiseGenerator {
    private CylinderNoiseGenerator() {

    }

    @Override
    public double evaluateNoise(double x) {
        throw new UnsupportedOperationException("Cannot generate a cylinder in 1D space.");
    }

    @Override
    public double evaluateNoise(double x, double y) {
        final double distFromCenter = Math.sqrt(x * x);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = Math.ceil(distFromCenter) - distFromCenter;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        final double distFromCenter = Math.sqrt(x * x + y * y);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = 1.0 - distFromSmallerSphere;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        final double distFromCenter = Math.sqrt(x * x + y * y + z * z);
        final double distFromSmallerSphere = distFromCenter - Math.floor(distFromCenter);
        final double distFromLargerSphere = 1.0 - distFromSmallerSphere;
        // The current interval is [0.0, 0.5] multiply by 2 for [0.0, 1.0]
        // Invert (1 - ...) since we want 1.0 to be on the circle and 0.0 to be between the circles.
        return 1 - 2 * Math.min(distFromSmallerSphere, distFromLargerSphere);
    }

    /**
     * Gets a {@link CylinderNoiseBuilder} to build a {@link CylinderNoiseGenerator}.
     *
     * @return {@link CylinderNoiseBuilder}.
     */
    @NotNull
    public static CylinderNoiseBuilder newBuilder() {
        return new CylinderNoiseBuilder();
    }

    /**
     * Builder for the {@link CylinderNoiseGenerator}.
     */
    public static final class CylinderNoiseBuilder implements NoiseSourceBuilder {

        private CylinderNoiseBuilder() {

        }

        @Override
        @NotNull
        public CylinderNoiseGenerator build() {
            return new CylinderNoiseGenerator();
        }
    }
}
