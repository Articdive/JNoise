package de.articdive.jnoise.core.util;

import de.articdive.jnoise.core.api.annotations.Vector2D;
import de.articdive.jnoise.core.api.annotations.Vector3D;
import de.articdive.jnoise.core.api.annotations.Vector4D;

/**
 * Utility class for mathematical functions.
 *
 * @author Articdive
 */
public final class MathUtil {
    private MathUtil() {

    }

    /**
     * Calculates 2^x of the specified value.
     *
     * @param x specified value
     * @return 2^x.
     */
    public static double exp2(double x) {
        return Math.pow(2, x);
    }

    /**
     * Calculates log_2 of the specified value.
     *
     * @param x specified value
     * @return log_2(x).
     */
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    /**
     * Calculates the dot product of the specified 2D vectors.
     *
     * @param a a double array representing a 2D vector.
     * @param b a double array representing a 2D vector.
     * @return the dot product of the two 2D vectors.
     */
    public static double dot2D(@Vector2D double[] a, @Vector2D double[] b) {
        return (a[0] * b[0]) + (a[1] * b[1]);
    }

    /**
     * Calculates the dot product of the specified 3D vectors.
     *
     * @param a a double array representing a 3D vector.
     * @param b a double array representing a 3D vector.
     * @return the dot product of the two 3D vectors.
     */
    public static double dot3D(@Vector3D double[] a, @Vector3D double[] b) {
        return (a[0] * b[0]) + (a[1] * b[1]) + (a[2] * b[2]);
    }

    /**
     * Calculates the dot product of the specified 4D vectors.
     *
     * @param a a double array representing a 4D vector.
     * @param b a double array representing a 4D vector.
     * @return the dot product of the two 4D vectors.
     */
    public static double dot4D(@Vector4D double[] a, @Vector4D double[] b) {
        return (a[0] * b[0]) + (a[1] * b[1]) + (a[2] * b[2]) + (a[3] * b[3]);
    }
}
