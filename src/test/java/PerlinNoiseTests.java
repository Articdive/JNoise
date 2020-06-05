import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.interpolation.InterpolationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lukas Mansour
 */
class PerlinNoiseTests {
    private final JNoise perlinLinear = JNoise.newBuilder().perlin().setInterpolationType(InterpolationType.LINEAR).build();
    private final JNoise perlinLinearPrecise = JNoise.newBuilder().perlin().setInterpolationType(InterpolationType.LINEAR_PRECISE).build();
    private final JNoise perlinCosine = JNoise.newBuilder().perlin().setInterpolationType(InterpolationType.COSINE).build();
    private final JNoise perlinNearestNeighbor = JNoise.newBuilder().perlin().setInterpolationType(InterpolationType.NEAREST_NEIGHBOUR).build();
    
    @Test
    @DisplayName("Linear Noise Zero Equality")
    void testLinearZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i)));
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i, i, i, i)));
        }
    }
    
    @Test
    @DisplayName("Linear Precise Zero Equality")
    void testLinearPreciseZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinLinearPrecise.getNoise(i)));
            assertEquals(0.0, Math.abs(perlinLinearPrecise.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinLinearPrecise.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinLinearPrecise.getNoise(i, i, i, i)));
        }
    }
    
    @Test
    @DisplayName("Cosine Zero Equality")
    void testCosineZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i)));
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i, i, i, i)));
        }
    }
    
    @Test
    @DisplayName("Nearest-Neighbor Zero Equality")
    void testNearestNeighborZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinNearestNeighbor.getNoise(i)));
            assertEquals(0.0, Math.abs(perlinNearestNeighbor.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinNearestNeighbor.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinNearestNeighbor.getNoise(i, i, i, i)));
        }
    }
}
