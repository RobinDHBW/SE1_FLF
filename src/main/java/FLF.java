import Cabin.Cabin;
import Seating.Seat;
import Seating.SeatFirefighting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FLF {
    private final Cabin cabin;

    private FLF(Builder builder) {

        this.cabin = builder.build().cabin;
    }

    /**
     *
     */
    public static class Builder {

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