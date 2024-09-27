package factory;

import machine.Lathe;
import machine.Machine;
import machine.Welder;
import main.Main;

import javax.swing.*;

public class LatheMachineMaker extends MachineMaker {
    private static LatheMachineMaker machineMaker;

    public static LatheMachineMaker getInstance() {
        if (machineMaker == null) {
            machineMaker = new LatheMachineMaker();
        }
        return machineMaker;
    }

    public Machine newMachine( String name , double minTemperature , double maxTemperature , int rotationSpeed , boolean collision ) {
        return new Lathe( name , minTemperature , maxTemperature , rotationSpeed , collision);
    }
    public Machine newMachine() {
        return new Lathe();
    }

    @Override
    public void machineCreation() {
        JLabel name = new JLabel ( "Please insert the lathe's name: " ) ,
                maxSpeed = new JLabel( "Please insert the MAXIMUM ROTATION SPEED given by the factory: " ),
                minTemperature = new JLabel( "Please insert the MINIMUM temperature handled by the machine: " ),
                maxTemperature = new JLabel( "Please insert the MAXIMUM temperature handled by the machine: " );
        JTextField tfName = new JTextField (),
                tfMaxSpeed = new JTextField () ,
                tfMinTemp = new JTextField() ,
                tfMaxTemp = new JTextField() ;
        Object[] objects =
                { name , tfName , maxSpeed , tfMaxSpeed , minTemperature , tfMinTemp , maxTemperature, tfMaxTemp };

        int option = 0;

        while ( option != -1 && ( tfName.getText().isBlank() || !tfMaxSpeed.getText().matches( "^[0-9]+$" ) ||
                !tfMinTemp.getText().matches( "^-?\\d+$" ) || !tfMaxTemp.getText().matches( "^[0-9]+$" ) ) ) {
            option = JOptionPane.showConfirmDialog
                    ( null , objects , "Cadastrar uma máquina" , JOptionPane.DEFAULT_OPTION , JOptionPane.DEFAULT_OPTION );
        }

        if ( option == -1 ) {
            return;
        }
        Main.addLathe((Lathe)this.newMachine( tfName.getText() , Double.parseDouble( tfMinTemp.getText()) , Double.parseDouble(tfMaxTemp.getText()) , Integer.parseInt(tfMaxSpeed.getText()) , false));
    }


}
