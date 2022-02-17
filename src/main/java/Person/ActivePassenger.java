package Person;

import Button.ButtonPush;
import Button.IDCardReader;
import IDCard.IDCard;
import Joystick.Joystick;
import Joystick.JoystickType;

public abstract class ActivePassenger extends Person{

    private Joystick joystick;
    private ButtonPush doorToggleInside            ;
    private ButtonPush doorToggleOutside;
    private IDCardReader idCardReader;

    public ActivePassenger(String name, IDCard card){
        super(name, card);
    }

    public void equip(Joystick stick, ButtonPush doorToggleInside,ButtonPush doorToggleOutside, IDCardReader idCardReader){
        this.joystick = stick;
        this.doorToggleInside = doorToggleInside;
        this.doorToggleOutside = doorToggleOutside;
        this.idCardReader = idCardReader;
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
    public void toggleDoor(){
        (isInVehicle ? doorToggleInside : doorToggleOutside).operateDevice();
    }

    public void toggleDoorLock(){
        this.idCardReader.operateDevice();
    }

}
