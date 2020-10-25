package ie.dylangore.wit.distsys1.assignment1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class that handles MySQL connections and queries
 */
@SuppressWarnings("SqlResolve")
public class MySQLHandler {

    // MySQL Connection Details
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASS = "";
    private static final String MYSQL_HOST = "localhost";
    private static final int MYSQL_PORT = 3306;
    private static final String MYSQL_DB = "test";
    private static final String tableName = "employees";

    /**
     * Get a new MySQL database connection
     * @return the connection object
     */
    public static Connection getConnection() {
        // Connect to MySQL
        Connection conn = null;
        try {
            Assignment1.logger.info("Attempting MySQL connection...");
            Properties connectionProps = new Properties();
            connectionProps.put("user", MYSQL_USER);
            connectionProps.put("password", MYSQL_PASS);
            conn = DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DB, connectionProps);
            Assignment1.logger.info("Connected to database");
        } catch (SQLException e) {
            Assignment1.logger.severe("Could not connect to the database\n" + e.getMessage());
        }
        return conn;
    }

    /**
     * Function to query the MySQL database for a list of employees
     * @return the list of employees
     */
    public List<Assignment1.Employee> getEmployees() {
        // Get the MySQL connection
        Connection conn = getConnection();
        // Create the ArrayList to store the employees
        ArrayList<Assignment1.Employee> employees = new ArrayList<>();
        if (conn != null){
            try {
                // Create a MySQL statement
                Statement s = conn.createStatement ();
                // Execute the query
                s.executeQuery ("SELECT * FROM " + tableName);
                // Store the results of the query in a ResultSet
                ResultSet results = s.getResultSet ();
                // For each result in the ResultSet, create a new employee Object and add it to the employees list
                int count = 0;
                while (results.next ()) {
                    String firstName = results.getString ("firstName");
                    String lastName = results.getString ("lastName");
                    int ssn = results.getInt ("ssn");
                    int salary = results.getInt("salary");
                    String gender = results.getString("gender");
                    employees.add(new Assignment1.Employee(firstName, lastName, ssn, salary, gender));
                    ++count;
                }
                // Close the connection
                results.close ();
                s.close ();
                Assignment1.logger.info(count + " employees were retrieved from the database");

            } catch (Exception ex) {
                Assignment1.logger.severe("Could not query the database for employees\n" + ex.getMessage());
            }
            // Return the list of employees found in the database
            return employees;
        }else{
            // Return null if the MySQL connection could not be established
            return null;
        }

    }

    /**
     * Function to delete an employee
     * @param ssn the employee's SSN
     * @return the result message - "OK" or the exception message
     */
    public static String deleteEmployee(int ssn){
        // Get the MySQL connection
        Connection conn = getConnection();
        if (conn != null) {
            try {
                Statement s = conn.createStatement();
                s.execute("DELETE FROM " + tableName + " WHERE ssn = " + ssn);
                s.close();
                conn.close();
                Assignment1.logger.info("Deleted employee with SSN " + ssn);
                return "OK";
            } catch (SQLException ex) {
                Assignment1.logger.severe("Error while deleting employee (SSN: " + ssn + ")\n" + ex.getMessage());
                return ex.getMessage();
            }
        }else{
            return "Error establishing a connection to the database";
        }
    }

    /**
     * Function to add a new employee
     * @param firstName the employee's first name
     * @param lastName the employee's last name
     * @param ssn the employee's SSN
     * @param salary the employee's salary
     * @param gender the employee's gender
     * @return the result message - "OK" or the exception message
     */
    public static String addEmployee(String firstName, String lastName, int ssn, int salary, String gender){
        // Get the MySQL connection
        Connection conn = getConnection();
        if (conn != null){
            try {
                // Create the query string
                String query = "Insert Into  " + tableName + "(firstName, lastName, ssn, salary, gender) VALUES (?,?,?,?,?)";
                // Create a PreparedStatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, firstName);
                preparedStmt.setString(2, lastName);
                preparedStmt.setInt(3, ssn);
                preparedStmt.setInt(4, salary);
                preparedStmt.setString(5, gender);
                // Execute the query and close the connection
                preparedStmt.execute();
                preparedStmt.close();
                conn.close();
                Assignment1.logger.info("Added new employee " + firstName + " " + lastName);
                // Return "OK" if everything worked as expected
                return "OK";
            } catch (SQLException ex) {
                // Return the exception message if there was an error
                Assignment1.logger.info("Error while adding new employee\n" + ex.getMessage());
                return ex.getMessage();
            }
        }else{
            return "Error establishing a connection to the database";
        }
    }

    /**
     * Send a MySQL query to update an existing employee
     * @param firstName the desired value for the employee's first name
     * @param lastName the desired value for the employee's last name
     * @param ssn the employee's SSN - This cannot be changed as it is the Primary Key
     * @param salary the desired value for the employee's salary
     * @param gender the desired value for the employee's gender
     * @return the result message - "OK" or the exception message
     */
    public static String updateEmployee(String firstName, String lastName, int ssn, int salary, String gender){
        // Get the MySQL connection
        Connection conn = getConnection();
        if (conn != null){
            try {
                // Create the query string
                String query = "UPDATE " + tableName + " SET firstName = ?, lastName = ?, salary = ?, gender = ? WHERE ssn = ?";
                // create a PreparedStatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, firstName);
                preparedStmt.setString(2, lastName);
                preparedStmt.setInt(3, salary);
                preparedStmt.setString(4, gender);
                preparedStmt.setInt(5, ssn);
                // Execute the update and close the connection
                preparedStmt.executeUpdate();
                conn.close();
                Assignment1.logger.info("Updated Employee " + ssn + gender);
                // Return "OK" if everything worked as expected
                return "OK";
            } catch (SQLException ex) {
                // Return the exception message if there was an error
                Assignment1.logger.severe("Error while updating employee (SSN: " + ssn + ")\n" + ex.getMessage());
                return ex.getMessage();
            }
        }else{
            return "Error establishing a connection to the database";
        }

    }
}
