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

package de.articdive.jnoise.noise.worley;

import de.articdive.jnoise.api.NoiseResult;
import de.articdive.jnoise.util.vectors.Vector;
import org.jetbrains.annotations.Nullable;

/**
 * @author Lukas Mansour
 */
public final class WorleyNoiseResult<V extends Vector> implements NoiseResult {
    private final double noiseValue;
    private final V closestPoint;

    WorleyNoiseResult(double noiseValue, @Nullable V closestPoint) {
        this.noiseValue = noiseValue;
        this.closestPoint = closestPoint;
    }

    @Override
    public double getNoiseValue() {
        return noiseValue;
    }

    @Nullable
    public V getClosestPoint() {
        return closestPoint;
    }
}
