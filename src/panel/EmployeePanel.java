package panel;

import data.Data;
import data.DataEmployee;
import structure.Observer;

import javax.swing.*;
import java.util.*;

public class EmployeePanel implements Observer {

    private static final Map<String, DataEmployee> EMPLOYEES_LOG_ERRORS = new HashMap<>();

    @Override
    public void update(Data data) {

    }

//  Criação da tela
      private static final JList<String> MANAGERS_LOG_ERRORS = new JList<>();
      private static final JList<String> OPERATORS_LOG_ERRORS = new JList<>();

    private void screen() {
        /// Elementos da tela de lista de Employees

        //Cria um título para a página de Operators
        JLabel tituloOperator = new JLabel("Subscribed Operators");
        tituloOperator.setFont(tituloOperator.getFont().deriveFont(24.0f));
        tituloOperator.setBounds((700 - tituloOperator.getPreferredSize().width) / 2, 20, 300, 50);

        //Cria um título para a página de Managers
        JLabel tituloManager = new JLabel("Subscribed Managers");
        tituloManager.setFont(tituloManager.getFont().deriveFont(24.0f));
        tituloManager.setBounds((700 - tituloManager.getPreferredSize().width) / 2, 20, 300, 50);

        // Jlabel vazio para solucionar erro
        JLabel erroOperator = new JLabel();
        JLabel erroManager = new JLabel();

        /// Cria os elementos da JList de Operators
        OPERATORS_LOG_ERRORS.setModel(getMachinesData("DataWelder"));
        OPERATORS_LOG_ERRORS.setCellRenderer(new InformationPanel.MyListCellRenderer());

        OPERATORS_LOG_ERRORS.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPaneWelder = new JScrollPane();
        scrollPaneWelder.setBounds(50, 120, 580, 500);
        scrollPaneWelder.setViewportView(OPERATORS_LOG_ERRORS);
        scrollPaneWelder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /// Cria os elementos da JList de Lathes
        MANAGERS_LOG_ERRORS.setModel(getMachinesData("DataLathe"));
        MANAGERS_LOG_ERRORS.setCellRenderer(new InformationPanel.MyListCellRenderer());

        MANAGERS_LOG_ERRORS.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPaneLathe = new JScrollPane();
        scrollPaneLathe.setBounds(50, 120, 580, 500);
        scrollPaneLathe.setViewportView(MANAGERS_LOG_ERRORS);
        scrollPaneLathe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

}
