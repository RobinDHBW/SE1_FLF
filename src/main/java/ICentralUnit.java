import Lights.Light;


public interface ICentralUnit {
    public void switchLight(Light light);
    public void steer(Double degree);
    public void adjustSpeed(Integer speed);
}
