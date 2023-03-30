package de.articdive.jnoise.generators.noise_parameters.return_type_functions;

import de.articdive.jnoise.core.api.noisegen.NoiseGenerator;

import java.util.function.ToDoubleFunction;

/**
 * Interface marking the implementing class as a return type function for noise generation.
 * On Creation of the {@link NoiseGenerator} a check will be sent to
 * {@link #isValidArrayLength(int)} to see if the expected array length (a.k.a. depth) is supported.
 *
 * @author Articdive
 */
public interface ReturnDistanceFunction extends ToDoubleFunction<double[]> {
    /**
     * @param distances an array containing the shortest distances (sorted from 0).
     * @return the combined/denoted distance created from the specified distances.
     */
    @Override
    double applyAsDouble(double[] distances);

    /**
     * Called once on creation of a {@link NoiseGenerator} (if using {@link ReturnDistanceFunction}).
     *
     * @param depth the amount of distances that will be stored (i.e. the length of the distances array for {@link #applyAsDouble(double[])}).
     * @return true if the depth is supported, false otherwise.
     */
    boolean isValidArrayLength(int depth);
}
