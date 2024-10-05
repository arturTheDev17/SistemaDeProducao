package main;

import factory.MachineMaker;
import machine.Machine;
import panel.EmployeePanel;
import panel.InformationPanel;
import structure.Mediator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    // List of machines in the system
    private static final ArrayList<Machine> MACHINES = new ArrayList<>();
    // Mediator instance for communication between machines and panels
    private static Mediator MEDIATOR = Mediator.getInstance();
    // Machine maker instance for creating machines
    private static MachineMaker MACHINE_MAKER = MachineMaker.getInstance();

    public static void main(String[] args) throws InterruptedException {
        // Initialize the mediator and machine maker
        MEDIATOR = Mediator.getInstance();
        MACHINE_MAKER = MachineMaker.getInstance();

        // Create machines
        for (int i = 0; i < 5; i++) {
            MACHINES.add(MACHINE_MAKER.newMachine("Machine " + (i + 1), new HashMap<>()));
            MACHINES.get(i).subscribe(MEDIATOR);
            MACHINES.get(i).addAttribute("isDisconnected", false);
            MACHINES.get(i).addAttribute("Temperature", new Double[]{-5., 80., 0.});
        }

        // Subscribe panels to the mediator
        MEDIATOR.subscribe(new InformationPanel());
        MEDIATOR.subscribe(new EmployeePanel());

        // Start generating random data for machines
        while (true) {
            randomizerMachineDataAsync();
            Thread.sleep(5000);
        }
    }

    /**
     * Method to randomize machine data asynchronously.
     * Each machine's attributes are updated with random values.
     */
    private static void randomizerMachineDataAsync() {
        Random ran = new Random();
        for (Machine machine : MACHINES) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                // Update attributes of the machine with random values
                for (String name : machine.getOwnData().getATRIBUTTES().keySet()) {
                    Serializable value = machine.getOwnData().getATRIBUTTES().get(name);
                    if (value instanceof Boolean bool) {
                        bool = ran.nextBoolean();
                        machine.getOwnData().getATRIBUTTES().replace(name, bool);
                    } else if (value instanceof Double[] array) {
                        array[2] = (ran.nextDouble() * 120 - 20); // Random temperature between -20 and 100
                        machine.getOwnData().getATRIBUTTES().replace(name, array);
                    }
                }
                // Notify the mediator of the updated data
                machine.changeData(machine.getOwnData());
            }, (long) (Math.random() * 5), TimeUnit.SECONDS);
        }
    }

    /**
     * Method to add a new machine to the system.
     * This method also subscribes the new machine to the mediator.
     *
     * @param mac The machine to be added.
     */
    public static void addMachine(Machine mac) {
        MACHINES.add(mac);
        mac.subscribe(MEDIATOR);
    }
}

//    public static void removeWelder(String name) {
//        for ( Welder welder : WELD_MACHINES ) {
//            if (name.equals(welder.getMachineName())) {
//                WELD_MACHINES.remove(welder);
//                welder.unsubscribe(MEDIATOR);
//            }
//        }
//    }
