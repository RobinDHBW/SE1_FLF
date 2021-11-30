package Cabin;

import Button.ButtonPush;

public class Busdoor {
    private final VehicleSide side;
    private Boolean isOpen = false;

    /**
     * @TODO Person should interact with button to open door -> Maybe eventListener
     */
    public ButtonPush btnPushOutside = new ButtonPush();
    public ButtonPush btnPushInside = new ButtonPush();

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
