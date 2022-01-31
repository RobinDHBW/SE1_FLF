import Button.ButtonRotaryWaterCannonFront;
import Button.ButtonRotaryWaterCannonRoof;
import Button.ButtonSwitch;
import Button.Pedal;
import Cabin.*;
import Drive.Drive;
import FLF.FLF;
import Instruments.BatteryIndicator;
import Instruments.Speedometer;
import Instruments.SteeringWheel;
import Joystick.Joystick;
import Lights.*;
import Seating.Seat;
import Tank.MixingProcessor;
//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFLFBuilder {
private FLF proto;

    @BeforeEach
    void buildProto(){
        this.proto=new FLF.Builder().build();
    }

    @TestFactory
    Stream<DynamicTest> testCtrlPanelBuild() {
        ControlPanel ctrProto = this.proto.getCabin().getCtrlPanel();
        return  Arrays.asList(
                DynamicTest.dynamicTest("check btnSwitchEngines", () -> assertTrue(ctrProto.getBtnSwitchEngines() instanceof ButtonSwitch)),
                DynamicTest.dynamicTest("check btnSwitchWarnlight", () -> assertTrue(ctrProto.getBtnSwitchWarnlight() instanceof ButtonSwitch)),
                DynamicTest.dynamicTest("check btnSwitchBluelight", () -> assertTrue(ctrProto.getBtnSwitchBluelight() instanceof ButtonSwitch)),
                DynamicTest.dynamicTest("check btnSwitchFrontlight", () -> assertTrue(ctrProto.getBtnSwitchFrontlight() instanceof ButtonSwitch)),
                DynamicTest.dynamicTest("check btnSwitchRooflight", () -> assertTrue(ctrProto.getBtnSwitchRooflight() instanceof ButtonSwitch)),
                DynamicTest.dynamicTest("check btnSwitchSidelight", () -> assertTrue(ctrProto.getBtnSwitchSidelight() instanceof ButtonSwitch))
        ).stream();
    }


    @TestFactory
    Stream<DynamicTest> testCabinBuild() {
        Cabin cabProto = this.proto.getCabin();
        return Arrays.asList(
                DynamicTest.dynamicTest("check seatList", ()->assertTrue(cabProto.getSeatList().size()==4 && cabProto.getSeatList().get(0) instanceof Seat)),
                DynamicTest.dynamicTest("check batteryIndicator", ()->assertTrue(cabProto.getBatteryIndicator() instanceof BatteryIndicator)),
                DynamicTest.dynamicTest("check speedometer", ()->assertTrue(cabProto.getSpeedometer() instanceof Speedometer)),
                DynamicTest.dynamicTest("check gasPedal", ()->assertTrue(cabProto.getGasPedal() instanceof Pedal)),
                DynamicTest.dynamicTest("check brakePedal", ()->assertTrue(cabProto.getBrakePedal() instanceof Pedal)),
                DynamicTest.dynamicTest("check steeringWheel", ()->assertTrue(cabProto.getSteeringWheel() instanceof SteeringWheel)),
                DynamicTest.dynamicTest("check btnRotaryWaterCannonRoof", ()->assertTrue(cabProto.getBtnRotaryWaterCannonRoof() instanceof ButtonRotaryWaterCannonRoof)),
                DynamicTest.dynamicTest("check btnRotaryWaterCannonFront", ()->assertTrue(cabProto.getBtnRotaryWaterCannonFront() instanceof ButtonRotaryWaterCannonFront)),
                DynamicTest.dynamicTest("check ctrlPanel", ()->assertTrue(cabProto.getCtrlPanel() instanceof ControlPanel)),
                DynamicTest.dynamicTest("check centralUnit", ()->assertTrue(cabProto.getCentralUnit() instanceof CentralUnit)),
                DynamicTest.dynamicTest("check joystickDriver", ()->assertTrue(cabProto.getJoystickDriver() instanceof Joystick)),
                DynamicTest.dynamicTest("check joystickOperator", ()->assertTrue(cabProto.getJoystickOperator() instanceof Joystick)),
                DynamicTest.dynamicTest("check busDoorLeft", ()->assertTrue(cabProto.getBusDoorLeft() instanceof BusDoor)),
                DynamicTest.dynamicTest("check busDoorRight", ()->assertTrue(cabProto.getBusDoorRight() instanceof BusDoor))
        ).stream();
    }

    /**
     * @TODO check flashingBlueLights for position
     * @TODO check CabinBuilder
     * @return
     */
    @TestFactory
    Stream<DynamicTest> testFLFBuild() {

        return Arrays.asList(
                DynamicTest.dynamicTest("Check Cabin", () -> assertTrue(this.proto.getCabin() instanceof Cabin)),
                DynamicTest.dynamicTest("Check SearchLightFront", () -> assertTrue(this.proto.getSearchLightsFront().size() == 6 && this.proto.getSearchLightsFront().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check SearchLightSide", () -> assertTrue(this.proto.getSearchLightsSide().size() == 10 && this.proto.getSearchLightsSide().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check SearchLightRoof", () -> assertTrue(this.proto.getSearchLightsRoof().size() == 4 && this.proto.getSearchLightsRoof().get(0) instanceof SearchLight)),
                DynamicTest.dynamicTest("Check directionIndicatorsLeft", () -> assertTrue(this.proto.getDirectionIndicatorsLeft().size() == 2 && this.proto.getDirectionIndicatorsLeft().get(0) instanceof DirectionIndicator)),
                DynamicTest.dynamicTest("Check directionIndicatorsRight", () -> assertTrue(this.proto.getDirectionIndicatorsRight().size() == 2 && this.proto.getDirectionIndicatorsRight().get(0) instanceof DirectionIndicator)),
                DynamicTest.dynamicTest("Check brakingLights", () -> assertTrue(this.proto.getBrakingLights().size() == 2 && this.proto.getBrakingLights().get(0) instanceof BrakingLight)),
                DynamicTest.dynamicTest("Check flashingBlueLights", () -> assertTrue(this.proto.getFlashingBlueLights().size() == 10 && this.proto.getFlashingBlueLights().get(0) instanceof FlashingBlueLight)),
                DynamicTest.dynamicTest("Check warningLights", () -> assertTrue(this.proto.getWarningLights().size() == 2 && this.proto.getWarningLights().get(0) instanceof WarningLight)),
                DynamicTest.dynamicTest("Check cabin", () -> assertTrue(this.proto.getCabin() instanceof Cabin)),
                DynamicTest.dynamicTest("Check drive", () -> assertTrue(this.proto.getDrive() instanceof Drive)),
                DynamicTest.dynamicTest("Check mixingProcessor", () -> assertTrue(this.proto.getMixingProcessor() instanceof MixingProcessor))
        ).stream();

    }
}
