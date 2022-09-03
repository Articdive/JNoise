package de.articdive.jnoise.core.api.pipeline;

/**
 * Interface that denotes a source of noise, which can evaluate a value at a specified location.
 *
 * @author Articdive
 */
public interface NoiseSource {

    /**
     * Evaluates noise at a 1D point.
     *
     * @param x X-Coordinate of the 1D point.
     * @return noise value at the 1D point.
     */
    double evaluateNoise(double x);

    /**
     * Evaluates noise at a 2D point.
     *
     * @param x X-Coordinate of the 2D point.
     * @param y Y-Coordinate of the 2D point.
     * @return noise value at the 2D point.
     */
    double evaluateNoise(double x, double y);

    /**
     * Evaluates noise at a 3D point.
     *
     * @param x X-Coordinate of the 3D point.
     * @param y Y-Coordinate of the 3D point.
     * @param z Z-Coordinate of the 3D point.
     * @return noise value at the 3D point.
     */
    double evaluateNoise(double x, double y, double z);

    /**
     * Evaluates noise at a 4D point.
     *
     * @param x X-Coordinate of the 4D point.
     * @param y Y-Coordinate of the 4D point.
     * @param z Z-Coordinate of the 4D point.
     * @param w W-Coordinate of the 4D point.
     * @return noise value at the 4D point.
     */
    double evaluateNoise(double x, double y, double z, double w);
}