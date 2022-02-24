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

package de.articdive.jnoise.core.util.vectors;

/**
 * Interface that denotes a mathematical vector.
 *
 * @author Articdive
 */
public interface Vector {
    /**
     * Gets the length of the Vector on the X axis.
     *
     * @return X component of the {@link Vector}.
     */
    double getX();

    /**
     * Gets the length of the Vector on the Y axis.
     *
     * @return Y component of the {@link Vector}.
     * @throws UnsupportedOperationException if the Vector does not have a Y component.
     */
    default double getY() {
        throw new UnsupportedOperationException("This vector does not have a Y component.");
    }

    /**
     * Gets the length of the Vector on the Z axis.
     *
     * @return Z component of the {@link Vector}.
     * @throws UnsupportedOperationException if the Vector does not have a Z component.
     */
    default double getZ() {
        throw new UnsupportedOperationException("This vector does not have a Z component.");
    }

    /**
     * Gets the length of the Vector on the W axis.
     *
     * @return W component of the {@link Vector}.
     * @throws UnsupportedOperationException if the Vector does not have a W component.
     */
    default double getW() {
        throw new UnsupportedOperationException("This vector does not have a W component.");
    }
}
