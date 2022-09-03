package de.articdive.jnoise.modifiers.clamp;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;

/**
 * Simple Modifier that clamps the noise output between 2 values.
 *
 * @author Articdive
 */
public final class ClampModifier implements NoiseModifier {
    private final double lower;
    private final double upper;

    /**
     * Creates a new ClampModifier that clamps the noise output value between 2 values.
     * @param lower the lower value to clamp between.
     * @param upper the higher value to clamp between.
     */
    public ClampModifier(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public double apply(double result) {
        return Math.max(lower, Math.min(upper, result));
    }
}