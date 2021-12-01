package Button;

public class Pedal extends ButtonTap{
    protected int stepSize;
    protected PedalType pedalType;
    protected Integer speed =0;

    public Pedal(PedalType pedalType, Integer stepSize)
    {
        this.pedalType = pedalType;
        this.stepSize=stepSize;
    }

    @Override
    public void tapButton() {
        this.speed += switch (pedalType){
            case BREAK -> stepSize*-1;
            case ACCELERATE -> stepSize;
        };
    }

    public Integer getSpeed() {
        return speed;
    }
}