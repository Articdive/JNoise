import de.articdive.jnoise.generators.noisegen.worley.WorleyNoiseGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Articdive
 */
final class WorleyNoiseTests {
    public static final double ACCEPTABLE_DEVIATION = 1E-10;
    private final WorleyNoiseGenerator worley = WorleyNoiseGenerator.newBuilder().build();

    @Test
    @DisplayName("Worley Noise 1D - Integer Coordinates")
    void testInteger1D() {
        assertAll("Is 1D Worley Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.2143837142520424, worley.evaluateNoise(0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.16471142928852653, worley.evaluateNoise(-1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.13794657725932383, worley.evaluateNoise(1), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2687130558128326, worley.evaluateNoise(-2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3215012444626965, worley.evaluateNoise(2), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.008392394629992804, worley.evaluateNoise(5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.029913095614996155, worley.evaluateNoise(-5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02648219508631556, worley.evaluateNoise(100), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.007266572445085194, worley.evaluateNoise(-1000), ACCEPTABLE_DEVIATION),
            () -> assertEquals(1.6252136212316923E-4, worley.evaluateNoise(24), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 1D - Positive Coordinates")
    void testPositive1D() {
        assertAll("Is 1D Worley Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.016534976391332862, worley.evaluateNoise(0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0044904469793535995, worley.evaluateNoise(1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.01778726696503066, worley.evaluateNoise(2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1344183539266818, worley.evaluateNoise(3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.061302583675407425, worley.evaluateNoise(70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(2.653813310497925E-4, worley.evaluateNoise(33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02688357227045333, worley.evaluateNoise(10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2560743499587214, worley.evaluateNoise(11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14244997479579638, worley.evaluateNoise(27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07824670542768661, worley.evaluateNoise(120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 1D - Negative Coordinates")
    void testNegative1D() {
        assertAll("Is 1D Worley Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.0013678244037107386, worley.evaluateNoise(-0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.008864870490097727, worley.evaluateNoise(-1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(3.376553424470455E-4, worley.evaluateNoise(-2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.009983158297138264, worley.evaluateNoise(-3), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.01992878325912375, worley.evaluateNoise(-70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.338246147426227, worley.evaluateNoise(-33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.04660561955901375, worley.evaluateNoise(-10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.20622193443286438, worley.evaluateNoise(-11), ACCEPTABLE_DEVIATION),
            () -> assertEquals(2.0135256726602665E-4, worley.evaluateNoise(-27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02107162278289358, worley.evaluateNoise(-90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 1D - Random Coordinates")
    void testRandom1D() {
        Random random = new Random(1000);
        assertAll("Is 1D Worley Noise outputting correct values?",
            () -> assertEquals(0.16879637428641023, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(1.6502114876594277E-4, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.010993587575962197, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.029717441719234558, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.019975488139615853, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.13981236667293495, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0037194777859804904, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07754370142527534, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.018724194268056004, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06880454448331678, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 2D - Integer Coordinates")
    void testInteger2D() {
        assertAll("Is 2D Worley Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.5142091900745751, worley.evaluateNoise(0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08141345363496139, worley.evaluateNoise(-1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4946955301144814, worley.evaluateNoise(1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07704682492494538, worley.evaluateNoise(-2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6517225476643032, worley.evaluateNoise(2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.42500517617762323, worley.evaluateNoise(5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2222323280029278, worley.evaluateNoise(-5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07386300836980682, worley.evaluateNoise(100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.02485325620707323, worley.evaluateNoise(-1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.049797562840288724, worley.evaluateNoise(24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 2D - Positive Coordinates")
    void testPositive2D() {
        assertAll("Is 2D Worley Noise outputting correct values for positive coordinates?",
            () -> assertEquals(9.772109092217364E-4, worley.evaluateNoise(0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2068415271771973, worley.evaluateNoise(1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.24642334554539208, worley.evaluateNoise(2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11587230481466135, worley.evaluateNoise(3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.299771706527259, worley.evaluateNoise(70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08841598166578284, worley.evaluateNoise(33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09786222502289546, worley.evaluateNoise(10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.29896444058172, worley.evaluateNoise(11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07365789045300233, worley.evaluateNoise(27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.07851561478019586, worley.evaluateNoise(120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 2D - Negative Coordinates")
    void testNegative2D() {
        assertAll("Is 2D Worley Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.17170331495384433, worley.evaluateNoise(-0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.30684888434661894, worley.evaluateNoise(-1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10981205939348343, worley.evaluateNoise(-2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3341531796086049, worley.evaluateNoise(-3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.0361897525580111, worley.evaluateNoise(-70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.548736533319111, worley.evaluateNoise(-33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2813287775772332, worley.evaluateNoise(-10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1329793451529307, worley.evaluateNoise(-11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14600156990176522, worley.evaluateNoise(-27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.260050089250326, worley.evaluateNoise(-90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 2D - Random Coordinates")
    void testRandom2D() {
        Random random = new Random(1000);
        assertAll("Is 2D Worley Noise outputting correct values?",
            () -> assertEquals(0.47012807081842384, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10350116353421132, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.14983085593550047, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.26591315641550833, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.19158860132911698, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09566426950144626, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06954786982274652, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3028919462610453, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1887001878379774, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5140016320183338, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 3D - Integer Coordinates")
    void testInteger3D() {
        assertAll("Is 3D Worley Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.16954329435971158, worley.evaluateNoise(0, 24, 0.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.018672808518323945, worley.evaluateNoise(-1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.8414260770906938, worley.evaluateNoise(1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3193748283484327, worley.evaluateNoise(-2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.15207744254482608, worley.evaluateNoise(2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.34348989522004875, worley.evaluateNoise(5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.20631528954143008, worley.evaluateNoise(-5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3212402316861479, worley.evaluateNoise(100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6007919776139964, worley.evaluateNoise(-1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.30686517632097965, worley.evaluateNoise(24, 0, 24.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 3D - Positive Coordinates")
    void testPositive3D() {
        assertAll("Is 3D Worley Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.002940947657812931, worley.evaluateNoise(0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5798789978383332, worley.evaluateNoise(1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.32908124163210817, worley.evaluateNoise(2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.18227708106099375, worley.evaluateNoise(3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3779294029971933, worley.evaluateNoise(70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1035790756958745, worley.evaluateNoise(33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.36246921309270247, worley.evaluateNoise(10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.06697441011557313, worley.evaluateNoise(11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.33774419015945234, worley.evaluateNoise(27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1033317054536498, worley.evaluateNoise(120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 3D - Negative Coordinates")
    void testNegative3D() {
        assertAll("Is 3D Worley Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.2563166419709144, worley.evaluateNoise(-0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1642146448026869, worley.evaluateNoise(-1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08086356696996044, worley.evaluateNoise(-2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3241642429295173, worley.evaluateNoise(-3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5292610227408882, worley.evaluateNoise(-70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.45400910589969634, worley.evaluateNoise(-33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2022908269729519, worley.evaluateNoise(-10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2989989013470719, worley.evaluateNoise(-11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.05532799318375835, worley.evaluateNoise(-27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11802158860947319, worley.evaluateNoise(-90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 3D - Random Coordinates")
    void testRandom3D() {
        Random random = new Random(1000);
        assertAll("Is 3D Worley Noise outputting correct values?",
            () -> assertEquals(0.3340597050718075, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2841343453196309, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3241115528298048, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6516095845334734, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.30524496934035, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.29947866393607087, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.16693756877666938, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2675585218487936, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3339531874704227, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.08336596657259104, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 4D - Integer Coordinates")
    void testInteger4D() {
        assertAll("Is 4D Worley Noise outputting correct values for integer coordinates?",
            () -> assertEquals(0.5975299600436452, worley.evaluateNoise(0, 24, 0, 24.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3846143020947318, worley.evaluateNoise(-1, -1000, -1, -1000.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6393002825894175, worley.evaluateNoise(1, 100, 1, 100.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.21228207606708674, worley.evaluateNoise(-2, -5, -2, -5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17815697053308224, worley.evaluateNoise(2, 5, 2, 5.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2384112240733026, worley.evaluateNoise(5, 2, 5, 2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.15906286562110705, worley.evaluateNoise(-5, -2, -5, -2.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4041184684694912, worley.evaluateNoise(100, 1, 100, 1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4609126416549272, worley.evaluateNoise(-1000, -1, -1000, -1.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3885181433624199, worley.evaluateNoise(24, 0, 24, 0.0), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 4D - Positive Coordinates")
    void testPositive4D() {
        assertAll("Is 4D Worley Noise outputting correct values for positive coordinates?",
            () -> assertEquals(0.31708873066323184, worley.evaluateNoise(0.5, 120941.094, 0.5, 120941.094), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4250701660663597, worley.evaluateNoise(1.5, 27.8924, 1.5, 27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5092517624498338, worley.evaluateNoise(2.5, 11, 2.5, 11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7022998447900377, worley.evaluateNoise(3, 10.33, 3, 10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4105687191399851, worley.evaluateNoise(70.654, 33.14, 70.654, 33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.3365750295574936, worley.evaluateNoise(33.14, 70.654, 33.14, 70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4104209989036526, worley.evaluateNoise(10.33, 3, 10.33, 3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.31367849787980207, worley.evaluateNoise(11, 2.5, 11, 2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7230181036890142, worley.evaluateNoise(27.8924, 1.5, 27.8924, 1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.11685971826859916, worley.evaluateNoise(120941.094, 0.5, 120941.094, 0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 4D - Negative Coordinates")
    void testNegative4D() {
        assertAll("Is 4D Worley Noise outputting correct values for negative coordinates?",
            () -> assertEquals(0.2643129846731483, worley.evaluateNoise(-0.5, -90.419824, -0.5, -90.419824), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5675475195188417, worley.evaluateNoise(-1.5, -27.8924, -1.5, -27.8924), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.1410185898150939, worley.evaluateNoise(-2.5, -11, -2.5, -11.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4949096465331384, worley.evaluateNoise(-3, -10.33, -3, -10.33), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.6545216680327905, worley.evaluateNoise(-70.654, -33.14, -70.654, -33.14), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.09327384842411914, worley.evaluateNoise(-33.14, -70.654, -33.14, -70.654), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.396347072869059, worley.evaluateNoise(-10.33, -3, -10.33, -3.0), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7941069064389736, worley.evaluateNoise(-11, -2.5, -11, -2.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17223850006101651, worley.evaluateNoise(-27.8924, -1.5, -27.8924, -1.5), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.10095959057519141, worley.evaluateNoise(-90.419824, -0.5, -90.419824, -0.5), ACCEPTABLE_DEVIATION)
        );
    }

    @Test
    @DisplayName("Worley Noise 4D - Random Coordinates")
    void testRandom4D() {
        Random random = new Random(1000);
        assertAll("Is 4D Worley Noise outputting correct values?",
            () -> assertEquals(0.5652667036418451, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.05877090311903511, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.25629511905587343, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.2921285757436658, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.5464122998443909, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.4625526041261592, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17345473638568162, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.17400059332707607, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.017892027631489373, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION),
            () -> assertEquals(0.7443818082348129, worley.evaluateNoise(random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000), random.nextDouble() * random.nextInt(100000)), ACCEPTABLE_DEVIATION)
        );
    }
}
