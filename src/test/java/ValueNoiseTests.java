import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.interpolation.InterpolationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lukas Mansour
 */
final class ValueNoiseTests {
    private final JNoise valueLinear = JNoise.newBuilder().value().setInterpolation(InterpolationType.LINEAR).build();
    private final JNoise valueCosine = JNoise.newBuilder().value().setInterpolation(InterpolationType.COSINE).build();
    private final JNoise valueQuadratic = JNoise.newBuilder().value().setInterpolation(InterpolationType.QUADRATIC).build();
    private final JNoise valueCubic = JNoise.newBuilder().value().setInterpolation(InterpolationType.CUBIC).build();
    private final JNoise valueQuartic = JNoise.newBuilder().value().setInterpolation(InterpolationType.QUARTIC).build();
    private final JNoise valueQuintic = JNoise.newBuilder().value().setInterpolation(InterpolationType.QUINTIC).build();

    @Test
    @DisplayName("Linear Value Noise 2D")
    void testLinear2D() {
        assertEquals(-0.09535490401354925, valueLinear.getNoise(20.20, 20.30));
        assertEquals(-0.09772375160009644, valueLinear.getNoise(20.21, 20.29));
        assertEquals(-0.09994057668513730, valueLinear.getNoise(20.22, 20.28));
        assertEquals(-0.10200537926867334, valueLinear.getNoise(20.23, 20.27));
        assertEquals(-0.10391815935070307, valueLinear.getNoise(20.24, 20.26));
        assertEquals(-0.10567891693122791, valueLinear.getNoise(20.25, 20.25));
        assertEquals(-0.10728765201024715, valueLinear.getNoise(20.26, 20.24));
        assertEquals(-0.10874436458776027, valueLinear.getNoise(20.27, 20.23));
        assertEquals(-0.11004905466376833, valueLinear.getNoise(20.28, 20.22));
        assertEquals(-0.11120172223827036, valueLinear.getNoise(20.29, 20.21));
        assertEquals(-0.11220236731126723, valueLinear.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Cosine Value Noise 2D")
    void testCosine2D() {
        assertEquals(-0.12012021908269205, valueCosine.getNoise(20.20, 20.30));
        assertEquals(-0.12333676016663497, valueCosine.getNoise(20.21, 20.29));
        assertEquals(-0.12626411325770956, valueCosine.getNoise(20.22, 20.28));
        assertEquals(-0.12889553568903636, valueCosine.getNoise(20.23, 20.27));
        assertEquals(-0.13122566699002497, valueCosine.getNoise(20.24, 20.26));
        assertEquals(-0.13325054508308426, valueCosine.getNoise(20.25, 20.25));
        assertEquals(-0.13496761675491270, valueCosine.getNoise(20.26, 20.24));
        assertEquals(-0.13637574236616360, valueCosine.getNoise(20.27, 20.23));
        assertEquals(-0.13747519478618360, valueCosine.getNoise(20.28, 20.22));
        assertEquals(-0.13826765256251622, valueCosine.getNoise(20.29, 20.21));
    }

    @Test
    @DisplayName("Quadratic Value Noise 2D")
    void testQuadratic2D() {
        assertEquals(-0.16170228027264460, valueQuadratic.getNoise(20.20, 20.30));
        assertEquals(-0.16313820057824946, valueQuadratic.getNoise(20.21, 20.29));
        assertEquals(-0.16444307284525814, valueQuadratic.getNoise(20.22, 20.28));
        assertEquals(-0.16561625857916515, valueQuadratic.getNoise(20.23, 20.27));
        assertEquals(-0.16665730171246523, valueQuadratic.getNoise(20.24, 20.26));
        assertEquals(-0.16756592860465636, valueQuadratic.getNoise(20.25, 20.25));
        assertEquals(-0.16834204804223730, valueQuadratic.getNoise(20.26, 20.24));
        assertEquals(-0.16898575123870863, valueQuadratic.getNoise(20.27, 20.23));
        assertEquals(-0.16949731183457364, valueQuadratic.getNoise(20.28, 20.22));
        assertEquals(-0.16987718589733640, valueQuadratic.getNoise(20.29, 20.21));
        assertEquals(-0.17012601192150356, valueQuadratic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Cubic Value Noise 2D")
    void testCubic2D() {
        assertEquals(-0.18870237934920167, valueCubic.getNoise(20.20, 20.30));
        assertEquals(-0.18941917538482540, valueCubic.getNoise(20.21, 20.29));
        assertEquals(-0.19004527691932510, valueCubic.getNoise(20.22, 20.28));
        assertEquals(-0.19058157923299351, valueCubic.getNoise(20.23, 20.27));
        assertEquals(-0.19102900925720723, valueCubic.getNoise(20.24, 20.26));
        assertEquals(-0.19138852694263095, valueCubic.getNoise(20.25, 20.25));
        assertEquals(-0.19166112608013770, valueCubic.getNoise(20.26, 20.24));
        assertEquals(-0.19184783457444995, valueCubic.getNoise(20.27, 20.23));
        assertEquals(-0.19194971417049920, valueCubic.getNoise(20.28, 20.22));
        assertEquals(-0.19196785963250407, valueCubic.getNoise(20.29, 20.21));
        assertEquals(-0.19190339737576810, valueCubic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Quartic Value Noise 2D")
    void testQuartic2D() {
        assertEquals(-0.19678114270566893, valueQuartic.getNoise(20.20, 20.30));
        assertEquals(-0.19709097997795907, valueQuartic.getNoise(20.21, 20.29));
        assertEquals(-0.19735298734611420, valueQuartic.getNoise(20.22, 20.28));
        assertEquals(-0.19756863849789227, valueQuartic.getNoise(20.23, 20.27));
        assertEquals(-0.19773927454705680, valueQuartic.getNoise(20.24, 20.26));
        assertEquals(-0.19786610436585067, valueQuartic.getNoise(20.25, 20.25));
        assertEquals(-0.19795020478754427, valueQuartic.getNoise(20.26, 20.24));
        assertEquals(-0.19799252067446282, valueQuartic.getNoise(20.27, 20.23));
        assertEquals(-0.19799386484995940, valueQuartic.getNoise(20.28, 20.22));
        assertEquals(-0.19795491789586603, valueQuartic.getNoise(20.29, 20.21));
        assertEquals(-0.19787622782002060, valueQuartic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Quintic Value Noise 2D")
    void testQuintic2D() {
        assertEquals(-0.19911409182293321, valueQuintic.getNoise(20.20, 20.30));
        assertEquals(-0.19923593788818170, valueQuintic.getNoise(20.21, 20.29));
        assertEquals(-0.19933611245718450, valueQuintic.getNoise(20.22, 20.28));
        assertEquals(-0.19941585452286950, valueQuintic.getNoise(20.23, 20.27));
        assertEquals(-0.19947622708284290, valueQuintic.getNoise(20.24, 20.26));
        assertEquals(-0.19951811921157600, valueQuintic.getNoise(20.25, 20.25));
        assertEquals(-0.19954224811333576, valueQuintic.getNoise(20.26, 20.24));
        assertEquals(-0.19954916115445034, valueQuintic.getNoise(20.27, 20.23));
        assertEquals(-0.19953923787443478, valueQuintic.getNoise(20.28, 20.22));
        assertEquals(-0.19951269197645155, valueQuintic.getNoise(20.29, 20.21));
        assertEquals(-0.19946957329851503, valueQuintic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Linear Value Noise 3D")
    void testLinear3D() {
        assertEquals(-0.492526784682521760, valueLinear.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.495897015264078260, valueLinear.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.498579343679760100, valueLinear.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.500579497285504100, valueLinear.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.501903203437243700, valueLinear.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.502556189490914500, valueLinear.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.502544182802450600, valueLinear.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.501872910727787100, valueLinear.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.500548100622858400, valueLinear.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.498575479843600070, valueLinear.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.495960775745945500, valueLinear.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Cosine Value Noise 3D")
    void testCosine3D() {
        assertEquals(-0.6408548656211982, valueCosine.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.6490643717369429, valueCosine.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.6558666286473566, valueCosine.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.6612336770268737, valueCosine.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.6651437420228368, valueCosine.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.6675812626700944, valueCosine.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.6685368878079773, valueCosine.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.6680074391629253, valueCosine.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.6659958424681816, valueCosine.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.6625110276902912, valueCosine.getNoise(20.29, 20.21, 20.29));
    }

    @Test
    @DisplayName("Quadratic Value Noise 3D")
    void testQuadratic3D() {
        assertEquals(-0.8245052893158410, valueQuadratic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.8284603738376790, valueQuadratic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.8318052701096875, valueQuadratic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.8345355578506002, valueQuadratic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.8366475109909568, valueQuadratic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.8381380936639625, valueQuadratic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.8390049555090501, valueQuadratic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.8392464262881686, valueQuadratic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.8388615098147854, valueQuadratic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.8378498771956058, valueQuadratic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.8362118593850097, valueQuadratic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Cubic Value Noise 3D")
    void testCubic3D() {
        assertEquals(-0.9414808540620131, valueCubic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.9438029208160136, valueCubic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.9457226336022574, valueCubic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.9472407779161217, valueCubic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.9483582041934660, valueCubic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.9490758270627215, valueCubic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.9493946219511316, valueCubic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.9493156190413079, valueCubic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.9488398945768053, valueCubic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.9479685595182993, valueCubic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.9467027455551893, valueCubic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Quartic Value Noise 3D")
    void testQuartic3D() {
        assertEquals(-0.9752871991603094, valueQuartic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.9763671568602617, valueQuartic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.9772411159823927, valueQuartic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.9779128562988488, valueQuartic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.9783855384158494, valueQuartic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.9786617021371867, valueQuartic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.9787432638614462, valueQuartic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.9786315129557486, valueQuartic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.9783271070601749, valueQuartic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.9778300662884853, valueQuartic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.9771397663022436, valueQuartic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Quintic Value Noise 3D")
    void testQuintic3D() {
        assertEquals(-0.9848440587726456, valueQuintic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.9852862144994571, valueQuintic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.9856371188334737, valueQuintic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.9859005721529960, valueQuintic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.9860795900545043, valueQuintic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.9861764063361024, valueQuintic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.9861924757327281, valueQuintic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.9861284763661597, valueQuintic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.9859843118726130, valueQuintic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.9857591131702157, valueQuintic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.9854512398277185, valueQuintic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Linear Value Noise 4D")
    void testLinear4D() {
        assertEquals(0.24072627388640433, valueLinear.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(0.24356395878146360, valueLinear.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(0.24605313678062865, valueLinear.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(0.24821011182614516, valueLinear.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(0.25005143165115960, valueLinear.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(0.25159388777972490, valueLinear.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(0.25285451552679620, valueLinear.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(0.25385059399823284, valueLinear.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(0.25459964609079910, valueLinear.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(0.25511943849216200, valueLinear.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(0.25542798168089340, valueLinear.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Cosine Value Noise 4D")
    void testCosine4D() {
        assertEquals(0.27259061654956684, valueCosine.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(0.27615610997414860, valueCosine.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(0.27915795068695770, valueCosine.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(0.28161505543083620, valueCosine.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(0.28354969504585564, valueCosine.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(0.28498720818186896, valueCosine.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(0.28595567487454465, valueCosine.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(0.28648555299245937, valueCosine.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(0.28660928111163330, valueCosine.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(0.28636085189119187, valueCosine.getNoise(20.29, 20.21, 20.29, 20.21));
    }

    @Test
    @DisplayName("Quadratic Value Noise 4D")
    void testQuadratic4D() {
        assertEquals(0.29935981810117840, valueQuadratic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(0.30014061211102010, valueQuadratic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(0.30078007852213130, valueQuadratic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(0.30128318089707956, valueQuadratic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(0.30165502391239570, valueQuadratic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(0.30190084072522420, valueQuadratic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(0.30202597853452360, valueQuadratic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(0.30203588233067590, valueQuadratic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(0.30193607683145685, valueQuadratic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(0.30173214660641345, valueQuadratic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(0.30142971439579413, valueQuadratic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Cubic Value Noise 4D")
    void testCubic4D() {
        assertEquals(0.30963024416287804, valueCubic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(0.30978058969193600, valueCubic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(0.30988641254138627, valueCubic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(0.30994939010671224, valueCubic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(0.30997102577442615, valueCubic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(0.30995266492908014, valueCubic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(0.30989550943558186, valueCubic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(0.30980063058765850, valueCubic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(0.30966898051935493, valueCubic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(0.30950140208354554, valueCubic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(0.30929863720955470, valueCubic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Quartic Value Noise 4D")
    void testQuartic4D() {
        assertEquals(0.31193602959271344, valueQuartic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(0.31198104188492080, valueQuartic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(0.31201052708108656, valueQuartic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(0.31202484114862006, valueQuartic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(0.31202422164434520, valueQuartic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(0.31200879853349395, valueQuartic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(0.31197860365772850, valueQuartic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(0.31193357892167045, valueQuartic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(0.31187358326880765, valueQuartic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(0.31179839851924035, valueQuartic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(0.31170773414349495, valueQuartic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Quintic Value Noise 4D")
    void testQuintic4D() {
        assertEquals(0.31250025380005650, valueQuintic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(0.31251784341412190, valueQuintic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(0.31252966779301160, valueQuintic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(0.31253583621789170, valueQuintic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(0.31253638726112876, valueQuintic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(0.31253129192421913, valueQuintic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(0.31252045611683140, valueQuintic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(0.31250372255768480, valueQuintic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(0.31248087216950565, valueQuintic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(0.31245162503251340, valueQuintic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(0.31241564095375090, valueQuintic.getNoise(20.30, 20.20, 20.30, 20.20));
    }
}
