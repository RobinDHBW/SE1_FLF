package Lights;


public abstract class FlashingBlueLight extends LEDLight {
    public FlashingBlueLight(LightPosition position, Integer ledCount){
        super(position, ledCount, LEDColor.BLUE);
    }
}
