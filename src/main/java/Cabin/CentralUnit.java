package Cabin;

import Button.SwitchDevice;
import Drive.Drive;
import Drive.ElectricEngine;

import Firefighting.WaterCannon;
import Firefighting.WaterDieSelfprotection;
import Lights.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CentralUnit {
    private List<WarningLight> warningLights;
    private List<FlashingBlueLight> flashingBlueLights;
    private List<SearchLight> searchLightsFront;
    private List<SearchLight> searchLightsRoof;
    private List<SearchLight> searchLightsSide;
    private List<DirectionIndicator> indicatorsLeft;
    private List<DirectionIndicator> indicatorsRight;
    private List<WaterDieSelfprotection> selfprotection;
    private Drive drive;

    public CentralUnit(
            List<WarningLight> warningLights,
            List<FlashingBlueLight> flashingBlueLights,
            List<SearchLight> searchLightsFront,
            List<SearchLight> searchLightsRoof,
            List<SearchLight> searchLightsSide,
            List<DirectionIndicator> indicatorsLeft,
            List<DirectionIndicator> indicatorsRight,
            List<WaterDieSelfprotection> selfprotection,
            Drive drive
    ) {
        this.warningLights = warningLights;
        this.flashingBlueLights = flashingBlueLights;
        this.searchLightsFront = searchLightsFront;
        this.searchLightsRoof = searchLightsRoof;
        this.searchLightsSide = searchLightsSide;
        this.indicatorsLeft = indicatorsLeft;
        this.indicatorsRight = indicatorsRight;
        this.selfprotection = selfprotection;
        this.drive = drive;
    }


    public void switchEngines() {
        this.drive.toggleEngine();
    }

    public void switchWarningLight() {
        for (Light l : this.warningLights) {
            l.toggle();
        }
    }

    public void switchBlueLight() {
        for (Light l : this.flashingBlueLights) {
            l.toggle();
        }
    }

    public void switchFrontLight() {
        for (Light l : this.searchLightsFront) {
            l.toggle();
        }
    }

    public void switchRoofLight() {
        for (Light l : this.searchLightsRoof) {
            l.toggle();
        }
    }

    public void switchSideLight() {
        for (Light l : this.searchLightsSide) {
            l.toggle();
        }
    }

    public void switchSelfprotection() {
        for (WaterCannon c : this.selfprotection) {
            c.toggle();
        }
    }

    public void steer(Integer degree) {
        this.drive.steer(degree);
        Integer angle = this.drive.getSteeringAngle();

        //First switch off all Indicators
        for (DirectionIndicator di : Stream.concat(indicatorsLeft.stream(), indicatorsRight.stream()).collect(Collectors.toList())) {
            if (di.getState()) di.toggle();
        }

        //Switch on needed indicators
        if (angle < 0) {
            for (DirectionIndicator di : indicatorsLeft) {
                if (!di.getState()) di.toggle();
            }
        } else if (angle > 0) {
            for (DirectionIndicator di : indicatorsRight) {
                if (!di.getState()) di.toggle();
            }
        }
    }

    public void accelerate() {
        this.drive.accelerate();
    }

    public void brake() {
        this.drive.brake();
    }
}