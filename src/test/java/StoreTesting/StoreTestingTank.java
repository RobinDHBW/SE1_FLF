package StoreTesting;


import Tank.Tank;
import Tank.TankSubject;
import org.junit.jupiter.api.Test;

public class StoreTestingTank {
    Tank tank = new Tank(TankSubject.FOAM, 10,10,10);
    @Test
    public void testFill(){
        tank.fill(TankSubject.FOAM, 25);
        Integer fillState = tank.getRelativeFillState();
        System.out.println(fillState);
    }
}
