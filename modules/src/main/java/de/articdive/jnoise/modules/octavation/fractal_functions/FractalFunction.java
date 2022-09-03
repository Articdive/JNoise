package de.articdive.jnoise.modules.octavation.fractal_functions;

/**
 * @author Articdive
 */
@FunctionalInterface
public interface FractalFunction {
    // FBM is actually the base layout for the OctavationModule
    FractalFunction FBM = a -> a;

    FractalFunction BILLOW = a -> Math.abs(a) * 2 - 1;

    FractalFunction RIDGED_MULTI = a -> 1 - Math.abs(a);

    /**
     * Applies the fractalization step to a noise output.
     *
     * @param a value (noise output) to fractalize.
     * @return a fractalized value for a.
     */
    double fractalize(double a);
}