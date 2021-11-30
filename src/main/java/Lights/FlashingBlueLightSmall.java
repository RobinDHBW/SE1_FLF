package Lights;

public class FlashingBlueLightSmall extends FlashingBlueLight {
    public Led led;

    public FlashingBlueLightSmall() {
        led = new Led("blue");
    }

    public Led getLed() {return led;}
}
