package Drive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Drive {

    private List<ElectricEngine> engines = new ArrayList<>();
    private List<AxleSteerable> steerables = new ArrayList<>();
    private List<Axle> axles = new ArrayList<>();

    private Integer speed = 0;

    public Drive() {
        for (int i = 0; i < 2; i++) {
            engines.add(new ElectricEngine(2));
            steerables.add(new AxleSteerable());
            axles.add(new Axle());
        }
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer brake() {
        Double calc = Double.valueOf(this.speed);
        for (Axle a : Stream.concat(steerables.stream(), axles.stream()).collect(Collectors.toList())) {
            calc += a.brake();
        }
        this.speed = calc.intValue();
        return speed;
    }

    public Integer accelerate() {
        for (ElectricEngine e : engines) {
            this.speed += e.accelerate();
        }
        return this.speed;
    }

    public void toggleEngine() {
        for (ElectricEngine e : engines) {
            e.toggle();
        }
    }

    public void steer(Integer degree) {
        for (AxleSteerable as : steerables) {
            as.steer(degree);
        }
    }
}
