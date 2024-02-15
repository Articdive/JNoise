package de.articdive.jnoise.core.api.transformers;

import de.articdive.jnoise.core.api.annotations.Vector1D;
import de.articdive.jnoise.core.api.annotations.Vector2D;
import de.articdive.jnoise.core.api.annotations.Vector3D;
import de.articdive.jnoise.core.api.annotations.Vector4D;
import org.jspecify.annotations.NullMarked;

/**
 * Interface that denotes a detailed transformer, which is used to transform coordinate tuples parts before the noise generation step.
 * For a more efficient transform for transforming singular coordinate parts see {@link SimpleTransformer}.
 *
 * @author Articdive
 */
@NullMarked
public interface DetailedTransformer {
    /**
     * Transforms an x coordinate before noise evaluation via a side-effect.
     *
     * @param vec1D a double array representing a 1D vector containing the x coordinate to transform.
     */
    void transform1D(@Vector1D double[] vec1D);

    /**
     * Transforms an x and y coordinate before noise evaluation via a side-effect.
     *
     * @param vec2D a double array representing a 2D vector containing the x and y coordinate to transform.
     */
    void transform2D(@Vector2D double[] vec2D);

    /**
     * Transforms an x, y and z coordinate before noise evaluation via a side-effect.
     *
     * @param vec3D a double array representing a 3D vector containing the x, y and z coordinate to transform.
     */
    void transform3D(@Vector3D double[] vec3D);

    /**
     * Transforms an x, y, z and w coordinate before noise evaluation via a side-effect.
     *
     * @param vec4D a double array representing a 4D vector containing the x, y, z and w coordinate to transform.
     */
    void transform4D(@Vector4D double[] vec4D);
}
