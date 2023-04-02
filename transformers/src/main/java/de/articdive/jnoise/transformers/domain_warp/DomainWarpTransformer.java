package de.articdive.jnoise.transformers.domain_warp;

import de.articdive.jnoise.core.api.pipeline.NoiseSource;
import de.articdive.jnoise.core.api.pipeline.NoiseSourceBuilder;
import de.articdive.jnoise.core.api.transformers.DetailedTransformer;
import de.articdive.jnoise.core.util.vectors.Vector2D;
import de.articdive.jnoise.core.util.vectors.Vector3D;
import de.articdive.jnoise.core.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

/**
 * Domain warping transformer. This will allow for pinching, streching, twisting, bending and basically any deformation on noise.
 *
 * @author Articdive
 */
public final class DomainWarpTransformer implements DetailedTransformer {
    private final NoiseSource noiseSource;
    private final Vector4D warpingVector;
    private final Vector2D offset2D;
    private final Vector3D[] offset3D;
    private final Vector4D[] offset4D;


    private DomainWarpTransformer(
        @NotNull NoiseSource noiseSource,
        @NotNull Vector4D warpingVector,
        @NotNull Vector2D offset2D,
        @NotNull Vector3D[] offset3D,
        @NotNull Vector4D[] offset4D
    ) {
        this.noiseSource = noiseSource;
        this.warpingVector = warpingVector;
        this.offset2D = offset2D;
        this.offset3D = offset3D;
        this.offset4D = offset4D;
    }

    @Override
    public double transform(double x) {
        throw new UnsupportedOperationException("A domain warp cannot be applied on a 1-dimensional field.");
    }

    @Override
    public @NotNull Vector2D transform(double x, double y) {
        double q0 = noiseSource.evaluateNoise(x, y);
        double q1 = noiseSource.evaluateNoise(x + offset2D.x(), y + offset2D.y());

        return new Vector2D(x + warpingVector.x() * q0, y + warpingVector.y() * q1);
    }

    @Override
    public @NotNull Vector3D transform(double x, double y, double z) {
        Vector3D offset0 = offset3D[0];
        Vector3D offset1 = offset3D[1];

        double q0 = noiseSource.evaluateNoise(x, y, z);
        double q1 = noiseSource.evaluateNoise(x + offset0.x(), y + offset0.y(), z + offset0.z());
        double q2 = noiseSource.evaluateNoise(x + offset1.x(), y + offset1.y(), z + offset1.z());

        return new Vector3D(x + warpingVector.x() * q0, y + warpingVector.y() * q1, z + warpingVector.z() * q2);
    }

    @Override
    public @NotNull Vector4D transform(double x, double y, double z, double w) {
        Vector4D offset0 = offset4D[0];
        Vector4D offset1 = offset4D[1];
        Vector4D offset2 = offset4D[2];

        double q0 = noiseSource.evaluateNoise(x, y, z, w);
        double q1 = noiseSource.evaluateNoise(x + offset0.x(), y + offset0.y(), z + offset0.z(), w + offset0.w());
        double q2 = noiseSource.evaluateNoise(x + offset1.x(), y + offset1.y(), z + offset1.z(), w + offset1.w());
        double q3 = noiseSource.evaluateNoise(x + offset2.x(), y + offset2.y(), z + offset2.z(), w + offset2.w());

        return new Vector4D(x + warpingVector.x() * q0, y + warpingVector.y() * q1, z + warpingVector.z() * q2, w + warpingVector.w() * q3);
    }

    @NotNull
    public static DomainWarpTransformerBuilder newBuilder() {
        return new DomainWarpTransformerBuilder();
    }

    public static final class DomainWarpTransformerBuilder {
        private NoiseSource noiseSource;
        private Vector4D warpingVector = new Vector4D(4, 4, 4, 4);
        private Vector2D offset2D = new Vector2D(5.2, 1.3);
        private Vector3D[] offset3D = new Vector3D[]{new Vector3D(5.2, 1.3, 9.2), new Vector3D(1.7, 8.3, 2.8)};

