package de.articdive.jnoise.modules.combination;

@FunctionalInterface
public interface Combiner {
    Combiner ADD = Double::sum;
    Combiner MULTIPLY = (a, b) -> a * b;
    Combiner MAX = Math::max;
    Combiner MIN = Math::min;
    Combiner POW = Math::pow;

    double combine(double a, double b);
}