package de.articdive.jnoise.generators.noisegen.worley;

import de.articdive.jnoise.core.api.functions.Combiner;
import de.articdive.jnoise.core.api.noisegen.SeededExplicitNoiseGenerator;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.core.util.HashUtil;
import de.articdive.jnoise.core.util.vectors.Vector;
import de.articdive.jnoise.core.util.vectors.Vector1D;
import de.articdive.jnoise.core.util.vectors.Vector2D;
import de.articdive.jnoise.core.util.vectors.Vector3D;
import de.articdive.jnoise.core.util.vectors.Vector4D;
import de.articdive.jnoise.generators.noise_parameters.distance_functions.DistanceFunction;
import de.articdive.jnoise.generators.noise_parameters.distance_functions.DistanceFunctionType;
import de.articdive.jnoise.generators.noise_parameters.return_type_functions.ReturnDistanceFunction;
import de.articdive.jnoise.generators.noise_parameters.return_type_functions.ReturnDistanceFunctionType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntToLongFunction;

/**
 * The bounds of the worley noise implementation heavily depend on the distance functions,
 * they will be in the interval of: [0.0, {@link Double#MAX_VALUE}], it is highly recommended to clamp the output.
 * The more feature points there are, the lower the upper bound will be.
 * if no feature points exist, then the distance will be {@link Double#MAX_VALUE}.
 *
 * @author Articdive
 */
public final class WorleyNoiseGenerator implements SeededExplicitNoiseGenerator<WorleyNoiseResult<Vector>> {
    private final long seed;

    private final int depth;
    private final DistanceFunction distanceFunction;
    private final IntToLongFunction fpAmountFunction;
    private final ReturnDistanceFunction returnDistanceFunction;
    private final Combiner minFunction;

    private WorleyNoiseGenerator(long seed, int depth, DistanceFunction distanceFunction, IntToLongFunction fpAmountFunction, ReturnDistanceFunction returnDistanceFunction, Combiner minFunction) {
        this.seed = seed;
        this.depth = depth;
        this.distanceFunction = distanceFunction;
        this.fpAmountFunction = fpAmountFunction;
        this.returnDistanceFunction = returnDistanceFunction;
        this.minFunction = minFunction;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        return evaluateNoiseResult(x, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        return evaluateNoiseResult(x, y, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        return evaluateNoiseResult(x, y, z, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        return evaluateNoiseResult(x, y, z, w, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x) {
        return evaluateNoiseResult(x, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return evaluateNoiseResult(x, y, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return evaluateNoiseResult(x, y, z, seed).getValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return evaluateNoiseResult(x, y, z, w, seed).getValue();
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, long seed) {
        long iX = (long) Math.floor(x);
        double[] distancesStack = new double[depth];
        Arrays.fill(distancesStack, Double.MAX_VALUE);
        // distancesStack[0] is the shortest distance.
        Vector1D closestPoint = null;

        //TODO: Add Grid size as a parameter in all worley noise types. (Here it is 3)
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;


            int hash = HashUtil.hash1D(seed, secX);
            Random fpRNG = new Random(hash);

            // For performance reasons this can at most be 10:
            for (int i = 0; i < Math.max(1, fpAmountFunction.applyAsLong(hash)); i++) {
                double pointX = fpRNG.nextDouble() + secX;
                double distance = distanceFunction.distance(
                    x,
                    pointX
                );

                // Handle the distances stack from bottom up
                for (int d = depth - 1; d >= 1; d--) {
                    distancesStack[d] = Math.max(minFunction.applyTo(distancesStack[d], distance), distancesStack[d - 1]);
                }
                if (distance < distancesStack[0]) {
                    distancesStack[0] = minFunction.applyTo(distance, distancesStack[0]);
                    closestPoint = new Vector1D(pointX);
                }
            }
        }
        return new WorleyNoiseResult<>(returnDistanceFunction.applyAsDouble(distancesStack), closestPoint);
    }


    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        double[] distancesStack = new double[depth];
        Arrays.fill(distancesStack, Double.MAX_VALUE);
        // distancesStack[0] is the shortest distance.
        Vector2D closestPoint = null;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                // Split into unit square/cube/section.
                long secY = iY + yOffset;

                int hash = HashUtil.hash2D(seed, secX, secY);
                Random fpRNG = new Random(hash);

                // For performance reasons this can at most be 10:
                for (int i = 0; i < Math.max(1, fpAmountFunction.applyAsLong(hash)); i++) {
                    double pointX = fpRNG.nextDouble() + secX;
                    double pointY = fpRNG.nextDouble() + secY;
                    double distance = distanceFunction.distance(
                        x,
                        y,
                        pointX,
                        pointY
                    );

                    // Handle the distances stack from bottom up
                    for (int d = depth - 1; d >= 1; d--) {
                        distancesStack[d] = Math.max(minFunction.applyTo(distancesStack[d], distance), distancesStack[d - 1]);
                    }
                    if (distance < distancesStack[0]) {
                        distancesStack[0] = minFunction.applyTo(distance, distancesStack[0]);
                        closestPoint = new Vector2D(pointX, pointY);
                    }
                }
            }
        }
        return new WorleyNoiseResult<>(returnDistanceFunction.applyAsDouble(distancesStack), closestPoint);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, double z, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        double[] distancesStack = new double[depth];
        Arrays.fill(distancesStack, Double.MAX_VALUE);
        // distancesStack[0] is the shortest distance.
        Vector3D closestPoint = null;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                long secY = iY + yOffset;

                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    long secZ = iZ + zOffset;

                    int hash = HashUtil.hash3D(seed, secX, secY, secZ);
                    Random fpRNG = new Random(hash);

                    // For performance reasons this can at most be 10:
                    for (int i = 0; i < Math.max(1, fpAmountFunction.applyAsLong(hash)); i++) {
                        double pointX = fpRNG.nextDouble() + secX;
                        double pointY = fpRNG.nextDouble() + secY;
                        double pointZ = fpRNG.nextDouble() + secZ;
                        double distance = distanceFunction.distance(
                            x,
                            y,
                            z,
                            pointX,
                            pointY,
                            pointZ
                        );

                        // Handle the distances stack from bottom up
                        for (int d = depth - 1; d >= 1; d--) {
                            distancesStack[d] = Math.max(minFunction.applyTo(distancesStack[d], distance), distancesStack[d - 1]);
                        }
                        if (distance < distancesStack[0]) {
                            distancesStack[0] = minFunction.applyTo(distance, distancesStack[0]);
                            closestPoint = new Vector3D(pointX, pointY, pointZ);
                        }
                    }

                }
            }
        }
        return new WorleyNoiseResult<>(returnDistanceFunction.applyAsDouble(distancesStack), closestPoint);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        double[] distancesStack = new double[depth];
        Arrays.fill(distancesStack, Double.MAX_VALUE);
        // distancesStack[0] is the shortest distance.
        Vector4D closestPoint = null;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                long secY = iY + yOffset;

                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    long secZ = iZ + zOffset;

