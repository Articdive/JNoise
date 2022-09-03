package de.articdive.jnoise.transformers.scale;

import de.articdive.jnoise.core.api.transformers.SimpleTransformer;

/**
 * Simplistic Scale transformer (Frequency transformer) that multiplies each coordinate part by the specified scale value.
 *
 * @author Articdive
 */
public final class ScaleTransformer implements SimpleTransformer {
    private final double scaleX;
    private final double scaleY;
    private final double scaleZ;
    private final double scaleW;

    /**
     * @param scale scale value for all dimensions.
     */
    public ScaleTransformer(double scale) {
        this(scale, scale, scale, scale);
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
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
        this.scaleW = scaleW;
    }

    @Override
    public double transformX(double x) {
        return x * scaleX;
    }

    @Override
    public double transformY(double y) {
        return y * scaleY;
    }

    @Override
    public double transformZ(double z) {
        return z * scaleZ;
    }

    @Override
    public double transformW(double w) {
        return w * scaleW;
    }
}
