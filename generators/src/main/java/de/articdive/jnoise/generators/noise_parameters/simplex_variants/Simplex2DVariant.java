package de.articdive.jnoise.generators.noise_parameters.simplex_variants;

/**
 * Enum representing the different variants of 2D OpenSimplex2 noise.
 *
 * @author Articdive
 */
public enum Simplex2DVariant {
    /**
     * 2D OpenSimplex2 noise, standard lattice orientation.
     */
    CLASSIC,
    /**
     * 2D OpenSimplex2 noise, with Y pointing down the main diagonal.
     * Might be better for a 2D sandbox style game, where Y is vertical.
     * Probably slightly less optimal for heightmaps or continent maps,
     * unless your map is centered around an equator. It's a slight
     * difference, but the option is here to make it easy.
     */
    IMPROVE_X
}
