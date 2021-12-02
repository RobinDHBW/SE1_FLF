package ControlPanel;

import Button.*;
import FLF.FLF;
import Lights.WarningLight;
import Seating.Seat;

import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel implements IButtonListener {
    private final List<ButtonBoolean> buttonList;

    public ControlPanel(Builder builder) {
        ControlPanel built = builder.build();
        this.buttonList = built.buttonList;

    }

    @Override
    public void onToggle(Button o) {
        ButtonSwitch btnSwitch = (ButtonSwitch) o;

        switch (btnSwitch.getDevice()) {
            case ENGINES -> FLF.CentralUnit.toggleEngines();
            case WARNINGLIGHTS -> FLF.CentralUnit.switchLight(SwitchDevice.WARNINGLIGHTS);
        }
    }

    public static class Builder {
        private final List<ButtonBoolean> buttonList = new ArrayList<>();

        public Builder() {
            this.buttonList.add(new ButtonSwitch(SwitchDevice.ENGINES));
            this.buttonList.add(new ButtonSwitch(SwitchDevice.WARNINGLIGHTS));
            this.buttonList.add(new ButtonSwitch(SwitchDevice.FLASHINGBLUELIGHTS));
            this.buttonList.add(new ButtonSwitch(SwitchDevice.FRONTLIGHTS));
            this.buttonList.add(new ButtonSwitch(SwitchDevice.ROOFLIGHTS));
            this.buttonList.add(new ButtonSwitch(SwitchDevice.SIDELIGHTS));
        }

        public ControlPanel build() {
            return new ControlPanel(this);
        }
    }

}
