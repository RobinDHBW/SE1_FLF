package FLF;

import Button.*;
import Cabin.Cabin;
import Drive.Drive;
import Firefighting.WaterCannonFront;
import Firefighting.WaterCannonRoof;
import Firefighting.WaterDieSelfprotection;
import Lights.*;
import Tank.MixingProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FLF {
    private final List<SearchLight> searchLightsFront;
    private final List<SearchLight> searchLightsRoof;
    private final List<SearchLight> searchLightsSide;
    private final List<DirectionIndicator> directionIndicators;
    private final List<BrakingLight> brakingLights;

    private final List<FlashingBlueLight> flashingBlueLights;
    private final List<WarningLight> warningLights;

    private final Cabin cabin;

    private final Drive drive;

    private final WaterCannonRoof waterCannonRoof;
    private final WaterCannonFront waterCannonFront;
    private final List<WaterDieSelfprotection> waterDiesSelfprotection;

    private final MixingProcessor mixingProcessor;
    //private final BatteryManagement batteryManagement;


    private FLF(Builder builder) {

        FLF built = builder.build();
        this.brakingLights = built.brakingLights;
        this.searchLightsFront = built.searchLightsFront;
        this.searchLightsRoof = built.searchLightsRoof;
        this.searchLightsSide = built.searchLightsSide;
        this.directionIndicators = built.directionIndicators;

        this.flashingBlueLights = built.flashingBlueLights;
        this.warningLights = built.warningLights;

        this.cabin = built.cabin;

        this.drive = built.drive;

        this.waterCannonRoof = built.waterCannonRoof;
        this.waterCannonFront = built.waterCannonFront;
        this.waterDiesSelfprotection = built.waterDiesSelfprotection;

        this.mixingProcessor = built.mixingProcessor;
        //this.batteryManagement = built.batteryManagement;
    }

    /**
     *
     */
    public static class Builder {

        private final static List<WarningLight> warningLights = new ArrayList<>();
        private final List<DirectionIndicator> directionIndicators = new ArrayList<>();
        private final List<BrakingLight> brakingLights = new ArrayList<>();
        private final List<SearchLight> searchLightsFront = new ArrayList<>();
        private final List<SearchLight> searchLightsRoof = new ArrayList<>();
        private final List<SearchLight> searchLightsSide = new ArrayList<>();
        private final List<FlashingBlueLight> flashingBlueLights = new ArrayList<>();
        private final Cabin cabin;

        private final Drive drive = new Drive();

        private final WaterCannonRoof waterCannonRoof = new WaterCannonRoof(500);
        private final WaterCannonFront waterCannonFront = new WaterCannonFront(500, 90);
        private final List<WaterDieSelfprotection> waterDiesSelfprotection = new ArrayList<>();

        private final MixingProcessor mixingProcessor = new MixingProcessor();
        //private final BatteryManagement batteryManagement = BatteryManagement;

        public Builder() {

            buildLights();
            Pedal pedalAcc = new Pedal(drive) {
                @Override
                public void operateDevice() {
                    ((Drive) this.operatingDevice).accelerate();
                }
            };
            Pedal pedalBrake = new Pedal(drive) {
                @Override
                public void operateDevice() {
                    ((Drive) this.operatingDevice).brake();
                }
            };

            ButtonRotaryWaterCannonFront btnCannonFront = new ButtonRotaryWaterCannonFront(this.waterCannonFront){
                @Override
                public void operateDevice() {
                    ((WaterCannonFront) this.operatingDevice).setSprayCapacityPerlIteration(this.amountPerIteration);
                }
            };

            ButtonRotaryWaterCannonRoof btnCannonRoof = new ButtonRotaryWaterCannonRoof(this.waterCannonRoof){
                @Override
                public void operateDevice() {
                    ((WaterCannonRoof) this.operatingDevice).setSprayCapacityPerlIteration(this.amountPerIteration);
                }
            };

            this.cabin = new Cabin.Builder(this.buildControlPanelButtons(), pedalAcc, pedalBrake, btnCannonRoof, btnCannonFront).build();


            //add Waterdies
            for (int i = 0; i < 7; i++) {
                this.waterDiesSelfprotection.add(new WaterDieSelfprotection(100));
            }
        }

        private void buildLights() {
            // add Breaklights
            for (int i = 0; i < 2; i++) {
                LightPosition position = i == 0 ? LightPosition.BACK_LEFT : LightPosition.BACK_RIGHT;
                this.brakingLights.add(new BrakingLight(position));
            }

            // add Searchlights Front
            for (int i = 0; i < 6; i++) {
                LightPosition position = switch (i) {
                    case 0, 1, 2 -> LightPosition.FRONT_LEFT;
                    default -> LightPosition.FRONT_RIGHT;
                };
                this.searchLightsFront.add(new SearchLight(position));
            }
            for (int i = 0; i < 4; i++) {
                this.searchLightsRoof.add(new SearchLight(LightPosition.ROOF_FRONT));
            }
            for (int i = 0; i < 10; i++) {
                LightPosition position = i < 5 ? LightPosition.LEFT_SIDE : LightPosition.RIGHT_SIDE;
                this.searchLightsSide.add(new SearchLight((position)));
            }

            // add Indicators
            for (int i = 0; i < 4; i++) {
                LightPosition position = switch (i) {
                    case 0 -> LightPosition.FRONT_LEFT;
                    case 1 -> LightPosition.FRONT_RIGHT;
                    case 2 -> LightPosition.BACK_LEFT;
                    default -> LightPosition.BACK_RIGHT;
                };
                this.directionIndicators.add(new DirectionIndicator(position));
            }

            // add small FlashingBlueLights
            for (int i = 0; i < 2; i++) {
                this.flashingBlueLights.add(new FlashingBlueLightSmall(LightPosition.FRONT_AREA));
            }

            // add medium FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_BACK_LEFT : LightPosition.ROOF_BACK_RIGHT;
                this.flashingBlueLights.add(new FlashingBlueLightMedium(position));
            }

            // add big FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_FRONT_LEFT : LightPosition.ROOF_FRONT_RIGHT;
                this.flashingBlueLights.add(new FlashingBlueLightBig(position));
            }

            // add WarningLights
            this.warningLights.add(new WarningLight(LightPosition.ROOF_FRONT_LEFT));
            this.warningLights.add(new WarningLight(LightPosition.ROOF_BACK_RIGHT));
        }

        private List<ButtonSwitch> buildControlPanelButtons() {
            List<ButtonSwitch> switches = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                switches.add(new ButtonSwitch(switch (i) {
                    case 0 -> warningLights;
                    case 1 -> flashingBlueLights;
                    case 2 -> searchLightsFront;
                    case 3 -> searchLightsRoof;
                    default -> searchLightsSide;
                }) {
                    @Override
                    public void operateDevice() {
                        for (Light l : (ArrayList<Light>) this.operatingDevice)
                            l.toggle();
                    }
                });
            }

            switches.add(new ButtonSwitch(waterDiesSelfprotection) {
                @Override
                public void operateDevice() {
                    ((WaterDieSelfprotection) this.operatingDevice).toggle();
                }
            });

            switches.add(new ButtonSwitch(drive) {
                @Override
                public void operateDevice() {
                    ((Drive) this.operatingDevice).toggleEngine();
                }
            });

            return switches;
        }

        /**
         * @return FLF.FLF
         */
        public FLF build() {
            return new FLF(this);
        }
    }
}