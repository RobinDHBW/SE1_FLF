import Button.Button;
import Button.IButtonListener;
import Cabin.Cabin;
import Lights.*;
import Seating.Seat;
import Seating.SeatFirefighting;

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
    }

    /**
     *
     */
    public static class Builder {

        private final List<DirectionIndicator> directionIndicators = new ArrayList<>();
        private final List<BrakingLight> brakingLights = new ArrayList<>();
        private final List<SearchLight> searchLights = new ArrayList<>();
        private final WarningLight warningLight = new WarningLight();
        private final FlashingBlueLightSmall flashingBlueLightSmall = new FlashingBlueLightSmall();
        private final FlashingBlueLightMedium flashingBlueLightMedium = new FlashingBlueLightMedium();
        private final FlashingBlueLightBig flashingBlueLightBig = new FlashingBlueLightBig();
        private final Cabin cabin = new Cabin.Builder().build();
        private final CentralUnit centralUnit = new CentralUnit();

        public Builder() {

            // add Breaklights
            for (int i = 0; i < 2; i++) {
                LightPosition position = i == 0 ? LightPosition.BACK_LEFT : LightPosition.BACK_RIGHT;
                brakingLights.add(new BrakingLight(position));
            }

            // add Searchlights
            for (int i = 0; i < 10; i++) {
                LightPosition position = switch (i) {
                    case 0, 1, 2 -> LightPosition.FRONT_LEFT;
                    case 3, 4, 5 -> LightPosition.FRONT_RIGHT;
                    default -> LightPosition.ROOF_FRONT;
                };
                searchLights.add(new SearchLight(position));
            }

            // add Indicators
            for (int i = 0; i < 4; i++) {
                LightPosition position = switch (i) {
                    case 0 -> LightPosition.FRONT_LEFT;
                    case 1 -> LightPosition.FRONT_RIGHT;
                    case 2 -> LightPosition.BACK_LEFT;
                    default -> LightPosition.BACK_RIGHT;
                };
                directionIndicators.add(new DirectionIndicator(position));
            }
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