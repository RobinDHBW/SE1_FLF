import BatteryManagement.BatteryManagement;
import Button.Button;
import Button.IButtonListener;
import Cabin.Cabin;
import ControlPanel.ControlPanel;
import Firefighting.WaterCannonFront;
import Firefighting.WaterCannonRoof;
import Firefighting.WaterDieSelfprotection;
import Lights.*;
import Seating.Seat;
import Seating.SeatFirefighting;
import Tank.MixingProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FLF {
    private final List<SearchLight> searchLights;
    private final List<DirectionIndicator> directionIndicators;
    private final List<BrakingLight> brakingLights;

    private final List<FlashingBlueLightSmall> flashingBlueLightsSmall;
    private final List<FlashingBlueLightMedium> flashingBlueLightsMedium;
    private final List<FlashingBlueLightBig> flashingBlueLightsBig;
    private final List<WarningLight> warningLights;

    private final Cabin cabin;
    private final CentralUnit centralUnit;
    private final ControlPanel controlPanel;

    private final WaterCannonRoof waterCannonRoof;
    private final WaterCannonFront waterCannonFront;
    private final List<WaterDieSelfprotection> waterDiesSelfprotection;

    private final MixingProcessor mixingProcessor;
    //private final BatteryManagement batteryManagement;


    private FLF(Builder builder) {

        FLF built = builder.build();
        this.brakingLights = built.brakingLights;
        this.searchLights = built.searchLights;
        this.directionIndicators = built.directionIndicators;

        this.flashingBlueLightsSmall = built.flashingBlueLightsSmall;
        this.flashingBlueLightsMedium = built.flashingBlueLightsMedium;
        this.flashingBlueLightsBig = built.flashingBlueLightsBig;
        this.warningLights = built.warningLights;

        this.cabin = built.cabin;
        this.centralUnit = built.centralUnit;
        this.controlPanel = built.controlPanel;

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

        private final List<DirectionIndicator> directionIndicators = new ArrayList<>();
        private final List<BrakingLight> brakingLights = new ArrayList<>();
        private final List<SearchLight> searchLights = new ArrayList<>();

        private final List<FlashingBlueLightSmall> flashingBlueLightsSmall = new ArrayList<>();
        private final List<FlashingBlueLightMedium> flashingBlueLightsMedium = new ArrayList<>();
        private final List<FlashingBlueLightBig> flashingBlueLightsBig = new ArrayList<>();
        private final List<WarningLight> warningLights = new ArrayList<>();

        private final Cabin cabin = new Cabin.Builder().build();
        private final CentralUnit centralUnit = new CentralUnit();
        private final ControlPanel controlPanel = new ControlPanel.Builder().build();

        private final WaterCannonRoof waterCannonRoof = new WaterCannonRoof(500);
        private final WaterCannonFront waterCannonFront = new WaterCannonFront(500, 90);
        private final List<WaterDieSelfprotection> waterDiesSelfprotection = new ArrayList<>();

        private final MixingProcessor mixingProcessor = new MixingProcessor();
        //private final BatteryManagement batteryManagement = BatteryManagement;

        public Builder() {

            buildLights();


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

            // add Searchlights
            for (int i = 0; i < 10; i++) {
                LightPosition position = switch (i) {
                    case 0, 1, 2 -> LightPosition.FRONT_LEFT;
                    case 3, 4, 5 -> LightPosition.FRONT_RIGHT;
                    default -> LightPosition.ROOF_FRONT;
                };
                this.searchLights.add(new SearchLight(position));
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
                this.flashingBlueLightsSmall.add(new FlashingBlueLightSmall(LightPosition.FRONT_AREA));
            }

            // add medium FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_BACK_LEFT : LightPosition.ROOF_BACK_RIGHT;
                this.flashingBlueLightsMedium.add(new FlashingBlueLightMedium(position));
            }

            // add big FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_FRONT_LEFT : LightPosition.ROOF_FRONT_RIGHT;
                this.flashingBlueLightsBig.add(new FlashingBlueLightBig(position));
            }

            // add WarningLights
            this.warningLights.add(new WarningLight(LightPosition.ROOF_FRONT_LEFT));
            this.warningLights.add(new WarningLight(LightPosition.ROOF_BACK_RIGHT));
        }

        /**
         * @return FLF
         */
        public FLF build() {
            return new FLF(this);
        }
    }

    public static class CentralUnit implements ICentralUnit {
        private Integer speed = 0;

        public CentralUnit() {

        }

        @Override
        public void switchLight(Light light) {
            // Access all FLF properties with -> FLF.this.
        }

        @Override
        public void steer(Double degree) {

        }

        @Override
        public void adjustSpeed(Integer speed) {

        }
    }
}