package factory;

import machine.Lathe;
import machine.Machine;

public class LatheMachineMaker implements MachineMaker {
    @Override
    public Machine newMachine() {
        return new Lathe();
    }

}
