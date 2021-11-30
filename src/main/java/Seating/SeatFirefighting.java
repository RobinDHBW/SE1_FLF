package Seating;

import Person.Person;

public class SeatFirefighting extends Seat {
    protected Person personAllowed;

    public SeatFirefighting(boolean leftSide, boolean occupied) {
        super(0, leftSide, occupied);
    }
}