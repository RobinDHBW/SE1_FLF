package Seating;

import Person.Person;

public class Seat {
    private final Respirator respirator = new Respirator();
    protected Integer seatRow = 0;
    protected boolean leftSide;
    protected boolean occupied = false;
    protected Person seatWarmer;

    public Seat(Integer seatRow, Boolean leftSide) {
        this.seatRow = seatRow;
        this.leftSide = leftSide;

    }

    public void sitDown(Person person) {
        this.seatWarmer = person;
        this.occupied = true;
    }

    public Person leave() {
        Person p = this.seatWarmer;
        this.seatWarmer = null;
        this.occupied = false;
        return p;
    }

    public Respirator getRespirator() {
        return respirator;
    }
    public Integer getSeatRow() {
        return seatRow;
    }

    public Boolean getLeftSide() {
        return leftSide;
    }

    public Boolean getOccupied() {
        return occupied;
    }

}