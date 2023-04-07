import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noisegen.value.ValueNoiseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Articdive
 */
final class ValueNoiseTests {
    public static final double ACCEPTABLE_DEVIATION = 1E-10;
    private final ValueNoiseGenerator value = ValueNoiseGenerator.newBuilder()
        .setSeed(3301)
        .setInterpolation(Interpolation.LINEAR)
        .setFadeFunction(FadeFunction.NONE).build();

    @Test
    @DisplayName("Value Noise 1D - Integer Coordinates")
    void testInteger1D() {
        assertAll("Is 1D Value Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.8427160647697747, value.evaluateNoise(0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.15602755546569824, value.evaluateNoise(-1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4751463197171688, value.evaluateNoise(1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.26402019849047065, value.evaluateNoise(-2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.47226761234924197, value.evaluateNoise(2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9387339316308498, value.evaluateNoise(5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.50511035323143, value.evaluateNoise(-5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.24195495015010238, value.evaluateNoise(100), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8508484731428325, value.evaluateNoise(-1000), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6003133882768452, value.evaluateNoise(24), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 1D - Positive Coordinates")
    void testPositive1D() {
        assertAll("Is 1D Value Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.6589311922434717, value.evaluateNoise(0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4737069660332054, value.evaluateNoise(1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6291684282477945, value.evaluateNoise(2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.786069244146347, value.evaluateNoise(3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5504955483665687, value.evaluateNoise(70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5484539309237151, value.evaluateNoise(33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.35728029300924385, value.evaluateNoise(10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.21943286061286926, value.evaluateNoise(11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6840815910449245, value.evaluateNoise(27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5061937968847365, value.evaluateNoise(120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 1D - Negative Coordinates")
    void testNegative1D() {
        assertAll("Is 1D Value Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.3433442546520382, value.evaluateNoise(-0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0539963215123862, value.evaluateNoise(-1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5551419768016785, value.evaluateNoise(-2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8462637551128864, value.evaluateNoise(-3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8349694410981594, value.evaluateNoise(-70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3912177896965293, value.evaluateNoise(-33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9352734796283766, value.evaluateNoise(-10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8332420848309994, value.evaluateNoise(-11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6072749985584992, value.evaluateNoise(-27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0235208188815072, value.evaluateNoise(-90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 1D - Random Coordinates")
    void testRandom1D() {
        Random random = new Random(1000);
        assertAll("Is 1D Value Noise outputting correct values?",
            () -> assertEquals(0.5816735800663855, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6314236140115446, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.49484099722861363, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8282316281899836, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6639265098886382, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6788767260976447, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6218749918903453, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6404144364326743, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6008337100727694, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.48452063324959743, value.evaluateNoise(random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 1D - Bounds")
    void test1DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 24); i++) {
            double x = random.nextDouble() * random.nextInt(100000);
            double noise = value.evaluateNoise(x);
            if (noise < -1 || noise > 1) {
                fail("Is 1D Value Noise outputting in the correct interval?");
            }
        }
    }

    @Test
    @DisplayName("Value Noise 2D - Integer Coordinates")
    void testInteger2D() {
        assertAll("Is 2D Value Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.6938335145823658, value.evaluateNoise(0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7760105133056641, value.evaluateNoise(-1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8420981951057911, value.evaluateNoise(1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7434535324573517, value.evaluateNoise(-2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.08089824393391609, value.evaluateNoise(2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.26163697242736816, value.evaluateNoise(5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.22915860638022423, value.evaluateNoise(-5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6709747314453125, value.evaluateNoise(100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.27360010519623756, value.evaluateNoise(-1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6003133882768452, value.evaluateNoise(24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 2D - Positive Coordinates")
    void testPositive2D() {
        assertAll("Is 2D Value Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.1498744306283735, value.evaluateNoise(0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1125819898886605, value.evaluateNoise(1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.6373074010480195, value.evaluateNoise(2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8002434452110901, value.evaluateNoise(3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0630431385657962, value.evaluateNoise(70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07824781713821069, value.evaluateNoise(33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11390198142733429, value.evaluateNoise(10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2546430442016572, value.evaluateNoise(11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24804366055708316, value.evaluateNoise(27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.20965399672128165, value.evaluateNoise(120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 2D - Negative Coordinates")
    void testNegative2D() {
        assertAll("Is 2D Value Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.29434362767652533, value.evaluateNoise(-0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3053567036343735, value.evaluateNoise(-1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.49732044455595315, value.evaluateNoise(-2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.026421556235291188, value.evaluateNoise(-3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09901379785947378, value.evaluateNoise(-70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.041313207284735046, value.evaluateNoise(-33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6363646578555926, value.evaluateNoise(-10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.043790825409814715, value.evaluateNoise(-11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.04180526255052522, value.evaluateNoise(-27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09800641689341733, value.evaluateNoise(-90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 2D - Random Coordinates")
    void testRandom2D() {
        Random random = new Random(1000);
        assertAll("Is 2D Value Noise outputting correct values?",
            () -> assertEquals(0.2689539548498669, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.47154674916885947, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.44133448182517804, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.33446881584122146, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08082512965323307, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1628449092640329, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.647293248523506, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11272036640641103, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.07689164310762336, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4753252267728052, value.evaluateNoise(random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 2D - Bounds")
    void test2DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 10); i++) {
            for (int j = 0; j < (2 << 10); j++) {
                double x = random.nextDouble() * random.nextInt(100000);
                double y = random.nextDouble() * random.nextInt(100000);
                double noise = value.evaluateNoise(x, y);
                if (noise < -1 || noise > 1) {
                    fail("Is 2D Value Noise outputting in the correct interval?");
                }
            }
        }
    }

    @Test
    @DisplayName("Value Noise 3D - Integer Coordinates")
    void testInteger3D() {
        assertAll("Is 3D Value Noise outputting correct values for integer coordinates?",
            () -> assertEquals(-0.6938335145823658, value.evaluateNoise(0, 24, 0.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9388421433977783, value.evaluateNoise(-1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2443405664525926, value.evaluateNoise(1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.07616687193512917, value.evaluateNoise(-2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6659495830535889, value.evaluateNoise(2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1643399759195745, value.evaluateNoise(5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(8.543352596461773E-4, value.evaluateNoise(-5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.40396955609321594, value.evaluateNoise(100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.7187097482383251, value.evaluateNoise(-1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7276746002025902, value.evaluateNoise(24, 0, 24.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 3D - Positive Coordinates")
    void testPositive3D() {
        assertAll("Is 3D Value Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.28633574742758106, value.evaluateNoise(0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.18377303072717022, value.evaluateNoise(1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0932251161430031, value.evaluateNoise(2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2660143512906506, value.evaluateNoise(3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.16173864027412027, value.evaluateNoise(70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.39315223961881685, value.evaluateNoise(33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5495083161544986, value.evaluateNoise(10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7205059516709298, value.evaluateNoise(11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02130594852355261, value.evaluateNoise(27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.1071479331062342, value.evaluateNoise(120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 3D - Negative Coordinates")
    void testNegative3D() {
        assertAll("Is 3D Value Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.38883963331178606, value.evaluateNoise(-0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.03653608464617364, value.evaluateNoise(-1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.032117162598297, value.evaluateNoise(-2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09483299491461364, value.evaluateNoise(-3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3989582332590378, value.evaluateNoise(-70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.34903920170297315, value.evaluateNoise(-33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.05087155700912699, value.evaluateNoise(-10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4237517898436636, value.evaluateNoise(-11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.14518478239528013, value.evaluateNoise(-27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.12298319576271166, value.evaluateNoise(-90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 3D - Random Coordinates")
    void testRandom3D() {
        Random random = new Random(1000);
        assertAll("Is 3D Value Noise outputting correct values?",
            () -> assertEquals(0.20562225304106524, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2187666688308692, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19843483985674787, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.31490994168164643, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0356755540891206, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3261873559373319, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3765482376200321, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.38766494430585424, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.42613546957688164, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19922841120295606, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 3D - Bounds")
    void test3DBounds() {
        Random random = new Random();
        for (int i = 0; i < (2 << 7); i++) {
            for (int j = 0; j < (2 << 7); j++) {
                for (int k = 0; k < (2 << 7); k++) {
                    double x = random.nextDouble() * random.nextInt(100000);
                    double y = random.nextDouble() * random.nextInt(100000);
                    double z = random.nextDouble() * random.nextInt(100000);
                    double noise = value.evaluateNoise(x, y, z);
                    if (noise < -1 || noise > 1) {
                        fail("Is 3D Value Noise outputting in the correct interval?");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Value Noise 4D - Integer Coordinates")
    void testInteger4D() {
        assertAll("Is 4D Value Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.2489168462343514, value.evaluateNoise(0, 24, 0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9207308064214885, value.evaluateNoise(-1, -1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6653252397663891, value.evaluateNoise(1, 100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8389126225374639, value.evaluateNoise(-2, -5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.8389126225374639, value.evaluateNoise(2, 5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3457227679900825, value.evaluateNoise(5, 2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3457227679900825, value.evaluateNoise(-5, -2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.49156073248013854, value.evaluateNoise(100, 1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.9939561388455331, value.evaluateNoise(-1000, -1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7276746002025902, value.evaluateNoise(24, 0, 24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 4D - Positive Coordinates")
    void testPositive4D() {
        assertAll("Is 4D Value Noise outputting correct values for positive coordinates?",
            () -> assertEquals(-0.4044478693821575, value.evaluateNoise(0.5, 120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.33491967935276157, value.evaluateNoise(1.5, 27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.28593652485869825, value.evaluateNoise(2.5, 11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.5689382022392936, value.evaluateNoise(3, 10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0870457570201709, value.evaluateNoise(70.654, 33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.32845330248964455, value.evaluateNoise(33.14, 70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(2.05802388954901E-4, value.evaluateNoise(10.33, 3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.028804203728213906, value.evaluateNoise(11, 2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.23336452827699028, value.evaluateNoise(27.8924, 1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.2771243307607623, value.evaluateNoise(120941.094, 0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 4D - Negative Coordinates")
    void testNegative4D() {
        assertAll("Is 4D Value Noise outputting correct values for negative coordinates?",
            () -> assertEquals(-0.004114280429781875, value.evaluateNoise(-0.5, -90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08180717040652138, value.evaluateNoise(-1.5, -27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24302689009346068, value.evaluateNoise(-2.5, -11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.3132793215465732, value.evaluateNoise(-3, -10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(7.415237958384446E-4, value.evaluateNoise(-70.654, -33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3580123609372445, value.evaluateNoise(-33.14, -70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.0901883214506321, value.evaluateNoise(-10.33, -3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06527283950708807, value.evaluateNoise(-11, -2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.013543014455891456, value.evaluateNoise(-27.8924, -1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.09656251233293797, value.evaluateNoise(-90.419824, -0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 4D - Random Coordinates")
    void testRandom4D() {
        Random random = new Random(1000);
        assertAll("Is 4D Value Noise outputting correct values?",
            () -> assertEquals(0.19976460372598498, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.031172583952309096, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.43413633934533147, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.38307813960351283, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3044382970864596, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.06588790351312747, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.055029150891418854, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.10472304770632307, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09569567923276204, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION),
            () -> assertEquals(-0.028595447891510195, value.evaluateNoise(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble()), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Value Noise 4D - Bounds")
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
                        double noise = value.evaluateNoise(x, y, z, w);
                        if (noise < -1 || noise > 1) {
                            fail("Is 4D Value Noise outputting in the correct interval?");
                        }
                    }
                }
            }
        }
    }
}
