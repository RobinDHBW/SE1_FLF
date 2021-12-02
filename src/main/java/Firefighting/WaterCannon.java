package Firefighting;

import Tank.TankSubject;

public class WaterCannon implements IWaterCannon {
    protected Boolean state = false;
    protected Integer sprayCapacityPerlIteration;

    public WaterCannon(Integer sprayCapacityPerlIteration) {
        this.sprayCapacityPerlIteration = sprayCapacityPerlIteration;
    }

    @Override
    public void toggle() {
        this.state = !this.state;
    }

    @Override
    public void spray(TankSubject[] toSpray) {
        for (int i = 0; i < toSpray.length; i++) {
            toSpray[i] = null;
        }
    }

    public void setSprayCapacityPerlIteration(Integer sprayCapacityPerlIteration) {
        this.sprayCapacityPerlIteration = sprayCapacityPerlIteration;
    }

    public Integer getSprayCapacityPerlIteration() {
        return sprayCapacityPerlIteration;
    }
}
