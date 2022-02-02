import FLF.*;

public class Application {

    public static void main(String[] args) {
        FLF flf = new FLF.Builder().build();
        Szenarios szenarios = new Szenarios(flf);
        //szenarios.park();
    }
}