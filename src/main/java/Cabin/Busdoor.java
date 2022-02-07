package Cabin;

import Button.ButtonPush;

public class Busdoor {
    private final VehicleSide side;
    private Boolean isOpen = false;
    private ButtonPush btnPushOutside = new ButtonPush(this) {
        @Override
        public void operateDevice() {
            ((Busdoor) this.operatingDevice).operateDoor();
        }
    };
    private ButtonPush btnPushInside = new ButtonPush(this) {
        @Override
        public void operateDevice() {
            ((Busdoor) this.operatingDevice).operateDoor();
        }
    };

    private void operateDoor() {
        this.isOpen = !this.isOpen;
    }

    public Busdoor(VehicleSide side) {
        this.side = side;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void toggleDoor(Boolean fromOutside) {
        if (fromOutside) {
            btnPushOutside.operateDevice();
        } else {
            btnPushInside.operateDevice();
        }
    }

}
