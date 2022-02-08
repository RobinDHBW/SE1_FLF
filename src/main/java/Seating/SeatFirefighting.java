package Seating;

import Person.Person;

public class SeatFirefighting extends Seat {
    protected Person personAllowed;

    public SeatFirefighting(Person personAllowed, Boolean leftSide) {
        super(0, leftSide);
        this.personAllowed = personAllowed;
    }

    @Override
    public void sitDown(Person person) {
        try {
            if (!person.getClass().equals(personAllowed.getClass()))
                throw new Exception("This Seat is reserved for: " + personAllowed.toString());
            super.sitDown(person);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public Person getPersonAllowed() {
        return personAllowed;
    }
}