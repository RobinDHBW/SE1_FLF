import FLF.FLF;
import Firefighting.CannonIdentifier;
import Person.Driver;
import Person.Infantry;
import Person.Operator;

import java.util.ArrayList;

public class Szenarios {
    private FLF flf;

    private Driver driver;
    private Operator operator;
    private ArrayList<Infantry> infanterists;

    public Szenarios(FLF flf){
    this.flf = flf;
    }

    public void park(){
        this.driver = new Driver();
        this.operator = new Operator();

        this.flf.enterFLF(driver, true);
        this.flf.enterFLF(operator, false);

        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_FRONT)) this.driver.toggleCannon();
        if(this.flf.getMixingProcessor().getCannonState(CannonIdentifier.CANNON_ROOF)) this.operator.toggleCannon();
    }
}
