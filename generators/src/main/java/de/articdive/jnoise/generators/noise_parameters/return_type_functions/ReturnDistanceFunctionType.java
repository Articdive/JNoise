package de.articdive.jnoise.generators.noise_parameters.return_type_functions;

/**
 * Enum representing a few commonly used return distance functions for noise generation.
 *
 * @author Articdive
 */
public enum ReturnDistanceFunctionType implements ReturnDistanceFunction {
    /**
     * Shortest distance.
     */
    DISTANCE_0 {
        @Override
        public double applyAsDouble(double[] value) {
            return value[0];
        }

        @Override
        public boolean isValidArrayLength(int depth) {
            return depth >= 1;
        }
    },
    /**
     * Second shortest distance.
     */
    DISTANCE_1 {
        @Override
        public double applyAsDouble(double[] value) {
            return value[1];
        }

        @Override
        public boolean isValidArrayLength(int depth) {
            return depth >= 2;
        }
    },
    /**
     * Addition of the two shortest distances.
     */
    DISTANCE_01_ADD {
        @Override
        public double applyAsDouble(double[] value) {
            return value[0] + value[1];
        }

        @Override
        public boolean isValidArrayLength(int depth) {
            return depth >= 2;
        }

    },
    /**
     * Subtraction of the two shortest distances.
     */
    DISTANCE_01_SUB {
        @Override
        public double applyAsDouble(double[] value) {
            return value[0] + value[1];
        }

        @Override
        public boolean isValidArrayLength(int depth) {
            return depth >= 2;
        }
    },
    /**
     * Multiplication of the two shortest distances.
     */
    DISTANCE_01_MUL {
        @Override
        public double applyAsDouble(double[] value) {
            return value[0] * value[1];
        }

        @Override
        public boolean isValidArrayLength(int depth) {
            return depth >= 2;
        }
    }

}
