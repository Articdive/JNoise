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

package de.articdive.jnoise.modifiers.clamp;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;

/**
 * Simple Modifier that clamps the noise output between 2 values.
 *
 * @author Articdive
 */
public final class ClampModifier implements NoiseModifier {
    private final double lower;
    private final double upper;

    public ClampModifier(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public double apply(double result) {
        return Math.max(lower, Math.min(upper, result));
    }
}