package de.articdive.jnoise.core.api.noisegen;

import de.articdive.jnoise.core.api.pipeline.ExplicitNoiseSource;

/**
 * Interface that denotes a {@link NoiseGenerator}, which can additionally evaluate a {@link NoiseResult} at a location.
 *
 * @param <NR> {@link NoiseResult} class
 * @author Articdive
 */
public interface ExplicitNoiseGenerator<NR extends NoiseResult> extends ExplicitNoiseSource<NR>, NoiseGenerator {
}
