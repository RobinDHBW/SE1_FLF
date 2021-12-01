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
    private final DirectionIndicator directionIndicator;
    private final BrakingLight brakingLight;
    private final SearchLight searchLight;
    private final WarningLight warningLight;
    private final FlashingBlueLightSmall flashingBlueLightSmall;
    private final FlashingBlueLightMedium flashingBlueLightMedium;
    private final FlashingBlueLightBig flashingBlueLightBig;
    private final Cabin cabin;

    private FLF(Builder builder) {

        this.directionIndicator = builder.build().directionIndicator;
        this.brakingLight = builder.build().brakingLight;
        this.searchLight = builder.build().searchLight;
        this.warningLight = builder.build().warningLight;
        this.flashingBlueLightSmall = builder.build().flashingBlueLightSmall;
        this.flashingBlueLightMedium = builder.build().flashingBlueLightMedium;
        this.flashingBlueLightBig = builder.build().flashingBlueLightBig;
        this.cabin = builder.build().cabin;

    }

    /**
     *
     */
    public static class Builder {

        private final DirectionIndicator directionIndicator = new DirectionIndicator();
        private final BrakingLight brakingLight = new BrakingLight();
        private final SearchLight searchLight = new SearchLight();
        private final WarningLight warningLight = new WarningLight();
        private final FlashingBlueLightSmall flashingBlueLightSmall = new FlashingBlueLightSmall();
        private final FlashingBlueLightMedium flashingBlueLightMedium = new FlashingBlueLightMedium();
        private final FlashingBlueLightBig flashingBlueLightBig = new FlashingBlueLightBig();
        private final Cabin cabin = new Cabin.Builder().build();

        public Builder() {


        }

        /**
         * @return FLF
         */
        public FLF build() {
            return new FLF(this);
        }
    }
}