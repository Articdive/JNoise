package de.articdive.jnoise.core.api.pipeline;

import org.jetbrains.annotations.NotNull;

/**
 * Interface that denotes a builder for a {@link NoiseSource}.
 *
 * @author Articdive
 */
public interface NoiseSourceBuilder {
    /**
     * Builds the NoiseSource.
     *
     * @return {@link NoiseSource} resulting from the parameters of the {@link NoiseSourceBuilder}.
     */
    @NotNull NoiseSource build();
}