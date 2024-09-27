package panel;

import data.Data;
import data.DataEmployee;
import data.DataLathe;
import data.DataWelder;
import structure.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class EmployeePanel implements Observer {

    private static final Map<String, DataEmployee> EMPLOYEES_LOG_ERRORS = new HashMap<>();
    private static boolean screenActive = false;

    @Override
    public void update(Data data) {
        updateListOfLogs((DataEmployee) data);
    }

    private void updateListOfLogs(DataEmployee dataEmployee) {
        EMPLOYEES_LOG_ERRORS.put(dataEmployee.getMachineName(), dataEmployee);
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
        OPERATORS_LOG_ERRORS.setModel(getLogData("Operator"));
        OPERATORS_LOG_ERRORS.setCellRenderer(new MyListCellRenderer());

        OPERATORS_LOG_ERRORS.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPaneOperators = new JScrollPane();
        scrollPaneOperators.setBounds(50, 120, 580, 500);
        scrollPaneOperators.setViewportView(OPERATORS_LOG_ERRORS);
        scrollPaneOperators.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /// Cria os elementos da JList de Lathes
        MANAGERS_LOG_ERRORS.setModel(getLogData("Manager"));
        MANAGERS_LOG_ERRORS.setCellRenderer(new MyListCellRenderer());

        MANAGERS_LOG_ERRORS.setBackground( new java.awt.Color( 220, 220, 255 ) );
        JScrollPane scrollPaneManagers = new JScrollPane();
        scrollPaneManagers.setBounds(50, 120, 580, 500);
        scrollPaneManagers.setViewportView(MANAGERS_LOG_ERRORS);
        scrollPaneManagers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        /// Criação da tela com JDialog

        // Cria um JDialog para a aplicação
        JDialog dialog = new JDialog();
        dialog.setTitle("Logs of the machines");
        dialog.setSize(700, 800);
        dialog.setLocation( 1000 , 150 );
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Cria um JTabbedPane para a aplicação
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(700,800);

        // Cria um JPanel para a aba de operadores
        JPanel panelOperators = new JPanel();
        panelOperators.setLayout( null );
        panelOperators.setSize(700, 800);
        panelOperators.setBackground(Color.white);

        // Adiciona os elementos na aba de welders
        panelOperators.add(tituloOperator);
        panelOperators.add(scrollPaneOperators);
        panelOperators.add(erroOperator);

        // Cria um JPanel para a aba de managers
        JPanel panelManager = new JPanel();
        panelManager.setLayout( null );
        panelManager.setSize(700, 800);
        panelManager.setBackground(Color.white);

        // Adiciona os elementos na aba de welders
        panelManager.add(tituloManager);
        panelManager.add(scrollPaneManagers);
        panelManager.add(erroManager);

        // Adiciona as abas no JDialog
        tabbedPane.add("Operators", panelOperators);
        tabbedPane.add("Manager", panelManager);
        dialog.add(tabbedPane);

        // Exibe a tela
        dialog.setVisible(true);

    }

    private void updateDataScreen() {
        OPERATORS_LOG_ERRORS.setModel(getLogData("Operator"));
        OPERATORS_LOG_ERRORS.setCellRenderer(new MyListCellRenderer());
        MANAGERS_LOG_ERRORS.setModel(getLogData("Manager"));
        MANAGERS_LOG_ERRORS.setCellRenderer(new MyListCellRenderer());
    }

    private DefaultListModel getLogData(String typeEmployee) {
        DefaultListModel listModel = new DefaultListModel<>();

        for(Map.Entry<String, DataEmployee> entry : EMPLOYEES_LOG_ERRORS.entrySet()) {
            String key = entry.getKey();
            DataEmployee value = entry.getValue();

            if(value.getTypeEmployee().equals(typeEmployee)) {
                listModel.addElement(value);
            }
        }
        return listModel;
    }

    private class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (((DataEmployee)value).getTypeEmployee().equals("Operator")) {

                setText(
                        "<html>\u200E<br/>"+
                        "<br/> " + (((DataEmployee) value).getMachineName()) + (((DataEmployee) value).getProblems().size() != 0 ?

                                "<span style=\"color: #FF0000\"> " + ((DataEmployee) value).getProblems().size() + " problems </span>"

                                : "<span style=\"color: #00DD00\"> no problems</span>")

                                +

                        "<br/><br/>" + (((DataEmployee) value).getProblems().isEmpty() ?

                                "<span style=\"color: #00DD00\">All fine!</span>"

                                : "<span style=\"color: #FF0000\">Problems: " + formatProblems(((DataEmployee) value).getProblems()) + "</span>"

                                )


                        + "<br/>\u200E"
                );
            } else if (((DataEmployee)value).getTypeEmployee().equals("Manager")) {
                setText(
                        "<html>\u200E<br/>"+
                        ((DataEmployee) value).getMachineName() +
                        "<br/> " + (((DataEmployee) value).getMachineName()) + (((DataEmployee) value).getProblems().isEmpty() ?
                        "<span style=\"color: #FF0000\"> " + ((DataEmployee) value).getProblems().size() + " problems </span>"
                        : "<span style=\"color: #00DD00\"> no problems</span>") +
                        "<br/><br/>" + (((DataEmployee) value).getProblems().isEmpty() ?
                        "Problems:<br/><span style=\"color: #FF0000\">" + formatProblems(((DataEmployee) value).getProblems()) + "</span>"
                        : "<span style=\"color: #FF0000\">All fine!</span>")
                        + "<br/>\u200E"
                );
            } else {
                setText(
                        "<html>\u200E<br/>"+
                        ((DataEmployee) value).getMachineName() +
                        "<br/> " + (((DataEmployee) value).getMachineName()) + (((DataEmployee) value).getProblems().isEmpty() ?
                        "<span style=\"color: #FF0000\"> " + ((DataEmployee) value).getProblems().size() + " problems </span>"
                        : "<span style=\"color: #00DD00\"> no problems</span>") +
                        "<br/><br/>" + (((DataEmployee) value).getProblems().isEmpty() ?
                        "Problems:<br/><span style=\"color: #FF0000\">" + formatProblems(((DataEmployee) value).getProblems()) + "</span>"
                        : "<span style=\"color: #FF0000\">All fine!</span>")
                        + "<br/>\u200E"
                );
            }

            return this;
        }

    }


    private String formatProblems(ArrayList<String> problems) {
        StringBuilder problemsFormatted = new StringBuilder();

        for( String problem : problems) {
            problemsFormatted.append("<br/>* ").append(problem);
        }

        return problemsFormatted.toString();
    }

}
