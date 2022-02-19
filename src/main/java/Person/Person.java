package Person;

import CentralUnit.CryptoUnit;
import Configuration.Configuration;
import IDCard.IDCard;

public abstract class Person {

    private CryptoUnit cryptoUnit = new CryptoUnit();
    protected Boolean isInVehicle = false;
    protected final String name;
    protected final IDCard idCard;
    public Person(String name) {
        this.name = name;
        this.idCard = new IDCard(cryptoUnit.encrypt(Configuration.instance.flfIdentifier + "-" + this.name + "-" + Configuration.instance.cuCode, Configuration.instance.cuCode));
    }

    public void setIsInVehicle(Boolean isIn){this.isInVehicle = isIn;}

    public String getName() {
        return name;
    }
}