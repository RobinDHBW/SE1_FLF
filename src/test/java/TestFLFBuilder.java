import Cabin.Cabin;
import Drive.Drive;
import FLF.FLF;
import Lights.*;
import Tank.MixingProcessor;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFLFBuilder {

    /**
     * @TODO check flashingBlueLights for position
     * @TODO check CabinBuilder
     * @return
     */
    @TestFactory
    Collection<DynamicTest> testBuild() {
        FLF proto = new FLF.Builder().build();
        return Arrays.asList(
                DynamicTest.dynamicTest("Check Cabin", () -> assertTrue(proto.getCabin() instanceof Cabin)),
                DynamicTest.dynamicTest("Check SearchLightFront", () -> assertTrue(proto.getSearchLightsFront().size() == 6 && proto.getSearchLightsFront().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check SearchLightSide", () -> assertTrue(proto.getSearchLightsSide().size() == 10 && proto.getSearchLightsSide().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check SearchLightRoof", () -> assertTrue(proto.getSearchLightsRoof().size() == 4 && proto.getSearchLightsRoof().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check directionIndicatorsLeft", () -> assertTrue(proto.getDirectionIndicatorsLeft().size() == 2 && proto.getDirectionIndicatorsLeft().get(0) instanceof DirectionIndicator)),
                DynamicTest.dynamicTest("Check directionIndicatorsRight", () -> assertTrue(proto.getDirectionIndicatorsRight().size() == 2 && proto.getDirectionIndicatorsRight().get(0) instanceof DirectionIndicator)),
                DynamicTest.dynamicTest("Check brakingLights", () -> assertTrue(proto.getBrakingLights().size() == 2 && proto.getBrakingLights().get(0) instanceof BrakingLight)),
                DynamicTest.dynamicTest("Check flashingBlueLights", () -> assertTrue(proto.getFlashingBlueLights().size() == 10 && proto.getFlashingBlueLights().get(0) instanceof FlashingBlueLight)),
                DynamicTest.dynamicTest("Check warningLights", () -> assertTrue(proto.getWarningLights().size() == 2 && proto.getWarningLights().get(0) instanceof WarningLight)),
                DynamicTest.dynamicTest("Check cabin", () -> assertTrue(proto.getCabin() instanceof Cabin)),
                DynamicTest.dynamicTest("Check drive", () -> assertTrue(proto.getDrive() instanceof Drive)),
                DynamicTest.dynamicTest("Check mixingProcessor", () -> assertTrue(proto.getMixingProcessor() instanceof MixingProcessor))
        );

    }
}
