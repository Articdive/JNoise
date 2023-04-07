import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noisegen.worley.WorleyNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PipelineSyntaxTest {
    @Test
    @DisplayName("Syntax - Return Types")
    public void testSyntaxReturnTypes() {
        assertAll("Is the JNoiseBuilder returning the correct types for the chaining syntax?",
            () -> assertThrows(IllegalArgumentException.class, () -> JNoise.newBuilder().perlin(1729, Interpolation.LINEAR, FadeFunction.QUINTIC_POLY).buildDetailed()),
            () -> assertThrows(IllegalArgumentException.class, () -> JNoise.newBuilder().value(1729, Interpolation.LINEAR, FadeFunction.QUINTIC_POLY).buildDetailed()),
            () -> assertThrows(IllegalArgumentException.class, () -> JNoise.newBuilder().constant(1729).buildDetailed()),
            () -> assertThrows(IllegalArgumentException.class, () -> JNoise.newBuilder().white(1729).buildDetailed()),
            () -> assertThrows(IllegalArgumentException.class, () -> JNoise.newBuilder().build()),
            () -> assertThrows(IllegalArgumentException.class, () -> JNoise.newBuilder().buildDetailed()),
            () -> assertDoesNotThrow(() -> JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().build()).buildDetailed()),
            () -> assertDoesNotThrow(() -> JNoise.newBuilder().scale(0.5).worley(WorleyNoiseGenerator.newBuilder().build()).buildDetailed()),
            () -> assertDoesNotThrow(() -> JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().build()).scale(0.5).buildDetailed())
        );
    }
}