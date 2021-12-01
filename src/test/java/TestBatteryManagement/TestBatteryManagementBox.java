package TestBatteryManagement;

import BatteryManagement.BatteryBox;
import BatteryManagement.BatteryUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBatteryManagementBox {

    @Test
    public void testFill() {
        Integer width = 2, height = 2, quantity = 1000;
        BatteryBox batteryBox = new BatteryBox(width, height);
        batteryBox.fill(BatteryUnit.POSITIVE, quantity);

        assertEquals(1 / ((100 * 10 * 100) / (quantity.doubleValue() / (width * height))), batteryBox.getRelativeFillState());
    }

    @Test
    public void testTakeout() {
        Integer width = 2, height = 2, quantity = 100000, remove = 25000;
        BatteryBox batteryBox = new BatteryBox(2, 2);
        batteryBox.fill(BatteryUnit.POSITIVE, quantity);
        Enum output[] = batteryBox.remove(remove);

        assertEquals(output[0], BatteryUnit.POSITIVE);
        assertEquals(1 / ((100 * 10 * 100) / ((quantity.doubleValue() - remove) / (width * height))), batteryBox.getRelativeFillState());
    }
}
