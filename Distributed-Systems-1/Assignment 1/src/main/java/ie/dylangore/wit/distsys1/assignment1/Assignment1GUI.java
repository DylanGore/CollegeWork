package ie.dylangore.wit.distsys1.assignment1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;

/**
 * The class responsible for constructing and managing the GUI
 */
public class Assignment1GUI {
    // Global layout variables
    private static final int MAX_HEIGHT = 300;
    private static final int TEXT_WIDTH = 15;

    // Define Formatters
    NumberFormatter salaryFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());

    // Table Variables
    String[][] employeeData = {};
    String[] tableColumns = {"First Name", "Last Name", "SSN", "Salary", "Gender"};

    // Create the main frame
    JFrame frame = new JFrame("Assignment 1");

    // Additional Panels
    JPanel panelLeft = new JPanel();
    JPanel panelButtons = new JPanel();
    JPanel panelButtonsBottom = new JPanel();

    // Misc Elements
    JLabel labelTitle = new JLabel("JAVA / MySQL CRUD APPLICATION");
    JTable tableEmployees = new JTable(employeeData, tableColumns);

    // Fields and Labels
    JTextField textFirstName = new JTextField(TEXT_WIDTH);
    JLabel labelFirstName = new JLabel("First Name: ");
    JTextField textLastName = new JTextField(TEXT_WIDTH);
    JLabel labelLastName = new JLabel("Last Name: ");
    JTextField textSSN = new JTextField(TEXT_WIDTH);
    JLabel labelSSN = new JLabel("SSN: ");
    JFormattedTextField textSalary = new JFormattedTextField(salaryFormatter);
    JLabel labelSalary = new JLabel("Salary: ");
    JComboBox<String> comboBoxGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
    JLabel labelGender = new JLabel("Gender: ");

    // Buttons
    JButton buttonAdd = new JButton("Add");
    JButton buttonUpdate = new JButton("Update");
    JButton buttonDelete = new JButton("Delete");

    JButton buttonUpdateTable = new JButton("Update Table");
    JButton buttonClearForm = new JButton("Clear Form");

    // MySQL Connection
    MySQLHandler mysql = new MySQLHandler();

    /**
     * GUI constructor
     */
    public Assignment1GUI() {
        // Set Initial button states
        buttonUpdate.setEnabled(false);
        buttonDelete.setEnabled(false);

        // Set initial combo box state
        comboBoxGender.setSelectedItem("Other");

        // Configure formatters
        salaryFormatter.setValueClass(Integer.class);
        salaryFormatter.setMinimum(0);
        salaryFormatter.setAllowsInvalid(false);
        salaryFormatter.setCommitsOnValidEdit(true);

        // Update the table with current data
        updateTable();

        // Table setup
        tableEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEmployees.setDefaultEditor(Object.class, null);
        ListSelectionModel selectionModel = tableEmployees.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            // Stop function being called twice on selection change
            if (e.getValueIsAdjusting())
                return;

            // Get currently selected row
            int row = tableEmployees.getSelectedRow();

            // Disable update and delete buttons when nothing is selected
            if (row < 0){
                buttonUpdate.setEnabled(false);
                buttonDelete.setEnabled(false);
                // Ensure the SSN field is editable if nothing is selected to allow a user to add a new employee
                textSSN.setEditable(true);
                
            }else{
                buttonUpdate.setEnabled(true);
                buttonDelete.setEnabled(true);
                // Disable editing the SSN text field as this should not be edited in the GUI
            	textSSN.setEditable(false);
                

                // Fill the form with the data from the currently selected row
                textFirstName.setText(String.valueOf(tableEmployees.getValueAt(row, 0)));
                textLastName.setText(String.valueOf(tableEmployees.getValueAt(row, 1)));
                textSSN.setText(String.valueOf(tableEmployees.getValueAt(row, 2)));
                textSalary.setText(String.valueOf(tableEmployees.getValueAt(row, 3)));
                comboBoxGender.setSelectedItem(String.valueOf(tableEmployees.getValueAt(row, 4)));
            }
        });

        // Add button listeners
        buttonAdd.addActionListener(this::buttonClicked);
        buttonUpdate.addActionListener(this::buttonClicked);
        buttonDelete.addActionListener(this::buttonClicked);
        buttonUpdateTable.addActionListener(this::buttonClicked);
        buttonClearForm.addActionListener(this::buttonClicked);

        // Button Panel
        panelButtons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,5,0,5);
        gbc.gridy = 0;

        gbc.gridx = 0;
        panelButtons.add(buttonAdd, gbc);
        gbc.gridx = 1;
        panelButtons.add(buttonUpdate, gbc);
        gbc.gridx = 2;
        panelButtons.add(buttonDelete, gbc);

        // Bottom Button Panel
        panelButtonsBottom.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0,5,0,5);
        gbc.gridy = 0;
        gbc.gridx = 0;
        panelButtonsBottom.add(buttonUpdateTable, gbc);
        gbc.gridx = 1;
        panelButtonsBottom.add(buttonClearForm, gbc);


        // Left Panel
        panelLeft.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        // Left: First Name
        textFirstName.setMinimumSize(textFirstName.getPreferredSize());
        gbc.gridy = 0;
        gbc.gridx = 0;
        panelLeft.add(labelFirstName,gbc);
        gbc.gridx = 1;
        panelLeft.add(textFirstName, gbc);
        // Left: Last Name
        textLastName.setMinimumSize(textLastName.getPreferredSize());
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelLeft.add(labelLastName, gbc);
        gbc.gridx = 1;
        panelLeft.add(textLastName, gbc);
        // Left: SSN
        textSSN.setMinimumSize(textSSN.getPreferredSize());
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelLeft.add(labelSSN, gbc);
        gbc.gridx = 1;
        panelLeft.add(textSSN, gbc);
        // Left: Salary
        textSalary.setMinimumSize(textSalary.getPreferredSize());
        gbc.gridy = 3;
        gbc.gridx = 0;
        panelLeft.add(labelSalary, gbc);
        gbc.gridx = 1;
        panelLeft.add(textSalary, gbc);
        // Left: Gender
        comboBoxGender.setMinimumSize(comboBoxGender.getPreferredSize());
        gbc.gridy = 4;
        gbc.gridx = 0;
        panelLeft.add(labelGender, gbc);
        gbc.gridx = 1;
        panelLeft.add(comboBoxGender, gbc);
        // Left: Buttons
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panelLeft.add(panelButtons, gbc);
        gbc.gridy=6;
        panelLeft.add(panelButtonsBottom, gbc);

        // Combobox Formatting
        comboBoxGender.setBackground(textFirstName.getBackground());

        // Title Formatting
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitle.setBorder(new EmptyBorder(10,0,15,0));
        // Set sidebar width
        panelLeft.setPreferredSize(new Dimension(300, MAX_HEIGHT));
        // Set layout locations and add to frame
        frame.setLayout(new BorderLayout());
        frame.add(labelTitle, BorderLayout.NORTH);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(new JScrollPane(tableEmployees));
        // Frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,MAX_HEIGHT));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Function to handle all button clicks
     * @param event the ActionEvent that corresponds to a button click
     */
    private void buttonClicked(ActionEvent event){
        boolean isFormValid = false;

        // Initialize variables for the form values
        String firstName = "", lastName = "";
        int ssn = 0, salary = 0;
        String gender = "O";

        // Log the button press
        Assignment1.logger.info("Button pressed: " + ((JButton)event.getSource()).getText());

        // Attempt to parse all of the form fields to ensure the data is valid
        try {
            firstName = textFirstName.getText();
            lastName = textLastName.getText();
            ssn = Integer.parseInt(textSSN.getText());
            salary = Integer.parseInt(textSalary.getText().replace(",", ""));
            gender = String.valueOf(comboBoxGender.getSelectedItem());
            isFormValid = true;
        } catch (Exception ex){
            Assignment1.logger.info("Form data invalid!");
        }

        switch (((JButton)event.getSource()).getText().toLowerCase()){
            case "add":
                if (isFormValid){
                    String result = MySQLHandler.addEmployee(firstName, lastName, ssn, salary, gender);
                    if (result.equals("OK")) {
                        // Update the data in the table
                        updateTable();
                        // Clear the form
                        clearForm();
                    }else{
                        // Show an error dialog
                        JOptionPane.showMessageDialog(frame, "Error when adding new employee:\n" + result, "MySQL Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    // Display an error dialog if the form data is invalid
                    JOptionPane.showMessageDialog(frame, "Form data is invalid!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "update":
                // Update an existing employee
                if (isFormValid){
                    // Execute the update and save the result as a String
                    String result = MySQLHandler.updateEmployee(firstName, lastName, ssn, salary, gender);
                    // If there was an error, display an error dialog
                    if (!result.equals("OK")){
                        JOptionPane.showMessageDialog(frame, "Error while updating employee:\n" + result, "MySQL Error", JOptionPane.ERROR_MESSAGE);
                    }
                    // Update the data in the table
                    updateTable();
                    // Clear the form
                    clearForm();
                }else{
                    // Display an error dialog if the form data is invalid
                    JOptionPane.showMessageDialog(frame, "Form data is invalid!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "delete":
                // Get the currently selected table row
                int selectedRow = tableEmployees.getSelectedRow();
                // If there is no row selected, do nothing
                if (selectedRow < 0){
                    Assignment1.logger.info("No row selected, no employee will be deleted");
                    break;
                }
                // Get the currently selected values to display to the user
                int selectedSSN = Integer.parseInt((String) tableEmployees.getModel().getValueAt(selectedRow, 2));
                String selectedFirstName = (String)tableEmployees.getModel().getValueAt(selectedRow, 0);
                // Display a confirmation dialog to the user
                int input = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete " + selectedFirstName + " ?", "Delete Employee", JOptionPane.YES_NO_OPTION);
                if (input == 0 && selectedSSN > -1){
                    // Get the result
                    String result = MySQLHandler.deleteEmployee(selectedSSN);
                    // Delete the selected user if the user confirms the operation
                    if (!result.equals("OK")){
                        // Display an error dialog if the delete was not successful
                        JOptionPane.showMessageDialog(frame, "Error while deleting user:\n" + result, "MySQL Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // Clear the form
                clearForm();
                // Update the data in the table with the latest from the database
                updateTable();
                break;
            case "update table":
                // Query the MySQL database for the latest data
                updateTable();
                break;
            case "clear form":
                // Clear all form fields
                clearForm();
                break;
            default:
                Assignment1.logger.info("The functionality for the button you have pressed has not been implemented.");
        }
    }

    /**
     * Function to clear all form fields and deselect any selected table entries
     */
    void clearForm(){
        // Clear all form fields
        textFirstName.setText("");
        textLastName.setText("");
        textSSN.setText("");
        textSalary.setValue(null);
        comboBoxGender.setSelectedItem("Other");
        // Clear the currently selected table item
        tableEmployees.clearSelection();
        // Ensure editing the SSN field is enabled
        textSSN.setEditable(true);
    }

    /**
     * Function to query the MySQL database for the latest data and use it to update the JTable
     */
    void updateTable(){
        // Define a new table model
        DefaultTableModel model = new DefaultTableModel();
        // Get the latest employee list
        List<Assignment1.Employee> employees = mysql.getEmployees();
        // Set the table columns
        for (String tableColumn : tableColumns) {
            model.addColumn(tableColumn);
        }

        if (employees != null){
            // Add each employee as a new table row
            for (int i = 0; i < employees.size(); i++){
                model.insertRow(i, employees.get(i).getDataArray());
            }
        }else{
            // The employees list should only be null if the connection to MySQL has failed, show a message dialog to the user
            JOptionPane.showMessageDialog(frame, "Error establishing a database connection!", "MySQL Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        // Set the table to use the new model
        tableEmployees.setModel(model);
    }
}
