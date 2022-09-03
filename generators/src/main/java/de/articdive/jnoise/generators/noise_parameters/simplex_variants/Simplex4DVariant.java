package de.articdive.jnoise.generators.noise_parameters.simplex_variants;

/**
 * Enum representing the different variants of 4D OpenSimplex2 noise.
 *
 * @author Articdive
 */
public enum Simplex4DVariant {
    /**
     * 4D OpenSimplex2 noise, standard (fallback) lattice orientation.
     */
    CLASSIC,
    /**
     * 4D OpenSimplex2 noise, with XY and ZW forming orthogonal triangular-based planes.
     * Recommended for 3D terrain, where X and Y (or Z and W) are horizontal.
     * Recommended for noise(x, y, sin(time), cos(time)) trick.
     */
    IMPROVE_XY_IMPROVE_ZW,
    /**
     * 4D OpenSimplex2 noise, with XYZ oriented like noise3_ImproveXY
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     * in a space where Z is vertical
     */
    IMPROVE_XYZ_IMPROVE_XY,
    /**
     * 4D OpenSimplex2 noise, with XYZ oriented like noise3_ImproveXZ
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     * in a space where Y is vertical
     */
    IMPROVE_XYZ_IMPROVE_XZ,
    /**
     * 4D OpenSimplex2 noise, with XYZ oriented like noise3_Fallback
     * and W for an extra degree of freedom. W repeats eventually.
     * Recommended for time-varied animations which texture a 3D object (W=time)
     * where there isn't a clear distinction between horizontal and vertical
     */
    IMRPOVE_XYZ
}
