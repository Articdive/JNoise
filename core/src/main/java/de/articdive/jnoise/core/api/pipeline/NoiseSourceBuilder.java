package de.articdive.jnoise.core.api.pipeline;

import org.jspecify.annotations.NullMarked;

/**
 * Interface that denotes a builder for a {@link NoiseSource}.
 *
 * @author Articdive
 */
@NullMarked
public interface NoiseSourceBuilder {
    /**
     * Builds the NoiseSource.
     *
     * @return {@link NoiseSource} resulting from the parameters of the {@link NoiseSourceBuilder}.
     */
    NoiseSource build();
}