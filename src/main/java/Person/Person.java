package Person;

import IDCard.IDCard;

public abstract class Person {

    private Boolean isInVehicle = false;
    private final String name;
    private final IDCard idCard;
    public Person(String name, IDCard idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    public void setIsInVehicle(Boolean isIn){this.isInVehicle = isIn;}

    public String getName() {
        return name;
    }
}