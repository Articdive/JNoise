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
        assertEquals(0.46858606205321760, valueLinear.getNoise(20.20, 20.30));
        assertEquals(0.48095887113902820, valueLinear.getNoise(20.21, 20.29));
        assertEquals(0.49315421136952803, valueLinear.getNoise(20.22, 20.28));
        assertEquals(0.50517208274472550, valueLinear.getNoise(20.23, 20.27));
        assertEquals(0.51701248526461240, valueLinear.getNoise(20.24, 20.26));
        assertEquals(0.52867541892919690, valueLinear.getNoise(20.25, 20.25));
        assertEquals(0.54016088373847480, valueLinear.getNoise(20.26, 20.24));
        assertEquals(0.55146887969244230, valueLinear.getNoise(20.27, 20.23));
        assertEquals(0.56259940679110710, valueLinear.getNoise(20.28, 20.22));
        assertEquals(0.57355246503446170, valueLinear.getNoise(20.29, 20.21));
        assertEquals(0.58432805442251330, valueLinear.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Cosine Value Noise 2D")
    void testCosine2D() {
        assertEquals(0.5425376394926695, valueCosine.getNoise(20.20, 20.30));
        assertEquals(0.5576478831576213, valueCosine.getNoise(20.21, 20.29));
        assertEquals(0.5722875802570215, valueCosine.getNoise(20.22, 20.28));
        assertEquals(0.5864377843974118, valueCosine.getNoise(20.23, 20.27));
        assertEquals(0.6000813048792091, valueCosine.getNoise(20.24, 20.26));
        assertEquals(0.6132027363940580, valueCosine.getNoise(20.25, 20.25));
        assertEquals(0.6257884818874290, valueCosine.getNoise(20.26, 20.24));
        assertEquals(0.6378267685338003, valueCosine.getNoise(20.27, 20.23));
        assertEquals(0.6493076567985127, valueCosine.getNoise(20.28, 20.22));
        assertEquals(0.6602230425874057, valueCosine.getNoise(20.29, 20.21));
    }

    @Test
    @DisplayName("Quadratic Value Noise 2D")
    void testQuadratic2D() {
        assertEquals(0.6635650766624133, valueQuadratic.getNoise(20.20, 20.30));
        assertEquals(0.6704999755648084, valueQuadratic.getNoise(20.21, 20.29));
        assertEquals(0.6771808017852511, valueQuadratic.getNoise(20.22, 20.28));
        assertEquals(0.6836068099545536, valueQuadratic.getNoise(20.23, 20.27));
        assertEquals(0.6897774676661454, valueQuadratic.getNoise(20.24, 20.26));
        assertEquals(0.6956924554760917, valueQuadratic.getNoise(20.25, 20.25));
        assertEquals(0.7013516669030768, valueQuadratic.getNoise(20.26, 20.24));
        assertEquals(0.7067552084284119, valueQuadratic.getNoise(20.27, 20.23));
        assertEquals(0.7119033994960406, valueQuadratic.getNoise(20.28, 20.22));
        assertEquals(0.7167967725125250, valueQuadratic.getNoise(20.29, 20.21));
        assertEquals(0.7214360728470612, valueQuadratic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Cubic Value Noise 2D")
    void testCubic2D() {
        assertEquals(0.7339848116445279, valueCubic.getNoise(20.20, 20.30));
        assertEquals(0.7370227671397351, valueCubic.getNoise(20.21, 20.29));
        assertEquals(0.7398559724804012, valueCubic.getNoise(20.22, 20.28));
        assertEquals(0.7424912372742466, valueCubic.getNoise(20.23, 20.27));
        assertEquals(0.7449354080780033, valueCubic.getNoise(20.24, 20.26));
        assertEquals(0.7471953699946425, valueCubic.getNoise(20.25, 20.25));
        assertEquals(0.7492780476316999, valueCubic.getNoise(20.26, 20.24));
        assertEquals(0.7511904054207227, valueCubic.getNoise(20.27, 20.23));
        assertEquals(0.7529394472978268, valueCubic.getNoise(20.28, 20.22));
        assertEquals(0.7545322157453616, valueCubic.getNoise(20.29, 20.21));
        assertEquals(0.7559757901946941, valueCubic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Quartic Value Noise 2D")
    void testQuartic2D() {
        assertEquals(0.7558143853612332, valueQuartic.getNoise(20.20, 20.30));
        assertEquals(0.7570076089802017, valueQuartic.getNoise(20.21, 20.29));
        assertEquals(0.7580830498028222, valueQuartic.getNoise(20.22, 20.28));
        assertEquals(0.7590486172355286, valueQuartic.getNoise(20.23, 20.27));
        assertEquals(0.7599119446128714, valueQuartic.getNoise(20.24, 20.26));
        assertEquals(0.7606803895856444, valueQuartic.getNoise(20.25, 20.25));
        assertEquals(0.7613610343573353, valueQuartic.getNoise(20.26, 20.24));
        assertEquals(0.7619606857635400, valueQuartic.getNoise(20.27, 20.23));
        assertEquals(0.7624858751925503, valueQuartic.getNoise(20.28, 20.22));
        assertEquals(0.7629428583488989, valueQuartic.getNoise(20.29, 20.21));
        assertEquals(0.7633376148652374, valueQuartic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Quintic Value Noise 2D")
    void testQuintic2D() {
        assertEquals(0.7624030949467910, valueQuintic.getNoise(20.20, 20.30));
        assertEquals(0.7628432217016551, valueQuintic.getNoise(20.21, 20.29));
        assertEquals(0.7632264905937898, valueQuintic.getNoise(20.22, 20.28));
        assertEquals(0.7635585535732952, valueQuintic.getNoise(20.23, 20.27));
        assertEquals(0.7638446709154072, valueQuintic.getNoise(20.24, 20.26));
        assertEquals(0.7640897251684802, valueQuintic.getNoise(20.25, 20.25));
        assertEquals(0.7642982350794885, valueQuintic.getNoise(20.26, 20.24));
        assertEquals(0.7644743694954046, valueQuintic.getNoise(20.27, 20.23));
        assertEquals(0.7646219612399004, valueQuintic.getNoise(20.28, 20.22));
        assertEquals(0.7647445209659212, valueQuintic.getNoise(20.29, 20.21));
        assertEquals(0.7648452509857832, valueQuintic.getNoise(20.30, 20.20));
    }

    @Test
    @DisplayName("Linear Value Noise 3D")
    void testLinear3D() {
        assertEquals(-0.015465804846957712, valueLinear.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.009857386871866373, valueLinear.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.003954933462754558, valueLinear.getNoise(20.22, 20.28, 20.22));
        assertEquals(0.0022292501147988253, valueLinear.getNoise(20.23, 20.27, 20.23));
        assertEquals(0.0086828585952062890, valueLinear.getNoise(20.24, 20.26, 20.24));
        assertEquals(0.0153935867128893730, valueLinear.getNoise(20.25, 20.25, 20.25));
        assertEquals(0.0223491292022626800, valueLinear.getNoise(20.26, 20.24, 20.26));
        assertEquals(0.0295371807977405100, valueLinear.getNoise(20.27, 20.23, 20.27));
        assertEquals(0.0369454362337448700, valueLinear.getNoise(20.28, 20.22, 20.28));
        assertEquals(0.0445615902446874350, valueLinear.getNoise(20.29, 20.21, 20.29));
        assertEquals(0.0523733375649904900, valueLinear.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Cosine Value Noise 3D")
    void testCosine3D() {
        assertEquals(-0.20313094737075510, valueCosine.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.19822045346639680, valueCosine.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.19202802608709920, valueCosine.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.18458748613176700, valueCosine.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.17593984376814492, valueCosine.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.16613300239752368, valueCosine.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.15522141501594416, valueCosine.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.14326569573202885, valueCosine.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.13033218954867660, valueCosine.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.11649250383665646, valueCosine.getNoise(20.29, 20.21, 20.29));
    }

    @Test
    @DisplayName("Quadratic Value Noise 3D")
    void testQuadratic3D() {
        assertEquals(-0.39743666987708975, valueQuadratic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.39463305022112040, valueQuadratic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.39114174437649700, valueQuadratic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.38696886011517230, valueQuadratic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.38212175738020770, valueQuadratic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.37660903967207560, valueQuadratic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.37044054395835300, valueQuadratic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.36362732910676787, valueQuadratic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.35618166284160907, valueQuadratic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.34811700722353070, valueQuadratic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.33944800265268550, valueQuadratic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Cubic Value Noise 3D")
    void testCubic3D() {
        assertEquals(-0.5278003455910405, valueCubic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.5275753925001112, valueCubic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.5268647790038036, valueCubic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.5256617619869450, valueCubic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.5239600229799604, valueCubic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.5217537001978059, valueCubic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.5190374191564304, valueCubic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.5158063218585053, valueCubic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.5120560945456324, valueCubic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.5077829940204576, valueCubic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.5029838725490154, valueCubic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Quartic Value Noise 3D")
    void testQuartic3D() {
        assertEquals(-0.5624535160831287, valueQuartic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.5627088955351214, valueQuartic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.5627299672234475, valueQuartic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.5625116651661664, valueQuartic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.5620481696655184, valueQuartic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.5613329211647466, valueQuartic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.5603586352472610, valueQuartic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.5591173189093444, valueQuartic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.5576002882580249, valueQuartic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.5557981878063110, valueQuartic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.5537010115586508, valueQuartic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Quintic Value Noise 3D")
    void testQuintic3D() {
        assertEquals(-0.5714900170413222, valueQuintic.getNoise(20.20, 20.30, 20.20));
        assertEquals(-0.5716765706203079, valueQuintic.getNoise(20.21, 20.29, 20.21));
        assertEquals(-0.5717667779735468, valueQuintic.getNoise(20.22, 20.28, 20.22));
        assertEquals(-0.5717590950042164, valueQuintic.getNoise(20.23, 20.27, 20.23));
        assertEquals(-0.5716509953959923, valueQuintic.getNoise(20.24, 20.26, 20.24));
        assertEquals(-0.5714389573272936, valueQuintic.getNoise(20.25, 20.25, 20.25));
        assertEquals(-0.5711184507870978, valueQuintic.getNoise(20.26, 20.24, 20.26));
        assertEquals(-0.5706839256130837, valueQuintic.getNoise(20.27, 20.23, 20.27));
        assertEquals(-0.5701288003938713, valueQuintic.getNoise(20.28, 20.22, 20.28));
        assertEquals(-0.5694454523996617, valueQuintic.getNoise(20.29, 20.21, 20.29));
        assertEquals(-0.5686252087295275, valueQuintic.getNoise(20.30, 20.20, 20.30));
    }

    @Test
    @DisplayName("Linear Value Noise 4D")
    void testLinear4D() {
        assertEquals(-0.27155371284820107, valueLinear.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.27584339618881243, valueLinear.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.28006512703797710, valueLinear.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.28421394052081140, valueLinear.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.28828524744426487, valueLinear.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.29227483429713175, valueLinear.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.29617886325004095, valueLinear.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.29999387215546070, valueLinear.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.30371677454770190, valueLinear.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.30734485964290936, valueLinear.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.31087579233907187, valueLinear.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Cosine Value Noise 4D")
    void testCosine4D() {
        assertEquals(-0.44794587009341924, valueCosine.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.45792778866370615, valueCosine.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.46720196172111650, valueCosine.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.47572269105491805, valueCosine.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.48344754878030720, valueCosine.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.49033766570427710, valueCosine.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.49635798082828700, valueCosine.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.50147744978187660, valueCosine.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.50566921062627180, valueCosine.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.50891070612672750, valueCosine.getNoise(20.29, 20.21, 20.29, 20.21));
    }

    @Test
    @DisplayName("Quadratic Value Noise 4D")
    void testQuadratic4D() {
        assertEquals(-0.6596092051544484, valueQuadratic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.6656579108017713, valueQuadratic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.6712022544693969, valueQuadratic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.6762333260673238, valueQuadratic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.6807429331022821, valueQuadratic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.6847236088751742, valueQuadratic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.6881686188658261, valueQuadratic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.6910719652956248, valueQuadratic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.6934283898648659, valueQuadratic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.6952333746679604, valueQuadratic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.6964831412959921, valueQuadratic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Cubic Value Noise 4D")
    void testCubic4D() {
        assertEquals(-0.7953076267759388, valueCubic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.7987949311502677, valueCubic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.8018517354091396, valueCubic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.8044800763802960, valueCubic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.8066823675905997, valueCubic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.8084613828788036, valueCubic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.8098202392719605, valueCubic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.8107623791149174, valueCubic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.8112915514493645, valueCubic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.8114117926457722, valueCubic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.8111274062981765, valueCubic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Quartic Value Noise 4D")
    void testQuartic4D() {
        assertEquals(-0.8358031381779730, valueQuartic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.8373583827599663, valueQuartic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.8386762829442288, valueQuartic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.8397637546489628, valueQuartic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.8406271462458399, valueQuartic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.8412722249340656, valueQuartic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.8417041643498862, valueQuartic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.8419275332901904, valueQuartic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.8419462854411555, valueQuartic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.8417637500151618, valueQuartic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.841382623211427, valueQuartic.getNoise(20.30, 20.20, 20.30, 20.20));
    }

    @Test
    @DisplayName("Quintic Value Noise 4D")
    void testQuintic4D() {
        assertEquals(-0.8475484399314703, valueQuintic.getNoise(20.20, 20.30, 20.20, 20.30));
        assertEquals(-0.8481644271044300, valueQuintic.getNoise(20.21, 20.29, 20.21, 20.29));
        assertEquals(-0.8486715590279789, valueQuintic.getNoise(20.22, 20.28, 20.22, 20.28));
        assertEquals(-0.8490760368986171, valueQuintic.getNoise(20.23, 20.27, 20.23, 20.27));
        assertEquals(-0.8493832004044602, valueQuintic.getNoise(20.24, 20.26, 20.24, 20.26));
        assertEquals(-0.8495975333102798, valueQuintic.getNoise(20.25, 20.25, 20.25, 20.25));
        assertEquals(-0.8497226698474615, valueQuintic.getNoise(20.26, 20.24, 20.26, 20.24));
        assertEquals(-0.8497614017892918, valueQuintic.getNoise(20.27, 20.23, 20.27, 20.23));
        assertEquals(-0.8497156861073099, valueQuintic.getNoise(20.28, 20.22, 20.28, 20.22));
        assertEquals(-0.8495866531188780, valueQuintic.getNoise(20.29, 20.21, 20.29, 20.21));
        assertEquals(-0.8493746150495843, valueQuintic.getNoise(20.30, 20.20, 20.30, 20.20));
    }
}
