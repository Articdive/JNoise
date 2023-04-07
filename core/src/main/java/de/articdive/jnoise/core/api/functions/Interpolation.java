package de.articdive.jnoise.core.api.functions;

/**
 * Interface marking the implementing class as an interpolation function for noise generation.
 *
 * @author Articdive
 */
@FunctionalInterface
public interface Interpolation {
    Interpolation LINEAR = (x, a, b) -> a + x * (b - a);
    Interpolation QUADRATIC = (x, a, b) -> a + (b - a) * x * x;
    Interpolation CUBIC = (x, a, b) -> a + (b - a) * x * x * x;
    Interpolation QUARTIC = (x, a, b) -> a + (b - a) * x * x * x * x;
    Interpolation COSINE = (x, a, b) -> a + ((1.0 - Math.cos(x * Math.PI)) / 2.0) * (b - a);

    /**
     * Interpolates a value between two known values.
     *
     * @param x Position for the data to be interpolated.
     * @param a The first known value.
     * @param b The second known value.
     * @return an interpolated value for x.
     */
    double lerp(double x, double a, double b);

    /**
     * Interpolates between an unknown number of values.
     * The amount of positions repsents the dimension
     * The amount of values must be 2^(amount of position).
     *
     * @param positions A list of positions starting with the first stage position and going up in order.
     * @param values    A list of values starting with the first stage values and going up in order.
     * @return an interpolated value between all the given positions.
     */
    default double lerp(double[] positions, double[] values) {
        if (values.length != 1 << positions.length) {
            throw new IllegalArgumentException("The amount of values must be 2^(amount of fractals).");
        }
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < ((1 << positions.length) - i); j += 2) {
                values[j / 2] = lerp(positions[i], values[j], values[j + 1]);
            }
        }
        // This should only have 1 element, the final value!
        return values[0];
    }
}
