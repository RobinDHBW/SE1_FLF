package Cabin;

import Button.ButtonPush;

public class Busdoor {
    private Boolean isOpen = false;
    private final ButtonPush btnPushOutside = new ButtonPush(this) {
        @Override
        public void operateDevice() {
            ((Busdoor) this.operatingDevice).operateDoor();
        }
    };
    private final ButtonPush btnPushInside = new ButtonPush(this) {
        @Override
        public void operateDevice() {
            ((Busdoor) this.operatingDevice).operateDoor();
        }
    };

    private void operateDoor() {
        this.isOpen = !this.isOpen;
    }

    public Busdoor(VehicleSide side) {
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