                    for (int wOffset = -1; wOffset <= 1; wOffset++) {
                        long secW = iW + wOffset;

                        int hash = HashUtil.hash4D(seed, secX, secY, secZ, secW);
                        Random fpRNG = new Random(hash);

                        // For performance reasons this can at most be 10:
                        for (int i = 0; i < fpAmountFunction.applyAsLong(hash); i++) {
                            double pointX = fpRNG.nextDouble() + secX;
                            double pointY = fpRNG.nextDouble() + secY;
                            double pointZ = fpRNG.nextDouble() + secZ;
                            double pointW = fpRNG.nextDouble() + secW;
                            double distance = distanceFunction.distance(
                                x,
                                y,
                                z,
                                w,
                                pointX,
                                pointY,
                                pointZ,
                                pointW
                            );

                            // Handle the distances stack from bottom up
                            for (int d = depth - 1; d >= 1; d--) {
                                distancesStack[d] = Math.max(minFunction.applyTo(distancesStack[d], distance), distancesStack[d - 1]);
                            }
                            if (distance < distancesStack[0]) {
                                distancesStack[0] = minFunction.applyTo(distance, distancesStack[0]);
                                closestPoint = new Vector4D(pointX, pointY, pointZ, pointW);
                            }
                        }
                    }

                }
            }
        }
        return new WorleyNoiseResult<>(returnDistanceFunction.applyAsDouble(distancesStack), closestPoint);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x) {
        return evaluateNoiseResult(x, seed);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y) {
        return evaluateNoiseResult(x, y, seed);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, double z) {
        return evaluateNoiseResult(x, y, z, seed);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, double z, double w) {
        return evaluateNoiseResult(x, y, z, w, seed);
    }

    @Override
    public long getSeed() {
        return seed;
    }

    /**
     * Gets a {@link WorleyNoiseBuilder} to build a {@link WorleyNoiseGenerator}.
     *
     * @return {@link WorleyNoiseBuilder}.
     */
    @NotNull
    public static WorleyNoiseBuilder newBuilder() {
        return new WorleyNoiseBuilder();
    }

    /**
     * Builder for the {@link WorleyNoiseGenerator}.
     */
    public static final class WorleyNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;
        private int depth = 1;
        private DistanceFunction distanceFunction = DistanceFunctionType.EUCLIDEAN_SQUARED;
        private IntToLongFunction fpAmountFunction = i -> 1;
        private ReturnDistanceFunction returnDistanceFunction = ReturnDistanceFunctionType.DISTANCE_0;
        private Combiner minFunction = Combiner.MIN;

        private WorleyNoiseBuilder() {

        }

        /**
         * Sets the seed for the {@link WorleyNoiseGenerator}.
         *
         * @param seed the new seed for the {@link WorleyNoiseGenerator}.
         * @return {@link WorleyNoiseBuilder} this
         */
        @NotNull
        public WorleyNoiseBuilder setSeed(long seed) {
            this.seed = seed;
            return this;
        }

        /**
         * Sets the depth for the {@link WorleyNoiseGenerator}.
         * Depth represents how many of the shortest distances will be used.
         * As an example a depth of 1, means only the shortest distance will be passed to the return type function (See: {@link #setReturnDistanceFunction(ReturnDistanceFunction)})
         * A depth of 2, means the shortest distance, and the second shortest distance will be passed.
         *
         * @param depth The new depth for the {@link WorleyNoiseGenerator}.
         * @return {@link WorleyNoiseBuilder} this
         */
        @NotNull
        public WorleyNoiseBuilder setDepth(int depth) {
            this.depth = depth;
            return this;
        }

        /**
         * Sets the distance function for the {@link WorleyNoiseGenerator}.
         *
         * @param distanceFunction The new {@link DistanceFunction} for the {@link WorleyNoiseGenerator}.
         * @return {@link WorleyNoiseBuilder} this
         */
        @NotNull
        public WorleyNoiseBuilder setDistanceFunction(DistanceFunction distanceFunction) {
            if (distanceFunction == null) {
                throw new IllegalArgumentException("Distance function cannot be null.");
            }
            this.distanceFunction = distanceFunction;
            return this;
        }

        /**
         * Sets the function supplying the amount of feature points in a unit section.
         * Increasing this number can add more "features" (points) to one grid in a worley generation step.
         * It is recommended to keep this at 1 (the default) for performance reasons!
         * The function must not return negative values.
         *
         * @param fpAmountFunction The new (feature point amount function) for the {@link WorleyNoiseGenerator}
         * @return {@link WorleyNoiseBuilder} this
         */
        @NotNull
        public WorleyNoiseBuilder setFeaturePointAmountFunction(IntToLongFunction fpAmountFunction) {
            if (fpAmountFunction == null) {
                throw new IllegalArgumentException("Featurepoint amount function cannot be null.");
            }
            this.fpAmountFunction = fpAmountFunction;
            return this;
        }

        /**
         * Sets the return distance function for the {@link WorleyNoiseGenerator}.
         * The return distance function takes an array of shortest distances (See: {@link #setDepth(int)} for length).
         * The array of shortest distances is converted to the return value.
         * Typically all one needs is the shortest distance (See: {@link ReturnDistanceFunctionType#DISTANCE_0}).
         * However taking the second shortest, or a combination can be interesting.
         *
         * @param returnDistanceFunction The new return distance function for the {@link WorleyNoiseGenerator}.
         * @return {@link WorleyNoiseBuilder} this
         */
        @NotNull
        public WorleyNoiseBuilder setReturnDistanceFunction(ReturnDistanceFunction returnDistanceFunction) {
            if (returnDistanceFunction == null) {
                throw new IllegalArgumentException("Return distance function cannot be null.");
            }
            this.returnDistanceFunction = returnDistanceFunction;
            return this;
        }

        /**
         * Sets the minimization function for the {@link WorleyNoiseGenerator}.
         * A normal min Function {@link Math#min(int, int)} is a non-continious function and can have effects on smoothness.
         *
         * @param minFunction The new minimization function for the {@link WorleyNoiseGenerator}.
         * @return {@link WorleyNoiseBuilder} this
         */
        public WorleyNoiseBuilder setMinFunction(Combiner minFunction) {
            if (minFunction == null) {
                throw new IllegalArgumentException("Minimization function cannot be null.");
            }
            this.minFunction = minFunction;
            return this;
        }

        @Override
        @NotNull
        public WorleyNoiseGenerator build() {
            if (!returnDistanceFunction.isValidArrayLength(depth)) {
                throw new IllegalArgumentException("Invalid depth for the specified return distance function!");
            }
            return new WorleyNoiseGenerator(seed, depth, distanceFunction, fpAmountFunction, returnDistanceFunction, minFunction);
        }
    }
}
