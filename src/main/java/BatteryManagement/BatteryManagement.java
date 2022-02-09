package BatteryManagement;

import java.util.List;

public enum BatteryManagement {
    instance;
    private final BatteryBox batteryBox = new BatteryBox(2, 2);

    public List<Coulomb> remove(Double quantity) {
        return batteryBox.remove(quantity);
    }

    public void fill(Double quantity) {
        batteryBox.fill(quantity);
    }

    public void fillComplete() {
        Double actualFillState = batteryBox.getCapacity() * batteryBox.getRelativeFillState();
        this.fill(batteryBox.getCapacity() - actualFillState);
    }

    public Double getRelativeFillState() {
        return batteryBox.getRelativeFillState();
    }
}
