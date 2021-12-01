package BatteryManagement;

public enum BatteryManagement {
    ;
    private BatteryBox batteryBox = new BatteryBox(2, 2);

    public Enum[] remove(Integer quantity) {
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
}
