package Cabin;

import Button.*;

import java.util.ArrayList;
import java.util.List;

public class ControlPanel implements IButtonListener {
    private final List<ButtonSwitch> buttonList;

    public ControlPanel(Builder builder) {
        ControlPanel built = builder.build();
        this.buttonList = built.buttonList;

    }

    @Override
    public void onToggle(Button o) {
        o.operateDevice();
    }

    public static class Builder {
        private final List<ButtonSwitch> buttonList;

        public Builder(List<ButtonSwitch> switches) {
            this.buttonList = switches;
        }

        public ControlPanel build() {
            return new ControlPanel(this);
        }
    }

}
