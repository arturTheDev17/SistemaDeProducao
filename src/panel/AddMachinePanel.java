package panel;

import machine.Machine;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class AddMachinePanel {

    private Machine machine;
    public Machine screen() {
        JLabel title = new JLabel("Subscribed Welders");
        title.setFont(title.getFont().deriveFont(24.0f));
        title.setBounds((700 - title.getPreferredSize().width) / 2, 20, 300, 50);

        JLabel attributeName = new JLabel("Attribute name:");
        attributeName.setBounds( 100 , 80, attributeName.getPreferredSize().width, attributeName.getPreferredSize().height);
        JTextField attributeNameInput = new JTextField();
        attributeNameInput.setColumns(20);
        attributeNameInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        attributeNameInput.setBounds( 100 , 105, attributeNameInput.getPreferredSize().width, attributeNameInput.getPreferredSize().height);

        JLabel attributeMin = new JLabel("Attribute min value:");
        attributeMin.setBounds( 100 , 80, attributeMin.getPreferredSize().width, attributeMin.getPreferredSize().height);
        JTextField attributeMinInput = new JTextField();
        attributeMinInput.setColumns(20);
        attributeMinInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        attributeMinInput.setBounds( 100 , 105, attributeMinInput.getPreferredSize().width, attributeMinInput.getPreferredSize().height);

        JLabel attributeMax = new JLabel("Attribute min value:");
        attributeMax.setBounds( 100 , 80, attributeMax.getPreferredSize().width, attributeMax.getPreferredSize().height);
        JTextField attributeMaxInput = new JTextField();
        attributeMaxInput.setColumns(20);
        attributeMaxInput.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
        attributeMaxInput.setBounds( 100 , 105, attributeMaxInput.getPreferredSize().width, attributeMaxInput.getPreferredSize().height);

        JButton addAttributeButton = new JButton("Add a new attribute");
        addAttributeButton.setBounds( 380 , 660, 250, addAttributeButton.getPreferredSize().height);

        JButton addAttributeButton = new JButton("Add a new attribute");
        addAttributeButton.setBounds( 380 , 660, 250, addAttributeButton.getPreferredSize().height);
    }

    private Machine getMachine() {
        return machine;
    }

    private void setMachine(Machine machine) {
        this.machine = machine;
    }
}
