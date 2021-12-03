package Firefighting;

import Tank.Tank;
import Tank.TankSubject;

import java.util.List;

public interface IWaterCannon {
    void toggle();
    void spray(List<TankSubject> toSpray);
}
