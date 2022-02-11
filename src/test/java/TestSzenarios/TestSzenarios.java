package TestSzenarios;

import Button.RoofCannonMode;
import FLF.FLF;
import Firefighting.CannonIdentifier;
import Person.Driver;
import Person.EmployeeFirebase;
import Person.Infantry;
import Person.Operator;
import Seating.Seat;
import Tank.MixingRate;
import Tank.TankSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestSzenarios {
    private FLF flf;

    private Driver driver;
    private Operator operator;
    private ArrayList<Infantry> infanterists;
    private EmployeeFirebase employee;

    public TestSzenarios() {
    }

    @BeforeEach
    void initRoutine() {
        this.flf = new FLF.Builder().build();
        if (!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if (!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);
        this.driver = new Driver();
        this.operator = new Operator();
        this.employee = new EmployeeFirebase();

        this.flf.toggleMaintenance(employee);
        employee.loadBatteries();
        employee.fillWaterTank();
        employee.fillFoamTank();
        this.flf.toggleMaintenance(employee);

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);
        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(false);

        if (this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if (this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A) {
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }
    }

    @TestFactory
    Stream<DynamicTest> park() {
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if (this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if (this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if (this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if (this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if (this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if (this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(false);

        for (int i = 0; i < 2; i++) {
            this.flf.leaveFLF(i, true);
            this.flf.leaveFLF(i, false);
        }

        for (Seat s : this.flf.getCabin().getSeatList()) {
            tests.add(DynamicTest.dynamicTest("check Seat", () -> assertFalse(s.getOccupied())));
        }

        Double battFull = this.flf.getDrive().getRelativeFillState();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check Engines", () -> assertFalse(this.flf.getDrive().getEngineState())),
                DynamicTest.dynamicTest("check FrontLights", () -> assertFalse(this.flf.getSearchLightFrontState())),
                DynamicTest.dynamicTest("check RoofLights", () -> assertFalse(this.flf.getSearchLightRoofState())),
                DynamicTest.dynamicTest("check SideLights", () -> assertFalse(this.flf.getSearchLightSideState())),
                DynamicTest.dynamicTest("check WarnLights", () -> assertFalse(this.flf.getWarnLightsState())),
                DynamicTest.dynamicTest("check BlueLights", () -> assertFalse(this.flf.getBlueLightState())),
                DynamicTest.dynamicTest("check RotaryButtonFrontCannon", () -> assertTrue(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() == 1)),
                DynamicTest.dynamicTest("check RotaryButtonRoofCannon", () -> assertTrue(this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() == RoofCannonMode.A)),
                DynamicTest.dynamicTest("check LeftDoor", () -> assertTrue(this.flf.getCabin().getBusDoorLeft().getOpen())),
                DynamicTest.dynamicTest("check RightDoor", () -> assertTrue(this.flf.getCabin().getBusDoorRight().getOpen())),
                DynamicTest.dynamicTest("check FrontCannon", () -> assertFalse(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT))),
                DynamicTest.dynamicTest("check RoofCannon", () -> assertFalse(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF))),
                DynamicTest.dynamicTest("check WaterTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER))),
                DynamicTest.dynamicTest("check FoamTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM))),
                DynamicTest.dynamicTest("check Batteries", () -> assertEquals(1, battFull))
        );


        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> controlRide() {
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if (!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if (!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if (this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if (this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if (!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if (this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for (Seat s : this.flf.getCabin().getSeatList()) {
            if (s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            } else {
                tests.add(DynamicTest.dynamicTest("check SeatBack", () -> assertFalse(s.getOccupied())));
            }
        }

        Double battFull = this.flf.getDrive().getRelativeFillState();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check Engines", () -> assertTrue(this.flf.getDrive().getEngineState())),
                DynamicTest.dynamicTest("check FrontLights", () -> assertTrue(this.flf.getSearchLightFrontState())),
                DynamicTest.dynamicTest("check RoofLights", () -> assertFalse(this.flf.getSearchLightRoofState())),
                DynamicTest.dynamicTest("check SideLights", () -> assertFalse(this.flf.getSearchLightSideState())),
                DynamicTest.dynamicTest("check WarnLights", () -> assertTrue(this.flf.getWarnLightsState())),
                DynamicTest.dynamicTest("check BlueLights", () -> assertFalse(this.flf.getBlueLightState())),
                DynamicTest.dynamicTest("check RotaryButtonFrontCannon", () -> assertTrue(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() == 1)),
                DynamicTest.dynamicTest("check RotaryButtonRoofCannon", () -> assertTrue(this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() == RoofCannonMode.A)),
                DynamicTest.dynamicTest("check LeftDoor", () -> assertFalse(this.flf.getCabin().getBusDoorLeft().getOpen())),
                DynamicTest.dynamicTest("check RightDoor", () -> assertFalse(this.flf.getCabin().getBusDoorRight().getOpen())),
                DynamicTest.dynamicTest("check FrontCannon", () -> assertFalse(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT))),
                DynamicTest.dynamicTest("check RoofCannon", () -> assertFalse(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF))),
                DynamicTest.dynamicTest("check WaterTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER))),
                DynamicTest.dynamicTest("check FoamTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM))),
                DynamicTest.dynamicTest("check Batteries", () -> assertEquals(1, battFull))
        );

        Integer setpointConsumption = 0;

        for (int i = 0; i < 7; i++) {
            this.driver.accelerate();
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        for (int i = 0; i < 5; i++) {
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        this.driver.steer(true, 5);
        Integer steerState1 = this.flf.getDrive().getSteeringAngle();
        for (int i = 0; i < 3; i++) {
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        this.driver.steer(false, 5);
        Integer steerState2 = this.flf.getDrive().getSteeringAngle();
        for (int i = 0; i < 5; i++) {
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        this.driver.steer(false, 5);
        Integer steerState3 = this.flf.getDrive().getSteeringAngle();
        for (int i = 0; i < 5; i++) {
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        for (int i = 0; i < 7; i++) {
            this.driver.brake();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
            if (this.flf.getDrive().getSpeed() > 0) this.flf.drive();
        }


        Integer finalSetpointConsumption = setpointConsumption;
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check steerLeft", () -> assertEquals(-5, steerState1)),
                DynamicTest.dynamicTest("check steerStraight", () -> assertEquals(0, steerState2)),
                DynamicTest.dynamicTest("check steerRight", () -> assertEquals(5, steerState3)),
                DynamicTest.dynamicTest("check consumption", () -> assertEquals(finalSetpointConsumption, this.flf.getDrive().getCapacity() - this.flf.getDrive().getAbsoluteFillState()))
        );
        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> emergencyRide() {
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if (!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if (!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if (!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if (this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if (!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if (!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for (Seat s : this.flf.getCabin().getSeatList()) {
            if (s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            } else {
                tests.add(DynamicTest.dynamicTest("check SeatBack", () -> assertFalse(s.getOccupied())));
            }
        }

        Double battFull = this.flf.getDrive().getRelativeFillState();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check Engines", () -> assertTrue(this.flf.getDrive().getEngineState())),
                DynamicTest.dynamicTest("check FrontLights", () -> assertTrue(this.flf.getSearchLightFrontState())),
                DynamicTest.dynamicTest("check RoofLights", () -> assertTrue(this.flf.getSearchLightRoofState())),
                DynamicTest.dynamicTest("check SideLights", () -> assertFalse(this.flf.getSearchLightSideState())),
                DynamicTest.dynamicTest("check WarnLights", () -> assertTrue(this.flf.getWarnLightsState())),
                DynamicTest.dynamicTest("check BlueLights", () -> assertTrue(this.flf.getBlueLightState())),
                DynamicTest.dynamicTest("check RotaryButtonFrontCannon", () -> assertTrue(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() == 1)),
                DynamicTest.dynamicTest("check RotaryButtonRoofCannon", () -> assertTrue(this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() == RoofCannonMode.A)),
                DynamicTest.dynamicTest("check LeftDoor", () -> assertFalse(this.flf.getCabin().getBusDoorLeft().getOpen())),
                DynamicTest.dynamicTest("check RightDoor", () -> assertFalse(this.flf.getCabin().getBusDoorRight().getOpen())),
                DynamicTest.dynamicTest("check FrontCannon", () -> assertFalse(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT))),
                DynamicTest.dynamicTest("check RoofCannon", () -> assertFalse(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF))),
                DynamicTest.dynamicTest("check WaterTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER))),
                DynamicTest.dynamicTest("check FoamTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM))),
                DynamicTest.dynamicTest("check Batteries", () -> assertEquals(1, battFull))
        );

        Integer setpointConsumption = 0;
        for (int i = 0; i < 20; i++) {
            this.driver.accelerate();
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        for (int i = 0; i < 10; i++) {
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        Integer finalSetpointConsumption = setpointConsumption;
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check consumption", () -> assertEquals(finalSetpointConsumption, this.flf.getDrive().getCapacity() - this.flf.getDrive().getAbsoluteFillState()))
        );

        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> tankerBurns() {
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if (!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if (!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if (!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if (!this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if (!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if (!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for (Seat s : this.flf.getCabin().getSeatList()) {
            if (s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            } else {
                tests.add(DynamicTest.dynamicTest("check SeatBack", () -> assertFalse(s.getOccupied())));
            }
        }

        Double battFull = this.flf.getDrive().getRelativeFillState();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check LeftDoor", () -> assertFalse(this.flf.getCabin().getBusDoorLeft().getOpen())),
                DynamicTest.dynamicTest("check RightDoor", () -> assertFalse(this.flf.getCabin().getBusDoorRight().getOpen())),
                DynamicTest.dynamicTest("check Engines", () -> assertTrue(this.flf.getDrive().getEngineState())),
                DynamicTest.dynamicTest("check RoofLights", () -> assertTrue(this.flf.getSearchLightRoofState())),
                DynamicTest.dynamicTest("check FrontLights", () -> assertTrue(this.flf.getSearchLightFrontState())),
                DynamicTest.dynamicTest("check SideLights", () -> assertTrue(this.flf.getSearchLightSideState())),
                DynamicTest.dynamicTest("check WarnLights", () -> assertTrue(this.flf.getWarnLightsState())),
                DynamicTest.dynamicTest("check BlueLights", () -> assertTrue(this.flf.getBlueLightState())),
                DynamicTest.dynamicTest("check WaterTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER))),
                DynamicTest.dynamicTest("check FoamTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM))),
                DynamicTest.dynamicTest("check Batteries", () -> assertEquals(1, battFull))
        );

        Integer calculatedWaterConsumption = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer calculatedFoamConsumption = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        this.operator.toggleSelfProtection();

        Integer cannonConsumption1 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        calculatedWaterConsumption -= 7 * this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_SELFPROTECTION);
        Integer calcConsumption1 = calculatedWaterConsumption;


        this.driver.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() < 7) {
            this.operator.rightRotaryButtonFrontCannon();
        }

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }


        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.FIVE) {
            this.driver.switchMix();
        }

        for (int i = 0; i < 3; i++) {
            this.driver.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_FRONT);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }
        Integer cannonConsumption2 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption3 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);
        Integer calcConsumption2 = calculatedWaterConsumption;
        Integer calcConsumption3 = calculatedFoamConsumption;


        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.THREE) {
            this.operator.switchMix();
        }

        for (int i = 0; i < 3; i++) {
            this.operator.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_ROOF);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }
        Integer cannonConsumption4 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption5 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);
        Integer calcConsumption4 = calculatedWaterConsumption;
        Integer calcConsumption5 = calculatedFoamConsumption;

        this.driver.toggleCannon();
        this.operator.toggleCannon();


        Collections.addAll(tests,
                DynamicTest.dynamicTest("check WaterConsumptionSelfProtection", () -> assertEquals(calcConsumption1, cannonConsumption1)),
                DynamicTest.dynamicTest("check WaterConsumptionFrontCannon", () -> assertEquals(calcConsumption2, cannonConsumption2)),
                DynamicTest.dynamicTest("check FoamConsumptionFrontCannon", () -> assertEquals(calcConsumption3, cannonConsumption3)),
                DynamicTest.dynamicTest("check WaterConsumptionRoofCannon", () -> assertEquals(calcConsumption4, cannonConsumption4)),
                DynamicTest.dynamicTest("check FoamConsumptionRoofCannon", () -> assertEquals(calcConsumption5, cannonConsumption5))

        );

        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> tugInFlames() {
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if (!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if (!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if (!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if (!this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if (!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if (!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for (Seat s : this.flf.getCabin().getSeatList()) {
            if (s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            } else {
                tests.add(DynamicTest.dynamicTest("check SeatBack", () -> assertFalse(s.getOccupied())));
            }
        }

        Double battFull = this.flf.getDrive().getRelativeFillState();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check LeftDoor", () -> assertFalse(this.flf.getCabin().getBusDoorLeft().getOpen())),
                DynamicTest.dynamicTest("check RightDoor", () -> assertFalse(this.flf.getCabin().getBusDoorRight().getOpen())),
                DynamicTest.dynamicTest("check Engines", () -> assertTrue(this.flf.getDrive().getEngineState())),
                DynamicTest.dynamicTest("check RoofLights", () -> assertTrue(this.flf.getSearchLightRoofState())),
                DynamicTest.dynamicTest("check FrontLights", () -> assertTrue(this.flf.getSearchLightFrontState())),
                DynamicTest.dynamicTest("check SideLights", () -> assertTrue(this.flf.getSearchLightSideState())),
                DynamicTest.dynamicTest("check WarnLights", () -> assertTrue(this.flf.getWarnLightsState())),
                DynamicTest.dynamicTest("check BlueLights", () -> assertTrue(this.flf.getBlueLightState())),
                DynamicTest.dynamicTest("check WaterTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER))),
                DynamicTest.dynamicTest("check FoamTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM))),
                DynamicTest.dynamicTest("check Batteries", () -> assertEquals(1, battFull))
        );

        Integer calculatedWaterConsumption = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer calculatedFoamConsumption = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        this.driver.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() < 6) {
            this.operator.rightRotaryButtonFrontCannon();
        }

        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.TEN) {
            this.driver.switchMix();
        }
        for (int i = 0; i < 3; i++) {
            this.driver.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_FRONT);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption1 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption2 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption1 = calculatedWaterConsumption;
        Integer calcConsumption2 = calculatedFoamConsumption;

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }

        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.FIVE) {
            this.operator.switchMix();
        }

        for (int i = 0; i < 5; i++) {
            this.operator.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_ROOF);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption3 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption4 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption3 = calculatedWaterConsumption;
        Integer calcConsumption4 = calculatedFoamConsumption;


        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 2) {
            this.operator.leftRotaryButtonFrontCannon();
        }
        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.THREE) {
            this.driver.switchMix();
        }
        for (int i = 0; i < 3; i++) {
            this.driver.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_FRONT);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption5 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption6 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption5 = calculatedWaterConsumption;
        Integer calcConsumption6 = calculatedFoamConsumption;

        Collections.addAll(tests,
                DynamicTest.dynamicTest("check WaterConsumptionFrontCannon", () -> assertEquals(calcConsumption1, cannonConsumption1)),
                DynamicTest.dynamicTest("check FoamConsumptionFrontCannon", () -> assertEquals(calcConsumption2, cannonConsumption2)),
                DynamicTest.dynamicTest("check WaterConsumptionRoofCannon", () -> assertEquals(calcConsumption3, cannonConsumption3)),
                DynamicTest.dynamicTest("check FoamConsumptionRoofCannon", () -> assertEquals(calcConsumption4, cannonConsumption4)),
                DynamicTest.dynamicTest("check WaterConsumptionFrontCannon", () -> assertEquals(calcConsumption5, cannonConsumption5)),
                DynamicTest.dynamicTest("check FoamConsumptionFrontCannon", () -> assertEquals(calcConsumption6, cannonConsumption6))
        );

        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> fireInPowerPlant() {
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if (!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if (!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if (!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if (!this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if (!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if (!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for (Seat s : this.flf.getCabin().getSeatList()) {
            if (s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            } else {
                tests.add(DynamicTest.dynamicTest("check SeatBack", () -> assertFalse(s.getOccupied())));
            }
        }

        Double battFull = this.flf.getDrive().getRelativeFillState();
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check LeftDoor", () -> assertFalse(this.flf.getCabin().getBusDoorLeft().getOpen())),
                DynamicTest.dynamicTest("check RightDoor", () -> assertFalse(this.flf.getCabin().getBusDoorRight().getOpen())),
                DynamicTest.dynamicTest("check Engines", () -> assertTrue(this.flf.getDrive().getEngineState())),
                DynamicTest.dynamicTest("check RoofLights", () -> assertTrue(this.flf.getSearchLightRoofState())),
                DynamicTest.dynamicTest("check FrontLights", () -> assertTrue(this.flf.getSearchLightFrontState())),
                DynamicTest.dynamicTest("check SideLights", () -> assertTrue(this.flf.getSearchLightSideState())),
                DynamicTest.dynamicTest("check WarnLights", () -> assertTrue(this.flf.getWarnLightsState())),
                DynamicTest.dynamicTest("check BlueLights", () -> assertTrue(this.flf.getBlueLightState())),
                DynamicTest.dynamicTest("check WaterTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.WATER))),
                DynamicTest.dynamicTest("check FoamTank", () -> assertEquals(1, this.flf.getMixingProcessor().getTankFillState(TankSubject.FOAM))),
                DynamicTest.dynamicTest("check Batteries", () -> assertEquals(1, battFull))
        );

        Integer calculatedWaterConsumption = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer calculatedFoamConsumption = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        this.driver.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() < 6) {
            this.operator.rightRotaryButtonFrontCannon();
        }

        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.TEN) {
            this.driver.switchMix();
        }
        for (int i = 0; i < 5; i++) {
            this.driver.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_FRONT);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption1 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption2 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption1 = calculatedWaterConsumption;
        Integer calcConsumption2 = calculatedFoamConsumption;

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }

        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.TEN) {
            this.operator.switchMix();
        }

        for (int i = 0; i < 5; i++) {
            this.operator.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_ROOF);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption3 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption4 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption3 = calculatedWaterConsumption;
        Integer calcConsumption4 = calculatedFoamConsumption;

        for (int i = 0; i < 5; i++) {
            this.operator.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_ROOF);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption5 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption6 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption5 = calculatedWaterConsumption;
        Integer calcConsumption6 = calculatedFoamConsumption;

        while (this.flf.getMixingProcessor().getMixingRate() != MixingRate.THREE) {
            this.driver.switchMix();
        }
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1) {
            this.operator.leftRotaryButtonFrontCannon();
        }
        for (int i = 0; i < 5; i++) {
            this.driver.spray();
            Integer sprayCap = this.flf.getMixingProcessor().getSprayCapacity(CannonIdentifier.CANNON_FRONT);
            Integer foamNeeded = (sprayCap / 100) * this.flf.getMixingProcessor().getMixingRateValue();
            calculatedWaterConsumption -= (sprayCap - foamNeeded);
            calculatedFoamConsumption -= foamNeeded;
        }

        Integer cannonConsumption7 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.WATER);
        Integer cannonConsumption8 = this.flf.getMixingProcessor().getAbsoluteFillState(TankSubject.FOAM);

        Integer calcConsumption7 = calculatedWaterConsumption;
        Integer calcConsumption8 = calculatedFoamConsumption;

        Collections.addAll(tests,
                DynamicTest.dynamicTest("check WaterConsumptionFrontCannon", () -> assertEquals(calcConsumption1, cannonConsumption1)),
                DynamicTest.dynamicTest("check FoamConsumptionFrontCannon", () -> assertEquals(calcConsumption2, cannonConsumption2)),
                DynamicTest.dynamicTest("check WaterConsumptionRoofCannon", () -> assertEquals(calcConsumption3, cannonConsumption3)),
                DynamicTest.dynamicTest("check FoamConsumptionRoofCannon", () -> assertEquals(calcConsumption4, cannonConsumption4)),
                DynamicTest.dynamicTest("check WaterConsumptionRoofCannon", () -> assertEquals(calcConsumption5, cannonConsumption5)),
                DynamicTest.dynamicTest("check FoamConsumptionRoofCannon", () -> assertEquals(calcConsumption6, cannonConsumption6)),
                DynamicTest.dynamicTest("check WaterConsumptionFrontCannon", () -> assertEquals(calcConsumption7, cannonConsumption7)),
                DynamicTest.dynamicTest("check FoamConsumptionFrontCannon", () -> assertEquals(calcConsumption8, cannonConsumption8))
        );

        return  tests.stream();
    }
}
