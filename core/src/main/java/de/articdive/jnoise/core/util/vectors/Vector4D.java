package de.articdive.jnoise.core.util.vectors;

import org.jspecify.annotations.NullMarked;

/**
 * Record that denotes a mathematical 4D vector with an X, Y, Z and W component.
 *
 * @author Articdive
 */
@NullMarked
public record Vector4D(double x, double y, double z, double w) implements Vector {

    /**
     * Calculates the dot product of this vector with another 4D vector.
     *
     * @param vector4D {@link Vector4D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(Vector4D vector4D) {
        return (x * vector4D.x) + (y * vector4D.y) + (z * vector4D.z) + (w * vector4D.w);
    }
}