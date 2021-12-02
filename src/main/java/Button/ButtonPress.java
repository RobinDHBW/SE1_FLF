package Button;

public class ButtonPress extends Button{
    private boolean isActivated;

    public boolean isHeld5seconds() {
        return isActivated;
    }

    public ButtonPress(){
        this.isActivated = false;
    }

    public void hold5sec() {
        if(isHeld5seconds()) {
            this.isActivated = false;
        } else this.isActivated = true;
    }

}
