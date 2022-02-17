package Person;

import IDCard.IDCard;

public abstract class Person {

    protected Boolean isInVehicle = false;
    protected final String name;
    protected final IDCard idCard;
    public Person(String name, IDCard idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    public void setIsInVehicle(Boolean isIn){this.isInVehicle = isIn;}

    public String getName() {
        return name;
    }
}