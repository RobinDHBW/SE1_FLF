import Cabin.Cabin;
import Seating.Seat;
import Seating.SeatFirefighting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FLF {
    private final List<Seat> seatList;
    private final Cabin cabin;

    private FLF(Builder builder) {
        this.seatList = builder.build().seatList;
        this.cabin = builder.build().cabin;
    }

    /**
     *
     */
    public static class Builder {
        private final List<Seat> seatList = new ArrayList<>();
        private final  Cabin cabin = new Cabin.Builder().build();

        public Builder() {

            for (int i = 0; i < 2; i++) {
                boolean leftSide = i == 0 ;
                seatList.add(new Seat(1, leftSide, false));
                seatList.add(new SeatFirefighting(leftSide, false));
            }


        }

        /**
         * @return FLF
         */
        public FLF build() {
            return new FLF(this);
        }
    }
}