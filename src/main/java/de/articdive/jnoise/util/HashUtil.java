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

package de.articdive.jnoise.util;

/**
 * @author Lukas Mansour
 */
public final class HashUtil {
    public static final int X_PRIME = 1619;
    public static final int Y_PRIME = 31337;
    public static final int Z_PRIME = 6971;
    public static final int W_PRIME = 1013;

    private HashUtil() {

    }

    public static int hash2D(long seed, long x, long y) {
        long hash = seed ^ (X_PRIME * x);
        hash ^= (Y_PRIME * y);

        return (int) finalizeHash(hash);
    }

    public static int hash3D(long seed, long x, long y, long z) {
        long hash = seed ^ (X_PRIME * x);
        hash ^= (Y_PRIME * y);
        hash ^= (Z_PRIME * z);

        return (int) finalizeHash(hash);
    }

    public static int hash4D(long seed, long x, long y, long z, long w) {
        long hash = seed ^ (X_PRIME * x);
        hash ^= (Y_PRIME * y);
        hash ^= (Z_PRIME * z);
        hash ^= (W_PRIME * w);

        return (int) finalizeHash(hash);
    }

    private static long finalizeHash(long hash) {
        hash = hash * hash * hash * 60493;
        return (hash >> 13) ^ hash;
    }
}
