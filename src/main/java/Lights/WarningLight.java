package Lights;

public class WarningLight extends Light {
    public Led led;

    public WarningLight() {
        led = new Led("orange");
    }

    public Led getLed() {return led;}
}
