import Cabin.Cabin;
import FLF.FLF;
import Lights.SearchLight;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFLFBuilder {

    @TestFactory
    Collection<DynamicTest> testBuild() {
        FLF proto = new FLF.Builder().build();
        return Arrays.asList(
                DynamicTest.dynamicTest("Check Cabin", () -> assertTrue(proto.getCabin() instanceof Cabin)),
                DynamicTest.dynamicTest("Check SearchLightFront", () -> assertTrue(proto.getSearchLightsFront().size() == 6 && proto.getSearchLightsFront().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check SearchLightSide", () -> assertTrue(proto.getSearchLightsSide().size() == 10 && proto.getSearchLightsSide().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check SearchLightRoof", () -> assertTrue(proto.getSearchLightsRoof().size() == 4 && proto.getSearchLightsRoof().get(0) instanceof SearchLight))
        );

    }
}
