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

    public ClampModifier(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public double apply(double result) {
        return Math.max(lower, Math.min(upper, result));
    }
}