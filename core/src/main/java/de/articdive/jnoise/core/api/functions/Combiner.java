package de.articdive.jnoise.core.api.functions;

import de.articdive.jnoise.core.util.MathUtil;

/**
 * Interface marking the implementing class as a function that combines to double values into 1 double value.
 * As an example this is used to mark the minimization function in Worley Noise.
 *
 * @author Articdive
 */
@FunctionalInterface
public interface Combiner {
    Combiner ADD = Double::sum;
    Combiner MULTIPLY = (a, b) -> a * b;
    Combiner MAX = Math::max;
    Combiner MIN = Math::min;
    Combiner POW = Math::pow;

    Combiner EXPONENTIAL_SMOOTH_MIN = (a, b) -> {
        double res = MathUtil.exp2(-32.0 * a) + MathUtil.exp2(-32.0 * b);
        return -MathUtil.log2(res) / 32.0;
    };
    Combiner POWER_SMOOTH_MIN = (a, b) -> {
        a = Math.pow(a, 8);
        b = Math.pow(b, 8);
        return Math.pow((a * b) / (a + b), 1.0 / 8);
    };
    Combiner POLYNOMIAL_SMOOTH_MIN = (a, b) -> {
        double h = Math.max(0.1 - Math.abs(a - b), 0.0) / 0.1;
        return Math.min(a, b) - h * h * 0.1 * (1.0 / 4.0);
    };

    /**
     * Combines two double values into one double value.
     *
     * @param a first double.
     * @param b second double.
     * @return the resulting double.
     * @deprecated Use {@link #applyTo(double, double)} - makes more sense with conventions.
     */
    @Deprecated(since = "4.1.0", forRemoval = true)
    default double combine(double a, double b) {
        return applyTo(a, b);
    }

    /**
     * Combines two double values into one double value.
     *
     * @param a first double.
     * @param b second double.
     * @return the resulting double.
     */
    double applyTo(double a, double b);
}