package TestSzenarios;

import Button.ButtonSwitch;
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
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestSzenarios {
    private FLF flf;

    private Driver driver;
    private Operator operator;
    private ArrayList<Infantry> infanterists;
    private EmployeeFirebase employee;

    public TestSzenarios(){
    }

    @BeforeEach
    void initRoutine(){
        this.flf = new FLF.Builder().build();
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);
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

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }
    }

    @TestFactory
    Stream<DynamicTest> park(){
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if(this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if(this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(false);

        for(int i =0; i<2; i++){
            this.flf.leaveFLF(i, true);
            this.flf.leaveFLF(i, false);
        }

        for(Seat s : this.flf.getCabin().getSeatList()){
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
    Stream<DynamicTest> controlRide(){
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for(Seat s : this.flf.getCabin().getSeatList()){
            if(s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            }else {
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

        for(int i =0; i<7; i++){
            this.driver.accelerate();
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        for(int i=0; i<5;i++){
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        this.driver.steer(true, 5);
        Integer steerState1 = this.flf.getDrive().getSteeringAngle();
        for(int i=0; i<3; i++){
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        this.driver.steer(false, 5);
        Integer steerState2 = this.flf.getDrive().getSteeringAngle();
        for(int i=0; i<5; i++){
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        this.driver.steer(false, 5);
        Integer steerState3 = this.flf.getDrive().getSteeringAngle();
        for(int i=0; i<5; i++){
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        for(int i=0; i<7;i++){
            this.driver.brake();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
            if(this.flf.getDrive().getSpeed() > 0) this.flf.drive();
        }


        Integer finalSetpointConsumption = setpointConsumption;
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check steerLeft", ()->assertEquals(-5, steerState1)),
                DynamicTest.dynamicTest("check steerStraight", ()->assertEquals(0, steerState2)),
                DynamicTest.dynamicTest("check steerRight", ()->assertEquals(5, steerState3)),
                DynamicTest.dynamicTest("check consumption", ()->assertEquals(finalSetpointConsumption, this.flf.getDrive().getCapacity()-this.flf.getDrive().getAbsoluteFillState()))
        );
        return tests.stream();
    }

    @TestFactory
    Stream<DynamicTest> emergencyRide(){
        ArrayList<DynamicTest> tests = new ArrayList<>();

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        for(Seat s : this.flf.getCabin().getSeatList()){
            if(s.getSeatRow() == 0) {
                tests.add(DynamicTest.dynamicTest("check SeatFront", () -> assertTrue(s.getOccupied())));
            }else {
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
        for(int i =0; i<20; i++){
            this.driver.accelerate();
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        for(int i =0; i<10;i++){
            this.flf.drive();
            setpointConsumption += this.flf.getDrive().getSpeed() * 25;
        }

        Integer finalSetpointConsumption = setpointConsumption;
        Collections.addAll(tests,
                DynamicTest.dynamicTest("check consumption", ()->assertEquals(finalSetpointConsumption, this.flf.getDrive().getCapacity()-this.flf.getDrive().getAbsoluteFillState()))
        );

        return tests.stream();
    }

    public void tankerBurns(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);

        this.driver = new Driver();
        this.operator = new Operator();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(true);

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(!this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }

        this.operator.toggleSelfProtection();
        this.flf.spray(CannonIdentifier.CANNON_SELFPROTECTION);
        this.operator.toggleSelfProtection();

        this.driver.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() <6){
            this.operator.rightRotaryButtonFrontCannon();
        }

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C){
            this.operator.rightRotaryButtonRoofCannon();
        }


        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.FIVE){
            this.driver.switchMix();
        }

        for(int i =0; i<3;i++){
            this.driver.spray();
        }

        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.THREE){
            this.operator.switchMix();
        }

        for(int i =0; i<3;i++){
            this.operator.spray();
        }

        this.driver.toggleCannon();
        this.operator.toggleCannon();

    }

    public void tugInFlames(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);

        this.driver = new Driver();
        this.operator = new Operator();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(true);

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(!this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }

        this.driver.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() < 7) {
            this.operator.rightRotaryButtonFrontCannon();
        }

        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.TEN){
            this.driver.switchMix();
        }
        for(int i=0; i<3;i++) {
            this.driver.spray();
        }

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }

        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.FIVE){
            this.operator.switchMix();
        }

        for(int i=0; i<5;i++) {
            this.operator.spray();
        }


        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 2) {
            this.operator.leftRotaryButtonFrontCannon();
        }
        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.THREE){
            this.driver.switchMix();
        }
        for(int i=0; i<3;i++) {
            this.driver.spray();
        }
    }

    public void fireInPowerPlant(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);

        this.driver = new Driver();
        this.operator = new Operator();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(true);

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(!this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }

        this.driver.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() < 7) {
            this.operator.rightRotaryButtonFrontCannon();
        }

        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.TEN){
            this.driver.switchMix();
        }
        for(int i=0; i<5;i++) {
            this.driver.spray();
        }

        this.operator.toggleCannon();
        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C) {
            this.operator.rightRotaryButtonRoofCannon();
        }

        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.TEN){
            this.operator.switchMix();
        }

        for(int i=0; i<5; i++){
            this.operator.spray();
        }
        for(int i=0; i<5; i++){
            this.operator.spray();
        }

        while(this.flf.getMixingProcessor().getMixingRate() != MixingRate.THREE){
            this.driver.switchMix();
        }
        while (this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1) {
            this.operator.leftRotaryButtonFrontCannon();
        }
        for(int i=0; i<5;i++) {
            this.driver.spray();
        }
    }
}
