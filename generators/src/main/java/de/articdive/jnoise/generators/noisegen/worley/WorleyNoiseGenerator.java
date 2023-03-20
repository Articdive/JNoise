package de.articdive.jnoise.generators.noisegen.worley;

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
import org.jetbrains.annotations.NotNull;

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
    private final DistanceFunction distanceFunction;
    private final IntToLongFunction fpAmountFunction;

    private WorleyNoiseGenerator(long seed, DistanceFunction distanceFunction, IntToLongFunction fpAmountFunction) {
        this.seed = seed;
        this.distanceFunction = distanceFunction;
        this.fpAmountFunction = fpAmountFunction;
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
        double shortestDistance = Double.MAX_VALUE;
        Vector1D closestPoint = null;

        //TODO: Add Grid size as a parameter in all worley noise types. (Here it is 3)
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;


            int hash = HashUtil.hash1D(seed, secX);
            Random fpRNG = new Random(hash);

            // For performance reasons this can at most be 10:
            for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.applyAsLong(hash), 10)); i++) {
                double pointX = fpRNG.nextDouble() + secX;
                double distance = distanceFunction.distance(
                    x,
                    pointX
                );

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    closestPoint = new Vector1D(pointX);
                }
            }
        }
        return new WorleyNoiseResult<>(shortestDistance, closestPoint);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        double shortestDistance = Double.MAX_VALUE;
        Vector2D closestPoint = null;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                // Split into unit square/cube/section.
                long secY = iY + yOffset;

                int hash = HashUtil.hash2D(seed, secX, secY);
                Random fpRNG = new Random(hash);

                // For performance reasons this can at most be 10:
                for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.applyAsLong(hash), 10)); i++) {
                    double pointX = fpRNG.nextDouble() + secX;
                    double pointY = fpRNG.nextDouble() + secY;
                    double distance = distanceFunction.distance(
                        x,
                        y,
                        pointX,
                        pointY
                    );

                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        closestPoint = new Vector2D(pointX, pointY);
                    }
                }
            }
        }
        return new WorleyNoiseResult<>(shortestDistance, closestPoint);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, double z, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        double shortestDistance = Double.MAX_VALUE;
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
                    for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.applyAsLong(hash), 10)); i++) {
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

                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                            closestPoint = new Vector3D(pointX, pointY, pointZ);
                        }
                    }

                }
            }
        }
        return new WorleyNoiseResult<>(shortestDistance, closestPoint);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector> evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        double shortestDistance = Double.MAX_VALUE;
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
                        for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.applyAsLong(hash), 10)); i++) {
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

                            if (distance < shortestDistance) {
                                shortestDistance = distance;
                                closestPoint = new Vector4D(pointX, pointY, pointZ, pointW);
                            }
                        }
                    }

                }
            }
        }
        return new WorleyNoiseResult<>(shortestDistance, closestPoint);
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

    @NotNull
    public static WorleyNoiseBuilder newBuilder() {
        return new WorleyNoiseBuilder();
    }

    public static final class WorleyNoiseBuilder implements NoiseSourceBuilder {
        private long seed = 1729;
        private DistanceFunction distanceFunction = DistanceFunctionType.EUCLIDEAN_SQUARED;
        private IntToLongFunction fpAmountFunction = i -> 1;

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
         * Increasing this number can add more "features" to one area.
         * The values are clamped between 1 and 10 for performance reasons!
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

        @Override
        @NotNull
        public WorleyNoiseGenerator build() {
            return new WorleyNoiseGenerator(seed, distanceFunction, fpAmountFunction);
        }
    }
}
