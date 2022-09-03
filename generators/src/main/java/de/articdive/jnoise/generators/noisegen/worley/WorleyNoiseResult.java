package de.articdive.jnoise.generators.noisegen.worley;

import de.articdive.jnoise.core.api.noisegen.NoiseResult;
import de.articdive.jnoise.core.util.vectors.Vector;
import org.jetbrains.annotations.Nullable;

/**
 * This class wraps the result of Worley Noise.
 *
 * @author Articdive
 */
public final class WorleyNoiseResult<V extends Vector> implements NoiseResult {
    private final @Nullable V closestPoint;
    private final double unmodifiedValue;
    private double value;

    public WorleyNoiseResult(double value, @Nullable V closestPoint) {
        this.unmodifiedValue = value;
        this.value = value;
        this.closestPoint = closestPoint;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    public double getUnmodifiedValue() {
        return unmodifiedValue;
    }

    @Nullable
    public V getClosestPoint() {
        return closestPoint;
    }
}
