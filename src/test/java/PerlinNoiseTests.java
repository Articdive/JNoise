import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.interpolation.InterpolationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lukas Mansour
 */
final class PerlinNoiseTests {
    private final JNoise perlinLinear = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).build();
    private final JNoise perlinCosine = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.COSINE).build();
    private final JNoise perlinQuadratic = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.QUADRATIC).build();
    private final JNoise perlinCubic = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.CUBIC).build();
    private final JNoise perlinQuartic = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.QUARTIC).build();
    private final JNoise perlinQuintic = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.QUINTIC).build();

    @Test
    @DisplayName("Linear Noise Zero Equality")
    void testLinearZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinLinear.getNoise(i, i, i, i)));
        }
    }

    @Test
    @DisplayName("Cosine Noise Zero Equality")
    void testCosineZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinCosine.getNoise(i, i, i, i)));
        }
    }

    @Test
    @DisplayName("Quadratic Noise Zero Equality")
    void testQuadraticZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinQuadratic.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinQuadratic.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinQuadratic.getNoise(i, i, i, i)));
        }
    }

    @Test
    @DisplayName("Cubic Noise Zero Equality")
    void testCubicZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinCubic.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinCubic.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinCubic.getNoise(i, i, i, i)));
        }
    }

    @Test
    @DisplayName("Quartic Noise Zero Equality")
    void testQuarticZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinQuartic.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinQuartic.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinQuartic.getNoise(i, i, i, i)));
        }
    }

    @Test
    @DisplayName("Quintic Noise Zero Equality")
    void testQuinticZeroEquality() {
        for (int i = 0; i < 10; i++) {
            assertEquals(0.0, Math.abs(perlinQuintic.getNoise(i, i)));
            assertEquals(0.0, Math.abs(perlinQuintic.getNoise(i, i, i)));
            assertEquals(0.0, Math.abs(perlinQuintic.getNoise(i, i, i, i)));
        }
    }

    @Test
    @DisplayName("Linear Noise 2D")
    void testLinear2D() {
        assertEquals(-0.38191272832000060, perlinLinear.getNoise(20.20, 20.30));
        assertEquals(-0.37322978832434955, perlinLinear.getNoise(20.21, 20.29));
        assertEquals(-0.36466750195099185, perlinLinear.getNoise(20.22, 20.28));
        assertEquals(-0.35626929462737190, perlinLinear.getNoise(20.23, 20.27));
        assertEquals(-0.34807511899461100, perlinLinear.getNoise(20.24, 20.26));
        assertEquals(-0.34012126922607420, perlinLinear.getNoise(20.25, 20.25));
        assertEquals(-0.33244024230402747, perlinLinear.getNoise(20.26, 20.24));
        assertEquals(-0.32506064398499860, perlinLinear.getNoise(20.27, 20.23));
        assertEquals(-0.31800713680103740, perlinLinear.getNoise(20.28, 20.22));
        assertEquals(-0.31130042708550740, perlinLinear.getNoise(20.29, 20.21));
        assertEquals(-0.30495728767999960, perlinLinear.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Cosine Noise 2D")
    void testCosine2D() {
        assertEquals(-0.32911756513770910, perlinCosine.getNoise(20.20, 20.30));
        assertEquals(-0.31750530195457560, perlinCosine.getNoise(20.21, 20.29));
        assertEquals(-0.30625368886368800, perlinCosine.getNoise(20.22, 20.28));
        assertEquals(-0.29543465457057183, perlinCosine.getNoise(20.23, 20.27));
        assertEquals(-0.28511351845410726, perlinCosine.getNoise(20.24, 20.26));
        assertEquals(-0.27534868253207960, perlinCosine.getNoise(20.25, 20.25));
        assertEquals(-0.26619145244635356, perlinCosine.getNoise(20.26, 20.24));
        assertEquals(-0.25768597805282760, perlinCosine.getNoise(20.27, 20.23));
        assertEquals(-0.24986929952078762, perlinCosine.getNoise(20.28, 20.22));
        assertEquals(-0.24277148096972512, perlinCosine.getNoise(20.29, 20.21));
    }

    @Test
    @DisplayName("Quadratic Noise 2D")
    void testQuadratic2D() {
        assertEquals(-0.31219941275000745, perlinQuadratic.getNoise(20.20, 20.30));
        assertEquals(-0.30150763024990320, perlinQuadratic.getNoise(20.21, 20.29));
        assertEquals(-0.29097247385956415, perlinQuadratic.getNoise(20.22, 20.28));
        assertEquals(-0.28062153394062467, perlinQuadratic.getNoise(20.23, 20.27));
        assertEquals(-0.27048025232264317, perlinQuadratic.getNoise(20.24, 20.26));
        assertEquals(-0.26057195760586180, perlinQuadratic.getNoise(20.25, 20.25));
        assertEquals(-0.25091792556507000, perlinQuadratic.getNoise(20.26, 20.24));
        assertEquals(-0.24153746281113053, perlinQuadratic.getNoise(20.27, 20.23));
        assertEquals(-0.23244801086054356, perlinQuadratic.getNoise(20.28, 20.22));
        assertEquals(-0.22366526680853860, perlinQuadratic.getNoise(20.29, 20.21));
        assertEquals(-0.21520331595385178, perlinQuadratic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Cubic Noise 2D")
    void testCubic2D() {
        assertEquals(-0.30183090800532140, perlinCubic.getNoise(20.20, 20.30));
        assertEquals(-0.29156223726622250, perlinCubic.getNoise(20.21, 20.29));
        assertEquals(-0.28135234046985110, perlinCubic.getNoise(20.22, 20.28));
        assertEquals(-0.27120432342669370, perlinCubic.getNoise(20.23, 20.27));
        assertEquals(-0.26112148713245253, perlinCubic.getNoise(20.24, 20.26));
        assertEquals(-0.25110768212602500, perlinCubic.getNoise(20.25, 20.25));
        assertEquals(-0.24116762790646465, perlinCubic.getNoise(20.26, 20.24));
        assertEquals(-0.23130719255732418, perlinCubic.getNoise(20.27, 20.23));
        assertEquals(-0.22153362867590473, perlinCubic.getNoise(20.28, 20.22));
        assertEquals(-0.21185576267751094, perlinCubic.getNoise(20.29, 20.21));
        assertEquals(-0.20228413552250682, perlinCubic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Quartic Noise 2D")
    void testQuartic2D() {
        assertEquals(-0.30028853619476525, perlinQuartic.getNoise(20.20, 20.30));
        assertEquals(-0.29022262932628360, perlinQuartic.getNoise(20.21, 20.29));
        assertEquals(-0.28017328762688750, perlinQuartic.getNoise(20.22, 20.28));
        assertEquals(-0.27013928184646396, perlinQuartic.getNoise(20.23, 20.27));
        assertEquals(-0.26011984055893410, perlinQuartic.getNoise(20.24, 20.26));
        assertEquals(-0.25011480513062023, perlinQuartic.getNoise(20.25, 20.25));
        assertEquals(-0.24012477183880390, perlinQuartic.getNoise(20.26, 20.24));
        assertEquals(-0.23015122560590737, perlinQuartic.getNoise(20.27, 20.23));
        assertEquals(-0.22019666919415312, perlinQuartic.getNoise(20.28, 20.22));
        assertEquals(-0.21026475084198948, perlinQuartic.getNoise(20.29, 20.21));
        assertEquals(-0.20036039227175530, perlinQuartic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Quintic Noise 2D")
    void testQuintic2D() {
        assertEquals(-0.30004646432719710, perlinQuintic.getNoise(20.20, 20.30));
        assertEquals(-0.29003262580739770, perlinQuintic.getNoise(20.21, 20.29));
        assertEquals(-0.28002288425545446, perlinQuintic.getNoise(20.22, 20.28));
        assertEquals(-0.27001648507533116, perlinQuintic.getNoise(20.23, 20.27));
        assertEquals(-0.26001290712421155, perlinQuintic.getNoise(20.24, 20.26));
        assertEquals(-0.25001188565419590, perlinQuintic.getNoise(20.25, 20.25));
        assertEquals(-0.24001343905492714, perlinQuintic.getNoise(20.26, 20.24));
        assertEquals(-0.23001790441357792, perlinQuintic.getNoise(20.27, 20.23));
        assertEquals(-0.22002598616831054, perlinQuintic.getNoise(20.28, 20.22));
        assertEquals(-0.21003882135548507, perlinQuintic.getNoise(20.29, 20.21));
        assertEquals(-0.20005806414488353, perlinQuintic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Linear Noise 3D")
    void testLinear3D() {
        assertEquals(0.2152149951701008400, perlinLinear.getNoise(20.20, 20.30, 20.20));
        assertEquals(0.1915217587357307700, perlinLinear.getNoise(20.21, 20.29, 20.21));
        assertEquals(0.1674236776724761200, perlinLinear.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.1430221279831278800, perlinLinear.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.1184181866889740000, perlinLinear.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.0937115214765071900, perlinLinear.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.0689993613699836600, perlinLinear.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.0443755534474505540, perlinLinear.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.0199297090205966100, perlinLinear.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.004253558755106662, perlinLinear.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.028095305153998480, perlinLinear.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Cosine Noise 3D")
    void testCosine3D() {
        assertEquals(0.29615257527306920, perlinCosine.getNoise(20.20, 20.30, 20.20));
        assertEquals(0.28045406516328710, perlinCosine.getNoise(20.21, 20.29, 20.21));
        assertEquals(0.26407078349151114, perlinCosine.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.24697310435750970, perlinCosine.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.22913674035408474, perlinCosine.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.21054448822414498, perlinCosine.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.19118783987682433, perlinCosine.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.17106838344063974, perlinCosine.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.15019891938044225, perlinCosine.getNoise(20.28, 20.22, 20.28));
        assertEquals(0.12860422051902892, perlinCosine.getNoise(20.29, 20.21, 20.29));
    }

    @Test
    @DisplayName("Quadratic Noise 3D")
    void testQuadratic3D() {
        assertEquals(0.29857885735012130, perlinQuadratic.getNoise(20.20, 20.30, 20.20));
        assertEquals(0.28623267478513210, perlinQuadratic.getNoise(20.21, 20.29, 20.21));
        assertEquals(0.27360674238186444, perlinQuadratic.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.26068512516625186, perlinQuadratic.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.24745350294052906, perlinQuadratic.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.23389991322243717, perlinQuadratic.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.22001546620739126, perlinQuadratic.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.20579501308004713, perlinQuadratic.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.19123774912458500, perlinQuadratic.getNoise(20.28, 20.22, 20.28));
        assertEquals(0.17634773352372826, perlinQuadratic.getNoise(20.29, 20.21, 20.29));
        assertEquals(0.16113430854968486, perlinQuadratic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Cubic Noise 3D")
    void testCubic3D() {
        assertEquals(0.30047849210426550, perlinCubic.getNoise(20.20, 20.30, 20.20));
        assertEquals(0.29015208140306960, perlinCubic.getNoise(20.21, 20.29, 20.21));
        assertEquals(0.27978980109908935, perlinCubic.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.26937744075519954, perlinCubic.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.25889878273625440, perlinCubic.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.24833586432353236, perlinCubic.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.23766930699085550, perlinCubic.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.22687871655765920, perlinCubic.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.21594315560635854, perlinCubic.getNoise(20.28, 20.22, 20.28));
        assertEquals(0.20484168676257236, perlinCubic.getNoise(20.29, 20.21, 20.29));
        assertEquals(0.19355398223737894, perlinCubic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Quartic Noise 3D")
    void testQuartic3D() {
        assertEquals(0.30011894821246476, perlinQuartic.getNoise(20.20, 20.30, 20.20));
        assertEquals(0.29006985438217153, perlinQuartic.getNoise(20.21, 20.29, 20.21));
        assertEquals(0.28002065016719707, perlinQuartic.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.26996728557678970, perlinQuartic.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.25990490975723240, perlinQuartic.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.24982776429033560, perlinQuartic.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.23972907146231070, perlinQuartic.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.22960092995812745, perlinQuartic.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.21943423190449385, perlinQuartic.getNoise(20.28, 20.22, 20.28));
        assertEquals(0.20921861620660123, perlinQuartic.getNoise(20.29, 20.21, 20.29));
        assertEquals(0.19894247358480233, perlinQuartic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Quintic Noise 3D")
    void testQuintic3D() {
        assertEquals(0.30002176553662685, perlinQuintic.getNoise(20.20, 20.30, 20.20));
        assertEquals(0.29001358102717170, perlinQuintic.getNoise(20.21, 20.29, 20.21));
        assertEquals(0.28000652980766194, perlinQuintic.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.26999968995741870, perlinQuintic.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.25999199835322845, perlinQuintic.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.24998217121850355, perlinQuintic.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.23996860675860912, perlinQuintic.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.22994927215393046, perlinQuintic.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.21992157827722870, perlinQuintic.getNoise(20.28, 20.22, 20.28));
        assertEquals(0.20988224696299568, perlinQuintic.getNoise(20.29, 20.21, 20.29));
        assertEquals(0.19982717744958975, perlinQuintic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Linear Noise 4D")
    void testLinear4D() {
        assertEquals(-0.250273277659262770, perlinLinear.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.230299680259880240, perlinLinear.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.208817809193884620, perlinLinear.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.185946408483776200, perlinLinear.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.161811143873474730, perlinLinear.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.136543394641194030, perlinLinear.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.110279013415250260, perlinLinear.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.083157068371000970, perlinLinear.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.055318582108345074, perlinLinear.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.026905281062821786, perlinLinear.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(0.0019416314896686382, perlinLinear.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Cosine Noise 4D")
    void testCosine4D() {
        assertEquals(-0.36100402916191430, perlinCosine.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.33617193566070480, perlinCosine.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.30977470299715065, perlinCosine.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.28200872429971696, perlinCosine.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.25306303676667620, perlinCosine.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.22311924175876988, perlinCosine.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.19235153851159345, perlinCosine.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.16092676213122020, perlinCosine.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.12900433872777983, perlinCosine.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.09673609629990902, perlinCosine.getNoise(20.29, 20.21, 20.29, 20.21));
    }

    @Test
    @DisplayName("Quadratic Noise 4D")
    void testQuadratic4D() {
        assertEquals(-0.38514021468728260, perlinQuadratic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.35694454645038237, perlinQuadratic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.32817807712131625, perlinQuadratic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.29891357202545715, perlinQuadratic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.26922150801874994, perlinQuadratic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.23917060995513711, perlinQuadratic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.20882836574123276, perlinQuadratic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.17826149848064143, perlinQuadratic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.14753637752504353, perlinQuadratic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.11671935370252441, perlinQuadratic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.08587700744846698, perlinQuadratic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Cubic Noise 4D")
    void testCubic4D() {
        assertEquals(-0.39804574149420124, perlinCubic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.36846199859055473, perlinCubic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.33873371229841087, perlinCubic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.30888366505294170, perlinCubic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.27893048622549915, perlinCubic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.24888955056607495, perlinCubic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.21877390875102284, perlinCubic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.18859524231767033, perlinCubic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.15836483344289606, perlinCubic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.12809453791373943, perlinCubic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.09779774736146611, perlinCubic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Quartic Noise 4D")
    void testQuartic4D() {
        assertEquals(-0.39970513782639666, perlinQuartic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.36979390814016400, perlinQuartic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.33985014766844523, perlinQuartic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.30988104252521040, perlinQuartic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.27989161438791360, perlinQuartic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.24988516520650997, perlinQuartic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.21986369484469925, perlinQuartic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.18982830543563250, perlinQuartic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.15977960727201806, perlinQuartic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.12971814131729548, perlinQuartic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.09964483282283966, perlinQuartic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Quintic Noise 4D")
    void testQuintic4D() {
        assertEquals(-0.39995319113955075, perlinQuintic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.36997061728777697, perlinQuintic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.33998113967607010, perlinQuintic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.30998679964635810, perlinQuintic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.27998888588962156, perlinQuintic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.24998811402794116, perlinQuintic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.21998476696317120, perlinQuintic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.18997880862690084, perlinQuintic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.15996998446767900, perlinQuintic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.12995792271274510, perlinQuintic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.09994225111692662, perlinQuintic.getNoise(20.30, 20.20, 20.30, 20.20));
    }
}
