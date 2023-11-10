/**
 * The "transformers" JNoise module. 
 * 
 * Requires the core JNoise module.
 */
module de.articdive.jnoise.transformers {
  exports de.articdive.jnoise.transformers.domain_warp;
  exports de.articdive.jnoise.transformers.scale;

  requires transitive de.articdive.jnoise.core;
}