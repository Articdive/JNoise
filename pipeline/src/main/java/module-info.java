module de.articdive.jnoise.pipelines {
  exports de.articdive.jnoise.pipeline;

  requires transitive de.articdive.jnoise.core;
  requires transitive de.articdive.jnoise.transformers;
  requires transitive de.articdive.jnoise.generators;
  requires transitive de.articdive.jnoise.modules;
  requires transitive de.articdive.jnoise.modifiers;
}
