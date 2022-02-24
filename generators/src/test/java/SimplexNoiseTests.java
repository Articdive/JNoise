import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.opensimplex.SuperSimplexNoiseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Articdive
 */
final class SimplexNoiseTests {
    public static final double ACCEPTABLE_DEVIATION = 1E-10;
    private final SuperSimplexNoiseGenerator simplexSuper = SuperSimplexNoiseGenerator.newBuilder().setSeed(3301).build();
    private final FastSimplexNoiseGenerator simplexFast = FastSimplexNoiseGenerator.newBuilder().setSeed(3301).build();

    @Test
    @DisplayName("SuperSimplex Noise 1D - Integer Coordinates")
    void testInteger1DSuper() {
        assertAll("Is 1D SuperSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.04791662096977234, simplexSuper.evaluateNoise(0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(-1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.46213239431381226, simplexSuper.evaluateNoise(1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.826111376285553, simplexSuper.evaluateNoise(-2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.22229008376598358, simplexSuper.evaluateNoise(2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.42323756217956543, simplexSuper.evaluateNoise(5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.061045050621032715, simplexSuper.evaluateNoise(-5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.07309728115797043, simplexSuper.evaluateNoise(100), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.20758962631225586, simplexSuper.evaluateNoise(-1000), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2488023042678833, simplexSuper.evaluateNoise(24), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 1D - Integer Coordinates")
    void testInteger1DFast() {
        assertAll("Is 1D FastSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.09122662991285324, simplexFast.evaluateNoise(0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0, simplexFast.evaluateNoise(-1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8236600756645203, simplexFast.evaluateNoise(1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.9900658130645752, simplexFast.evaluateNoise(-2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3764711916446686, simplexFast.evaluateNoise(2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.643632709980011, simplexFast.evaluateNoise(5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.005847126245498657, simplexFast.evaluateNoise(-5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.12638747692108154, simplexFast.evaluateNoise(100), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3253846764564514, simplexFast.evaluateNoise(-1000), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4131718873977661, simplexFast.evaluateNoise(24), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 1D - Positive Coordinates")
    void testPositive1DSuper() {
        assertAll("Is 1D SuperSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.03287404775619507, simplexSuper.evaluateNoise(0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.40316393971443176, simplexSuper.evaluateNoise(1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.32406777143478394, simplexSuper.evaluateNoise(2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.009187083691358566, simplexSuper.evaluateNoise(3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5377256274223328, simplexSuper.evaluateNoise(70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.009266296401619911, simplexSuper.evaluateNoise(33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.23781780898571014, simplexSuper.evaluateNoise(10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03982660174369812, simplexSuper.evaluateNoise(11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5004363059997559, simplexSuper.evaluateNoise(27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.02359459176659584, simplexSuper.evaluateNoise(120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 1D - Positive Coordinates")
    void testPositive1DFast() {
        assertAll("Is 1D FastSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.07858303189277649, simplexFast.evaluateNoise(0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.335693895816803, simplexFast.evaluateNoise(1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5249485969543457, simplexFast.evaluateNoise(2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.057522326707839966, simplexFast.evaluateNoise(3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8844292759895325, simplexFast.evaluateNoise(70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07536864280700684, simplexFast.evaluateNoise(33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10475701838731766, simplexFast.evaluateNoise(10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10142235457897186, simplexFast.evaluateNoise(11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.572257936000824, simplexFast.evaluateNoise(27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.044469915330410004, simplexFast.evaluateNoise(120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 1D - Negative Coordinates")
    void testNegative1DSuper() {
        assertAll("Is 1D SuperSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.4596991240978241, simplexSuper.evaluateNoise(-0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.06260839849710464, simplexSuper.evaluateNoise(-1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.15884044766426086, simplexSuper.evaluateNoise(-2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.624020516872406, simplexSuper.evaluateNoise(-3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7203847169876099, simplexSuper.evaluateNoise(-70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03574826195836067, simplexSuper.evaluateNoise(-33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.529230535030365, simplexSuper.evaluateNoise(-10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4060392379760742, simplexSuper.evaluateNoise(-11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.49960482120513916, simplexSuper.evaluateNoise(-27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4999661445617676, simplexSuper.evaluateNoise(-90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 1D - Negative Coordinates")
    void testNegative1DFast() {
        assertAll("Is 1D FastSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.6210112571716309, simplexFast.evaluateNoise(-0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.017346646636724472, simplexFast.evaluateNoise(-1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09822678565979004, simplexFast.evaluateNoise(-2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8629607558250427, simplexFast.evaluateNoise(-3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7764114737510681, simplexFast.evaluateNoise(-70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.020128708332777023, simplexFast.evaluateNoise(-33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5316019654273987, simplexFast.evaluateNoise(-10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7769351005554199, simplexFast.evaluateNoise(-11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8731068968772888, simplexFast.evaluateNoise(-27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.531134307384491, simplexFast.evaluateNoise(-90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 1D - Random Coordinates")
    void testRandom1DSuper() {
        Random random = new Random(1000);
        assertAll("Is 1D SuperSimplex Noise outputting correct values?",
            () -> assertEquals(0.10084277391433716, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.03096526861190796, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4111748933792114, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0034994285088032484, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.03442114591598511, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.03796178102493286, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.03580021858215332, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02950456738471985, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06044808030128479, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4411793351173401, simplexSuper.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 1D - Random Coordinates")
    void testRandom1DFast() {
        Random random = new Random(1000);
        assertAll("Is 1D FastSimplex Noise outputting correct values?",
            () -> assertEquals(0.0032121390104293823, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.01276049017906189, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7242854237556458, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.01914813369512558, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09471499919891357, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.13507762551307678, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03572678565979004, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.01505783200263977, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.04749578237533569, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7853882908821106, simplexFast.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 1D - Bounds")
    void test1DBoundsSuper() {
        Random random = new Random();
        for (int i = 0; i < (2 << 24); i++) {
            double x = random.nextDouble() * random.nextInt(100000);
            double noise = simplexSuper.evaluateNoise(x);
            if (noise < -1 || noise > 1) {
                fail("Is 1D SuperSimplex Noise outputting in the correct interval?");
            }
        }
    }

    @Test
    @DisplayName("FastSimplex Noise 1D - Bounds")
    void test1DBoundsFast() {
        Random random = new Random();
        for (int i = 0; i < (2 << 24); i++) {
            double x = random.nextDouble() * random.nextInt(100000);
            double noise = simplexFast.evaluateNoise(x);
            if (noise < -1 || noise > 1) {
                fail("Is 1D FastSimplex Noise outputting in the correct interval?");
            }
        }
    }

    @Test
    @DisplayName("SuperSimplex Noise 2D - Integer Coordinates")
    void testInteger2DSuper() {
        assertAll("Is 2D SuperSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.4431307017803192, simplexSuper.evaluateNoise(0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.389212965965271, simplexSuper.evaluateNoise(-1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03521071374416351, simplexSuper.evaluateNoise(1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.21760977804660797, simplexSuper.evaluateNoise(-2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08066993951797485, simplexSuper.evaluateNoise(2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7684509754180908, simplexSuper.evaluateNoise(5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8187946677207947, simplexSuper.evaluateNoise(-5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.07309728115797043, simplexSuper.evaluateNoise(100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3237065076828003, simplexSuper.evaluateNoise(-1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5011305809020996, simplexSuper.evaluateNoise(24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 2D - Integer Coordinates")
    void testInteger2DFast() {
        assertAll("Is 2D FastSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.6768718361854553, simplexFast.evaluateNoise(0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5132539868354797, simplexFast.evaluateNoise(-1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.06096449866890907, simplexFast.evaluateNoise(1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.378164142370224, simplexFast.evaluateNoise(-2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2251630425453186, simplexFast.evaluateNoise(2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8400276899337769, simplexFast.evaluateNoise(5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9338159561157227, simplexFast.evaluateNoise(-5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.12638747692108154, simplexFast.evaluateNoise(100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5771178603172302, simplexFast.evaluateNoise(-1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8411674499511719, simplexFast.evaluateNoise(24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 2D - Positive Coordinates")
    void testPositive2DSuper() {
        assertAll("Is 2D SuperSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.6373153924942017, simplexSuper.evaluateNoise(0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.41966861486434937, simplexSuper.evaluateNoise(1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.12527547776699066, simplexSuper.evaluateNoise(2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3790988028049469, simplexSuper.evaluateNoise(3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8151332139968872, simplexSuper.evaluateNoise(70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.46849098801612854, simplexSuper.evaluateNoise(33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4745948314666748, simplexSuper.evaluateNoise(10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09999164938926697, simplexSuper.evaluateNoise(11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.017870068550109863, simplexSuper.evaluateNoise(27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11193899065256119, simplexSuper.evaluateNoise(120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 2D - Positive Coordinates")
    void testPositive2DFast() {
        assertAll("Is 2D FastSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.8305650949478149, simplexFast.evaluateNoise(0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3535725176334381, simplexFast.evaluateNoise(1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10572854429483414, simplexFast.evaluateNoise(2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6466035842895508, simplexFast.evaluateNoise(3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8005424737930298, simplexFast.evaluateNoise(70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4799640476703644, simplexFast.evaluateNoise(33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7786362767219543, simplexFast.evaluateNoise(10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1523304283618927, simplexFast.evaluateNoise(11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.057395100593566895, simplexFast.evaluateNoise(27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2820107936859131, simplexFast.evaluateNoise(120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 2D - Negative Coordinates")
    void testNegative2DSuper() {
        assertAll("Is 2D SuperSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.04836895316839218, simplexSuper.evaluateNoise(-0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24394837021827698, simplexSuper.evaluateNoise(-1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7819828391075134, simplexSuper.evaluateNoise(-2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.48371589183807373, simplexSuper.evaluateNoise(-3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5564604997634888, simplexSuper.evaluateNoise(-70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.43685460090637207, simplexSuper.evaluateNoise(-33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.515929102897644, simplexSuper.evaluateNoise(-10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4618406891822815, simplexSuper.evaluateNoise(-11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.14966189861297607, simplexSuper.evaluateNoise(-27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17607957124710083, simplexSuper.evaluateNoise(-90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 2D - Negative Coordinates")
    void testNegative2DFast() {
        assertAll("Is 2D FastSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.1111256331205368, simplexFast.evaluateNoise(-0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14716951549053192, simplexFast.evaluateNoise(-1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8107380867004395, simplexFast.evaluateNoise(-2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6703228950500488, simplexFast.evaluateNoise(-3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6750103235244751, simplexFast.evaluateNoise(-70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.498468816280365, simplexFast.evaluateNoise(-33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8003191947937012, simplexFast.evaluateNoise(-10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5093483924865723, simplexFast.evaluateNoise(-11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.16092351078987122, simplexFast.evaluateNoise(-27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.34131234884262085, simplexFast.evaluateNoise(-90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 2D - Random Coordinates")
    void testRandom2DSuper() {
        Random random = new Random(1000);
        assertAll("Is 2D SuperSimplex Noise outputting correct values?",
            () -> assertEquals(-0.047151532024145126, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0307641439139843, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4016909599304199, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08458002656698227, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03528216481208801, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4286304712295532, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2433299869298935, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08601805567741394, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.40843063592910767, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.18703405559062958, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 2D - Random Coordinates")
    void testRandom2DFast() {
        Random random = new Random(1000);
        assertAll("Is 2D FastSimplex Noise outputting correct values?",
            () -> assertEquals(-0.08023712038993835, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2110104262828827, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.718863308429718, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14625802636146545, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.20908625423908234, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8353022933006287, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2855379581451416, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.25620999932289124, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7327575087547302, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4525753855705261, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 2D - Bounds")
    void test2DBoundsSuper() {
        Random random = new Random();
        for (int i = 0; i < (2 << 10); i++) {
            for (int j = 0; j < (2 << 10); j++) {
                double x = random.nextDouble() * random.nextInt(100000);
                double y = random.nextDouble() * random.nextInt(100000);
                double noise = simplexSuper.evaluateNoise(x, y);
                if (noise < -1 || noise > 1) {
                    fail("Is 2D SuperSimplex Noise outputting in the correct interval?");
                }
            }
        }
    }

    @Test
    @DisplayName("FastSimplex Noise 2D - Bounds")
    void test2DBoundsFast() {
        Random random = new Random();
        for (int i = 0; i < (2 << 10); i++) {
            for (int j = 0; j < (2 << 10); j++) {
                double x = random.nextDouble() * random.nextInt(100000);
                double y = random.nextDouble() * random.nextInt(100000);
                double noise = simplexFast.evaluateNoise(x, y);
                if (noise < -1 || noise > 1) {
                    fail("Is 2D FastSimplex Noise outputting in the correct interval?");
                }
            }
        }
    }

    @Test
    @DisplayName("SuperSimplex Noise 3D - Integer Coordinates")
    void testInteger3DSuper() {
        assertAll("Is 3D SuperSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(0, 24, 0.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(-1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(-2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(-5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(-1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexSuper.evaluateNoise(24, 0, 24.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 3D - Integer Coordinates")
    void testInteger3DFast() {
        assertAll("Is 3D FastSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.0, simplexFast.evaluateNoise(0, 24, 0.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(-1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(-2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(-5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(-1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0, simplexFast.evaluateNoise(24, 0, 24.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 3D - Positive Coordinates")
    void testPositive3DSuper() {
        assertAll("Is 3D SuperSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.1673237383365631, simplexSuper.evaluateNoise(0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.23067103326320648, simplexSuper.evaluateNoise(1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08926215022802353, simplexSuper.evaluateNoise(2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09458504617214203, simplexSuper.evaluateNoise(3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.30408617854118347, simplexSuper.evaluateNoise(70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.03834269568324089, simplexSuper.evaluateNoise(33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5151665806770325, simplexSuper.evaluateNoise(10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.06748110800981522, simplexSuper.evaluateNoise(11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1902930587530136, simplexSuper.evaluateNoise(27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3809407949447632, simplexSuper.evaluateNoise(120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 3D - Positive Coordinates")
    void testPositive3DFast() {
        assertAll("Is 3D FastSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.1722334623336792, simplexFast.evaluateNoise(0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2508881688117981, simplexFast.evaluateNoise(1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.04438529536128044, simplexFast.evaluateNoise(2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06225627660751343, simplexFast.evaluateNoise(3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3077974319458008, simplexFast.evaluateNoise(70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.11053125560283661, simplexFast.evaluateNoise(33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6221261024475098, simplexFast.evaluateNoise(10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08446364849805832, simplexFast.evaluateNoise(11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.34973227977752686, simplexFast.evaluateNoise(27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4745906591415405, simplexFast.evaluateNoise(120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 3D - Negative Coordinates")
    void testNegative3DSuper() {
        assertAll("Is 3D SuperSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.25092098116874695, simplexSuper.evaluateNoise(-0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3705699145793915, simplexSuper.evaluateNoise(-1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09150134027004242, simplexSuper.evaluateNoise(-2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11592333763837814, simplexSuper.evaluateNoise(-3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.22730597853660583, simplexSuper.evaluateNoise(-70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.030170833691954613, simplexSuper.evaluateNoise(-33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5396011471748352, simplexSuper.evaluateNoise(-10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5065631866455078, simplexSuper.evaluateNoise(-11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6744904518127441, simplexSuper.evaluateNoise(-27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.222263902425766, simplexSuper.evaluateNoise(-90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 3D - Negative Coordinates")
    void testNegative3DFast() {
        assertAll("Is 3D FastSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.3545234799385071, simplexFast.evaluateNoise(-0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4486323595046997, simplexFast.evaluateNoise(-1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.05604025721549988, simplexFast.evaluateNoise(-2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17462123930454254, simplexFast.evaluateNoise(-3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3185797929763794, simplexFast.evaluateNoise(-70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.02876279130578041, simplexFast.evaluateNoise(-33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.716934084892273, simplexFast.evaluateNoise(-10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5009580850601196, simplexFast.evaluateNoise(-11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7187321782112122, simplexFast.evaluateNoise(-27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3098449110984802, simplexFast.evaluateNoise(-90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 3D - Random Coordinates")
    void testRandom3DSuper() {
        Random random = new Random(1000);
        assertAll("Is 3D SuperSimplex Noise outputting correct values?",
            () -> assertEquals(-0.31953543424606323, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.04814913123846054, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2605985999107361, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5651201009750366, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3833615183830261, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2403629571199417, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17458677291870117, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.14158158004283905, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4455273151397705, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2849099338054657, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 3D - Random Coordinates")
    void testRandom3DFast() {
        Random random = new Random(1000);
        assertAll("Is 3D FastSimplex Noise outputting correct values?",
            () -> assertEquals(-0.1908515989780426, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.009686354547739029, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4092363119125366, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6025327444076538, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.45959019660949707, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.34314996004104614, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08664542436599731, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09653449803590775, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5003254413604736, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.20903584361076355, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 3D - Bounds")
    void test3DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 7); i++) {
            for (int j = 0; j < (2 << 7); j++) {
                for (int k = 0; k < (2 << 7); k++) {
                    double x = random.nextDouble() * random.nextInt(100000);
                    double y = random.nextDouble() * random.nextInt(100000);
                    double z = random.nextDouble() * random.nextInt(100000);
                    double noise = simplexSuper.evaluateNoise(x, y, z);
                    if (noise < -1 || noise > 1) {
                        fail("Is 3D SuperSimplex Noise outputting in the correct interval?");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("FastSimplex Noise 3D - Bounds")
    void test3DBoundsFast() {
        Random random = new Random();
        for (int i = 0; i < (2 << 7); i++) {
            for (int j = 0; j < (2 << 7); j++) {
                for (int k = 0; k < (2 << 7); k++) {
                    double x = random.nextDouble() * random.nextInt(100000);
                    double y = random.nextDouble() * random.nextInt(100000);
                    double z = random.nextDouble() * random.nextInt(100000);
                    double noise = simplexFast.evaluateNoise(x, y, z);
                    if (noise < -1 || noise > 1) {
                        fail("Is 3D FastSimplex Noise outputting in the correct interval?");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("SuperSimplex Noise 4D - Integer Coordinates")
    void testInteger4DSuper() {
        assertAll("Is 4D SuperSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.3807819187641144, simplexSuper.evaluateNoise(0, 24, 0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3426094055175781, simplexSuper.evaluateNoise(-1, -1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17619776725769043, simplexSuper.evaluateNoise(1, 100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.21827417612075806, simplexSuper.evaluateNoise(-2, -5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.21828970313072205, simplexSuper.evaluateNoise(2, 5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.32905739545822144, simplexSuper.evaluateNoise(5, 2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3290903568267822, simplexSuper.evaluateNoise(-5, -2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6899905204772949, simplexSuper.evaluateNoise(100, 1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.11121580004692078, simplexSuper.evaluateNoise(-1000, -1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10616745054721832, simplexSuper.evaluateNoise(24, 0, 24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 4D - Integer Coordinates")
    void testInteger4DFast() {
        assertAll("Is 4D FastSimplex Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.2808057367801666, simplexFast.evaluateNoise(0, 24, 0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7210341691970825, simplexFast.evaluateNoise(-1, -1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5505936145782471, simplexFast.evaluateNoise(1, 100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.312113493680954, simplexFast.evaluateNoise(-2, -5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.29629483819007874, simplexFast.evaluateNoise(2, 5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6065744161605835, simplexFast.evaluateNoise(5, 2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6271781325340271, simplexFast.evaluateNoise(-5, -2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.36936548352241516, simplexFast.evaluateNoise(100, 1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5340601205825806, simplexFast.evaluateNoise(-1000, -1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4832538664340973, simplexFast.evaluateNoise(24, 0, 24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 4D - Positive Coordinates")
    void testPositive4DSuper() {
        assertAll("Is 4D SuperSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.18348491191864014, simplexSuper.evaluateNoise(0.5, 120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0034064073115587234, simplexSuper.evaluateNoise(1.5, 27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08497243374586105, simplexSuper.evaluateNoise(2.5, 11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.36191326379776, simplexSuper.evaluateNoise(3, 10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3323332369327545, simplexSuper.evaluateNoise(70.654, 33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2518858015537262, simplexSuper.evaluateNoise(33.14, 70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.017025096341967583, simplexSuper.evaluateNoise(10.33, 3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3706943690776825, simplexSuper.evaluateNoise(11, 2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4026792049407959, simplexSuper.evaluateNoise(27.8924, 1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02490839548408985, simplexSuper.evaluateNoise(120941.094, 0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 4D - Positive Coordinates")
    void testPositive4DFast() {
        assertAll("Is 4D FastSimplex Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.28054505586624146, simplexFast.evaluateNoise(0.5, 120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.13468365371227264, simplexFast.evaluateNoise(1.5, 27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2695182263851166, simplexFast.evaluateNoise(2.5, 11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19629397988319397, simplexFast.evaluateNoise(3, 10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2607949376106262, simplexFast.evaluateNoise(70.654, 33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24524074792861938, simplexFast.evaluateNoise(33.14, 70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1300586760044098, simplexFast.evaluateNoise(10.33, 3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3162946403026581, simplexFast.evaluateNoise(11, 2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.4599091112613678, simplexFast.evaluateNoise(27.8924, 1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.30638226866722107, simplexFast.evaluateNoise(120941.094, 0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 4D - Negative Coordinates")
    void testNegative4DSuper() {
        assertAll("Is 4D SuperSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.47210854291915894, simplexSuper.evaluateNoise(-0.5, -90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.02859949879348278, simplexSuper.evaluateNoise(-1.5, -27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07992710173130035, simplexSuper.evaluateNoise(-2.5, -11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3603430688381195, simplexSuper.evaluateNoise(-3, -10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3831663131713867, simplexSuper.evaluateNoise(-70.654, -33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2506444752216339, simplexSuper.evaluateNoise(-33.14, -70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.004889994859695435, simplexSuper.evaluateNoise(-10.33, -3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3048502504825592, simplexSuper.evaluateNoise(-11, -2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4160294532775879, simplexSuper.evaluateNoise(-27.8924, -1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7139514684677124, simplexSuper.evaluateNoise(-90.419824, -0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 4D - Negative Coordinates")
    void testNegative4DFast() {
        assertAll("Is 4D FastSimplex Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.12980762124061584, simplexFast.evaluateNoise(-0.5, -90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2358626276254654, simplexFast.evaluateNoise(-1.5, -27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2906792163848877, simplexFast.evaluateNoise(-2.5, -11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1839386373758316, simplexFast.evaluateNoise(-3, -10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2293124794960022, simplexFast.evaluateNoise(-70.654, -33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.050145912915468216, simplexFast.evaluateNoise(-33.14, -70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0678481012582779, simplexFast.evaluateNoise(-10.33, -3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3057078719139099, simplexFast.evaluateNoise(-11, -2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.42118778824806213, simplexFast.evaluateNoise(-27.8924, -1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6885654330253601, simplexFast.evaluateNoise(-90.419824, -0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 4D - Random Coordinates")
    void testRandom4DSuper() {
        Random random = new Random(1000);
        assertAll("Is 4D SuperSimplex Noise outputting correct values?",
            () -> assertEquals(-0.20621246099472046, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.023710500448942184, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.19986195862293243, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.39490965008735657, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.39067474007606506, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.12022848427295685, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.05974801629781723, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17156913876533508, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4410620629787445, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.36167842149734497, simplexSuper.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("FastSimplex Noise 4D - Random Coordinates")
    void testRandom4DFast() {
        Random random = new Random(1000);
        assertAll("Is 4D FastSimplex Noise outputting correct values?",
            () -> assertEquals(0.03632349893450737, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.013857902027666569, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09050571918487549, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.18765738606452942, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.43003085255622864, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.036095596849918365, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06660716235637665, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1292322278022766, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3158782124519348, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2086184322834015, simplexFast.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("SuperSimplex Noise 4D - Bounds")
    void test4DBoundsSuper() {
        Random random = new Random();
        for (int i = 0; i < (2 << 5); i++) {
            for (int j = 0; j < (2 << 5); j++) {
                for (int k = 0; k < (2 << 5); k++) {
                    for (int l = 0; l < (2 << 5); l++) {
                        double x = random.nextDouble() * random.nextInt(100000);
                        double y = random.nextDouble() * random.nextInt(100000);
                        double z = random.nextDouble() * random.nextInt(100000);
                        double w = random.nextDouble() * random.nextInt(100000);
                        double noise = simplexSuper.evaluateNoise(x, y, z, w);
                        if (noise < -1 || noise > 1) {
                            fail("Is 4D SuperSimplex Noise outputting in the correct interval?");
                        }
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("FastSimplex Noise 4D - Bounds")
    void test4DBoundsFast() {
        Random random = new Random();
        for (int i = 0; i < (2 << 5); i++) {
            for (int j = 0; j < (2 << 5); j++) {
                for (int k = 0; k < (2 << 5); k++) {
                    for (int l = 0; l < (2 << 5); l++) {
                        double x = random.nextDouble() * random.nextInt(100000);
                        double y = random.nextDouble() * random.nextInt(100000);
                        double z = random.nextDouble() * random.nextInt(100000);
                        double w = random.nextDouble() * random.nextInt(100000);
                        double noise = simplexFast.evaluateNoise(x, y, z, w);
                        if (noise < -1 || noise > 1) {
                            fail("Is 4D FastSimplex Noise outputting in the correct interval?");
                        }
                    }
                }
            }
        }
    }
}
