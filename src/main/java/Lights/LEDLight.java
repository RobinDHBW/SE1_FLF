package Lights;

import java.util.List;

/**
 * @TODO add this to uml
 */
public abstract class LEDLight extends Light{
    private List<LED> ledComposition;
    public LEDLight(LightPosition position, Integer ledCount, LEDColor color){
        super(position);
        for(int i=0; i<ledCount; i++){
            ledComposition.add(new LED(color));
        }
    }
}
