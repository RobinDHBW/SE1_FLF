package Drive;

import java.util.ArrayList;
import java.util.List;

public class Drive {

    private List<ElectricEngine> engines = new ArrayList<>();
    private List<AxleSteerable> steerables = new ArrayList<>();
    private List<Axle> axles = new ArrayList<>();

    private Integer speed = 0;

    public Drive() {
        for (int i = 0; i < 2; i++) {
            engines.add(new ElectricEngine(2));
            steerables.add(new AxleSteerable(5));
            axles.add(new Axle());
        }
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer brake() {
        return speed;
    }

    public Integer accelerate(){
        for(ElectricEngine e : engines){
            this.speed+=e.accelerate();
        }
        return this.speed;
    }
}
