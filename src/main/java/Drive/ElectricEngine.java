package Drive;

import BatteryManagement.Coulomb;

import java.util.List;

public class ElectricEngine {

    private Boolean state = false;
    private final Integer stepSize;

    public ElectricEngine(Integer stepSize) {
        this.stepSize = stepSize;
    }

    public Integer accelerate() {
        return this.stepSize;
    }

    public void drive(List<Coulomb> energy) {
        for (Coulomb slot : energy) {
            slot = null;
        }
    }

    public void toggle() {
        this.state = !this.state;
    }

    public Boolean getState() {
        return state;
    }
}
