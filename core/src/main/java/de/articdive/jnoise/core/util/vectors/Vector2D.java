package de.articdive.jnoise.core.util.vectors;

import org.jetbrains.annotations.NotNull;

/**
 * Record that denotes a mathematical 2D vector with an X and Y component.
 *
 * @author Articdive
 */
public record Vector2D(double x, double y) implements Vector {

    /**
     * Calculates the dot product of this vector with another 2D vector.
     *
     * @param vector2D {@link Vector2D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(@NotNull Vector2D vector2D) {
        return (x * vector2D.x) + (y * vector2D.y);
    }
}