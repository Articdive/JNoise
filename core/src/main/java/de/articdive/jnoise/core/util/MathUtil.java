package de.articdive.jnoise.core.util;

/**
 * Utility class for mathematical functions.
 *
 * @author Articdive
 */
public final class MathUtil {
    private MathUtil() {

    }

    public static double exp2(double x) {
        return Math.pow(2, x);
    }

    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
