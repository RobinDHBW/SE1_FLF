package Drive;

import java.util.ArrayList;
import java.util.List;

public class Axle {

    private List<Tire> tires = new ArrayList<>();
    private List<DiscBrake> brakes = new ArrayList<>();

    public Axle() {
        for (int i = 0; i < 2; i++) {
            tires.add(new Tire());
        }
        for (int i = 0; i < 6; i++) {
            brakes.add(new DiscBrake(Double.valueOf(4 / 24)));
        }
    }

    public Double brake() {
        return null;
    }
}
