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

package de.articdive.jnoise.transformers.scale;

import de.articdive.jnoise.core.api.transformers.SimpleTransformer;

public final class ScaleTransformer implements SimpleTransformer {
    private final double scaleX;
    private final double scaleY;
    private final double scaleZ;
    private final double scaleW;

    public ScaleTransformer(double scale) {
        this(scale, scale, scale, scale);
    }

    public ScaleTransformer(double scaleX, double scaleY, double scaleZ, double scaleW) {
        if (scaleX == 0 || scaleY == 0 || scaleZ == 0 || scaleW == 0) {
            throw new IllegalArgumentException("A scale value must be a non-zero value");
        }
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.scaleW = scaleW;

    }

    @Override
    public double transformX(double coord) {
        return coord * scaleX;
    }

    @Override
    public double transformY(double coord) {
        return coord * scaleY;
    }

    @Override
    public double transformZ(double coord) {
        return coord * scaleZ;
    }

    @Override
    public double transformW(double coord) {
        return coord * scaleW;
    }
}
