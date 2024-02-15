package de.articdive.jnoise.generators.noisegen.worley;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;
import de.articdive.jnoise.core.api.noisegen.NoiseResult;
import org.jspecify.annotations.Nullable;

/**
 * This class wraps the result of Worley Noise.
 *
 * @author Articdive
 */
public final class WorleyNoiseResult implements NoiseResult {
    private final @Nullable double[] closestPoint;
    private final double unmodifiedValue;
    private double value;

    WorleyNoiseResult(double value, @Nullable double[] closestPoint) {
        this.unmodifiedValue = value;
        this.value = value;
        this.closestPoint = closestPoint;
    }

    /**
     * Gets the current (already modified) noise value.
     *
     * @return the current, possibly modified, noise value.
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * Sets the noise value, usually used by {@link NoiseModifier} in post-processing.
     *
     * @param value new output for the pure mathematical value of the noise result.
     */
    @Override
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * The unmodified noise value. (The noise before modifiers were run).
     *
     * @return the unmodified noise value.
     */
    public double getUnmodifiedValue() {
        return unmodifiedValue;
    }

    /**
     * Returns the feature point to the specified noise coordinates.
     *
     * @return array of double representing the location vector of the closest point.
     */
    @Nullable
    public double[] getClosestPoint() {
        return closestPoint;
    }
}
