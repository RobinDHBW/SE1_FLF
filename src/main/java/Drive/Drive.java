package Drive;

import BatteryManagement.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Drive {

    private final List<ElectricEngine> engines = new ArrayList<>();
    private final List<AxleSteerable> steerables = new ArrayList<>();
    private final List<Axle> axles = new ArrayList<>();
    private final BatteryManagement batteryManagement = BatteryManagement.instance;
    private Integer speed = 0;

    public Drive() {
        for (int i = 0; i < 2; i++) {
            engines.add(new ElectricEngine(2));
            steerables.add(new AxleSteerable());
            axles.add(new Axle());
        }
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer brake() {
        if(this.speed == 0) return this.speed;
        Double calc = Double.valueOf(this.speed);
        for (Axle a : Stream.concat(steerables.stream(), axles.stream()).collect(Collectors.toList())) {
            calc += a.brake();
        }
        this.speed = calc.intValue();
        return speed;
    }

    public Integer accelerate() {
        for (ElectricEngine e : engines) {
            this.speed += e.accelerate();
        }
        return this.speed;
    }

    public Integer drive() {
        Integer eAmount = this.speed * 25;
        Integer mod = eAmount % engines.size();
        int i=0;
        for (ElectricEngine e : engines) {
            if(i==engines.size()) eAmount +=mod;
            List<Coulomb> energy = this.batteryManagement.remove(eAmount/engines.size());
            e.drive(energy);
            i++;
        }
        return this.speed;
    }

    public void toggleEngine() {
        for (ElectricEngine e : engines) {
            e.toggle();
        }
    }

    public void steer(Integer degree) {
        for (AxleSteerable as : steerables) {
            as.steer(degree);
        }
    }

    public Integer getSteeringAngle(){
        return this.steerables.get(0).getSteeringAngle();
    }

    public Boolean getEngineState(){
        return this.engines.get(0).getState();
    }

    public void fillComplete(){
        this.batteryManagement.fillComplete();
    }

    public Double getRelativeFillState() {
        return this.batteryManagement.getRelativeFillState();
    }
}
