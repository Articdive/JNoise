package de.articdive.jnoise.generators.noise_parameters.distance_functions;

/**
 * Interface marking the implementing class as a distance function for noise generation.
 *
 * @author Articdive
 */
public interface DistanceFunction {
    /**
     * Gets the distance from a 1D point to another 1D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @return a positive distance between P(x0) and Q(x1)
     */
    double distance(double x0, double x1);


    /**
     * Gets the distance from a 2D point to another 2D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param y0 Y-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @param y1 Y-Coordinate of the second point.
     * @return a positive distance between P(x0, y0) and Q(x1, y1)
     */
    double distance(double x0, double y0, double x1, double y1);

    /**
     * Gets the distance from a 3D point to another 3D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param y0 Y-Coordinate of the first point.
     * @param z0 Z-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @param y1 Y-Coordinate of the second point.
     * @param z1 Z-Coordinate of the second point.
     * @return a positive distance between (x0, y0, z0) and (x1, y1, z1)
     */
    double distance(double x0, double y0, double z0, double x1, double y1, double z1);

    /**
     * Gets the distance from a 4D point to another 4D point.
     *
     * @param x0 X-Coordinate of the first point.
     * @param y0 Y-Coordinate of the first point.
     * @param z0 Z-Coordinate of the first point.
     * @param w0 W-Coordinate of the first point.
     * @param x1 X-Coordinate of the second point.
     * @param y1 Y-Coordinate of the second point.
     * @param z1 Z-Coordinate of the second point.
     * @param w1 W-Coordinate of the second point.
     * @return a positive distance between (x0, y0, z0, w0) and (x1, y1, z1, w1)
     */
    double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1);
}
