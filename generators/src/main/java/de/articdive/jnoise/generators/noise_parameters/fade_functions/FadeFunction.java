package de.articdive.jnoise.generators.noise_parameters.fade_functions;

/**
 * Interface marking the implementing class as a fade function for noise generation.
 *
 * @author Articdive
 */
@FunctionalInterface
public interface FadeFunction {
    FadeFunction NONE = t -> t;
    FadeFunction SMOOTHSTEP = t -> t * t * (3 - 2 * t); // f(t) = -(2t^3) + 3t^2
    FadeFunction IMPROVED_PERLIN_NOISE = t -> t * t * t * (t * (t * 6 - 15) + 10); // f(t) = 6t^5 - (15t^4) + 10t^3

    /**
     * Specifies a value to fade, this is used to remove interpolation artefacts.
     *
     * @param t value (position) in the unit cube to fade.
     * @return the faded value at t.
     */
    double fade(double t);
}
