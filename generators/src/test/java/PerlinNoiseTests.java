import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noise_parameters.interpolation.Interpolation;
import de.articdive.jnoise.generators.noisegen.perlin.PerlinNoiseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Articdive
 */
final class PerlinNoiseTests {
    public static final double ACCEPTABLE_DEVIATION = 1E-10;
    private final PerlinNoiseGenerator perlin = PerlinNoiseGenerator.newBuilder()
        .setSeed(3301)
        .setInterpolation(Interpolation.LINEAR)
        .setFadeFunction(FadeFunction.NONE).build();

    @Test
    @DisplayName("Perlin Noise 1D - Integer Coordinates")
    void testInteger1D() {
        assertAll("Is 1D Perlin Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.0, perlin.evaluateNoise(0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(100), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1000), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(24), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 1D - Positive Coordinates")
    void testPositive1D() {
        assertAll("Is 1D Perlin Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.5, perlin.evaluateNoise(0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5, perlin.evaluateNoise(1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0, perlin.evaluateNoise(11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1920444800000023, perlin.evaluateNoise(27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 1D - Negative Coordinates")
    void testNegative1D() {
        assertAll("Is 1D Perlin Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.5, perlin.evaluateNoise(-0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.45256800000000225, perlin.evaluateNoise(-70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2408000000000008, perlin.evaluateNoise(-33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.48714361804800177, perlin.evaluateNoise(-90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 1D - Random Coordinates")
    void testRandom1D() {
        Random random = new Random(1000);
        assertAll("Is 1D Perlin Noise outputting correct values?",
            () -> assertEquals(-0.41164461088887505, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4887990413219073, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.10141977881591924, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.07570625015239103, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4996306171730205, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.49411099651368207, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.47967304839519004, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4949244830760444, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.45003509060665337, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.049706135433944754, perlin.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 1D - Bounds")
    void test1DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 24); i++) {
            double x = random.nextDouble() * random.nextInt(100000);
            double noise = perlin.evaluateNoise(x);
            if (noise < -Math.sqrt(0.5) || noise > Math.sqrt(0.5)) {
                fail("Is 1D Perlin Noise outputting in the correct interval?");
            }
        }
    }

    @Test
    @DisplayName("Perlin Noise 2D - Integer Coordinates")
    void testInteger2D() {
        assertAll("Is 2D Perlin Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.0, perlin.evaluateNoise(0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 2D - Positive Coordinates")
    void testPositive2D() {
        assertAll("Is 2D Perlin Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.1648360000021742, perlin.evaluateNoise(0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.07906663999999786, perlin.evaluateNoise(1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.38376720000000075, perlin.evaluateNoise(70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2634050400000023, perlin.evaluateNoise(33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.22110000000000002, perlin.evaluateNoise(10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.25, perlin.evaluateNoise(11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4192999999999989, perlin.evaluateNoise(27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08516399999782581, perlin.evaluateNoise(120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 2D - Negative Coordinates")
    void testNegative2D() {
        assertAll("Is 2D Perlin Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.6153577135360013, perlin.evaluateNoise(-0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.021111120000000205, perlin.evaluateNoise(-1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5, perlin.evaluateNoise(-2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.44220000000000004, perlin.evaluateNoise(-3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09416152000000094, perlin.evaluateNoise(-70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07874159999999983, perlin.evaluateNoise(-33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.25, perlin.evaluateNoise(-11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.30216663999999754, perlin.evaluateNoise(-27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6153577135360013, perlin.evaluateNoise(-90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 2D - Random Coordinates")
    void testRandom2D() {
        Random random = new Random(1000);
        assertAll("Is 2D Perlin Noise outputting correct values?",
            () -> assertEquals(0.37794368136909684, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.024936503184192473, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.21322888184916594, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.32152168162276595, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2462496166224506, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3242637009846562, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08304680395180848, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2609132984172458, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11669408481453969, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.045046454587199705, perlin.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 2D - Bounds")
    void test2DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 10); i++) {
            for (int j = 0; j < (2 << 10); j++) {
                double x = random.nextDouble() * random.nextInt(100000);
                double y = random.nextDouble() * random.nextInt(100000);
                double noise = perlin.evaluateNoise(x, y);
                if (noise < -Math.sqrt(1) || noise > Math.sqrt(1)) {
                    fail("Is 2D Perlin Noise outputting in the correct interval?");
                }
            }
        }
    }

    @Test
    @DisplayName("Perlin Noise 3D - Integer Coordinates")
    void testInteger3D() {
        assertAll("Is 3D Perlin Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.0, perlin.evaluateNoise(0, 24, 0.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(24, 0, 24.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 3D - Positive Coordinates")
    void testPositive3D() {
        assertAll("Is 3D Perlin Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.10150000000066939, perlin.evaluateNoise(0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06146112000000081, perlin.evaluateNoise(1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.125, perlin.evaluateNoise(2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.44220000000000004, perlin.evaluateNoise(3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.19080363760000169, perlin.evaluateNoise(70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08486755199999922, perlin.evaluateNoise(33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.444411, perlin.evaluateNoise(10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03830201046400028, perlin.evaluateNoise(27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.15431716799651646, perlin.evaluateNoise(120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 3D - Negative Coordinates")
    void testNegative3D() {
        assertAll("Is 3D Perlin Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.34852780902400227, perlin.evaluateNoise(-0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.22309999999999963, perlin.evaluateNoise(-1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.44220000000000004, perlin.evaluateNoise(-3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.40969267648000063, perlin.evaluateNoise(-70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.025685654400001368, perlin.evaluateNoise(-33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.15034800000000004, perlin.evaluateNoise(-10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.37021886255999864, perlin.evaluateNoise(-27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.15544571353599856, perlin.evaluateNoise(-90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 3D - Random Coordinates")
    void testRandom3D() {
        Random random = new Random(1000);
        assertAll("Is 3D Perlin Noise outputting correct values?",
            () -> assertEquals(0.15152654403156995, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(00.017367723366015664, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1249356976338511, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03878414168776423, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.05764337693717421, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03189884094488399, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1488822128223377, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.30400250794041145, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.029300415760297932, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19259837279511344, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 3D - Bounds")
    void test3DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 7); i++) {
            for (int j = 0; j < (2 << 7); j++) {
                for (int k = 0; k < (2 << 7); k++) {
                    double x = random.nextDouble() * random.nextInt(100000);
                    double y = random.nextDouble() * random.nextInt(100000);
                    double z = random.nextDouble() * random.nextInt(100000);
                    double noise = perlin.evaluateNoise(x, y, z);
                    if (noise < -Math.sqrt(1.5) || noise > Math.sqrt(1.5)) {
                        fail("Is 3D Perlin Noise outputting in the correct interval?");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Perlin Noise 4D - Integer Coordinates")
    void testInteger4D() {
        assertAll("Is 4D Perlin Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.0, perlin.evaluateNoise(0, 24, 0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1, -1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(1, 100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-2, -5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(2, 5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(5, 2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-5, -2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(100, 1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(-1000, -1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(24, 0, 24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 4D - Positive Coordinates")
    void testPositive4D() {
        assertAll("Is 4D Perlin Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.01639143799971786, perlin.evaluateNoise(0.5, 120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.32917256174399934, perlin.evaluateNoise(1.5, 27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5, perlin.evaluateNoise(2.5, 11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, perlin.evaluateNoise(3, 10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.27182589861120254, perlin.evaluateNoise(70.654, 33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2642432285312022, perlin.evaluateNoise(33.14, 70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.22331099999999998, perlin.evaluateNoise(10.33, 3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.25, perlin.evaluateNoise(11, 2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2811316712800005, perlin.evaluateNoise(27.8924, 1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08705943800113958, perlin.evaluateNoise(120941.094, 0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 4D - Negative Coordinates")
    void testNegative4D() {
        assertAll("Is 4D Perlin Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.05318918497630659, perlin.evaluateNoise(-0.5, -90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3844170477919957, perlin.evaluateNoise(-1.5, -27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.625, perlin.evaluateNoise(-2.5, -11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.29848499999999994, perlin.evaluateNoise(-3, -10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1382434891168003, perlin.evaluateNoise(-70.654, -33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.20898527604799982, perlin.evaluateNoise(-33.14, -70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.515163, perlin.evaluateNoise(-10.33, -3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.125, perlin.evaluateNoise(-11, -2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8024663582560005, perlin.evaluateNoise(-27.8924, -1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5422714444917661, perlin.evaluateNoise(-90.419824, -0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 4D - Random Coordinates")
    void testRandom4D() {
        Random random = new Random(1000);
        assertAll("Is 4D Perlin Noise outputting correct values?",
            () -> assertEquals(0.36178336273388484, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2371075064192641, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0524427130630821, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14896259837334663, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.02266677218407845, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19426829372528534, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2413202847593974, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4195586812951945, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.022944883089149076, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14727387182582619, perlin.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Perlin Noise 4D - Bounds")
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
                        double noise = perlin.evaluateNoise(x, y, z, w);
                        if (noise < -Math.sqrt(2) || noise > Math.sqrt(2)) {
                            fail("Is 4D Perlin Noise outputting in the correct interval?");
                        }
                    }
                }
            }
        }
    }
}
