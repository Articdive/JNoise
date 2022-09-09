package de.articdive.jnoise.core.util.vectors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Record that denotes a mathematical 4D vector with an X, Y, Z and W component.
 *
 * @author Articdive
 */
public final class Vector4D implements Vector {
    private final double x;
    private final double y;
    private final double z;
    private final double w;

    public Vector4D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public double w() {
        return w;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Vector4D) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
            Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y) &&
            Double.doubleToLongBits(this.z) == Double.doubleToLongBits(that.z) &&
            Double.doubleToLongBits(this.w) == Double.doubleToLongBits(that.w);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "Vector4D[" +
            "x=" + x + ", " +
            "y=" + y + ", " +
            "z=" + z + ", " +
            "w=" + w + ']';
    }

    /**
     * Calculates the dot product of this vector with another 4D vector.
     *
     * @param vector4D {@link Vector4D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(@NotNull Vector4D vector4D) {
        return (x * vector4D.x) + (y * vector4D.y) + (z * vector4D.z) + (w * vector4D.w);
    }
}