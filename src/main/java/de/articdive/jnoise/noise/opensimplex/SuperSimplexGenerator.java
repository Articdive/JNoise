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

package de.articdive.jnoise.noise.opensimplex;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.simplex_variants.Simplex2DVariant;
import de.articdive.jnoise.simplex_variants.Simplex3DVariant;
import de.articdive.jnoise.simplex_variants.Simplex4DVariant;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Articdive
 */
public final class SuperSimplexGenerator implements NoiseGenerator<SuperSimplexResult> {
    private static final Map<Long, OpenSimplex2S> simplexInstances = new HashMap<>();
    private final long seed;
    private final double frequency;
    private final Simplex2DVariant variant2D;
    private final Simplex3DVariant variant3D;
    private final Simplex4DVariant variant4D;

    SuperSimplexGenerator(
        long seed,
        double frequency,
        @NotNull Simplex2DVariant variant2D,
        @NotNull Simplex3DVariant variant3D,
        @NotNull Simplex4DVariant variant4D
    ) {
        this.seed = seed;
        this.frequency = frequency;
        this.variant2D = variant2D;
        this.variant3D = variant3D;
        this.variant4D = variant4D;
    }
    
    @Override
    public double evaluateNoise(double x, long seed) {
        return simplexInstances.computeIfAbsent(seed, OpenSimplex2S::new).noise2(x * frequency, 1.0);
    }
    
    @Override
    public double evaluateNoise(double x, double y, long seed) {
        OpenSimplex2S simplex = simplexInstances.computeIfAbsent(seed, OpenSimplex2S::new);
        
        return switch (variant2D) {
            case X_BEFORE_Y -> simplex.noise2_XBeforeY(x * frequency, y * frequency);
            case CLASSIC -> simplex.noise2(x * frequency, y * frequency);
        };
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z, long seed) {
        OpenSimplex2S simplex = simplexInstances.computeIfAbsent(seed, OpenSimplex2S::new);
        
        return switch (variant3D) {
            case XY_BEFORE_Z -> simplex.noise3_XYBeforeZ(x * frequency, y * frequency, z * frequency);
            case XZ_BEFORE_Y -> simplex.noise3_XZBeforeY(x * frequency, y * frequency, z * frequency);
            case CLASSIC -> simplex.noise3_Classic(x * frequency, y * frequency, z * frequency);
        };
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z, double w, long seed) {
        OpenSimplex2S simplex = simplexInstances.computeIfAbsent(seed, OpenSimplex2S::new);
        
        return switch (variant4D) {
            case XY_BEFORE_ZW -> simplex.noise4_XYBeforeZW(x * frequency, y * frequency, z * frequency, w * frequency);
            case XZ_BEFORE_YW -> simplex.noise4_XZBeforeYW(x * frequency, y * frequency, z * frequency, w * frequency);
            case XYZ_BEFORE_W -> simplex.noise4_XYZBeforeW(x * frequency, y * frequency, z * frequency, w * frequency);
            case CLASSIC -> simplex.noise4_Classic(x * frequency, y * frequency, z * frequency, w * frequency);
        };
    }

    @Override
    public double evaluateNoise(double x) {
        return evaluateNoise(x, seed);
    }

    @Override
    public double evaluateNoise(double x, double y) {
        return evaluateNoise(x, y, seed);

    }

    @Override
    public double evaluateNoise(double x, double y, double z) {
        return evaluateNoise(x, y, z, seed);
    }

    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        return evaluateNoise(x, y, z, w, seed);
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, long seed) {
        return new SuperSimplexResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, double y, long seed) {
        return new SuperSimplexResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, double y, double z, long seed) {
        return new SuperSimplexResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, double y, double z, double w, long seed) {
        return new SuperSimplexResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x) {
        return new SuperSimplexResult(evaluateNoise(x, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, double y) {
        return new SuperSimplexResult(evaluateNoise(x, y, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, double y, double z) {
        return new SuperSimplexResult(evaluateNoise(x, y, z, seed));
    }

    @Override
    @NotNull
    public SuperSimplexResult evaluateNoiseResult(double x, double y, double z, double w) {
        return new SuperSimplexResult(evaluateNoise(x, y, z, w, seed));
    }

    @Override
    public long getSeed() {
        return seed;
    }
}
