package panel;

import data.Data;
import data.DataEmployee;
import structure.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * The EmployeePanel class implements the Observer interface and represents
 * a panel that displays logs and error information for operators (employees)
 * associated with machines. It updates the UI when new log data is available.
 */
public class EmployeePanel implements Observer {

    // Map to store the log errors of employees, keyed by machine name
    private static final Map<String, DataEmployee> EMPLOYEES_LOG_ERRORS = new HashMap<>();

    private final static ArrayList<String> MACHINES_WITH_PROBLEMS = new ArrayList<>();
    // Flag to track if the UI screen is active
    private static boolean screenActive = false;

    // Counters for machines with problems and total problems
    private static int amountOfMachinesWthProblems = 0;
    private static int amountOfProblems = 0;

    private static JLabel labelCountMachinesWithProblems = new JLabel("teste");
    private static JLabel labelAmountOfProblems = new JLabel("teste");

    /**
     * Updates the panel with new data received from the observable.
     *
     * @param data The new data to update the panel with.
     */
    @Override
    public void update(Data data) {
        updateListOfLogs((DataEmployee) data);
    }

    /**
     * Updates the internal log list with the new DataEmployee information.
     *
     * @param dataEmployee The DataEmployee object containing the log information.
     */
    private void updateListOfLogs(DataEmployee dataEmployee) {
        // Check if there is existing data for the machine
        if (EMPLOYEES_LOG_ERRORS.get(dataEmployee.getMachineName()) != null) {
            DataEmployee oldData = EMPLOYEES_LOG_ERRORS.get(dataEmployee.getMachineName());

            // Update the amount of problems based on the new data
            if (oldData.getProblems().size() < dataEmployee.getProblems().size()) {
                amountOfProblems += dataEmployee.getProblems().size() - oldData.getProblems().size();
            } else if (oldData.getProblems().size() > dataEmployee.getProblems().size()) {
                amountOfProblems -= oldData.getProblems().size() - dataEmployee.getProblems().size();
            }

            // Update the count of machines with problems
            if (oldData.getProblems().isEmpty() && !dataEmployee.getProblems().isEmpty()) {
                amountOfMachinesWthProblems++;
                if (!MACHINES_WITH_PROBLEMS.contains(dataEmployee.getMachineName())) {
                    MACHINES_WITH_PROBLEMS.add(dataEmployee.getMachineName());
                }
            } else if (!oldData.getProblems().isEmpty() && dataEmployee.getProblems().isEmpty()) {
                amountOfMachinesWthProblems--;
                MACHINES_WITH_PROBLEMS.remove(dataEmployee.getMachineName());
            }
        } else {
            if (dataEmployee.getProblems().size() > 0) {
                amountOfProblems += dataEmployee.getProblems().size();
                amountOfMachinesWthProblems++;
                MACHINES_WITH_PROBLEMS.add(dataEmployee.getMachineName());
            }
        }

        // Update the log errors map
        EMPLOYEES_LOG_ERRORS.put(dataEmployee.getMachineName(), dataEmployee);
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

    /**
     * JList to display the error logs
     */
    private static final JList<String> LOG_ERRORS_LIST = new JList<>();

    /**
     * JList to display the error logs
     */
    private static final JList<String> MACHINES_WITH_PROBLEMS_LIST = new JList<>();

    /**
     * Creates and displays the user interface for the Employee Panel.
     */
    private void screen() {
        // Title for the operators' log page
        JLabel tituloOperator = new JLabel("Subscribed Operators");
        tituloOperator.setFont(tituloOperator.getFont().deriveFont(24.0f));
        tituloOperator.setBounds((700 - tituloOperator.getPreferredSize().width) / 2, 20, 300, 50);

        // Placeholder labels
        JLabel erroOperator = new JLabel();
        JLabel erroManager = new JLabel();

        // Set up the log errors list model
        LOG_ERRORS_LIST.setModel(getLogData("Employee"));
        LOG_ERRORS_LIST.setCellRenderer(new MyListCellRenderer());

        LOG_ERRORS_LIST.setBackground(new java.awt.Color(220, 220, 255));
        JScrollPane scrollPaneOperators = new JScrollPane();
        scrollPaneOperators.setBounds(50, 120, 580, 500);
        scrollPaneOperators.setViewportView(LOG_ERRORS_LIST);
        scrollPaneOperators.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the main dialog for the application
        JDialog dialog = new JDialog();
        dialog.setTitle("Logs of the machines");
        dialog.setSize(700, 800);
        dialog.setLocation(1000, 150);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a tabbed pane for the application
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setSize(700, 800);

        // Panel for operators
        JPanel panelOperators = new JPanel();
        panelOperators.setLayout(null);
        panelOperators.setSize(700, 800);
        panelOperators.setBackground(Color.white);

        // Add components to the operators panel
        panelOperators.add(tituloOperator);
        panelOperators.add(scrollPaneOperators);
        panelOperators.add(erroOperator);

        // Panel for managers
        JPanel panelManager = new JPanel();
        panelManager.setLayout(null);
        panelManager.setSize(700, 800);
        panelManager.setBackground(Color.white);

        // Title for the managers' page
        JLabel tituloManager = new JLabel("Manager analytics");
        tituloManager.setFont(tituloManager.getFont().deriveFont(24.0f));
        tituloManager.setBounds((700 - tituloManager.getPreferredSize().width) / 2, 20, 300, 50);

        labelCountMachinesWithProblems.setBounds(50, 100, 250, labelCountMachinesWithProblems.getPreferredSize().height);
        labelAmountOfProblems.setBounds(50, 140, 250, labelAmountOfProblems.getPreferredSize().height);

        JLabel titleListManagers = new JLabel("Machines with problems: ");
        titleListManagers.setFont(tituloManager.getFont().deriveFont(18.0f));
        titleListManagers.setBounds(50, 180, 300, 50);

        // Set up the log errors list model
        MACHINES_WITH_PROBLEMS_LIST.setModel(getLogData("Manager"));

        MACHINES_WITH_PROBLEMS_LIST.setBackground(new java.awt.Color(220, 220, 255));
        JScrollPane scrollPanelManagers = new JScrollPane();
        scrollPanelManagers.setBounds(50, 250, 580, 400);
        scrollPanelManagers.setViewportView(MACHINES_WITH_PROBLEMS_LIST);
        scrollPanelManagers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add components to the managers panel
        panelManager.add(tituloManager);
        panelManager.add(labelCountMachinesWithProblems);
        panelManager.add(labelAmountOfProblems);
        panelManager.add(titleListManagers);
        panelManager.add(scrollPanelManagers);
        panelManager.add(erroManager);

        // Add tabs to the dialog
        tabbedPane.add("Operators", panelOperators);
        tabbedPane.add("Managers", panelManager);
        dialog.add(tabbedPane);

        // Display the dialog
        dialog.setVisible(true);
    }

    /**
     * Updates the data displayed in the log errors list.
     */
    private void updateDataScreen() {
        LOG_ERRORS_LIST.setModel(getLogData("Employee"));
        LOG_ERRORS_LIST.setCellRenderer(new MyListCellRenderer());
        labelAmountOfProblems.setText("Amount of problems: " + amountOfProblems);
        labelCountMachinesWithProblems.setText("Machines with problems: " + amountOfMachinesWthProblems);
        MACHINES_WITH_PROBLEMS_LIST.setModel(getLogData("Manager"));
    }

    /**
     * Retrieves the log data and prepares it for display in the JList.
     *
     * @return A DefaultListModel containing the log entries.
     */
    private DefaultListModel getLogData(String type) {
        DefaultListModel listModel = new DefaultListModel<>();
        if (type.equals("Employee")) {
            for (Map.Entry<String, DataEmployee> entry : EMPLOYEES_LOG_ERRORS.entrySet()) {
                DataEmployee value = entry.getValue();
                listModel.addElement(value);
            }
        } else if (type.equals("Manager")) {
            for (String machineName : MACHINES_WITH_PROBLEMS) {
                listModel.addElement(machineName);
            }
        }
        return listModel;
    }

    /**
     * Custom renderer for the JList to format the display of log entries.
     */
    private class MyListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Format the log entry display
            setText(
                    "<html>\u200E<br/>" +
                            "<br/> " + (((DataEmployee) value).getMachineName()) + (((DataEmployee) value).getProblems().size() != 0 ?
                            "<span style=\"color: #FF0000\"> " + ((DataEmployee) value).getProblems().size() + " problem(s) </span>"
                            : "<span style=\"color: #00DD00\"> no problems</span>") +
                            "<br/><br/>" + (((DataEmployee) value).getProblems().isEmpty() ?
                            "<span style=\"color: #00DD00\">All fine!</span>"
                            : "<span style=\"color: #FF0000\">Problems: " + formatProblems(((DataEmployee) value).getProblems()) + "</span>") +
                            "<br/>\u200E"
            );

            return this;
        }
    }

    /**
     * Formats the list of problems into a string for display.
     *
     * @param problems The list of problems to format.
     * @return A formatted string of problems.
     */
    private String formatProblems(ArrayList<String> problems) {
        StringBuilder problemsFormatted = new StringBuilder();

        for (String problem : problems) {
            problemsFormatted.append("<br/>* ").append(problem);
        }

        return problemsFormatted.toString();
    }
}
