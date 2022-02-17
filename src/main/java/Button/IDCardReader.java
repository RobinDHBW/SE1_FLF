package Button;

import CentralUnit.CentralUnit;
import IDCard.IDCard;

public class IDCardReader extends Button {
    public IDCardReader(CentralUnit centralUnit){
        super(centralUnit);
    }

    @Override
    public void operateDevice() {
        ((CentralUnit) this.operatingDevice).accelerate();
    }


}
