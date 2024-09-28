package panel;

import data.Data;
import data.DataMachine;
import data.DataLathe;
import data.DataWelder;
import factory.WeldMachineMaker;
import machine.Machine;
import main.Main;
import structure.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InformationPanel implements Observer {

    public static final Map<String, DataMachine> MACHINES_DATA = new HashMap<String, DataMachine>();
    private boolean screenActive = false;

    @Override
    public void update(Data dataMachine) {
        updateListOfMachines((DataMachine) dataMachine);
    }

    public void updateListOfMachines(DataMachine dataMachine) {
        MACHINES_DATA.put(dataMachine.getMachineName(), dataMachine);
        showData();
    }

    private void showData() {
        if (!screenActive) {
            screenActive = true;
            screen();
        } else {
            updateDataScreen();
        }
    }

    // Criação da tela

    private static final JList<String> WELDERS_LIST = new JList<>();
    private static final JList<String> LATHES_LIST = new JList<>();

    private void screen() {

        /// Elementos da tela de lista de máquinas

        //Cria um título para a página de Welders
        JLabel tituloWelder = new JLabel("Subscribed Welders");
        tituloWelder.setFont(tituloWelder.getFont().deriveFont(24.0f));
        tituloWelder.setBounds((700 - tituloWelder.getPreferredSize().width) / 2, 20, 300, 50);

        //Cria um título para a página de Lathes
        JLabel tituloLathe = new JLabel("Subscribed Lathes");
        tituloLathe.setFont(tituloLathe.getFont().deriveFont(24.0f));
        tituloLathe.setBounds((700 - tituloLathe.getPreferredSize().width) / 2, 20, 300, 50);


        //Create the button of the page of Welders
        JButton buttonWelder = new JButton("Subscribe a new welder");
        buttonWelder.setBounds( 380 , 660, 250, buttonWelder.getPreferredSize().height);

//        JButton buttonRemoveWelder = new JButton( "Remove a welder");
//        buttonRemoveWelder.setBounds(50 , 660, 250, buttonWelder.getPreferredSize().height);

        //Create the button of the page of Welders
        JButton buttonLathe = new JButton("Subscribe a new lathe");
        buttonLathe.setBounds( 380 , 660, 250, buttonLathe.getPreferredSize().height);

        // Jlabel vazio para solucionar erro
        JLabel erroWelder = new JLabel();
        JLabel erroLathe = new JLabel();


        /// Cria os elementos da JList de Welders
        WELDERS_LIST.setModel(getMachinesData("DataWelder"));
        WELDERS_LIST.setCellRenderer(new MyListCellRenderer());

        WELDERS_LIST.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPaneWelder = new JScrollPane();
        scrollPaneWelder.setBounds(50, 120, 580, 500);
        scrollPaneWelder.setViewportView(WELDERS_LIST);
        scrollPaneWelder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /// Cria os elementos da JList de Lathes
        LATHES_LIST.setModel(getMachinesData("DataLathe"));
        LATHES_LIST.setCellRenderer(new MyListCellRenderer());

        LATHES_LIST.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPaneLathe = new JScrollPane();
        scrollPaneLathe.setBounds(50, 120, 580, 500);
        scrollPaneLathe.setViewportView(LATHES_LIST);
        scrollPaneLathe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /// Criação da tela com JDialog

        // Cria um JDialog para a aplicação
        JDialog dialog = new JDialog();
//        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialog.setTitle("All operating machines");
        dialog.setSize(700, 800);
        dialog.setLocation( 200 , 150 );
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        dialog.addWindowListener(new WindowAdapter() {
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });

        // Cria um JTabbedPane para a aplicação
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(700,800);

        // Cria um JPanel para a aba de Welders
        JPanel panelWelders = new JPanel();
        panelWelders.setLayout( null );
        panelWelders.setSize(700, 800);
        panelWelders.setBackground(Color.white);

        // Adiciona os elementos na aba de welders
        panelWelders.add(tituloWelder);
        panelWelders.add(scrollPaneWelder);
        panelWelders.add(buttonWelder);
//        panelWelders.add(buttonRemoveWelder);
        panelWelders.add(erroWelder);

        // Cria um JPanel para a aba de Lathes
        JPanel panelLathes = new JPanel();
        panelLathes.setLayout( null );
        panelLathes.setSize(700, 800);
        panelLathes.setBackground(Color.white);

        // Adiciona os elementos na aba de welders
        panelLathes.add(tituloLathe);
        panelLathes.add(scrollPaneLathe);
        panelLathes.add(buttonLathe);
        panelLathes.add(erroLathe);

        // Adiciona as abas no JDialog
        tabbedPane.add("Welders", panelWelders);
        tabbedPane.add("Lathes", panelLathes);
        dialog.add(tabbedPane);

        // Exibe a tela
        dialog.setVisible(true);

        buttonWelder.addActionListener(e -> {
            WeldMachineMaker.getInstance().machineCreation();
        });

//        buttonRemoveWelder.addActionListener(e -> {
//
//            if (WELDERS_LIST.getSelectedIndex() != -1) {
//                MACHINES_DATA.remove(WELDERS_LIST.getSelected);
//            }
//            System.out.println(WELDERS_LIST.getComponentCount());
//            getMachinesData("
//            popupRemove(  );
//
//        });
        buttonLathe.addActionListener(e -> {
            //LatheMachineMaker.getInstance().machineCreation();
        });

    }

//    private void popupRemove(Machine machine) {
//        JLabel name = new JLabel ( "Are you sure you want to remove " + machine.getMachineName() + "?" );
//
//        int option = 0;
//
//        option = JOptionPane.showConfirmDialog
//                ( null , name , "Confirm exclude" , JOptionPane.DEFAULT_OPTION , JOptionPane.WARNING_MESSAGE );
//
//        if ( option == -1 ) {
//            return;
//        }
//
//        Main.removeWelder( machine.getMachineName() );
//    }

    private void updateDataScreen() {
        WELDERS_LIST.setModel(getMachinesData("DataWelder"));
        WELDERS_LIST.setCellRenderer(new MyListCellRenderer());
        LATHES_LIST.setModel(getMachinesData("DataLathe"));
        LATHES_LIST.setCellRenderer(new MyListCellRenderer());
    }


    private DefaultListModel getMachinesData(String className) {
        // Inicia a lista de máquinas
        DefaultListModel listModel = new DefaultListModel<>();

        for (Map.Entry<String, DataMachine> entry : MACHINES_DATA.entrySet()) {
            String key = entry.getKey();
            DataMachine value = entry.getValue();

            if(value.getClass().getSimpleName().equals(className)) {
                listModel.addElement(value);
            }
        }
        return listModel;
    }

    private class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof DataLathe lathe) {
                setText(
                        "<html>\u200E<br/>"+
                        "Machine name: " + lathe.getMachineName() +
                        "<br/>Temperature: " + (lathe.getTemperature() < lathe.getParameters()[0] || lathe.getTemperature() > lathe.getParameters()[1] ? "<span style=\"color: #FF0000\">" + lathe.getTemperature() + "</span>" : "<span style=\"color: #00DD00\">" + lathe.getTemperature()) + "</span>" +
                        "<br/>Rotation Speed: " + (lathe.getRotationSpeed() > lathe.getParameters()[2] ? "<span style=\"color: #FF0000\">" + lathe.getRotationSpeed() + "</span>" : "<span style=\"color: #00DD00\">" + lathe.getRotationSpeed()) + "</span>" +
                        "<br/>Collision: " + (lathe.isCollision() ? "<span style=\"color: #FF0000\">" + lathe.isCollision() + "</span>" : "<span style=\"color: #00DD00\">" +  lathe.isCollision()) + "</span>" + "<br/>\u200E"
                );
            } else if (value instanceof DataWelder welder) {
                setText(
                        "<html>\u200E<br/>"+
                        "Machine name: " + welder.getMachineName() +
                        "<br/>Temperature: " + (welder.getTemperature() < welder.getParameters()[0] || welder.getTemperature() > welder.getParameters()[1] ? "<span style=\"color: #FF0000\">" + welder.getTemperature() + "</span>" : "<span style=\"color: #00DD00\">" + welder.getTemperature()) + "</span>" +
                        "<br/>Current: " + (welder.getCurrent()  > welder.getParameters()[2] ? "<span style=\"color: #FF0000\">" + welder.getCurrent() + "</span>" : "<span style=\"color: #00DD00\">" + welder.getCurrent()) + "</span>" +
                        "<br/>Active time: " + (welder.getActiveTime()  > welder.getParameters()[3] ? "<span style=\"color: #FF0000\">" + welder.getActiveTime() + "</span>" : "<span style=\"color: #00DD00\">" + welder.getActiveTime()) + "</span>" + "<br/>\u200E"
                );
            }

            return this;
        }

    }

}
