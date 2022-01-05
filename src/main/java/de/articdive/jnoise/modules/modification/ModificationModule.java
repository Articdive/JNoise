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

package de.articdive.jnoise.modules.modification;

import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.api.NoiseResult;
import de.articdive.jnoise.api.module.NoiseModule;
import org.jetbrains.annotations.NotNull;

/**
 * @author Articdive
 */
public class ModificationModule extends NoiseModule {
    private final Modifier modifier;
    
    ModificationModule(
        @NotNull NoiseGenerator<?> noiseGenerator,
        @NotNull Modifier modifier
    ) {
        super(noiseGenerator);
        this.modifier = modifier;
    }

    @Override
    public double apply1D(double noise, double x) {
        return modifier.modify(noise);
    }

    @Override
    @NotNull
    public NoiseResult apply1D(@NotNull NoiseResult noiseResult, double x) {
        return new ModifiedNoiseResult(apply1D(noiseResult.getPureValue(), x));
    }

    @Override
    public double apply2D(double noise, double x, double y) {
        return modifier.modify(noise);
    }

    @Override
    @NotNull
    public NoiseResult apply2D(@NotNull NoiseResult noiseResult, double x, double y) {
        return new ModifiedNoiseResult(apply2D(noiseResult.getPureValue(), x, y));
    }

    @Override
    public double apply3D(double noise, double x, double y, double z) {
        return modifier.modify(noise);
    }

    @Override
    @NotNull
    public NoiseResult apply3D(@NotNull NoiseResult noiseResult, double x, double y, double z) {
        return new ModifiedNoiseResult(apply3D(noiseResult.getPureValue(), x, y, z));
    }

    @Override
    public double apply4D(double noise, double x, double y, double z, double w) {
        return modifier.modify(noise);
    }

    @Override
    @NotNull
    public NoiseResult apply4D(@NotNull NoiseResult noiseResult, double x, double y, double z, double w) {
        return new ModifiedNoiseResult(apply4D(noiseResult.getPureValue(), x, y, z, w));
    }

    @NotNull
    public static ModificationModuleBuilder newBuilder() {
        return new ModificationModuleBuilder();
    }
}
