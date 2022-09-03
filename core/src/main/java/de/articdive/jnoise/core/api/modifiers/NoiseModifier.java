package de.articdive.jnoise.core.api.modifiers;

/**
 * Interface that denotes a noise modifier. A small algorithm that modifies the noise value in the final noise generation step.
 *
 * @author Articdive
 */
public interface NoiseModifier {

    /**
     * Modifies a noise value.
     *
     * @param result noise value to modify.
     * @return modified noise value.
     */
    double apply(double result);
}