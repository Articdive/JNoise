package de.articdive.jnoise.core.util.vectors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Record that denotes a mathematical 1D vector with an X component.
 *
 * @author Articdive
 */
public final class Vector1D implements Vector {
    private final double x;

    public Vector1D(double x) {
        this.x = x;
    }

    public double x() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Vector1D) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    @Override
    public String toString() {
        return "Vector1D[" +
            "x=" + x + ']';
    }

    /**
     * Calculates the dot product of this vector with another 1D vector.
     *
     * @param vector1D {@link Vector1D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(@NotNull Vector1D vector1D) {
        return (x * vector1D.x);
    }
}
