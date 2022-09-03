package de.articdive.jnoise.pipeline;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;
import de.articdive.jnoise.core.api.noisegen.NoiseResult;
import de.articdive.jnoise.core.api.pipeline.ExplicitNoiseSource;
import de.articdive.jnoise.core.api.transformers.DetailedTransformer;
import de.articdive.jnoise.core.api.transformers.SimpleTransformer;
import de.articdive.jnoise.core.util.vectors.Vector2D;
import de.articdive.jnoise.core.util.vectors.Vector3D;
import de.articdive.jnoise.core.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

/**
 * Secondary class for the JNoise Pipeline for handling {@link ExplicitNoiseSource}s and their {@link NoiseResult}s.
 *
 * @author Articdive
 */
public class JNoiseDetailed<NR extends NoiseResult> extends JNoise implements ExplicitNoiseSource<NR> {
    private final ExplicitNoiseSource<NR> source;

    protected JNoiseDetailed(
        @NotNull SimpleTransformer[] simpleTransformers,
        @NotNull DetailedTransformer[] detailedTransformers,
        @NotNull ExplicitNoiseSource<NR> source,
        @NotNull NoiseModifier[] modifiers
    ) {
        super(simpleTransformers, detailedTransformers, source, modifiers);
        this.source = source;
    }

    @Override
    @NotNull
    public NR evaluateNoiseResult(double x) {
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            x = simpleTransformer.transformX(x);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            x = detailedTransformer.transform(x);
        }
        NR output = source.evaluateNoiseResult(x);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }

    @Override
    @NotNull
    public NR evaluateNoiseResult(double x, double y) {
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            x = simpleTransformer.transformX(x);
            y = simpleTransformer.transformY(y);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            Vector2D vector2D = detailedTransformer.transform(x, y);
            x = vector2D.x();
            y = vector2D.y();
        }
        NR output = source.evaluateNoiseResult(x, y);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }

    @Override
    @NotNull
    public NR evaluateNoiseResult(double x, double y, double z) {
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            x = simpleTransformer.transformX(x);
            y = simpleTransformer.transformY(y);
            z = simpleTransformer.transformZ(z);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            Vector3D vector3D = detailedTransformer.transform(x, y, z);
            x = vector3D.x();
            y = vector3D.y();
            z = vector3D.z();
        }
        NR output = source.evaluateNoiseResult(x, y, z);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }

    @Override
    @NotNull
    public NR evaluateNoiseResult(double x, double y, double z, double w) {
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            x = simpleTransformer.transformX(x);
            y = simpleTransformer.transformY(y);
            z = simpleTransformer.transformZ(z);
            w = simpleTransformer.transformW(w);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            Vector4D vector4D = detailedTransformer.transform(x, y, z, w);
            x = vector4D.x();
            y = vector4D.y();
            z = vector4D.z();
            w = vector4D.w();
        }
        NR output = source.evaluateNoiseResult(x, y, z, w);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }
}
