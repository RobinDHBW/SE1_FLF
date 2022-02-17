package Button;

public class ButtonPress extends ButtonTap{
    private boolean isActivated;

    public boolean isHeld5seconds() {
        return isActivated;
    }

    public ButtonPress(Object o){
        super(o);
        this.isActivated = false;
    }

    public boolean hold5sec() {
        this.isActivated = !isHeld5seconds();
        return this.isActivated;
    }
}
