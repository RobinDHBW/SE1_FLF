package Cabin;

import Button.ButtonPush;
import Button.IDCardReader;
import IDCard.IDCard;

import java.util.Arrays;

public class Busdoor {
    private Boolean isOpen = false;
    private VehicleSide vehicleSide;
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

    private final IDCardReader idCardReader;

    private Boolean isLocked = false;

    public Busdoor(VehicleSide side, IDCardReader reader) {
        this.vehicleSide = side;
        this.idCardReader = reader;
    }

    private void operateDoor() {
        this.isOpen = !this.isOpen;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public ButtonPush getBtnPushInside() {
        return btnPushInside;
    }

    public IDCardReader getIdCardReader() {
        return idCardReader;
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
