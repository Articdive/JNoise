package de.articdive.jnoise.core.util.vectors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Record that denotes a mathematical 2D vector with an X and Y component.
 *
 * @author Articdive
 */
public final class Vector2D implements Vector {
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Vector2D) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
            Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2D[" +
            "x=" + x + ", " +
            "y=" + y + ']';
    }

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