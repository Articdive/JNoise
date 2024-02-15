package de.articdive.jnoise.transformers.domain_warp;

import de.articdive.jnoise.core.api.annotations.Vector1D;
import de.articdive.jnoise.core.api.annotations.Vector2D;
import de.articdive.jnoise.core.api.annotations.Vector3D;
import de.articdive.jnoise.core.api.annotations.Vector4D;
import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.core.api.transformers.DetailedTransformer;
import org.jspecify.annotations.NullMarked;

/**
 * Domain warping transformer. This will allow for pinching, streching, twisting, bending and basically any deformation on noise.
 *
 * @author Articdive
 */
@NullMarked
public final class DomainWarpTransformer implements DetailedTransformer {
    private final NoiseSource noiseSource;
    private final @Vector4D double[] warpingVector;
    private final @Vector2D double[] offset2D;
    private final @Vector3D double[][] offset3D;
    private final @Vector4D double[][] offset4D;


    private DomainWarpTransformer(
        NoiseSource noiseSource,
        @Vector4D double[] warpingVector,
        @Vector2D double[] offset2D,
        @Vector3D double[][] offset3D,
        @Vector4D double[][] offset4D
    ) {
        this.noiseSource = noiseSource;
        this.warpingVector = warpingVector;
        this.offset2D = offset2D;
        this.offset3D = offset3D;
        this.offset4D = offset4D;
    }

    @Override
    public void transform1D(@Vector1D double[] vec1D) {
        throw new UnsupportedOperationException("A domain warp cannot be applied on a 1-dimensional field.");
    }

    @Override
    public void transform2D(@Vector2D double[] vec2D) {
        double q0 = noiseSource.evaluateNoise(vec2D[0], vec2D[1]);
        double q1 = noiseSource.evaluateNoise(vec2D[0] + offset2D[0], vec2D[1] + offset2D[1]);

        vec2D[0] += warpingVector[0] * q0;
        vec2D[1] += warpingVector[1] * q1;
    }

    @Override
    public void transform3D(@Vector3D double[] vec3D) {
        double[] offset0 = offset3D[0];
        double[] offset1 = offset3D[1];

        double q0 = noiseSource.evaluateNoise(vec3D[0], vec3D[1], vec3D[2]);
        double q1 = noiseSource.evaluateNoise(vec3D[0] + offset0[0], vec3D[1] + offset0[1], vec3D[2] + offset0[2]);
        double q2 = noiseSource.evaluateNoise(vec3D[0] + offset1[0], vec3D[1] + offset1[1], vec3D[2] + offset1[2]);

        vec3D[0] += warpingVector[0] * q0;
        vec3D[1] += warpingVector[1] * q1;
        vec3D[2] += warpingVector[2] * q2;
    }

    @Override
    public void transform4D(@Vector4D double[] vec4D) {
        double[] offset0 = offset4D[0];
        double[] offset1 = offset4D[1];
        double[] offset2 = offset4D[2];

        double q0 = noiseSource.evaluateNoise(vec4D[0], vec4D[1], vec4D[2], vec4D[3]);
        double q1 = noiseSource.evaluateNoise(
            vec4D[0] + offset0[0],
            vec4D[1] + offset0[1],
            vec4D[2] + offset0[2],
            vec4D[3] + offset0[3]
        );
        double q2 = noiseSource.evaluateNoise(
            vec4D[0] + offset1[0],
            vec4D[1] + offset1[1],
            vec4D[2] + offset1[2],
            vec4D[3] + offset1[3]
        );
        double q3 = noiseSource.evaluateNoise(
            vec4D[0] + offset2[0],
            vec4D[1] + offset2[1],
            vec4D[2] + offset2[2],
            vec4D[3] + offset2[3]
        );

        vec4D[0] += warpingVector[0] * q0;
        vec4D[1] += warpingVector[1] * q1;
        vec4D[2] += warpingVector[2] * q2;
        vec4D[3] += warpingVector[2] * q3;
    }

    /**
     * Gets a {@link DomainWarpTransformerBuilder} to build a {@link DomainWarpTransformer}.
     *
     * @return {@link DomainWarpTransformerBuilder}.
     */
    public static DomainWarpTransformerBuilder newBuilder() {
        return new DomainWarpTransformerBuilder();
    }

