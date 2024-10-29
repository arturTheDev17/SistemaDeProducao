package panel;

import factory.MachineMaker;
import machine.Machine;
import main.Main;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represents a panel for adding new machines to the system.
 * It provides a graphical user interface (GUI) for users to input
 * the machine's name and its attributes, including the option to set
 * attributes as boolean or numerical values.
 */
public class AddMachinePanel {

    // Flag to determine if the attribute is of boolean type
    private static boolean isBooleanValue = false;
    // Variable to hold the name of the machine
    private static String nameMachine;
    // HashMap to store attributes of the machine
    private static final HashMap<String, Serializable> attributes = new HashMap<>();

    /**
     * Method to display the GUI for adding a new machine.
     */
    public static void screen() {
        // Page to add the name of the machine
        JLabel nameMachineText = new JLabel("Insert the name of the machine:");
        nameMachineText.setBounds((600 - nameMachineText.getPreferredSize().width) / 2, 100, 300, 50);

        JTextField nameMachineInput = new JTextField();
        nameMachineInput.setColumns(20);
        nameMachineInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        nameMachineInput.setBounds((600 - nameMachineInput.getPreferredSize().width) / 2, 150,
                nameMachineInput.getPreferredSize().width,
                nameMachineInput.getPreferredSize().height);

        JButton buttonToSaveMachineName = new JButton("Save name");
        buttonToSaveMachineName.setBounds((600 - buttonToSaveMachineName.getPreferredSize().width) / 2,
                200,
                buttonToSaveMachineName.getPreferredSize().width,
                buttonToSaveMachineName.getPreferredSize().height);

        // Page to add attributes
        JLabel title = new JLabel("");
        title.setFont(title.getFont().deriveFont(24.0f));

        JLabel attributeName = new JLabel("Attribute name:");
        attributeName.setBounds(50, 80, attributeName.getPreferredSize().width, attributeName.getPreferredSize().height);

        JTextField attributeNameInput = new JTextField();
        attributeNameInput.setColumns(20);
        attributeNameInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        attributeNameInput.setBounds(50, 105, attributeNameInput.getPreferredSize().width,
                attributeNameInput.getPreferredSize().height);

        JLabel attributeMin = new JLabel("Attribute min value:");
        attributeMin.setBounds(50, 160, attributeMin.getPreferredSize().width, attributeMin.getPreferredSize().height);

        JTextField attributeMinInput = new JTextField();
        attributeMinInput.setColumns(20);
        attributeMinInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        attributeMinInput.setBounds(50, 185, attributeMinInput.getPreferredSize().width,
                attributeMinInput.getPreferredSize().height);

        JLabel attributeMax = new JLabel("Attribute max value:");
        attributeMax.setBounds(320, 160, attributeMax.getPreferredSize().width, attributeMax.getPreferredSize().height);

        JTextField attributeMaxInput = new JTextField();
        attributeMaxInput.setColumns(20);
        attributeMaxInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        attributeMaxInput.setBounds(320, 185, attributeMaxInput.getPreferredSize().width,
                attributeMaxInput.getPreferredSize().height);

        JCheckBox booleanTypeCheck = new JCheckBox("Boolean value?");
        booleanTypeCheck.setBounds(50, 225, booleanTypeCheck.getPreferredSize().width, booleanTypeCheck.getPreferredSize().height);

        JButton addAttributeButton = new JButton("Add the new attribute");
        addAttributeButton.setBounds(50, 280, 200, addAttributeButton.getPreferredSize().height);

        JButton finishMachineButton = new JButton("Finish machine");
        finishMachineButton.setBounds(320, 280, 200, finishMachineButton.getPreferredSize().height);

        // Create the dialog for the panel
        JDialog dialog = new JDialog();
        dialog.setTitle("Add New Machine");
        dialog.setSize(600, 400);
        dialog.setLocation(200, 150);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add components to the dialog
        dialog.add(nameMachineText);
        dialog.add(nameMachineInput);
        dialog.add(buttonToSaveMachineName);

        // Display the dialog
        dialog.setVisible(true);

        // Action listener for saving the machine name
        buttonToSaveMachineName.addActionListener(e -> {
            attributes.clear();
            dialog.setVisible(false);
            nameMachine = nameMachineInput.getText();
            title.setText(nameMachine);
            title.setBounds((600 - title.getPreferredSize().width) / 2, 20, 300, 50);

            // Remove initial components and add attributes components
            dialog.remove(nameMachineText);
            dialog.remove(nameMachineInput);
            dialog.remove(buttonToSaveMachineName);

            dialog.add(title);
            dialog.add(attributeName);
            dialog.add(attributeNameInput);
            dialog.add(attributeMin);
            dialog.add(attributeMinInput);
            dialog.add(attributeMax);
            dialog.add(attributeMaxInput);
            dialog.add(booleanTypeCheck);
            dialog.add(addAttributeButton);
            dialog.add(finishMachineButton);
            dialog.setVisible(true);
        });

        // Action listener for toggling boolean attribute input
        booleanTypeCheck.addActionListener(e -> {
            isBooleanValue = !isBooleanValue;

            if (isBooleanValue) {
                attributeMinInput.setEnabled(false);
                attributeMinInput.setBackground(Color.decode("#DDDDDD"));
                attributeMaxInput.setEnabled(false);
                attributeMaxInput.setBackground(Color.decode("#DDDDDD"));
            } else {
                attributeMinInput.setEnabled(true);
                attributeMinInput.setBackground(Color.decode("#FFFFFF"));
                attributeMaxInput.setEnabled(true);
                attributeMaxInput.setBackground(Color.decode("#FFFFFF"));
            }
        });

        // Action listener for adding an attribute
        addAttributeButton.addActionListener(e -> {
            if (isBooleanValue) {
                if (attributeNameInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "The attribute name is empty", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    attributes.put(attributeNameInput.getText(), false);
                    JOptionPane.showMessageDialog(dialog, "The attribute " + attributeNameInput.getText() + " was added");
                    attributeNameInput.setText("");
                    attributeMinInput.setText("");
                    attributeMaxInput.setText("");
                }
            } else {
                boolean valueInvalid = true;
                if (attributeNameInput.getText().isEmpty() || attributeMinInput.getText().isEmpty() || attributeMaxInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "At least one attribute is empty", "", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    Double.parseDouble(attributeMinInput.getText());
                    Double.parseDouble(attributeMaxInput.getText());
                    valueInvalid = false;
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(dialog, "Min or Max value is invalid", "", JOptionPane.ERROR_MESSAGE);
                }

                if (!valueInvalid) {
                    attributes.put(attributeNameInput.getText(), new Double[]{Double.valueOf(attributeMinInput.getText()), Double.valueOf(attributeMaxInput.getText()), 0.});
                    JOptionPane.showMessageDialog(dialog, "The attribute " + attributeNameInput.getText() + " was added");
                    attributeNameInput.setText("");
                    attributeMinInput.setText("");
                    attributeMaxInput.setText("");
                }
            }
        });

        // Action listener for finishing machine creation
        finishMachineButton.addActionListener(e -> {
            Machine newMachine = MachineMaker.getInstance().newMachine(nameMachine, attributes);
            Main.addMachine(newMachine);
            dialog.setVisible(false);
        });
    }
}
