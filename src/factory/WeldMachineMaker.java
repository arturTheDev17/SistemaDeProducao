package factory;

import machine.Machine;
import machine.Welder;

public class WeldMachineMaker implements MachineMaker{
    @Override
    public Machine newMachine() {
        return new Welder();
    }
}
