package de.articdive.jnoise.generators.noise_parameters.min_functions;

/**
 * Interface marking the implementing class as a minimization function for noise generation.
 *
 * @author Articdive
 */
@FunctionalInterface
public interface MinimizationFunction {
    MinimizationFunction STANDARD = Math::min;

    double min(double a, double b);
}
