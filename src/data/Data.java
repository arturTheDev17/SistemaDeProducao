package data;

public abstract class Data {
    //TODO tostring
    private String machineName;
    private double temperature;

    public Data() {};

    public Data( double temperature ) {
        this.temperature = temperature;
    };

    public Data(String machineName, double temperature) {
        this.machineName = machineName;
        this.temperature = temperature;
    };

    @Override
    public String toString() {
        return  "Machine: " + this.machineName + "\n" +
                "Temperature: " + this.temperature + "\n";
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getMachineName() {
        return machineName;
    }
}
