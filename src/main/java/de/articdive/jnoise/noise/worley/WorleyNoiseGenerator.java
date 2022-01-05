/*
 * JNoise
 * Copyright (C) 2021-2022 Articdive
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.articdive.jnoise.noise.worley;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.distance_functions.DistanceFunction;
import de.articdive.jnoise.util.HashUtil;
import de.articdive.jnoise.util.vectors.Vector;
import de.articdive.jnoise.util.vectors.Vector1D;
import de.articdive.jnoise.util.vectors.Vector2D;
import de.articdive.jnoise.util.vectors.Vector3D;
import de.articdive.jnoise.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.LongFunction;

/**
 * The bounds of the worley noise implementation heavily depend on the distance functions,
 * they will be in the interval of: [0.0, {@link Double#MAX_VALUE}], it is highly recommended to clamp the output.
 * The more feature points there are, the upper bound will be less.
 * if no one feature points exist, then the distance will be {@link Double#MAX_VALUE}.
 *
 * @author Articdive
 */
public final class WorleyNoiseGenerator implements NoiseGenerator<WorleyNoiseResult<? extends Vector>> {
    private final long seed;
    private final double frequency;
    private final DistanceFunction distanceFunction;
    private final LongFunction<Integer> fpAmountFunction;

    WorleyNoiseGenerator(long seed, double frequency, DistanceFunction distanceFunction, LongFunction<Integer> fpAmountFunction) {
        this.seed = seed;
        this.frequency = frequency;
        this.distanceFunction = distanceFunction;
        this.fpAmountFunction = fpAmountFunction;
    }

    @Override
    public double evaluateNoise(double x, long seed) {
        return evaluateNoiseResult(x, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x, double y, long seed) {
        return evaluateNoiseResult(x, y, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        return evaluateNoiseResult(x, y, z, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        return evaluateNoiseResult(x, y, z, w, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x) {
        return evaluateNoiseResult(x, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return evaluateNoiseResult(x, y, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return evaluateNoiseResult(x, y, z, seed).getPureValue();
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return evaluateNoiseResult(x, y, z, w, seed).getPureValue();
    }

    @Override
    @NotNull
    public WorleyNoiseResult<Vector1D> evaluateNoiseResult(double x, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        double shortestDistance = Double.MAX_VALUE;
        Vector1D closestPoint = null;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;


            int hash = HashUtil.hash1D(seed, secX);
            Random fpRNG = new Random(hash);
            
            // For performance reasons this can at most be 10:
            for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.apply(hash), 10)); i++) {
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
    public WorleyNoiseResult<Vector2D> evaluateNoiseResult(double x, double y, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
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
                for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.apply(hash), 10)); i++) {
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
    public WorleyNoiseResult<Vector3D> evaluateNoiseResult(double x, double y, double z, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        long iZ = (long) Math.floor(z *= frequency);
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
                    for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.apply(hash), 10)); i++) {
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
    public WorleyNoiseResult<Vector4D> evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        long iX = (long) Math.floor(x *= frequency);
        long iY = (long) Math.floor(y *= frequency);
        long iZ = (long) Math.floor(z *= frequency);
        long iW = (long) Math.floor(w *= frequency);
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
                        for (int i = 0; i < Math.max(1, Math.min(fpAmountFunction.apply(hash), 10)); i++) {
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
    public WorleyNoiseResult<? extends Vector> evaluateNoiseResult(double x) {
        return evaluateNoiseResult(x, seed);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<? extends Vector> evaluateNoiseResult(double x, double y) {
        return evaluateNoiseResult(x, y, seed);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<? extends Vector> evaluateNoiseResult(double x, double y, double z) {
        return evaluateNoiseResult(x, y, z, seed);
    }

    @Override
    @NotNull
    public WorleyNoiseResult<? extends Vector> evaluateNoiseResult(double x, double y, double z, double w) {
        return evaluateNoiseResult(x, y, z, w, seed);
    }

    @Override
    public long getSeed() {
        return seed;
    }
}
