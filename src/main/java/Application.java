import FLF.*;
import IDCard.IDCard;
import Person.*;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        ArrayList<Person> authorizedPersons = new ArrayList<>();
        authorizedPersons.add(new Driver("Red Adair", new IDCard("abc")));
        authorizedPersons.add(new Operator("Sam", new IDCard("abc")));
        FLF flf = new FLF.Builder(authorizedPersons).build();
    }
}