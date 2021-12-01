package Cabin;

import Button.Button;
import Button.ButtonPush;
import Button.IButtonListener;

public class Busdoor implements IButtonListener {
    private final VehicleSide side;

    public ButtonPush btnPushOutside = new ButtonPush();
    public ButtonPush btnPushInside = new ButtonPush();
    private Boolean isOpen = false;

    public Busdoor(VehicleSide side) {
        this.side = side;
        btnPushInside.addListener(this);
        btnPushOutside.addListener(this);
    }


    @Override
    public void onToggleButton(Button o) {
        this.isOpen = !this.isOpen;
    }

    public Boolean getOpen() {
        return isOpen;
    }
}
