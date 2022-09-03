package de.articdive.jnoise.modifiers.absolute_value;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;

/**
 * Simple Modifier that takes the absolute value of the noise output.
 *
 * @author Articdive
 */
public final class AbsoluteValueModifier implements NoiseModifier {
    @Override
    public double apply(double result) {
        return Math.abs(result);
    }
}