package Cabin;

import Button.Button;
import Button.ButtonPush;
import Button.IButtonListener;

public class Busdoor {
    private final VehicleSide side;

    public ButtonPush btnPushOutside = new ButtonPush(this){
        @Override
        public void operateDevice() {
            ((Busdoor) this.operatingDevice).operateDoor();
        }
    };
    public ButtonPush btnPushInside = new ButtonPush(this){
        @Override
        public void operateDevice() {
            ((Busdoor) this.operatingDevice).operateDoor();
        }
    };
    private Boolean isOpen = false;

    public Busdoor(VehicleSide side) {
        this.side = side;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void operateDoor(){
        this.isOpen = !this.isOpen;
    }

}
