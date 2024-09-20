package data;

public class DataWelder extends DataMachine {
    private double current;
    private double activeTime;

    public DataWelder() {}

    public DataWelder(String machineName, double temperature, double current, double activeTime ) {
        super( machineName , temperature );
        this.current = current;
        this.activeTime = activeTime;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Current (A): " + this.current +
                "\nActive time (minutes): " + this.activeTime + "\n";
    }


    public void setCurrent(double current) {
        this.current = current;
    }

    public double getCurrent(){
        return this.current;
    }

    public double getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(double activeTime) {
        this.activeTime = activeTime;
    }
}
