package Cabin;
import java.util.Arrays;

public class Busdoor {
    private Boolean isOpen = false;
    private VehicleSide vehicleSide;
    private Boolean isLocked = true;

    public Busdoor(VehicleSide side) {
        this.vehicleSide = side;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void toggleDoor() {
        this.isOpen = !this.isOpen;
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
