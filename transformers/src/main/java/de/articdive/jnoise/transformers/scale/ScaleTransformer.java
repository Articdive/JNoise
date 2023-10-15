package de.articdive.jnoise.transformers.scale;

import de.articdive.jnoise.core.api.transformers.SimpleTransformer;
import de.articdive.jnoise.core.util.vectors.Vector4D;
import org.jspecify.annotations.NullMarked;

/**
 * Simplistic Scale transformer (Frequency transformer) that multiplies each coordinate part by the specified scale value.
 *
 * @author Articdive
 */
@NullMarked
public final class ScaleTransformer implements SimpleTransformer {
    private final Vector4D scale;

    /**
     * @param scale scale value for all dimensions.
     */
    public ScaleTransformer(double scale) {
        this(scale, scale, scale, scale);
    }

    /**
     * @param scaleVector Vector containing the scale value for all dimensions.
     */
    public ScaleTransformer(Vector4D scaleVector) {
        this(scaleVector.x(), scaleVector.y(), scaleVector.z(), scaleVector.w());
    }

    /**
     * @param scaleX scale value for the X-dimension.
     * @param scaleY scale value for the Y-dimension.
     * @param scaleZ scale value for the Z-dimension.
     * @param scaleW scale value for the W-dimension.
     */
    public ScaleTransformer(double scaleX, double scaleY, double scaleZ, double scaleW) {
        if (scaleX == 0 || scaleY == 0 || scaleZ == 0 || scaleW == 0) {
            throw new IllegalArgumentException("A scale value must be a non-zero value");
        }
        this.scale = new Vector4D(scaleX, scaleY, scaleZ, scaleW);
    }

    @Override
    public double transformX(double x) {
        return x * scale.x();
    }

    @Override
    public double transformY(double y) {
        return y * scale.y();
    }

    @Override
    public double transformZ(double z) {
        return z * scale.z();
    }

    @Override
    public double transformW(double w) {
        return w * scale.w();
    }
}
