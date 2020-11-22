/*
 * JNoise
 * Copyright (C) 2020 Articdive (Lukas Mansour)
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

import de.articdive.jnoise.api.DistanceFunction;
import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.util.HashUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.LongFunction;

/**
 * The bounds of the worley noise implementation heavily depend on the distance functions,
 * they will be in the interval of: [0.0, 1.0), where the upper max is not fully known, it is recommended to clamp the output.
 *
 * @author Lukas Mansour
 */
public final class WorleyNoiseGenerator extends NoiseGenerator {
    private final double frequency;
    private final DistanceFunction distanceFunction;
    private final LongFunction<Integer> fpAmountFunction;
    private final int n;

    WorleyNoiseGenerator(long seed, double frequency, DistanceFunction distanceFunction, LongFunction<Integer> fpAmountFunction, int n) {
        super(seed);
        this.frequency = frequency;
        this.distanceFunction = distanceFunction;
        this.fpAmountFunction = fpAmountFunction;
        this.n = Math.max(1, Math.min(n, 9));
    }

    @Override
    public double evaluateNoise(double x, double y) {
        x *= frequency;
        y *= frequency;
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        List<Double> distances = new ArrayList<>();
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                // Split into unit square/cube/section.
                long secY = iY + yOffset;

                int hash = HashUtil.hash2D(seed, secX, secY);
                // For performance reasons this can at most be 10:
                long numberFP = Math.max(1, Math.min(fpAmountFunction.apply(hash), 10));
                Random fpRNG = new Random(hash);

                for (int i = 0; i < numberFP; i++) {
                    distances.add(
                        distanceFunction.distance(
                            x,
                            y,
                            fpRNG.nextDouble() + secX,
                            fpRNG.nextDouble() + secY
                        )
                    );
                }
            }
        }
        // Skip the first (n-1) elements (If we want the closest point, we need to skip zero elements)
        return distances.stream().sorted().skip(n - 1).findFirst()
            // Not possible
            .orElseThrow(() -> new IllegalStateException("Arraylist can't have zero elements!"));
    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        List<Double> distances = new ArrayList<>();
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                long secY = iY + yOffset;

                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    long secZ = iZ + zOffset;

                    int hash = HashUtil.hash3D(seed, secX, secY, secZ);
                    // For performance reasons this can at most be 10:
                    long numberFP = Math.max(1, Math.min(fpAmountFunction.apply(hash), 10));
                    Random fpRNG = new Random(hash);
                    for (int i = 0; i < numberFP; i++) {
                        distances.add(
                            distanceFunction.distance(
                                x,
                                y,
                                z,
                                fpRNG.nextDouble() + secX,
                                fpRNG.nextDouble() + secY,
                                fpRNG.nextDouble() + secZ
                            )
                        );
                    }

                }
            }
        }
        return distances.stream().sorted().skip(n - 1).findFirst()
            .orElseThrow(() -> new IllegalStateException("Arraylist can't have zero elements!"));
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        long iX = (long) Math.floor(x);
        long iY = (long) Math.floor(y);
        long iZ = (long) Math.floor(z);
        long iW = (long) Math.floor(w);
        List<Double> distances = new ArrayList<>();
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            long secX = iX + xOffset;

            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                long secY = iY + yOffset;

                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    long secZ = iZ + zOffset;

                    for (int wOffset = -1; wOffset <= 1; wOffset++) {
                        long secW = iW + wOffset;

                        int hash = HashUtil.hash4D(seed, secX, secY, secZ, secW);
                        // For performance reasons this can at most be 10:
                        long numberFP = Math.max(1, Math.min(fpAmountFunction.apply(hash), 10));
                        Random fpRNG = new Random(hash);
                        for (int i = 0; i < numberFP; i++) {
                            distances.add(
                                distanceFunction.distance(
                                    x,
                                    y,
                                    z,
                                    w,
                                    fpRNG.nextDouble() + secX,
                                    fpRNG.nextDouble() + secY,
                                    fpRNG.nextDouble() + secZ,
                                    fpRNG.nextDouble() + secW
                                )
                            );
                        }
                    }

                }
            }
        }
        return distances.stream().sorted().skip(n - 1).findFirst()
            .orElseThrow(() -> new IllegalStateException("Arraylist can't have zero elements!"));
    }
}
