package de.articdive.jnoise.modules.octavation.fractal_functions;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;

/**
 * @author Articdive
 */
@FunctionalInterface
public interface FractalFunction {
    // FBM is actually the base layout for the OctavationModule
    FractalFunction FBM = a -> a;

    /**
     * @deprecated Use an {@link NoiseModifier} to make the noise unsigned and use {@link #TURBULENCE}
     */
    @Deprecated
    FractalFunction BILLOW = a -> Math.abs(a * 2 - 1);

    // Should be used on signed noise.
    FractalFunction TURBULENCE = Math::abs;

    FractalFunction RIDGED_MULTI = a -> (1 - Math.abs(a)) * (1 - Math.abs(a));

    /**
     * Applies the fractalization step to a noise output.
     *
     * @param noise value (noise output) to fractalize.
     * @return a fractalized value for the noise.
     */
    double fractalize(double noise);
}