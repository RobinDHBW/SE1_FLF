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
        Integer actualFillState = batteryBox.getAbsoluteFillState();
        this.fill(batteryBox.getCapacity() - actualFillState);
    }

    public Double getRelativeFillState() {
        return batteryBox.getRelativeFillState();
    }
    public Integer getCapacity(){return  batteryBox.getCapacity();}
}
