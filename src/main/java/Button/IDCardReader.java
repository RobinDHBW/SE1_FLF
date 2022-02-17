package Button;

import CentralUnit.CentralUnit;
import IDCard.IDCard;

public class IDCardReader extends Button {
    public IDCardReader(CentralUnit centralUnit){
        super(centralUnit);
    }

    public void operateDevice(IDCard card) {
        ((CentralUnit) this.operatingDevice).toggleDoorLock(card.getAuthCode());
    }


}
