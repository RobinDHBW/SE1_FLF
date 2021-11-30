package Cabin;

public class Busdoor {
    private final VehicleSide side;
    private Boolean isOpen = false;
    

    public Busdoor(VehicleSide side) {
        this.side = side;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public Boolean getOpen() {
        return isOpen;
    }
}
