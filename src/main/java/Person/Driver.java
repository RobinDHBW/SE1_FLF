package Person;

import Button.ButtonPush;
import Button.ButtonRotary;
import Button.IDCardReader;
import Button.Pedal;
import IDCard.IDCard;
import Instruments.SteeringWheel;
import Joystick.Joystick;

public class Driver extends ActivePassenger {
    private SteeringWheel steeringWheel;
    private Pedal gasPedal;
    private Pedal brakePedal;

    public Driver(String name, IDCard card) {
        super(name, card);
    }

    public void equip(SteeringWheel wheel, Pedal gas, Pedal brake, Joystick stick, ButtonPush doorToggleInside){
        this.steeringWheel = wheel;
        this.gasPedal = gas;
        this.brakePedal = brake;
        super.equip(stick, doorToggleInside);
    }

    public void uneqip(){
        this.steeringWheel = null;
        this.gasPedal = null;
        this.brakePedal = null;
        super.uneqip();
    }


    public void accelerate(){
        this.gasPedal.operateDevice();
    }

    public void brake(){
        this.brakePedal.operateDevice();
    }

    public void steer(Boolean isLeft, Integer degree){
        this.steeringWheel.steer(isLeft, degree);
    }
}