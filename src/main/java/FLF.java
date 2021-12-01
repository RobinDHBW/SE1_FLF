import Button.Button;
import Button.IButtonListener;
import Cabin.Cabin;
import Lights.Light;
import Seating.Seat;
import Seating.SeatFirefighting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FLF {
    private final 
    private final Cabin cabin;
    private final CentralUnit centralUnit;


    private FLF(Builder builder) {
        FLF built = builder.build();
        this.cabin = built.cabin;
        this.centralUnit = built.centralUnit;
    }

    /**
     *
     */
    public static class Builder {

        private final Cabin cabin = new Cabin.Builder().build();
        private final CentralUnit centralUnit = new CentralUnit();

        public Builder() {


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