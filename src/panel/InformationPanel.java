package panel;

import data.Data;
import data.DataLathe;
import data.DataWelder;
import structure.ObserverData;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InformationPanel implements ObserverData {

    public static final Map<String, Data> MACHINES_DATA = new HashMap<String, Data>();
    private boolean screenActive = false;

    @Override
    public void update(Data data) {
        updateListOfMachines(data);
    }

    public void updateListOfMachines(Data data) {
        MACHINES_DATA.put(data.getMachineName(), data);
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
        dialog.setTitle("Lista de Maquinas");
        dialog.setSize(700, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
            dialog.setVisible(false);
            dialog.dispose();
        });
    }

    private void updateDataScreen() {
        WELDERS_LIST.setModel(getMachinesData("DataWelder"));
        WELDERS_LIST.setCellRenderer(new MyListCellRenderer());
        LATHES_LIST.setModel(getMachinesData("DataLathe"));
        LATHES_LIST.setCellRenderer(new MyListCellRenderer());
    }


    private static DefaultListModel getMachinesData(String className) {
        // Inicia a lista de máquinas
        DefaultListModel listModel = new DefaultListModel<>();

        for(Map.Entry<String, Data> entry : MACHINES_DATA.entrySet()) {
            String key = entry.getKey();
            Data value = entry.getValue();

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
                        "<br/>Temperature: " + (lathe.getTemperature() > 60 ? "<span style=\"color: #FF0000\">" + lathe.getTemperature() + "</span>" : "<span style=\"color: #00DD00\">" + lathe.getTemperature()) + "</span>" +
                        "<br/>Rotation Speed: " + (lathe.getRotationSpeed() > 6000 ? "<span style=\"color: #FF0000\">" + lathe.getRotationSpeed() + "</span>" : "<span style=\"color: #00DD00\">" + lathe.getRotationSpeed()) + "</span>" +
                        "<br/>Collision: " + (lathe.isCollision() ? "<span style=\"color: #FF0000\">" + lathe.isCollision() + "</span>" : "<span style=\"color: #00DD00\">" +  lathe.isCollision()) + "</span>" + "<br/>\u200E"
                );
            } else if (value instanceof DataWelder welder) {
                setText(
                        "<html>\u200E<br/>"+
                        "Machine name: " + welder.getMachineName() +
                        "<br/>Temperature: " + (welder.getTemperature() > 60 ? "<span style=\"color: #FF0000\">" + welder.getTemperature() + "</span>" : "<span style=\"color: #00DD00\">" + welder.getTemperature()) + "</span>" +
                        "<br/>Current: " + (welder.getCurrent()  > 120 ? "<span style=\"color: #FF0000\">" + welder.getCurrent() + "</span>" : "<span style=\"color: #00DD00\">" + welder.getCurrent()) + "</span>" +
                        "<br/>Active time: " + (welder.getActiveTime()  > 60 ? "<span style=\"color: #FF0000\">" + welder.getActiveTime() + "</span>" : "<span style=\"color: #00DD00\">" + welder.getActiveTime()) + "</span>" + "<br/>\u200E"
                );
            }

            return this;
        }

    }

}
