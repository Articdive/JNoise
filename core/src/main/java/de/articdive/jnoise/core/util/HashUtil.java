package de.articdive.jnoise.core.util;

/**
 * Utility class for calculating hashes using a simple collection of primes.
 *
 * @author Articdive
 */
public final class HashUtil {
    /**
     * Constant used for calculating hashes along the X axis.
     */
    public static final int X_PRIME = 1619;
    /**
     * Constant used for calculating hashes along the Y axis.
     */
    public static final int Y_PRIME = 31337;
    /**
     * Constant used for calculating hashes along the Z axis.
     */
    public static final int Z_PRIME = 6971;
    /**
     * Constant used for calculating hashes along the W axis.
     */
    public static final int W_PRIME = 1013;

    private HashUtil() {

    }

    /**
     * Hashes a 1D point using a seed.
     *
     * @param seed seed to hash with.
     * @param x    X-Coordinate of the 1D point.
     * @return hash value of the 1D point.
     */
    public static int hash1D(long seed, long x) {
        long hash = seed ^ (X_PRIME * x);

        return (int) finalizeHash(hash);
    }

    /**
     * Hashes a 2D point using a seed.
     *
     * @param seed seed to hash with.
     * @param x    X-Coordinate of the 2D point.
     * @param y    Y-Coordinate of the 2D point.
     * @return hash value of the 2D point.
     */
    public static int hash2D(long seed, long x, long y) {
        long hash = seed ^ (X_PRIME * x);
        hash ^= (Y_PRIME * y);

        return (int) finalizeHash(hash);
    }

    /**
     * Hashes a 3D point using a seed.
     *
     * @param seed seed to hash with.
     * @param x    X-Coordinate of the 3D point.
     * @param y    Y-Coordinate of the 3D point.
     * @param z    Z-Coordinate of the 3D Point.
     * @return hash value of the 3D point.
     */
    public static int hash3D(long seed, long x, long y, long z) {
        long hash = seed ^ (X_PRIME * x);
        hash ^= (Y_PRIME * y);
        hash ^= (Z_PRIME * z);

        return (int) finalizeHash(hash);
    }

    /**
     * Hashes a 4D point using a seed.
     *
     * @param seed seed to hash with.
     * @param x    X-Coordinate of the 4D point.
     * @param y    Y-Coordinate of the 4D point.
     * @param z    Z-Coordinate of the 4D Point.
     * @param w    W-Coordinate of the 4D Point.
     * @return hash value of the 4D point.
     */
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
