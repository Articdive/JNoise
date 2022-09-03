package de.articdive.jnoise.core.api.pipeline;

import de.articdive.jnoise.core.api.noisegen.NoiseResult;
import org.jetbrains.annotations.NotNull;

/**
 * Interface that denotes an explicit {@link NoiseSource}, which can evaluate a {@link NoiseResult} at a location.
 * Used everywhere where a {@link NoiseSource} has a non-double as a result.
 *
 * @author Articdive
 */
public interface ExplicitNoiseSource<NR extends NoiseResult> extends NoiseSource {
    /**
     * Evaluates noise at a 1D point.
     *
     * @param x X-Coordinate of the 1D point.
     * @return {@link NR} denoting the noise value at the 1D point.
     */
    @NotNull
    NR evaluateNoiseResult(double x);

    /**
     * Evaluates noise at a 2D point.
     *
     * @param x X-Coordinate of the 2D point.
     * @param y Y-Coordinate of the 2D point.
     * @return {@link NR} denoting the noise value at the 2D point.
     */
    @NotNull
    NR evaluateNoiseResult(double x, double y);

    /**
     * Evaluates noise at a 3D point.
     *
     * @param x X-Coordinate of the 3D point.
     * @param y Y-Coordinate of the 3D point.
     * @param z Z-Coordinate of the 3D point.
     * @return {@link NR} denoting the noise value at the 3D point.
     */
    @NotNull
    NR evaluateNoiseResult(double x, double y, double z);

    /**
     * Evaluates noise at a 4D point.
     *
     * @param x X-Coordinate of the 4D point.
     * @param y Y-Coordinate of the 4D point.
     * @param z Z-Coordinate of the 4D point.
     * @param w W-Coordinate of the 4D point.
     * @return {@link NR} denoting the noise value at the 4D point.
     */
    @NotNull
    NR evaluateNoiseResult(double x, double y, double z, double w);
}
