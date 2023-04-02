package de.articdive.jnoise.generators.noise_parameters.min_functions;

import de.articdive.jnoise.core.util.MathUtil;

/**
 * Enum representing a few commonly used minimization functions for noise generation.
 *
 * @author Articdive
 */
public enum MinimizationFunctionType implements MinimizationFunction {
    EXPONENTIAL_SMOOTH_MIN {
        @Override
        public double min(double a, double b) {
            double res = MathUtil.exp2(-32.0 * a) + MathUtil.exp2(-32.0 * b);
            return -MathUtil.log2(res) / 32.0;
        }
    },
    POWER_SMOOTH_MIN {
        @Override
        public double min(double a, double b) {
            a = Math.pow(a, 8);
            b = Math.pow(b, 8);
            return Math.pow((a * b) / (a + b), 1.0 / 8);
        }
    },
    POLYNOMIAL_SMOOTH_MIN {
        @Override
        public double min(double a, double b) {
            double h = Math.max(0.1 - Math.abs(a - b), 0.0) / 0.1;
            return Math.min(a, b) - h * h * 0.1 * (1.0 / 4.0);
        }
    }
}
