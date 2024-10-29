package panel;

import main.Main;

import javax.swing.*;
import java.util.ArrayList;

public class RemoveMachinePanel {

    public static void screen(ArrayList<String> arrayMaquinas) {
        JLabel title = new JLabel("Remove a machine");
        title.setFont(title.getFont().deriveFont(24.0f));
        title.setBounds((500 - title.getPreferredSize().width) / 2, 30, title.getPreferredSize().width, title.getPreferredSize().height);

        JList<String> machinesList = new JList<>(getListModel(arrayMaquinas));
        machinesList.setBackground(new java.awt.Color(220, 220, 255));
        JScrollPane scrollPaneMachine = new JScrollPane();
        scrollPaneMachine.setBounds(50, 120, 390, 300);
        scrollPaneMachine.setViewportView(machinesList);
        scrollPaneMachine.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.setBounds((500 - (buttonConfirm.getPreferredSize().width + 100)) / 2,480,buttonConfirm.getPreferredSize().width + 100,buttonConfirm.getPreferredSize().height);

        JDialog dialog = new JDialog();
        dialog.setTitle("Machines to unsubscribe");
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dialog.add(title);
        dialog.add(scrollPaneMachine);
        dialog.add(buttonConfirm);

        dialog.setVisible(true);

        buttonConfirm.addActionListener(e -> {
            String machineName = machinesList.getSelectedValue();
            Main.unsubscribeMachine(machineName);
            InformationPanel.removeMachine(machineName);
            EmployeePanel.removeMachine(machineName);
            dialog.setVisible(false);
            dialog.dispose();
        });
    }

    private static DefaultListModel getListModel(ArrayList<String> arrayList) {
        // Initialize the list of machines
        DefaultListModel listModel = new DefaultListModel<>();

        for (String nameMachine : arrayList) {
            listModel.addElement(nameMachine);
        }
        return listModel;
    }

}
