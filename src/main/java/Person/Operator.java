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

    public Operator(String name) {
        super(name);
    }

    public void equip(ControlPanel panel, Joystick opJoystick, ButtonRotaryWaterCannonFront rotaryWaterCannonFront, ButtonRotaryWaterCannonRoof rotaryWaterCannonRoof) {
        this.panel = panel;
        this.rotaryWaterCannonRoof = rotaryWaterCannonRoof;
        this.rotaryWaterCannonFront = rotaryWaterCannonFront;
        super.equip(opJoystick);
    }

    public void uneqip(){
        this.panel = null;
        this.rotaryWaterCannonRoof = null;
        this.rotaryWaterCannonFront = null;
        super.uneqip();
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
        this.rotaryWaterCannonFront.operateDevice();

    }

    public void rightRotaryButtonFrontCannon() {
        this.rotaryWaterCannonFront.rotateRight();
        this.rotaryWaterCannonFront.operateDevice();
    }

    public void leftRotaryButtonRoofCannon() {
        this.rotaryWaterCannonRoof.rotateLeft();
        this.rotaryWaterCannonRoof.operateDevice();
    }

    public void rightRotaryButtonRoofCannon() {
        this.rotaryWaterCannonRoof.rotateRight();
        this.rotaryWaterCannonRoof.operateDevice();
    }
}