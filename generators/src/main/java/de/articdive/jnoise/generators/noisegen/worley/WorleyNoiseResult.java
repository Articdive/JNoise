/*
 * JNoise
 * Copyright (C) 2020-2022 Articdive
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

package de.articdive.jnoise.generators.noisegen.worley;

import de.articdive.jnoise.core.api.noisegen.NoiseResult;
import de.articdive.jnoise.core.util.vectors.Vector;
import org.jetbrains.annotations.Nullable;

/**
 * This class wraps the result of Worley Noise.
 *
 * @author Articdive
 */
public final class WorleyNoiseResult<V extends Vector> implements NoiseResult {
    private final @Nullable V closestPoint;
    private final double unmodifiedValue;
    private double value;

    public WorleyNoiseResult(double value, @Nullable V closestPoint) {
        this.unmodifiedValue = value;
        this.value = value;
        this.closestPoint = closestPoint;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    public double getUnmodifiedValue() {
        return unmodifiedValue;
    }

    @Nullable
    public V getClosestPoint() {
        return closestPoint;
    }
}
