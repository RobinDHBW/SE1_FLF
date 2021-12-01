package Firefighting;

public class WaterCannon implements IWaterCannon {
    protected Boolean state = false;
    protected Integer sprayCapacityPerlIteration;

    public WaterCannon(Integer sprayCapacityPerlIteration){
        this.sprayCapacityPerlIteration = sprayCapacityPerlIteration;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void spray() {

    }
}
