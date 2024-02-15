/**
 * The core JNoise module.
 *
 * Requires JSpecify.
 */
module de.articdive.jnoise.core {
  exports de.articdive.jnoise.core.api.functions;
  exports de.articdive.jnoise.core.api.modifiers;
  exports de.articdive.jnoise.core.api.modules;
  exports de.articdive.jnoise.core.api.noisegen;
  exports de.articdive.jnoise.core.api.pipeline;
  exports de.articdive.jnoise.core.api.transformers;

  exports de.articdive.jnoise.core.util;
  exports de.articdive.jnoise.core.api.annotations;

  requires transitive org.jspecify;
}
