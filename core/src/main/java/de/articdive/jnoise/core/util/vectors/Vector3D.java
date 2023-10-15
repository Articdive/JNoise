package de.articdive.jnoise.core.util.vectors;

import org.jspecify.annotations.NullMarked;

/**
 * Record that denotes a mathematical 3D vector with an X, Y and Z component.
 *
 * @author Articdive
 */
@NullMarked
public record Vector3D(double x, double y, double z) implements Vector {

    /**
     * Calculates the dot product of this vector with another 3D vector.
     *
     * @param vector3D {@link Vector3D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(Vector3D vector3D) {
        return (x * vector3D.x) + (y * vector3D.y) + (z * vector3D.z);
    }
}