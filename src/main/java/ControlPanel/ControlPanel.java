package ControlPanel;

import Button.ButtonBoolean;
import Button.ButtonSwitch;
import Button.SwitchDevice;
import Seating.Seat;

import java.util.ArrayList;
import java.util.List;

public class ControlPanel {
    private final List<ButtonBoolean> buttonList;

    public ControlPanel(Builder builder) {
        ControlPanel built = builder.build();
        this.buttonList = built.buttonList;

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
