package de.articdive.jnoise.core.util.vectors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Record that denotes a mathematical 3D vector with an X, Y and Z component.
 *
 * @author Articdive
 */
public final class Vector3D implements Vector {
    private final double x;
    private final double y;
    private final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Vector3D) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
            Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y) &&
            Double.doubleToLongBits(this.z) == Double.doubleToLongBits(that.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3D[" +
            "x=" + x + ", " +
            "y=" + y + ", " +
            "z=" + z + ']';
    }

    /**
     * Calculates the dot product of this vector with another 3D vector.
     *
     * @param vector3D {@link Vector3D} other vector to calculate the dot product with.
     * @return the dot product of the two vectors.
     */
    public double dot(@NotNull Vector3D vector3D) {
        return (x * vector3D.x) + (y * vector3D.y) + (z * vector3D.z);
    }
}