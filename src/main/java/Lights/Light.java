package Lights;

public abstract class Light {
    protected LightPosition position;
    public Light(LightPosition position){
        this.position = position;
    }
}
