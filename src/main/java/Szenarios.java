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
    private EmployeeFirebase eFB;

    public Szenarios(FLF flf){
        this.flf = flf;
    }

    public void park(){
        this.driver = new Driver();
        this.operator = new Operator();
        this.eFB = new EmployeeFirebase();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);
        this.flf.toggleMaintenance(eFB);

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();

        if(this.flf.getDrive().getEngineState()) this.operator.toggleEngines();
        if(this.flf.getSearchLightFrontState()) this.operator.toggleFrontLights();
        if(this.flf.getSearchLightRoofState()) this.operator.toggleRoofLights();
        if(this.flf.getSearchLightSideState()) this.operator.toggleSideLights();
        if(this.flf.getWarnLightsState()) this.operator.toggleWarnlights();
        if(this.flf.getBlueLightState()) this.operator.toggleBlueLights();

        while(this.flf.getCabin().getBtnRotaryWaterCannonFront().getMode() > 1 && this.flf.getCabin().getBtnRotaryWaterCannonRoof().getMode() != RoofCannonMode.A){
            this.flf.getCabin().getBtnRotaryWaterCannonFront().rotateLeft();
            this.flf.getCabin().getBtnRotaryWaterCannonRoof().rotateLeft();
        }

        eFB.loadBatteries();
        eFB.fillWaterTank();
        eFB.fillFoamTank();

        this.flf.toggleMaintenance(eFB);
        for(int i =0; i<2; i++){
            this.flf.leaveFLF(i, true);
            this.flf.leaveFLF(i, false);
        }
    }
}