        private Vector4D[] offset4D = new Vector4D[]{new Vector4D(5.2, 1.3, 9.2, 2.4), new Vector4D(1.7, 8.3, 2.8, 4.3), new Vector4D(1.9, 6.2, 4.1, 8.9)};

        private DomainWarpTransformerBuilder() {
        }

        /**
         * Sets the noise source for the {@link DomainWarpTransformer}.
         *
         * @param noiseSource the new noise source for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        @NotNull
        public DomainWarpTransformerBuilder setNoiseSource(NoiseSource noiseSource) {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Noise source cannot be null.");
            }
            this.noiseSource = noiseSource;
            return this;
        }

        /**
         * Sets the noise source for the {@link DomainWarpTransformer}.
         *
         * @param noiseSourceBuilder the new noise source for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        @NotNull
        public DomainWarpTransformerBuilder setNoiseSource(NoiseSourceBuilder noiseSourceBuilder) {
            if (noiseSourceBuilder == null) {
                throw new IllegalArgumentException("Noise source cannot be null.");
            }
            this.noiseSource = noiseSourceBuilder.build();
            return this;
        }

        /**
         * Sets the warping vector for the {@link DomainWarpTransformer}.
         *
         * @param warpingVector {@link Vector4D} the new warping vector for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        @NotNull
        public DomainWarpTransformerBuilder setWarpingVector(Vector4D warpingVector) {
            if (warpingVector == null) {
                throw new IllegalArgumentException("Warping vector cannot be null.");
            }
            this.warpingVector = warpingVector;
            return this;
        }

        /**
         * Sets the 2D offset for the {@link DomainWarpTransformer}.
         *
         * @param offset2D {@link Vector2D} the new 2D offset for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        @NotNull
        public DomainWarpTransformerBuilder set2DOffset(Vector2D offset2D) {
            if (offset2D == null) {
                throw new IllegalArgumentException("2D Offset cannot be null.");
            }
            this.offset2D = offset2D;
            return this;
        }

        /**
         * Sets the 3D offset for the {@link DomainWarpTransformer}.
         * Must contain 2 3D Vectors.
         *
         * @param offset3D {@link Vector3D} the new 3D offset for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        @NotNull
        public DomainWarpTransformerBuilder set3DOffset(Vector3D... offset3D) {
            if (offset3D == null) {
                throw new IllegalArgumentException("3D Offset cannot be null.");
            }
            if (offset3D.length < 2) {
                throw new IllegalArgumentException("3D Offset must have length 2 (Elements beyond index 1 will be ignored).");
            }
            if (offset3D[0] == null || offset3D[1] == null) {
                throw new IllegalArgumentException("3D Offset must contain non-null values.");
            }
            this.offset3D = new Vector3D[]{offset3D[0], offset3D[1]};
            return this;
        }

        /**
         * Sets the 4D offset for the {@link DomainWarpTransformer}.
         * Must contain 3 4D Vectors.
         *
         * @param offset4D {@link Vector4D} the new 4D offset for the {@link DomainWarpTransformer}.
         * @return {@link DomainWarpTransformerBuilder} this
         */
        @NotNull
        public DomainWarpTransformerBuilder set4DOffset(Vector4D... offset4D) {
            if (offset4D == null) {
                throw new IllegalArgumentException("4D Offset cannot be null.");
            }
            if (offset4D.length < 2) {
                throw new IllegalArgumentException("4D Offset must have length 2 (Elements beyond index 1 will be ignored).");
            }
            if (offset4D[0] == null || offset4D[1] == null || offset4D[2] == null) {
                throw new IllegalArgumentException("4D Offset must contain non-null values.");
            }
            this.offset4D = new Vector4D[]{offset4D[0], offset4D[1], offset4D[2]};
            return this;
        }

        @NotNull
        public DomainWarpTransformer build() {
            if (noiseSource == null) {
                throw new IllegalArgumentException("Noise source has not been defined.");
            }
            return new DomainWarpTransformer(noiseSource, warpingVector, offset2D, offset3D, offset4D);
        }
    }
}