    /**
     * Builder for the {@link DomainWarpTransformer}.
     */
    @NullMarked
    public static final class DomainWarpTransformerBuilder {
        private NoiseSource noiseSource;
        private @Vector4D double[] warpingVector = new double[]{4, 4, 4, 4};
        private @Vector2D double[] offset2D = new double[]{5.2, 1.3};
        private @Vector3D double[][] offset3D = new double[][]{
            new double[]{5.2, 1.3, 9.2},
            new double[]{1.7, 8.3, 2.8}
        };

        private @Vector4D double[][] offset4D = new double[][]{
            new double[]{5.2, 1.3, 9.2, 2.4},
            new double[]{1.7, 8.3, 2.8, 4.3},
            new double[]{1.9, 6.2, 4.1, 8.9}
        };

        private DomainWarpTransformerBuilder() {
        }

        /**
         * Sets the noise source for the {@link DomainWarpTransformer}.
         *
         * @param noiseSource the new noise source for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        public DomainWarpTransformerBuilder setNoiseSource(NoiseSource noiseSource) {
            this.noiseSource = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link DomainWarpTransformer}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        public DomainWarpTransformerBuilder setNoiseSource(NoiseSourceBuilder noiseSourceBuilder) {
            this.noiseSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the warping vector for the {@link DomainWarpTransformer}.
         *
         * @param warpingVector double array representing the new warping vector for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        public DomainWarpTransformerBuilder setWarpingVector(@Vector4D double[] warpingVector) {
            if (warpingVector.length != 4) {
                throw new IllegalArgumentException("Warping vector must have length 4.");
            }
            this.warpingVector = warpingVector;
            return this;
        }

        /**
         * Sets the 2D offset vector for the {@link DomainWarpTransformer}.
         *
         * @param offset2D double array representing the new 2D offset for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        public DomainWarpTransformerBuilder set2DOffset(@Vector2D double[] offset2D) {
            if (offset2D.length != 2) {
                throw new IllegalArgumentException("2D offset vector must have length 2.");
            }
            this.offset2D = offset2D.clone();
            return this;
        }

        /**
         * Sets the 3D offset vector for the {@link DomainWarpTransformer}.
         * Must contain 2 3D vectors.
         *
         * @param offset3D array of double arrays representing the 3D offsets for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        public DomainWarpTransformerBuilder set3DOffset(@Vector3D double[][] offset3D) {
            if (offset3D.length != 2) {
                throw new IllegalArgumentException("You must specify 2 3D offset vectors.");
            }
            if (offset3D[0].length != 3) {
                throw new IllegalArgumentException("First 3D offset vector must have length 3.");
            }
            if (offset3D[1].length != 3) {
                throw new IllegalArgumentException("First 3D offset vector must have length 3.");
            }
            this.offset3D = new double[][]{offset3D[0].clone(), offset3D[1].clone()};
            return this;
        }

        /**
         * Sets the 4D offset vector for the {@link DomainWarpTransformer}.
         * Must contain 3 4D vectors.
         *
         * @param offset4D array of double arrays representing the 4D offsets for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        public DomainWarpTransformerBuilder set4DOffset(@Vector4D double[][] offset4D) {
            if (offset4D.length != 3) {
                throw new IllegalArgumentException("You must specify 3 4D offset vectors.");
            }
            if (offset4D[0].length != 4) {
                throw new IllegalArgumentException("First 4D offset vector must have length 4.");
            }
            if (offset4D[1].length != 4) {
                throw new IllegalArgumentException("First 4D offset vector must have length 4.");
            }
            if (offset4D[2].length != 4) {
                throw new IllegalArgumentException("First 4D offset vector must have length 4.");
            }
            this.offset4D = new double[][]{offset4D[0].clone(), offset4D[1].clone(), offset4D[2].clone()};
            return this;
        }

        /**
         * Builds the {@link DomainWarpTransformer}
         *
         * @return the built {@link DomainWarpTransformer} or throws an error if misconfigured.
         */
        public DomainWarpTransformer build() {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Noise source must be defined.");
            }
            return new DomainWarpTransformer(noiseSource, warpingVector, offset2D, offset3D, offset4D);
        }
    }
}
