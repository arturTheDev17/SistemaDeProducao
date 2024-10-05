package panel;

import data.Data;
import data.DataMachine;
import structure.Observer;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The InformationPanel class implements the Observer interface and is responsible
 * for displaying information about subscribed machines. It updates the UI when new
 * data about machines is received.
 */
public class InformationPanel implements Observer {

    // Map to store data of machines, keyed by machine name
    public static final Map<String, DataMachine> MACHINES_DATA = new HashMap<>();

    // Flag to track if the UI screen is active
    private boolean screenActive = false;

    /**
     * Updates the panel with new data received from the observable.
     *
     * @param dataMachine The new data to update the panel with.
     */
    @Override
    public void update(Data dataMachine) {
        updateListOfMachines((DataMachine) dataMachine);
    }

    /**
     * Updates the internal list of machines with new data.
     *
     * @param dataMachine The DataMachine object containing the machine information.
     */
    public void updateListOfMachines(DataMachine dataMachine) {
        MACHINES_DATA.put(dataMachine.getMachineName(), dataMachine);
        showData();
    }

    /**
     * Displays the data on the screen, either by creating a new screen or updating the existing one.
     */
    private void showData() {
        if (!screenActive) {
            screenActive = true;
            screen();
        } else {
            updateDataScreen();
        }
    }

    // JList to display the machine data
    private static final JList<String> MACHINES_LIST = new JList<>();

    /**
     * Creates and displays the user interface for the Information Panel.
     */
    private void screen() {
        // Title for the machines list page
        JLabel tituloMachine = new JLabel("Subscribed Machines");
        tituloMachine.setFont(tituloMachine.getFont().deriveFont(24.0f));
        tituloMachine.setBounds((700 - tituloMachine.getPreferredSize().width) / 2, 20, 300, 50);

        // Button to subscribe a new machine
        JButton buttonMachine = new JButton("Subscribe a new machine");
        buttonMachine.setBounds(380, 660, 250, buttonMachine.getPreferredSize().height);

        // Placeholder label for error messages
        JLabel erroMachine = new JLabel();

        // Set up the machine list display
        MACHINES_LIST.setBackground(new java.awt.Color(220, 220, 255));
        JScrollPane scrollPaneMachine = new JScrollPane();
        scrollPaneMachine.setBounds(50, 120, 580, 500);
        scrollPaneMachine.setViewportView(MACHINES_LIST);
        scrollPaneMachine.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a dialog for the application
        JDialog dialog = new JDialog();
        dialog.setTitle("All operating machines");
        dialog.setSize(700, 800);
        dialog.setLocation(200, 150);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add components to the dialog
        dialog.add(tituloMachine);
        dialog.add(scrollPaneMachine);
        dialog.add(buttonMachine);
        dialog.add(erroMachine);

        // Display the dialog
        dialog.setVisible(true);

        // Action listener for the subscribe button
        buttonMachine.addActionListener(e -> {
            AddMachinePanel.screen();
        });
    }

    /**
     * Updates the data displayed in the machines list.
     */
    private void updateDataScreen() {
        MACHINES_LIST.setModel(getMachinesData());
        MACHINES_LIST.setCellRenderer(new MyListCellRenderer());
    }

    /**
     * Retrieves the machine data and prepares it for display in the JList.
     *
     * @return A DefaultListModel containing the machine data entries.
     */
    private DefaultListModel getMachinesData() {
        // Initialize the list of machines
        DefaultListModel listModel = new DefaultListModel<>();

        for (Map.Entry<String, DataMachine> entry : MACHINES_DATA.entrySet()) {
            DataMachine value = entry.getValue();
            listModel.addElement(value);
        }
        return listModel;
    }

    /**
     * Custom renderer for the JList to format the display of machine data.
     */
    private class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Format the machine data for display
            if (value instanceof DataMachine machine) {
                StringBuilder builder = new StringBuilder();
                for (String name : machine.getATRIBUTTES().keySet()) {
                    Serializable thisValue = machine.getATRIBUTTES().get(name);

                    // Format boolean attributes
                    if (thisValue instanceof Boolean bool) {
                        builder.append("<br/>" + name + ": " +
                                (bool ?
                                        "<span style=\"color: #FF0000\">" + bool + "</span>" :
                                        "<span style=\"color: #00DD00\">" + bool + "</span>"));
                    }
                    // Format double array attributes
                    else if (thisValue instanceof Double[] array) {
                        builder.append("<br/>" + name + ": " +
                                (array[2] < array[0] || array[2] > array[1] ?
                                        "<span style=\"color: #FF0000\">" + (double) (Math.round(array[2] * 100)) / 100 + "</span>" :
                                        "<span style=\"color: #00DD00\">" + (double) (Math.round(array[2] * 100)) / 100 + "</span>"));
                    }
                }

                // Set the text for the list item
                setText(
                        "<html>\u200E<br/>" +
                                "Machine name: " + machine.getMachineName() + builder + "<br/>\u200E"
                );
            }

            return this;
        }
    }
}
