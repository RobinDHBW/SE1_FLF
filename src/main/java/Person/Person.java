package Person;

public abstract class Person {

    private Boolean isInVehicle = false;
    public Person() {

    }

    public Boolean getIsInVehicle(){return isInVehicle;}
    public void setIsInVehicle(Boolean isIn){this.isInVehicle = isIn;}
}