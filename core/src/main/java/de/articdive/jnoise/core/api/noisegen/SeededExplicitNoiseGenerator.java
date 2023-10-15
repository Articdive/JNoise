package de.articdive.jnoise.core.api.noisegen;

import org.jspecify.annotations.NullMarked;

/**
 * Interface that denotes a {@link SeededNoiseGenerator}, which can additionally evaluate a {@link NoiseResult} at a location.
 *
 * @param <NR> {@link NoiseResult} class
 * @author Articdive
 */
@NullMarked
public interface SeededExplicitNoiseGenerator<NR extends NoiseResult> extends ExplicitNoiseGenerator<NR>, SeededNoiseGenerator {
    /**
     * Evaluates noise at a 1D point.
     *
     * @param x    X-Coordinate of the 1D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return {@link NR} denoting the noise value at the 1D point.
     */
    NR evaluateNoiseResult(double x, long seed);

    /**
     * Evaluates noise at a 2D point.
     *
     * @param x    X-Coordinate of the 2D point.
     * @param y    Y-Coordinate of the 2D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return {@link NR} denoting the noise value at the 2D point.
     */
    NR evaluateNoiseResult(double x, double y, long seed);

    /**
     * Evaluates noise at a 3D point.
     *
     * @param x    X-Coordinate of the 3D point.
     * @param y    Y-Coordinate of the 3D point.
     * @param z    Z-Coordinate of the 3D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return {@link NR} denoting the noise value at the 3D point.
     */
    NR evaluateNoiseResult(double x, double y, double z, long seed);

    /**
     * Evaluates noise at a 4D point.
     *
     * @param x    X-Coordinate of the 4D point.
     * @param y    Y-Coordinate of the 4D point.
     * @param z    Z-Coordinate of the 4D point.
     * @param w    W-Coordinate of the 4D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return {@link NR} denoting the noise value at the 4D point.
     */
    NR evaluateNoiseResult(double x, double y, double z, double w, long seed);
}
