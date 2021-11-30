package Lights;

import java.util.ArrayList;

public class FlashingBlueLightMedium extends FlashingBlueLight {
    public ArrayList<Led> led = new ArrayList<>();

    public FlashingBlueLightMedium() {
        for(int i=0; i<2; i++) {
            led.add(new Led("blue"));
        }
    }

    public ArrayList<Led> getLedArrayList() { return led; }
}
