package TestBatteryManagement;

import BatteryManagement.BatteryBox;
import BatteryManagement.BatteryManagement;
import BatteryManagement.BatteryUnit;
import BatteryManagement.Coulomb;
import Drive.Drive;
import FLF.FLF;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBatteryManagementBox {

    @Test
    public void testFill() {
        Integer width = 2, height = 2, quantity = 1000;
        BatteryBox batteryBox = new BatteryBox(width, height);
        batteryBox.fill(quantity);

        assertEquals(1 / ((100 * 10 * 100) / (quantity.doubleValue() / (width * height))), batteryBox.getRelativeFillState());
    }

    @Test
    public void testFillComplete() {
        Drive drive = new Drive();
        drive.fillComplete();


        assertEquals(1, drive.getRelativeFillState());
    }

    @Test
    public void testTakeout() {
        Integer width = 2, height = 2, quantity = 100000, remove = 25000;
        BatteryBox batteryBox = new BatteryBox(width, height);
        batteryBox.fill(quantity);
        List<Coulomb> output  = batteryBox.remove(remove);

        assertTrue(output.get(0).getClass().equals(new Coulomb().getClass()));
        assertEquals(1 / ((100 * 10 * 100) / ((quantity.doubleValue() - remove) / (width * height))), batteryBox.getRelativeFillState());
    }
}
