package de.articdive.jnoise.generators.noise_parameters.fade_functions;

/**
 * Interface marking the implementing class as a fade function for noise generation.
 * See <a href="https://iquilezles.org/articles/smoothsteps/">here</a> for more information
 *
 * @author Articdive
 */
@FunctionalInterface
public interface FadeFunction {
    FadeFunction NONE = x -> x;

    // Inigo Quilez START
    // POLY
    FadeFunction CUBIC_POLY = x -> x * x * (3 - 2 * x);
    FadeFunction SQUARTIC_POLY = x -> x * x * (2 - x * x);
    FadeFunction QUINTIC_POLY = x -> x * x * x * (x * (x * 6 - 15) + 10);
    // RATIONAL
    FadeFunction QUADRATIC_RATIONAL = x -> x * x / (2 * x * x - 2 * x + 1);
    FadeFunction CUBIC_RATIONAL = x -> x * x * x / (3 * x * x - 3 * x + 1);

    // PIECEWISE
    FadeFunction QUADRATIC_PIECEWISE = x -> (x < 0.5) ? (2 * x * x) : (2 * x * (2.0 - x) - 1);
    // TRIGONOMETRIC
    FadeFunction TRIGONOMETRIC = x -> 0.5 - 0.5 * Math.cos(Math.PI * x);

    // Inigo Quilez END
    /**
     * @deprecated Use {@link #CUBIC_POLY}
     */
    @Deprecated
    FadeFunction SMOOTHSTEP = CUBIC_POLY;

    /**
     * @deprecated Use {@link #QUINTIC_POLY}
     */
    @Deprecated
    FadeFunction IMPROVED_PERLIN_NOISE = QUINTIC_POLY;

    /**
     * Specifies a value to fade, this is used to remove interpolation artefacts.
     *
     * @param t value (position) in the unit cube to fade.
     * @return the faded value at t.
     */
    double fade(double t);
}
