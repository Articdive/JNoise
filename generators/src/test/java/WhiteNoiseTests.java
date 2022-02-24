import de.articdive.jnoise.generators.noisegen.white.WhiteNoiseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Articdive
 */
final class WhiteNoiseTests {
    public static final double ACCEPTABLE_DEVIATION = 1E-10;
    private final WhiteNoiseGenerator white = WhiteNoiseGenerator.newBuilder().build();

    @Test
    @DisplayName("White Noise 1D - Integer Coordinates")
    void testInteger1D() {
        assertAll("Is 1D White Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.38150172820314765, white.evaluateNoise(0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6811563670635223, white.evaluateNoise(-1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3334684185683727, white.evaluateNoise(1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2934371498413384, white.evaluateNoise(-2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.021508003119379282, white.evaluateNoise(2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8569407574832439, white.evaluateNoise(5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7796478271484375, white.evaluateNoise(-5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.614248491358012, white.evaluateNoise(100), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1911370432935655, white.evaluateNoise(-1000), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6774313128553331, white.evaluateNoise(24), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 1D - Positive Coordinates")
    void testPositive1D() {
        assertAll("Is 1D White Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.38150172820314765, white.evaluateNoise(0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3334684185683727, white.evaluateNoise(1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.021508003119379282, white.evaluateNoise(2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24051928520202637, white.evaluateNoise(3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.47399362130090594, white.evaluateNoise(70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5934749431908131, white.evaluateNoise(33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.47752114525064826, white.evaluateNoise(10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.9547901153564453, white.evaluateNoise(11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5, white.evaluateNoise(27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19707545265555382, white.evaluateNoise(120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 1D - Negative Coordinates")
    void testNegative1D() {
        assertAll("Is 1D White Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.6811563670635223, white.evaluateNoise(-0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2934371498413384, white.evaluateNoise(-1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08031630888581276, white.evaluateNoise(-2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08031630888581276, white.evaluateNoise(-3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9566271267831326, white.evaluateNoise(-70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.44179584411904216, white.evaluateNoise(-33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5477072186768055, white.evaluateNoise(-10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5477072186768055, white.evaluateNoise(-11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.10853139264509082, white.evaluateNoise(-27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.861331295222044, white.evaluateNoise(-90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 1D - Random Coordinates")
    void testRandom1D() {
        Random random = new Random(1000);
        assertAll("Is 1D White Noise outputting correct values?",
            () -> assertEquals(-0.4407958984375, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.24723024293780327, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3730596168898046, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7191150188446045, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6257446892559528, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.14014064893126488, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5180041142739356, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2507598102092743, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.15891986386850476, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.743461053352803, white.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 1D - Bounds")
    void test1DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 24); i++) {
            double x = random.nextDouble() * random.nextInt(100000);
            double noise = white.evaluateNoise(x);
            if (noise < -1 || noise > 1) {
                fail("Is 1D White Noise outputting in the correct interval?");
            }
        }
    }

    @Test
    @DisplayName("White Noise 2D - Integer Coordinates")
    void testInteger2D() {
        assertAll("Is 2D White Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.10761078679934144, white.evaluateNoise(0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4497237503528595, white.evaluateNoise(-1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.30410805717110634, white.evaluateNoise(1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.21563315391540527, white.evaluateNoise(-2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.28381386771798134, white.evaluateNoise(2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07776448130607605, white.evaluateNoise(5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7052444629371166, white.evaluateNoise(-5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7950621545314789, white.evaluateNoise(100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8749152310192585, white.evaluateNoise(-1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6774313128553331, white.evaluateNoise(24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 2D - Positive Coordinates")
    void testPositive2D() {
        assertAll("Is 2D White Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.5676898658275604, white.evaluateNoise(0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.13972868910059333, white.evaluateNoise(1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8554643094539642, white.evaluateNoise(2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08436227962374687, white.evaluateNoise(3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7567347325384617, white.evaluateNoise(70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.455742746591568, white.evaluateNoise(33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03176030516624451, white.evaluateNoise(10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08475356176495552, white.evaluateNoise(11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6847655647434294, white.evaluateNoise(27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19707545265555382, white.evaluateNoise(120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 2D - Negative Coordinates")
    void testNegative2D() {
        assertAll("Is 2D White Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.43065660586580634, white.evaluateNoise(-0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6171135394833982, white.evaluateNoise(-1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.367763954680413, white.evaluateNoise(-2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.367763954680413, white.evaluateNoise(-3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10753872990608215, white.evaluateNoise(-70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4346662126481533, white.evaluateNoise(-33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9831564468331635, white.evaluateNoise(-10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9831564468331635, white.evaluateNoise(-11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9315785025246441, white.evaluateNoise(-27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.41939677530899644, white.evaluateNoise(-90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 2D - Random Coordinates")
    void testRandom2D() {
        Random random = new Random(1000);
        assertAll("Is 2D White Noise outputting correct values?",
            () -> assertEquals(0.2878007688559592, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7498940490186214, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3705916558392346, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1979180984199047, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.543880449142307, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2987648434937, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2480439110659063, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5913050933741033, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0966000072658062, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.13755062595009804, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 2D - Bounds")
    void test2DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 10); i++) {
            for (int j = 0; j < (2 << 10); j++) {
                double x = random.nextDouble() * random.nextInt(100000);
                double y = random.nextDouble() * random.nextInt(100000);
                double noise = white.evaluateNoise(x, y);
                if (noise < -1 || noise > 1) {
                    fail("Is 2D White Noise outputting in the correct interval?");
                }
            }
        }
    }

    @Test
    @DisplayName("White Noise 3D - Integer Coordinates")
    void testInteger3D() {
        assertAll("Is 3D White Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.10761078679934144, white.evaluateNoise(0, 24, 0.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.29658822575584054, white.evaluateNoise(-1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4473263989202678, white.evaluateNoise(1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.36511148139834404, white.evaluateNoise(-2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8141263425350189, white.evaluateNoise(2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.968206490855664, white.evaluateNoise(5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1259607500396669, white.evaluateNoise(-5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.40772509574890137, white.evaluateNoise(100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1358720399439335, white.evaluateNoise(-1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4431033371947706, white.evaluateNoise(24, 0, 24.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 3D - Positive Coordinates")
    void testPositive3D() {
        assertAll("Is 3D White Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.5676898658275604, white.evaluateNoise(0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8751044236123562, white.evaluateNoise(1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.42865564301609993, white.evaluateNoise(2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.21870356099680066, white.evaluateNoise(3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.46882033348083496, white.evaluateNoise(70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.15645245602354407, white.evaluateNoise(33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.039243366569280624, white.evaluateNoise(10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.36145691154524684, white.evaluateNoise(11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.9946460723876953, white.evaluateNoise(27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7630849159322679, white.evaluateNoise(120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 3D - Negative Coordinates")
    void testNegative3D() {
        assertAll("Is 3D White Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.8078044950962067, white.evaluateNoise(-0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.354414586443454, white.evaluateNoise(-1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.22499367594718933, white.evaluateNoise(-2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.22499367594718933, white.evaluateNoise(-3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17656220262870193, white.evaluateNoise(-70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9887027740478516, white.evaluateNoise(-33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4371553957462311, white.evaluateNoise(-10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4371553957462311, white.evaluateNoise(-11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7496939464472234, white.evaluateNoise(-27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2870148606598377, white.evaluateNoise(-90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 3D - Random Coordinates")
    void testRandom3D() {
        Random random = new Random(1000);
        assertAll("Is 3D White Noise outputting correct values?",
            () -> assertEquals(-0.10508581763133407, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1890907771885395, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3642449676990509, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08479508757591248, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10988705093041062, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9744691140949726, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.43603894440457225, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4761537201702595, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3138611684553325, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6180735058151186, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 3D - Bounds")
    void test3DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 7); i++) {
            for (int j = 0; j < (2 << 7); j++) {
                for (int k = 0; k < (2 << 7); k++) {
                    double x = random.nextDouble() * random.nextInt(100000);
                    double y = random.nextDouble() * random.nextInt(100000);
                    double z = random.nextDouble() * random.nextInt(100000);
                    double noise = white.evaluateNoise(x, y, z);
                    if (noise < -1 || noise > 1) {
                        fail("Is 3D White Noise outputting in the correct interval?");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("White Noise 4D - Integer Coordinates")
    void testInteger4D() {
        assertAll("Is 4D White Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.3900879113934934, white.evaluateNoise(0, 24, 0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6317232572473586, white.evaluateNoise(-1, -1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.79304901277646422, white.evaluateNoise(1, 100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7256476623006165, white.evaluateNoise(-2, -5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7256476623006165, white.evaluateNoise(2, 5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.017333501484245062, white.evaluateNoise(5, 2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.017333501484245062, white.evaluateNoise(-5, -2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.9071244751103222, white.evaluateNoise(100, 1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.9586504404433072, white.evaluateNoise(-1000, -1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4431033371947706, white.evaluateNoise(24, 0, 24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 4D - Positive Coordinates")
    void testPositive4D() {
        assertAll("Is 4D White Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.6426268187351525, white.evaluateNoise(0.5, 120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6559396316297352, white.evaluateNoise(1.5, 27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0957421618513763, white.evaluateNoise(2.5, 11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3741967319510877, white.evaluateNoise(3, 10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.34856539173051715, white.evaluateNoise(70.654, 33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.20006553223356605, white.evaluateNoise(33.14, 70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9043144839815795, white.evaluateNoise(10.33, 3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4919377057813108, white.evaluateNoise(11, 2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7546161445789039, white.evaluateNoise(27.8924, 1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7630849159322679, white.evaluateNoise(120941.094, 0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 4D - Negative Coordinates")
    void testNegative4D() {
        assertAll("Is 4D White Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.06941556232050061, white.evaluateNoise(-0.5, -90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5603733449243009, white.evaluateNoise(-1.5, -27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5650095562450588, white.evaluateNoise(-2.5, -11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5650095562450588, white.evaluateNoise(-3, -10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.03915565321221948, white.evaluateNoise(-70.654, -33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.9987921570427716, white.evaluateNoise(-33.14, -70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.31781764375045896, white.evaluateNoise(-10.33, -3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.31781764375045896, white.evaluateNoise(-11, -2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8929354199208319, white.evaluateNoise(-27.8924, -1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.15305739315226674, white.evaluateNoise(-90.419824, -0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 4D - Random Coordinates")
    void testRandom4D() {
        Random random = new Random(1000);
        assertAll("Is 4D White Noise outputting correct values?",
            () -> assertEquals(-0.7480199672281742, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.015577316284179688, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24522662162780762, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08321475749835372, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.16960561042651534, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.909902157727629, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1429275623522699, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5118736433796585, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5835325606167316, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.936734352260828, white.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("White Noise 4D - Bounds")
    void test4DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 5); i++) {
            for (int j = 0; j < (2 << 5); j++) {
                for (int k = 0; k < (2 << 5); k++) {
                    for (int l = 0; l < (2 << 5); l++) {
                        double x = random.nextDouble() * random.nextInt(100000);
                        double y = random.nextDouble() * random.nextInt(100000);
                        double z = random.nextDouble() * random.nextInt(100000);
                        double w = random.nextDouble() * random.nextInt(100000);
                        double noise = white.evaluateNoise(x, y, z, w);
                        if (noise < -1 || noise > 1) {
                            fail("Is 4D White Noise outputting in the correct interval?");
                        }
                    }
                }
            }
        }
    }
}
