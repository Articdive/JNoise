package de.articdive.jnoise.modifiers.inverter;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;

/**
 * Simple Modifier that inverts the noise output.
 *
 * @author Articdive
 */
public final class InvertModifier implements NoiseModifier {
    @Override
    public double apply(double result) {
        return -result;
    }
}