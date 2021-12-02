package Firefighting;

import Tank.TankSubject;

public interface IWaterCannon {
    void toggle();
    void spray(TankSubject[] toSpray);
}
