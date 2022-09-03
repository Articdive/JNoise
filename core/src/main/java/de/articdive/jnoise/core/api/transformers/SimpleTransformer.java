package de.articdive.jnoise.core.api.transformers;

/**
 * Interface that denotes a simple transformer, which is used to transform a singular coordinate parts before the noise generation step.
 * For a transformer which can transform coordinate tuples see {@link DetailedTransformer}.
 *
 * @author Articdive
 */
public interface SimpleTransformer {
    /**
     * Transforms an x coordinate before noise evaluation.
     *
     * @param x coordinate to transform.
     * @return transformed x coordinate.
     */
    double transformX(double x);

    /**
     * Transforms a y coordinate before noise evaluation.
     *
     * @param y coordinate to transform.
     * @return transformed y coordinate.
     */
    double transformY(double y);

    /**
     * Transforms a z coordinate before noise evaluation.
     *
     * @param z coordinate to transform.
     * @return transformed z coordinate.
     */
    double transformZ(double z);

    /**
     * Transforms a w coordinate before noise evaluation.
     *
     * @param w coordinate to transform.
     * @return transformed w coordinate.
     */
    double transformW(double w);
}
