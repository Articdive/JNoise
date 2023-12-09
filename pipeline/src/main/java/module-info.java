/**
 * The "pipeline" JNoise module. 
 * 
 * Requires the "core", "transformers", "generators", 
 * "modules" and "modifiers" JNoise module.
 */
module de.articdive.jnoise.pipeline {
  exports de.articdive.jnoise.pipeline;

  requires transitive de.articdive.jnoise.core;
  requires transitive de.articdive.jnoise.transformers;
  requires transitive de.articdive.jnoise.generators;
  requires transitive de.articdive.jnoise.modules;
  requires transitive de.articdive.jnoise.modifiers;
}
