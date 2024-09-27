package factory;

import machine.Machine;

public class MachineMaker implements MachineMakerInterface {
    protected MachineMaker () {};
    @Override
    public Machine newMachine() {
        return null;
    }

    @Override
    public void machineCreation() {}

}
