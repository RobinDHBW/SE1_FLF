package Lights;

import java.util.ArrayList;
import java.util.List;

public abstract class LEDLight extends Light{
    private List<LED> ledComposition = new ArrayList<>();
    public LEDLight(LightPosition position, Integer ledCount, LEDColor color){
        super(position);
        for(int i=0; i<ledCount; i++){
            ledComposition.add(new LED(color));
        }
    }
}
