package de.articdive.jnoise.generators.noise_parameters.distance_functions;

/**
 * Enum representing a few commonly used distance functions for noise generation.
 *
 * @author Articdive
 */
public enum DistanceFunctionType implements DistanceFunction {
    /**
     * Euclidean distance function
     */
    EUCLIDEAN {
        @Override
        public double distance(double x0, double x1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, x1));
        }

        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, y0, x1, y1));
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, y0, z0, x1, y1, z1));
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return Math.sqrt(EUCLIDEAN_SQUARED.distance(x0, y0, z0, w0, x1, y1, z1, w1));
        }
    },
    /**
     * Euclidean distance function (squared)
     */
    EUCLIDEAN_SQUARED {
        @Override
        public double distance(double x0, double x1) {
            return (x0 - x1) * (x0 - x1);
        }

        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1) + (z0 - z1) * (z0 - z1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1) + (z0 - z1) * (z0 - z1) + (w0 - w1) * (w0 - w1);
        }
    },
    /**
     * Manhatten distance function
     */
    MANHATTAN {
        @Override
        public double distance(double x0, double x1) {
            return Math.abs(x0 - x1);
        }

        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return Math.abs(x0 - x1) + Math.abs(y0 - y1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return Math.abs(x0 - x1) + Math.abs(y0 - y1) + Math.abs(z0 - z1);
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return Math.abs(x0 - x1) + Math.abs(y0 - y1) + Math.abs(z0 - z1) + Math.abs(w0 - w1);
        }
    },
    /**
     * Chebyshev distance function
     */
    CHEBYSHEV {
        @Override
        public double distance(double x0, double x1) {
            return Math.abs(x0 - x1);
        }

        @Override
        public double distance(double x0, double y0, double x1, double y1) {
            return Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1));
        }

        @Override
        public double distance(double x0, double y0, double z0, double x1, double y1, double z1) {
            return Math.max(
                Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1)),
                Math.abs(z0 - z1)
            );
        }

        @Override
        public double distance(double x0, double y0, double z0, double w0, double x1, double y1, double z1, double w1) {
            return Math.max(
                Math.max(Math.abs(x0 - x1), Math.abs(y0 - y1)),
                Math.max(Math.abs(z0 - z1), Math.abs(w0 - w1))
            );
        }
    },
}
