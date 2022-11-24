package de.articdive.jnoise.generators.noise_parameters.simplex_variants;

/**
 * Enum representing the different variants of 3D OpenSimplex2 noise.
 *
 * @author Articdive
 */
public enum Simplex3DVariant {
    /**
     * 3D OpenSimplex2 noise, fallback rotation option
     * Use {@link Simplex3DVariant#IMPROVE_XY} or {@link Simplex3DVariant#IMPROVE_XZ} instead, wherever appropriate.
     * They have less diagonal bias. This function's best use is as a fallback.
     */
    CLASSIC,
    /**
     * 3D OpenSimplex2 noise, with better visual isotropy in (X, Y).
     * Recommended for 3D terrain and time-varied animations.
     * The Z coordinate should always be the "different" coordinate in whatever your use case is.
     * If Y is vertical in world coordinates, use {@link Simplex3DVariant#IMPROVE_XZ}
     * If Z is vertical in world coordinates, use this.
     */
    IMPROVE_XY,
    /**
     * 3D OpenSimplex2 noise, with better visual isotropy in (X, Z).
     * Recommended for 3D terrain and time-varied animations.
     * The Y coordinate should always be the "different" coordinate in whatever your use case is.
     * If Y is vertical in world coordinates, use this.
     * If Z is vertical in world coordinates, use {@link Simplex3DVariant#IMPROVE_XY}.
     */
    IMPROVE_XZ
}
