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

package de.articdive.jnoise.api.module;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.NoiseResult;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public abstract class NoiseModule {
    protected final NoiseGenerator<?> noiseGenerator;

    protected NoiseModule(@NotNull NoiseGenerator<?> noiseGenerator) {
        this.noiseGenerator = noiseGenerator;
    }

    public abstract double apply1D(double noise, double x);

    @NotNull
    public abstract NoiseResult apply1D(@NotNull NoiseResult noiseResult, double x);

    public abstract double apply2D(double noise, double x, double y);

    @NotNull
    public abstract NoiseResult apply2D(@NotNull NoiseResult noiseResult, double x, double y);

    public abstract double apply3D(double noise, double x, double y, double z);

    @NotNull
    public abstract NoiseResult apply3D(@NotNull NoiseResult noiseResult, double x, double y, double z);

    public abstract double apply4D(double noise, double x, double y, double z, double w);

    @NotNull
    public abstract NoiseResult apply4D(@NotNull NoiseResult noiseResult, double x, double y, double z, double w);
}
