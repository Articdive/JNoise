/**
 * The "modifiers" JNoise module. 
 * 
 * Requires the core JNoise module.
 */
module de.articdive.jnoise.modifiers {
  exports de.articdive.jnoise.modifiers.absolute_value;
  exports de.articdive.jnoise.modifiers.clamp;
  exports de.articdive.jnoise.modifiers.inverter;

  requires transitive de.articdive.jnoise.core;
}
