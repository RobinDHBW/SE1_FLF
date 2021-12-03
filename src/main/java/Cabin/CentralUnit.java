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
    private List<ElectricEngine> engines;
    private List<WarningLight> warningLights;
    private List<FlashingBlueLightBig> flashingBlueLightsBig;
    private List<FlashingBlueLightMedium> flashingBlueLightsMedium;
    private List<FlashingBlueLightSmall> flashingBlueLightsSmall;
    private List<SearchLight> searchLightsFront;
    private List<SearchLight> searchLightsRoof;
    private List<SearchLight> searchLightsSide;
    private List<WaterDieSelfprotection> selfprotection;
    private Drive drive;

    public CentralUnit(
            List<ElectricEngine> engines,
            List<WarningLight> warningLights,
            List<FlashingBlueLightBig> flashingBlueLightsBig,
            List<FlashingBlueLightMedium> flashingBlueLightsMedium,
            List<FlashingBlueLightSmall> flashingBlueLightsSmall,
            List<SearchLight> searchLightsFront,
            List<SearchLight> searchLightsRoof,
            List<SearchLight> searchLightsSide,
            List<WaterDieSelfprotection> selfprotection,
            Drive drive
    ) {
        this.engines = engines;
        this.warningLights = warningLights;
        this.flashingBlueLightsBig = flashingBlueLightsBig;
        this.flashingBlueLightsMedium = flashingBlueLightsMedium;
        this.flashingBlueLightsSmall = flashingBlueLightsSmall;
        this.searchLightsFront = searchLightsFront;
        this.searchLightsRoof = searchLightsRoof;
        this.searchLightsSide = searchLightsSide;
        this.selfprotection = selfprotection;
        this.drive = drive;
    }


    public void switchEngines() {
        for (ElectricEngine e : this.engines) {
            e.toggle();
        }
    }

    public void switchWarningLight() {
        for (Light l : this.warningLights) {
            l.toggle();
        }
    }

    public void switchBlueLight() {
        List<FlashingBlueLight> all = Stream.of(flashingBlueLightsBig, flashingBlueLightsMedium, flashingBlueLightsSmall).flatMap(Collection::stream).collect(Collectors.toList());

        for (Light l : all) {
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
    }

    /**
     * @TODO Consume Energy
     */
    public void accelerate() {
        this.drive.accelerate();
    }

    /**
     * @TODO Consume Energy
     */
    public void brake() {
        this.drive.brake();
    }
}