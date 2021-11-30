package Lights;

import java.util.ArrayList;

public class FlashingBlueLightBig extends FlashingBlueLight {
    public ArrayList<Led> led = new ArrayList<>();

    public FlashingBlueLightBig() {
        for(int i=0; i<4; i++) {
            led.add(new Led("blue"));
        }
    }

    public ArrayList<Led> getLedArrayList() { return led; }
}
