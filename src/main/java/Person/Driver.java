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
}