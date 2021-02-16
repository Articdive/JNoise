/*
 * JNoise
 * Copyright (C) 2021 Articdive (Lukas Mansour)
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

/**
 * @author Lukas Mansour
 */
public class FastSimplexGenerator extends NoiseGenerator<FastSimplexResult> {
    private final double frequency;
    private final OpenSimplex2F simplex;
    private final Simplex2DVariant variant2D;
    private final Simplex3DVariant variant3D;
    private final Simplex4DVariant variant4D;

    FastSimplexGenerator(
        long seed,
        double frequency,
        @NotNull Simplex2DVariant variant2D,
        @NotNull Simplex3DVariant variant3D,
        @NotNull Simplex4DVariant variant4D
    ) {
        super(seed);
        this.frequency = frequency;
        this.simplex = new OpenSimplex2F(seed);
        this.variant2D = variant2D;
        this.variant3D = variant3D;
        this.variant4D = variant4D;
    }

    @Override
    @NotNull
    public FastSimplexResult evaluateNoise(double x, double y) {
        x *= frequency;
        y *= frequency;

        double result;
        switch (variant2D) {
            case X_BEFORE_Y: {
                result = simplex.noise2_XBeforeY(x, y);
                break;
            }
            default:
            case CLASSIC: {
                result = simplex.noise2(x, y);
            }
        }

        return new FastSimplexResult(result);
    }

    @Override
    @NotNull
    public FastSimplexResult evaluateNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;

        double result;
        switch (variant3D) {
            case XY_BEFORE_Z: {
                result = simplex.noise3_XYBeforeZ(x, y, z);
                break;
            }
            case XZ_BEFORE_Y: {
                result = simplex.noise3_XZBeforeY(x, y, z);
                break;
            }
            default:
            case CLASSIC: {
                result = simplex.noise3_Classic(x, y, z);
            }
        }

        return new FastSimplexResult(result);
    }

    @Override
    @NotNull
    public FastSimplexResult evaluateNoise(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;

        double result;
        switch (variant4D) {
            case XY_BEFORE_ZW: {
                result = simplex.noise4_XYBeforeZW(x, y, z, w);
                break;
            }
            case XZ_BEFORE_YW: {
                result = simplex.noise4_XZBeforeYW(x, y, z, w);
                break;
            }
            case XYZ_BEFORE_W: {
                result = simplex.noise4_XYZBeforeW(x, y, z, w);
                break;
            }
            default:
            case CLASSIC: {
                result = simplex.noise4_Classic(x, y, z, w);
            }
        }

        return new FastSimplexResult(result);
    }
}
