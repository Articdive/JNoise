package de.articdive.jnoise.core.api.transformers;

import de.articdive.jnoise.core.util.vectors.Vector2D;
import de.articdive.jnoise.core.util.vectors.Vector3D;
import de.articdive.jnoise.core.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

/**
 * Interface that denotes a detailed transformer, which is used to transform coordinate tuples parts before the noise generation step.
 * For a more efficient transform for transforming singular coordinate parts see {@link SimpleTransformer}.
 *
 * @author Articdive
 */
public interface DetailedTransformer {
    /**
     * Transforms an x coordinate before noise evaluation.
     *
     * @param x coordinate to transform.
     * @return transformed x coordinate.
     */
    double transform(double x);

    /**
     * Transforms an x and y coordinate before noise evaluation.
     *
     * @param x X coordinate to transform.
     * @param y Y coordinate to transform.
     * @return {@link Vector2D} containing the transformed x and y coordinates.
     */
    @NotNull Vector2D transform(double x, double y);

    /**
     * Transforms an x, y and z coordinate before noise evaluation.
     *
     * @param x X coordinate to transform.
     * @param y Y coordinate to transform.
     * @param z Z coordinate to transform.
     * @return {@link Vector3D} containing the transformed x, y and z coordinates.
     */
    @NotNull Vector3D transform(double x, double y, double z);

    /**
     * Transforms an x, y, z and w coordinate before noise evaluation.
     *
     * @param x X coordinate to transform.
     * @param y Y coordinate to transform.
     * @param z Z coordinate to transform.
     * @param w W coordinate to transform.
     * @return {@link Vector4D} containing the transformed x, y, z and w coordinates.
     */
    @NotNull Vector4D transform(double x, double y, double z, double w);
}
