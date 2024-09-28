package factory;

import machine.Machine;

public class MachineMaker implements MachineMakerInterface {



    @Override
    public Machine newMachine() {
        return new Machine();
    }

}
