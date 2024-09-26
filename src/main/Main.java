package main;

import data.DataLathe;
import data.DataWelder;
import factory.LatheMachineMaker;
import factory.WeldMachineMaker;
import machine.Lathe;
import machine.Welder;
import panel.InformationPanel;
import structure.Mediator;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final LatheMachineMaker LATHE_MACHINE_MAKER = new LatheMachineMaker();
    private static final WeldMachineMaker WELD_MACHINE_MAKER = new WeldMachineMaker();
    private static final ArrayList<Welder> WELD_MACHINES = new ArrayList<>();
    private static final ArrayList<Lathe> LATHE_MACHINES = new ArrayList<>();
    private static Mediator MEDIATOR = Mediator.getInstance();

    public static void main(String[] args) throws InterruptedException {
        MEDIATOR = MEDIATOR.getInstance();

        //Criar as maquinas
        for (int i = 0; i < 5; i++) {
            WELD_MACHINES.add((Welder) WeldMachineMaker.newMachine());
            WELD_MACHINES.get(i).subscribe( MEDIATOR );
        }
//
//        for (int i = 0; i < 5; i++) {
//            LATHE_MACHINES.add((Lathe) LATHE_MACHINE_MAKER.newMachine());
//            LATHE_MACHINES.get(i).subscribe( MEDIATOR );
//            LATHE_MACHINES.get(i).changeData(new DataLathe("Lathe " + i, 30, 3000, false));
//        }

        MEDIATOR.subscribe( new InformationPanel());
        //gerador de dados aleatorios
        while (true) {
            randomizerWelderDataAsync();
//            randomizerLatheDataAsync();
            Thread.sleep(5000);
        }
    }

    private static void randomizerWelderDataAsync() {
        Random ran = new Random();
        for ( Welder welder : WELD_MACHINES ) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                double temperature = (ran.nextDouble()*120 - 20);
                double current = (ran.nextDouble()*200);
                double activeTime = (ran.nextDouble()*120);

                welder.changeData( new DataWelder( new double[]{} , welder.getMachineName() , temperature , current, activeTime ) );
            }, (long) (Math.random() * 5), TimeUnit.SECONDS);

        }
    }

    private static void randomizerLatheDataAsync() {
        Random ran = new Random();
        for ( Lathe lathe : LATHE_MACHINES ) {

            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.schedule(() -> {
                double temperature = (ran.nextDouble()*120 - 20);
                int rotationSpeed = (ran.nextInt(10000));
                boolean collision = (ran.nextBoolean());

                lathe.changeData( new DataLathe( temperature , rotationSpeed , collision ) );
            }, (long) (Math.random() * 5), TimeUnit.SECONDS);
        }
    }

    public static void addWelder(Welder welder) {
        WELD_MACHINES.add(welder);
        welder.subscribe(MEDIATOR);
    }

}
