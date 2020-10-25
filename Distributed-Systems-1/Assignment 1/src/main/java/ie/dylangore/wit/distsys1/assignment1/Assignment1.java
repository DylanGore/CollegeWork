package ie.dylangore.wit.distsys1.assignment1;

import java.util.logging.Logger;

/**
 * Main class
 */
public class Assignment1 {

    public static final Logger logger = Logger.getLogger("Assignment1-DylanGore");

    /**
     * Main function - program entry point
     * @param args main function args
     */
    public static void main(String[] args) {
        logger.info("Starting program...");
        // Create and display the GUI
        new Assignment1GUI();
    }

    /**
     * The employee object with all valid parameters
     */
    public static class Employee{
        String firstName;
        String lastName;
        int ssn;
        int salary;
        String gender;

        /**
         * The employee constructor
         * @param firstName the employee's first name
         * @param lastName the employee's last name
         * @param ssn the employee's SSN
         * @param salary the employee's salary
         * @param gender the employee's gender
         */
        public Employee(String firstName, String lastName, int ssn, int salary, String gender) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.ssn = ssn;
            this.salary = salary;
            this.gender = gender;

            logger.info("Created new employee: " + this.toString());
        }

        /**
         * Function to return employee information in a format accepted by a JTable
         * @return the string of employee data formatted for JTable
         */
        public Object[] getDataArray(){
            return new String[]{firstName, lastName, Integer.toString(ssn), Integer.toString(salary), gender};
        }

        /**
         * The toString function
         * @return the employee as a String
         */
        @Override
        public String toString() {
            return "Employee: " + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' +
                    ", ssn=" + ssn + ", salary=" + salary + ", gender=" + gender;
        }
    }
}
