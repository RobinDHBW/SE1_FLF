package Cabin;

import Drive.Drive;

import Firefighting.CannonIdentifier;
import Instruments.Speedometer;
import Lights.*;
import Tank.MixingProcessor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CentralUnit {
    private final List<WarningLight> warningLights;
    private final List<FlashingBlueLight> flashingBlueLights;
    private final List<SearchLight> searchLightsFront;
    private final List<SearchLight> searchLightsRoof;
    private final List<SearchLight> searchLightsSide;
    private final List<DirectionIndicator> indicatorsLeft;
    private final List<DirectionIndicator> indicatorsRight;
    private final MixingProcessor mixingProcessor;
    private final Drive drive;
    private final Speedometer speedometer;

    public CentralUnit(
            List<WarningLight> warningLights,
            List<FlashingBlueLight> flashingBlueLights,
            List<SearchLight> searchLightsFront,
            List<SearchLight> searchLightsRoof,
            List<SearchLight> searchLightsSide,
            List<DirectionIndicator> indicatorsLeft,
            List<DirectionIndicator> indicatorsRight,
            MixingProcessor mixingProcessor,
            Drive drive,
            Speedometer speedometer
    ) {
        this.warningLights = warningLights;
        this.flashingBlueLights = flashingBlueLights;
        this.searchLightsFront = searchLightsFront;
        this.searchLightsRoof = searchLightsRoof;
        this.searchLightsSide = searchLightsSide;
        this.indicatorsLeft = indicatorsLeft;
        this.indicatorsRight = indicatorsRight;
        this.mixingProcessor = mixingProcessor;
        this.drive = drive;
        this.speedometer = speedometer;
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
        this.mixingProcessor.toggle(CannonIdentifier.CANNON_SELFPROTECTION);
        this.mixingProcessor.spray(CannonIdentifier.CANNON_SELFPROTECTION);
        this.mixingProcessor.toggle(CannonIdentifier.CANNON_SELFPROTECTION);
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
        this.speedometer.setSpeed(this.drive.accelerate());
    }

    public void brake() {
        this.speedometer.setSpeed(this.drive.brake());
    }

    public void drive() {
        this.speedometer.setSpeed(this.drive.drive());
    }
}