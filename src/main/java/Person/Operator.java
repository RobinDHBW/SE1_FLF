package Person;

import Button.ButtonRotaryWaterCannonFront;
import Button.ButtonRotaryWaterCannonRoof;
import Cabin.ControlPanel;
import Joystick.Joystick;

public class Operator extends ActivePassenger {
    private ControlPanel panel;
    private Joystick opJoystick;
    private ButtonRotaryWaterCannonRoof rotaryWaterCannonRoof;
    private ButtonRotaryWaterCannonFront rotaryWaterCannonFront;

    public Operator() {
    }

    public void equip(ControlPanel panel, Joystick opJoystick, ButtonRotaryWaterCannonFront rotaryWaterCannonFront, ButtonRotaryWaterCannonRoof rotaryWaterCannonRoof) {
        this.panel = panel;
        this.equip(opJoystick);
    }

    public void toggleEngines() {
        this.panel.getBtnSwitchEngines().operateDevice();
    }

    public void toggleWarnlights() {
        this.panel.getBtnSwitchWarnlight().operateDevice();
    }

    public void toggleBlueLights() {
        this.panel.getBtnSwitchBluelight().operateDevice();
    }

    public void toggleFrontLights() {
        this.panel.getBtnSwitchFrontlight().operateDevice();
    }

    public void toggleRoofLights() {
        this.panel.getBtnSwitchRooflight().operateDevice();
    }

    public void toggleSideLights() {
        this.panel.getBtnSwitchSidelight().operateDevice();
    }

    public void toggleSelfProtection() {
        this.panel.getBtnSwitchSelfProtection().operateDevice();
    }

    public void leftRotaryButtonFrontCannon() {
        this.rotaryWaterCannonFront.rotateLeft();
    }

    public void rightRotaryButtonFrontCannon() {
        this.rotaryWaterCannonFront.rotateRight();
    }

    public void leftRotaryButtonRoofCannon() {
        this.rotaryWaterCannonRoof.rotateLeft();
    }

    public void rightRotaryButtonRoofCannon() {
        this.rotaryWaterCannonRoof.rotateRight();
    }
}