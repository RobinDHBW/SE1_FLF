package Person;

import Joystick.Joystick;

public abstract class ActivePassenger extends Person{

    Joystick joystick;

    public void equip(Joystick stick){
        this.joystick = stick;
    }

    public void toggleCannon(){
        this.joystick.pressBtnLeft();
    }

    public void switchMix(){
        this.joystick.pressBtnRight();
    }

    public void spray(){
        this.joystick.pushBtn();
    }
}
