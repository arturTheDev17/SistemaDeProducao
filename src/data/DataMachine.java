package data;

public abstract class DataMachine implements Data {
    private String machineName;
    private double temperature;
    private double[] parameters;

    public DataMachine() {};

    public DataMachine(double temperature ) {
        this.temperature = temperature;
    };

    public DataMachine(String machineName, double temperature , double[] parameters) {
        this.machineName = machineName;
        this.temperature = temperature;
        this.parameters = parameters;
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

    public double[] getParameters() {
        return parameters;
    }

    public void setParameters(double[] parameters) {
        this.parameters = parameters;
    }
}