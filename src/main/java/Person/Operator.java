package Person;

import Cabin.ControlPanel;
import Joystick.Joystick;

public class Operator extends ActivePassenger {
    private ControlPanel panel;
    private Joystick opJoystick;

    public Operator() {
    }

    public void equip(ControlPanel panel, Joystick opJoystick){
        this.panel =panel;
        this.equip(opJoystick);
    }

    public void toggleEngines(){
        this.panel.getBtnSwitchEngines().operateDevice();
    }

    public void toggleWarnlights(){
        this.panel.getBtnSwitchWarnlight().operateDevice();
    }

    public void toggleBlueLights(){
        this.panel.getBtnSwitchBluelight().operateDevice();
    }

    public void toggleFrontLights(){
        this.panel.getBtnSwitchFrontlight().operateDevice();
    }

    public void toggleRoofLights(){
        this.panel.getBtnSwitchRooflight().operateDevice();
    }

    public void toggleSideLights(){
        this.panel.getBtnSwitchSidelight().operateDevice();
    }

    public void toggleSelfProtection(){
        this.panel.getBtnSwitchSelfProtection().operateDevice();
    }
}