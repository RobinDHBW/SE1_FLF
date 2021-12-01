package Seating;

import Person.Person;

public class Seat {
    public Respirator respirator = new Respirator();
    protected int seatRow = 0;
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

    public void leave() {
        this.seatWarmer = null;
        this.occupied = false;
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