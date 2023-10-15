package de.articdive.jnoise.core.util.vectors;

import org.jspecify.annotations.NullMarked;

/**
 * Record that denotes a mathematical 1D vector with an X component.
 *
 * @author Articdive
 */
@NullMarked
public record Vector1D(double x) implements Vector {

    /**
     * Calculates the dot product of this vector with another 1D vector.
     *
     * @param vector1D {@link Vector1D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(Vector1D vector1D) {
        return (x * vector1D.x);
    }
}
