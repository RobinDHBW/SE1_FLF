package Cabin;

import Button.ButtonPush;

import java.util.Arrays;

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
    private Boolean isLocked = false;

    public Busdoor(VehicleSide side) {
    }

    private void operateDoor() {
        this.isOpen = !this.isOpen;
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

    public Boolean toggleDoorLock() {
        try {
            if (isOpen) throw new Exception("Door has to be closed before locking");
            this.isLocked = !this.isLocked;
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return false;
        }
    }

}
