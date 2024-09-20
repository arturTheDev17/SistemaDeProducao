package factory;

import machine.Lathe;
import machine.Machine;

public class LatheMachineMaker {
    public Machine newMachine() {
        return new Lathe();
    }



}
