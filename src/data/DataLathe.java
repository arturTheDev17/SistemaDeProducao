package data;

public class DataLathe extends DataMachine {
    private int rotationSpeed;
    private boolean collision;

    public DataLathe() {}

    public DataLathe(double[] parameters, String machineName ,double temperature, int rotationSpeed, boolean collision ) {
        super(machineName, temperature, parameters);
        this.rotationSpeed = rotationSpeed;
        this.collision = collision;
    };

    @Override
    public String toString() {
        return super.toString() +
                "RotationSpeed (RPM): " + this.rotationSpeed + "\n" +
                "Collided: " + this.collision + "\n";
    }

    @Override
    public void setTemperature(double temperature) {
        super.setTemperature(temperature);
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
