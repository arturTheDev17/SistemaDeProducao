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

    private static final JList<String> listaMaquinas = new JList<>();

    private void screen() {

        /// Elementos da tela de lista de máquinas

        //Cria um título para a aplicação
        JLabel titulo = new JLabel("Lista de máquinas");
        titulo.setFont(titulo.getFont().deriveFont(24.0f));
        titulo.setBounds((1300 - titulo.getPreferredSize().width) / 2, 20, 300, 50);

        // Jlabel vazio para solucionar erro
        JLabel erro = new JLabel();

        /// Cria os elementos da JList
        listaMaquinas.setModel(getMachinesData());
        listaMaquinas.setCellRenderer(new MyListCellRenderer());

        listaMaquinas.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 80, 1180, 370);
        scrollPane.setViewportView(listaMaquinas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /// Criação da tela com JDialog

        // Cria um JDialog para a aplicação
        JDialog dialog = new JDialog();
        dialog.setTitle("Lista de Maquinas");
        dialog.setSize(1300, 700);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Adiciona os elementos na tela
        dialog.add(titulo);
        dialog.add(scrollPane);
        dialog.add(erro);

        // Exibe a tela
        dialog.setVisible(true);
    }

    private void updateDataScreen() {
        listaMaquinas.setModel(getMachinesData());
        listaMaquinas.setCellRenderer(new MyListCellRenderer());
    }


    private static DefaultListModel getMachinesData() {
        // Inicia a lista de máquinas
        DefaultListModel listModel = new DefaultListModel<>();

        for(Map.Entry<String, Data> entry : MACHINES_DATA.entrySet()) {
            String key = entry.getKey();
            Data value = entry.getValue();

            listModel.addElement(value);
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
                        "<br/>Temperature: " + lathe.getTemperature() +
                        "<br/>Rotation Speed: " + lathe.getRotationSpeed() +
                        "<br/>Collision: " + lathe.isCollision() + "<br/>\u200E"
                );
            } else if (value instanceof DataWelder welder) {
                setText(
                        "<html>\u200E<br/>"+
                        "Machine name: " + welder.getMachineName() +
                        "<br/>Temperature: " + welder.getTemperature() +
                        "<br/>Current: " + welder.getCurrent() +
                        "<br/>Active time: " + welder.getActiveTime() + "<br/>\u200E"
                );
            }

            return this;
        }

    }

}
