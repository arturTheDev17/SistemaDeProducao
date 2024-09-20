package factory;

import main.Main;
import machine.Machine;
import machine.Welder;
import javax.swing.*;

public class WeldMachineMaker {
    public static Machine newMachine() {
        return new Welder();
    }

    public static Machine newMachine( String name , double minTemperature , double maxTemperature , double nominalCurrent , double activeTimeThreshold ) {
        return new Welder( name , minTemperature , maxTemperature , nominalCurrent , activeTimeThreshold );
    }

    public static Welder welderCreation ( ) {
        JLabel 	name = new JLabel ( "Please insert the welder's name: " ) ,
                nominalCurrent = new JLabel( "Please insert the CURRENT value given by the factory: " ),
                minTemperature = new JLabel( "Please insert the MINIMUM temperature handled by the machine: " ),
                maxTemperature = new JLabel( "Please insert the MAXIMUM temperature handled by the machine: " ),
                activeTime = new JLabel( "Please insert the active time THRESHOLD of this machine: (minutes)" );
        JTextField 	tfName = new JTextField (),
                tfCurrent = new JTextField () ,
                tfMinTemp = new JTextField() ,
                tfMaxTemp = new JTextField() ,
                tfActiveTime = new JTextField();
        Object[] objects =
                { name , tfName , nominalCurrent , tfCurrent , minTemperature , tfMinTemp , maxTemperature, tfMaxTemp , activeTime , tfActiveTime };

        int option = 0;

        while ( option != -1 && ( tfName.getText().isBlank() || !tfCurrent.getText().matches( "^[0-9]+$" ) ||
                !tfMinTemp.getText().matches( "^-?\\d+$" ) || !tfMaxTemp.getText().matches( "^[0-9]+$" ) || !tfActiveTime.getText().matches( "^[0-9]+$" ) ) ) {
                option = JOptionPane.showConfirmDialog
                    ( null , objects , "Cadastrar Motor elétrico WEG®" , JOptionPane.DEFAULT_OPTION , JOptionPane.DEFAULT_OPTION );
        }

        if ( option == -1 ) {
            return null;
        }
        Main.addWelder((Welder)newMachine( tfName.getText() , Double.parseDouble( tfMinTemp.getText()) , Double.parseDouble(tfMaxTemp.getText()) , Double.parseDouble(tfCurrent.getText()) , Double.parseDouble(tfActiveTime.getText()) ));
        return (Welder)newMachine( tfName.getText() , Double.parseDouble( tfMinTemp.getText()) , Double.parseDouble(tfMaxTemp.getText()) , Double.parseDouble(tfCurrent.getText()) , Double.parseDouble(tfActiveTime.getText()) );
    }




}
