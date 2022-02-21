package testBatteryManagement;

import batteryManagement.BatteryBox;
import batteryManagement.BatteryManagement;
import batteryManagement.Coulomb;
import drive.Drive;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBatteryManagementBox {

    @Test
    public void testFill() {
        int width = 2, height = 2, quantity = 1000;
        BatteryBox batteryBox = new BatteryBox(width, height);
        batteryBox.fill(quantity);

        assertEquals(1.0 / ((100 * 10 * 100) / (quantity / (width * height))), batteryBox.getRelativeFillState());
    }

    @Test
    public void testFillComplete() {
        Drive drive = new Drive();
        drive.fillComplete();

        assertEquals(1.0, drive.getRelativeFillState());
    }

    @Test
    public void testEmptyComplete() {
        BatteryManagement batteryManagement = BatteryManagement.instance;
        batteryManagement.fillComplete();
        batteryManagement.remove(batteryManagement.getCapacity());


        assertEquals(0, batteryManagement.getRelativeFillState());
    }

    @Test
    public void testTakeout() {
        Integer width = 2, height = 2, quantity = 100000, remove = 25000;
        BatteryBox batteryBox = new BatteryBox(width, height);
        batteryBox.fill(quantity);
        List<Coulomb> output  = batteryBox.remove(remove);

        assertEquals(output.get(0).getClass(), Coulomb.class);
        assertEquals(1 / ((100 * 10 * 100) / ((quantity.doubleValue() - remove) / (width * height))), batteryBox.getRelativeFillState());
    }
}
