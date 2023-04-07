package de.articdive.jnoise.core.util;

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
}
