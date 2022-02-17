package Person;

public abstract class Person {

    private Boolean isInVehicle = false;
    private final String name;
    public Person(String name) {
        this.name = name;
    }

    public void setIsInVehicle(Boolean isIn){this.isInVehicle = isIn;}
}