package Lights;

import java.util.List;

public abstract class FlashingBlueLight extends LEDLight {
    public FlashingBlueLight(LightPosition position, Integer ledCount){
        super(position, ledCount, LEDColor.BLUE);
    }
}
