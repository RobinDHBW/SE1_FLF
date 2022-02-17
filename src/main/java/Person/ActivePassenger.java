package Person;

import Joystick.Joystick;
import Joystick.JoystickType;

public abstract class ActivePassenger extends Person{

    Joystick joystick;

    public ActivePassenger(String name){
        super(name);
    }

    public void equip(Joystick stick){
        this.joystick = stick;
    }

    public void uneqip(){
        this.joystick = null;
    }


    public void toggleCannon(){
        if(this.joystick.getJoystickType() == JoystickType.CLASSIC) {
            this.joystick.pressBtnLeft();
        } else {
            this.joystick.pressBtn();
        }

    }

    public void switchMix(){
        if(this.joystick.getJoystickType() == JoystickType.CLASSIC) {
            this.joystick.pressBtnRight();
        } else {
            this.joystick.pressBtn();
        }

    }

    public void spray(){
        this.joystick.pushBtn();
    }
}
