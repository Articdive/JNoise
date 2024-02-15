package de.articdive.jnoise.pipeline;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;
import de.articdive.jnoise.core.api.noisegen.NoiseResult;
import de.articdive.jnoise.core.api.pipeline.ExplicitNoiseSource;
import de.articdive.jnoise.core.api.transformers.DetailedTransformer;
import de.articdive.jnoise.core.api.transformers.SimpleTransformer;
import org.jspecify.annotations.NullMarked;

/**
 * Secondary class for the JNoise Pipeline for handling {@link ExplicitNoiseSource}s and their {@link NoiseResult}s.
 *
 * @author Articdive
 */
@NullMarked
public class JNoiseDetailed<NR extends NoiseResult> extends JNoise implements ExplicitNoiseSource<NR> {
    private final ExplicitNoiseSource<NR> source;

    JNoiseDetailed(
        SimpleTransformer[] simpleTransformers,
        DetailedTransformer[] detailedTransformers,
        ExplicitNoiseSource<NR> source,
        NoiseModifier[] modifiers
    ) {
        super(simpleTransformers, detailedTransformers, source, modifiers);
        this.source = source;
    }

    @Override
    public NR evaluateNoiseResult(final double x) {
        double[] vec1D = new double[]{x};
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            vec1D[0] = simpleTransformer.transformX(vec1D[0]);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            detailedTransformer.transform1D(vec1D);
        }
        NR output = source.evaluateNoiseResult(vec1D[0]);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }

    @Override
    public NR evaluateNoiseResult(final double x, final double y) {
        double[] vec2D = new double[]{x, y};
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            vec2D[0] = simpleTransformer.transformX(vec2D[0]);
            vec2D[1] = simpleTransformer.transformY(vec2D[1]);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            detailedTransformer.transform2D(vec2D);
        }
        NR output = source.evaluateNoiseResult(vec2D[0], vec2D[1]);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }

    @Override
    public NR evaluateNoiseResult(final double x, final double y, final double z) {
        double[] vec3D = new double[]{x, y, z};
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            vec3D[0] = simpleTransformer.transformX(vec3D[0]);
            vec3D[1] = simpleTransformer.transformY(vec3D[1]);
            vec3D[2] = simpleTransformer.transformZ(vec3D[2]);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            detailedTransformer.transform3D(vec3D);
        }
        NR output = source.evaluateNoiseResult(vec3D[0], vec3D[1], vec3D[2]);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }

    @Override
    public NR evaluateNoiseResult(final double x, final double y, final double z, final double w) {
        double[] vec4D = new double[]{x, y, z, w};
        for (SimpleTransformer simpleTransformer : simpleTransformers) {
            vec4D[0] = simpleTransformer.transformX(vec4D[0]);
            vec4D[1] = simpleTransformer.transformY(vec4D[1]);
            vec4D[2] = simpleTransformer.transformZ(vec4D[2]);
            vec4D[3] = simpleTransformer.transformZ(vec4D[2]);
        }
        for (DetailedTransformer detailedTransformer : detailedTransformers) {
            detailedTransformer.transform4D(vec4D);
        }
        NR output = source.evaluateNoiseResult(vec4D[0], vec4D[1], vec4D[2], vec4D[3]);
        for (NoiseModifier modifier : modifiers) {
            output.setValue(modifier.apply(output.getValue()));
        }
        return output;
    }
}
