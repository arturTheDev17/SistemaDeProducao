import data.Data;
import data.DataLathe;
import data.DataWelder;
import factory.LatheMachineMaker;
import factory.MachineMaker;
import factory.WeldMachineMaker;
import machine.Lathe;
import machine.Welder;
import panel.InformationPanel;
import structure.Mediator;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static final MachineMaker LATHE_MACHINE_MAKER = new LatheMachineMaker();
    private static final MachineMaker WELD_MACHINE_MAKER = new WeldMachineMaker();
    private static final ArrayList<Welder> WELD_MACHINES = new ArrayList<>();
    private static final ArrayList<Lathe> LATHE_MACHINES = new ArrayList<>();
    private static Mediator MEDIATOR = new Mediator();

    public static void main(String[] args) throws InterruptedException {
        MEDIATOR = MEDIATOR.getInstance();

        //Criar as maquinas
        for (int i = 0; i < 5; i++) {
            WELD_MACHINES.add((Welder) WELD_MACHINE_MAKER.newMachine());
            WELD_MACHINES.get(i).subscribe( MEDIATOR );
            WELD_MACHINES.get(i).changeData(new DataWelder("Welder " + i, 30.0, 100, 0 ));
        }

        for (int i = 0; i < 5; i++) {
            LATHE_MACHINES.add((Lathe) LATHE_MACHINE_MAKER.newMachine());
            LATHE_MACHINES.get(i).subscribe( MEDIATOR );
            //TODO machinename não ta mais null vei
            LATHE_MACHINES.get(i).changeData(new DataLathe("Lathe " + i, 30, 3000, false));
        }

        MEDIATOR.subscribe( new InformationPanel());
        //gerador de dados aleatorios
        while (true) {
            randomizerLatheData();
            randomizerWelderData();
            Thread.sleep(10000);
        }
    }

    private static void randomizerLatheData() {
        Random ran = new Random();
        for ( Lathe lathe : LATHE_MACHINES ) {

//            double temperature = (ran.nextDouble(-20.0 , 100.0));
            double temperature = (ran.nextDouble()*120 - 20);
            int rotationSpeed = (ran.nextInt(10000));
            boolean collision = (ran.nextBoolean());

            lathe.changeData( new DataLathe( temperature , rotationSpeed , collision ) );
        }
    }

    private static void randomizerWelderData() {
        Random ran = new Random();
        for ( Welder welder : WELD_MACHINES ) {

//            double temperature = (ran.nextDouble(-20.0 , 100.0));
            double temperature = (ran.nextDouble()*120 - 20);
//            double current = (ran.nextDouble(200.0));
            double current = (ran.nextDouble()*200);
            double activeTime = (ran.nextDouble());

            welder.changeData( new DataWelder( temperature , current, activeTime ) );
        }
    }
}
