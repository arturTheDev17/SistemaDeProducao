package factory;

import machine.Machine;
import machine.Welder;
import javax.swing.*;

public class WeldMachineMaker implements MachineMaker {
    @Override
    public Machine newMachine() {
        return new Welder();
    }
    public static Welder welderCreation ( ) {
        JLabel 	name = new JLabel ( "Please insert the welder's name: " ) ,
                nominalCurrent = new JLabel( "Please insert the current value given by the factory: " ),
                minTemperature = new JLabel( "Please insert the minimum temperature handled by the machine: " ),
                maxTemperature = new JLabel( "Please insert the minimum temperature handled by the machine: " ),
                ;
        JTextField 	tfNome = new JTextField (),
                tfPreco = new JTextField () ,
                tfPotencia = new JTextField() ,
                tfTipoEnrolamento = new JTextField(),
                tfEficiencia = new JTextField(),
                tfTensao = new JTextField();

        Object[] juntador =
                { name , tfNome , preco , tfPreco , potencia , tfPotencia , tipoEnrolamento,
                        tfTipoEnrolamento, eficiencia, tfEficiencia , tensao , tfTensao };

        int teste = 0;

        while ( teste != -1 && ( tfNome.getText().isBlank() || !tfPreco.getText().matches( "^[0-9]+$" ) ||
                !tfPotencia.getText().matches( "^[0-9]+$" ) || !tfTensao.getText().matches( "^[0-9]+$" ) ||
                tfTipoEnrolamento.getText().isBlank() || !tfEficiencia.getText().matches( "^[0-9]+$" ) ) ) {

            teste = JOptionPane.showConfirmDialog
                    ( null , juntador , "Cadastrar Motor elétrico WEG®" , JOptionPane.DEFAULT_OPTION , JOptionPane.DEFAULT_OPTION );
        }

        if ( teste == -1 ) {
            return null;
        }
        Welder maquina = new Welder (    );
//        Welder produto = new Welder (
//                tfNome.getText() ,
//                "Motor Elétrico",
//                Float.parseFloat( tfPotencia.getText() ),
//                Float.parseFloat( tfTensao.getText() ),
//                Float.parseFloat( tfPreco.getText() ) ,
//                tfTipoEnrolamento.getText(),
//                Float.parseFloat( tfEficiencia.getText() )
//        );

        return new Welder( tfNome.getText() ,  );
    }




}
