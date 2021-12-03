package Cabin;

import Button.*;

import java.util.ArrayList;
import java.util.List;

public class ControlPanel {
    private final ButtonSwitch btnSwitchEngines;
    private final ButtonSwitch btnSwitchWarnlight;
    private final ButtonSwitch btnSwitchBluelight;
    private final ButtonSwitch btnSwitchFrontlight;
    private final ButtonSwitch btnSwitchRooflight;
    private final ButtonSwitch btnSwitchSidelight;
    private final ButtonSwitch btnSwitchSelfProtection;

    public ControlPanel(Builder builder) {
        ControlPanel built = builder.build();
        this.btnSwitchEngines = built.btnSwitchEngines;
        this.btnSwitchWarnlight = built.btnSwitchWarnlight;
        this.btnSwitchBluelight = built.btnSwitchBluelight;
        this.btnSwitchFrontlight = built.btnSwitchFrontlight;
        this.btnSwitchRooflight = built.btnSwitchRooflight;
        this.btnSwitchSidelight = built.btnSwitchSidelight;
        this.btnSwitchSelfProtection = built.btnSwitchSelfProtection;

    }

    public static class Builder {
        private final ButtonSwitch btnSwitchEngines;
        private final ButtonSwitch btnSwitchWarnlight;
        private final ButtonSwitch btnSwitchBluelight;
        private final ButtonSwitch btnSwitchFrontlight;
        private final ButtonSwitch btnSwitchRooflight;
        private final ButtonSwitch btnSwitchSidelight;
        private final ButtonSwitch btnSwitchSelfProtection;

        public Builder(
                ButtonSwitch btnSwitchEngines,
                ButtonSwitch btnSwitchWarnlight,
                ButtonSwitch btnSwitchBluelight,
                ButtonSwitch btnSwitchFrontlight,
                ButtonSwitch btnSwitchRooflight,
                ButtonSwitch btnSwitchSidelight,
                ButtonSwitch btnSwitchSelfProtection
        ) {
            this.btnSwitchEngines = btnSwitchEngines;
            this.btnSwitchWarnlight = btnSwitchWarnlight;
            this.btnSwitchBluelight = btnSwitchBluelight;
            this.btnSwitchFrontlight = btnSwitchFrontlight;
            this.btnSwitchRooflight = btnSwitchRooflight;
            this.btnSwitchSidelight = btnSwitchSidelight;
            this.btnSwitchSelfProtection = btnSwitchSelfProtection;
        }

        public ControlPanel build() {
            return new ControlPanel(this);
        }
    }

}
