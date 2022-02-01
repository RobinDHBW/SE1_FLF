package Person;

import Button.Pedal;
import Cabin.ControlPanel;
import Instruments.SteeringWheel;
import Joystick.Joystick;

public class Driver extends Person {
    private SteeringWheel steeringWheel;
    private Pedal gasPedal;
    private Pedal brakePedal;
    private Joystick drJoystick;

    public Driver() {

    }

    public void equip(SteeringWheel wheel, Pedal gas, Pedal brake, Joystick stick){
        this.steeringWheel = wheel;
        this.gasPedal = gas;
        this.brakePedal = brake;
        this.drJoystick = stick;
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

    public void toggleFrontCannon(){
        this.drJoystick.pressBtnLeft();
    }

    public void switchMix(){
        this.drJoystick.pressBtnRight();
    }

    public void spray(){
        this.drJoystick.pushBtn();
    }


}