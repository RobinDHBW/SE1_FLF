import Button.RoofCannonMode;
import FLF.FLF;
import Firefighting.CannonIdentifier;
import Person.Driver;
import Person.EmployeeFirebase;
import Person.Infantry;
import Person.Operator;

import java.util.ArrayList;

public class Szenarios {
    private FLF flf;

    private Driver driver;
    private Operator operator;
    private ArrayList<Infantry> infanterists;
    private EmployeeFirebase employee;

    public Szenarios(FLF flf){
        this.flf = flf;
    }

    private void doMaintenance(){
        this.employee = new EmployeeFirebase();
        this.flf.toggleMaintenance(employee);
        employee.loadBatteries();
        employee.fillWaterTank();
        employee.fillFoamTank();
        this.flf.toggleMaintenance(employee);
    }

    public void park(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);
        this.driver = new Driver();
        this.operator = new Operator();

        this.doMaintenance();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if(this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }

        for(int i =0; i<2; i++){
            this.flf.leaveFLF(i, true);
            this.flf.leaveFLF(i, false);
        }
    }

    public void controlRide(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);

        this.driver = new Driver();
        this.operator = new Operator();

        this.doMaintenance();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(true);

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }

        for(int i =0; i<7; i++){
            this.driver.accelerate();
            this.flf.drive();
        }

        for(int i=0; i<5;i++){
            this.flf.drive();
        }

        this.driver.steer(true, 5);
        for(int i=0; i<3; i++){
            this.flf.drive();
        }

        this.driver.steer(false, 5);
        for(int i=0; i<5; i++){
            this.flf.drive();
        }

        this.driver.steer(false, 5);
        for(int i=0; i<5; i++){
            this.flf.drive();
        }

        for(int i=0; i<7;i++){
            this.driver.accelerate();
            this.flf.drive();
        }
    }

    public void emergencyRide(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);

        this.driver = new Driver();
        this.operator = new Operator();

        this.doMaintenance();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        this.flf.toggleRightDoor(false);
        this.flf.toggleLeftDoor(true);

        if(!this.flf.getDrive().getEngineState()) this.operator.toggleEngines();

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(!this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(!this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(!this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(!this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.operator.leftRotaryButtonFrontCannon();
            this.operator.leftRotaryButtonRoofCannon();
        }

        for(int i =0; i<20; i++){
            this.driver.accelerate();
            this.flf.drive();
        }

        for(int i =0; i<10;i++){
            this.flf.drive();
        }

    }

    public void tankerBurns(){
        if(!this.flf.getCabin().getBusDoorRight().getOpen()) this.flf.toggleRightDoor(true);
        if(!this.flf.getCabin().getBusDoorLeft().getOpen()) this.flf.toggleLeftDoor(true);

        this.driver = new Driver();
        this.operator = new Operator();

        this.doMaintenance();

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

        while (this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.C){
            this.operator.rightRotaryButtonRoofCannon();
        }


        this.operator.rightRotaryButtonFrontCannon();
        this.driver.switchMix();
        for(int i =0; i<3;i++){
            this.driver.spray();
        }
        this.driver.toggleCannon();

        this.operator.toggleCannon();

    }
}
