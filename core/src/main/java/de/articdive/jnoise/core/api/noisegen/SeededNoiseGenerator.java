package de.articdive.jnoise.core.api.noisegen;

/**
 * Interface that denotes a seeded {@link NoiseGenerator}.
 * A seeded {@link NoiseGenerator} has reproducability and should return the same value for the same seed at the same location.
 *
 * @author Articdive
 */
public interface SeededNoiseGenerator extends NoiseGenerator {

    /**
     * Evaluates noise at a 1D point.
     *
     * @param x    X-Coordinate of the 1D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return noise value at the 1D point.
     */
    double evaluateNoise(double x, long seed);

    /**
     * Evaluates noise at a 2D point.
     *
     * @param x    X-Coordinate of the 2D point.
     * @param y    Y-Coordinate of the 2D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return noise value at the 2D point.
     */
    double evaluateNoise(double x, double y, long seed);

    /**
     * Evaluates noise at a 3D point.
     *
     * @param x    X-Coordinate of the 3D point.
     * @param y    Y-Coordinate of the 3D point.
     * @param z    Z-Coordinate of the 3D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return noise value at the 3D point.
     */
    double evaluateNoise(double x, double y, double z, long seed);

    /**
     * Evaluates noise at a 4D point.
     *
     * @param x    X-Coordinate of the 4D point.
     * @param y    Y-Coordinate of the 4D point.
     * @param z    Z-Coordinate of the 4D point.
     * @param w    W-Coordinate of the 4D point.
     * @param seed seed for the {@link SeededNoiseGenerator} to use.
     * @return noise value at the 4D point.
     */
    double evaluateNoise(double x, double y, double z, double w, long seed);

    /**
     * Returns the seed of the seeded noise generator.
     *
     * @return seed value of the noise generator.
     */
    long getSeed();
}
