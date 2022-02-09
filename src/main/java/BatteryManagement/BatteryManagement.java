package BatteryManagement;

import java.util.List;

public enum BatteryManagement {
    instance;
    private final BatteryBox batteryBox = new BatteryBox(2, 2);

    public List<Coulomb> remove(Integer quantity) {
        return batteryBox.remove(quantity);
    }

    public void fill(Integer quantity) {
        batteryBox.fill(quantity);
    }

    public void fillComplete() {
        Double actualFillState = batteryBox.getCapacity() * batteryBox.getRelativeFillState();
        this.fill(batteryBox.getCapacity() - actualFillState.intValue());
    }

    public Double getRelativeFillState() {
        return batteryBox.getRelativeFillState();
    }
    public Integer getCapacity(){return  batteryBox.getCapacity();}
}
