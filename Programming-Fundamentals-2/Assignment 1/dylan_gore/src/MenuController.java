import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class controls the Gym application.
 * @version 1.0 (01 Mar 2018)
 * @author Dylan Gore (20081224@mail.wit.ie)
 */
public class MenuController extends Object{

    Gym gym;
    Scanner input;

    /**
     * Main function
     * @param args default function
     */
    public static void main(String args[]) {
        new MenuController();
    }

    /**
     * The default constructor.
     */
    public MenuController() {
        input = new Scanner(System.in);

        gymMenu();
        runMainMenu();
    }

    /**
     * Displays the main menu for the application
     */
    private int displayMainMenu(){
        printLine("Gym Menu", false);
        printDivider();
        printLine("1) Add Member", true);
        printLine("2) List all members", true);
        printLine("3) Remove a member (by index)", true);
        printLine("4) Number of members in the gym", true);
        printDivider();
        printLine("5) List gym details", true);
        printLine("6) List members with ideal starting weight", true);
        printLine("7) List members with a specific BMI category", true);
        printLine("8) List all members stats inperically and metrically", true);
        printDivider();
        printLine("9) Save to XML", true);
        printLine("10) Load from XML", true);
        printLine("0) Exit", true);
        System.out.print("==> ");

        int option = input.nextInt();

        return option;
    }

    /**
     * Displays the add gym menu
     */
    private void gymMenu(){
        print("Please enter the gym...\n", false);
        print("Name: ", true);
        String gymName = input.nextLine();
        print("Manager Name: ", true);
        String managerName = input.nextLine();
        print("Phone Number: ", true);
        String phoneNumber = input.nextLine();
        printLine("-" + phoneNumber + "-", true);

        gym = new Gym(gymName, managerName, phoneNumber);
    }

    /**
     * Helper method used when creating menus.
     * @param text content of the line
     * @param indent
     */
    private void printLine(String text, boolean indent){
        if(indent == true){
            System.out.println("  " + text);
        }else{
            System.out.println(text);
        }
    }

    /**
     * Helper method used when creating menus.
     * @param text content of the line
     * @param indent
     */
    private void print(String text, boolean indent){
        if(indent == true){
            System.out.print("  " + text);
        }else{
            System.out.print(text);
        }
    }

    /**
     * Helper method used to print dividers when creating menus.
     */
    private void printDivider(){
        System.out.println("----------");
    }

    /**
     * Runs the main menu and deals with input
     */
    private void runMainMenu(){
        int option = displayMainMenu();
        while (option != 0){
            switch(option){
                case 1:
                    //Add new member
                    print("Id (between 100001 and 999999): ", false);
                    int memberId = input.nextInt();
                    print("Name (max 30 chars): ", false);
                    String memberName = input.nextLine();
                    memberName = input.nextLine();
                    print("Address: ", false);
                    String memberAddress = input.nextLine();
                    print("Height (between 1 and 3 metres): ", false);
                    double height = input.nextDouble();
                    print("Starting Weight (between 35 kg and 250 kg): ", false);
                    double startingWeight = input.nextDouble();
                    print("Gender (M/F): ", false);
                    String gender = input.nextLine();
                    gender = input.nextLine();
                    printLine("'" + gender + "'",  true);

                    gym.add(new Member(memberId, memberName, memberAddress, height, startingWeight, gender));
                    printLine("New member: " + memberName + " has been added!", false);
                    break;
                case 2:
                    //List all members
                    printLine("\nAll members: ", false);
                    printLine(gym.listMembers(), true);
                    break;
                case 3:
                    //Remove a member
                    printLine(gym.listMembers(), false);
                    print("\nIndex of member to delete: ", false);
                    int index = input.nextInt();
                    if(index <= gym.numberOfMembers()){
                        gym.remove(index);
                        printLine("Member deleted", false);
                    }else{
                        printLine("There is no member for this index number", false);
                    }
                    break;
                case 4:
                    //Print number of members in gym
                    printLine("Number of members: " + gym.numberOfMembers(), false);
                    break;
                case 5:
                    //List gym info
                    printLine("\n" + gym.toString(), true);
                    break;
                case 6:
                    //List members - ideal start weight
                    printLine("\nListing members with an ideal starting weight:", false);
                    printLine(gym.listMembersWithIdealWeight(), true);
                    break;
                case 7:
                    //List members - BMI category
                    print("\nPlease enter the category to search by: ", false);
                    String category = input.nextLine();
                    category = input.nextLine();
                    printLine(gym.listBySpecificBMICategory(category), true);
                    break;
                case 8:
                    //List members - Imperial and Metric
                    printLine("\nListing members with stats in both imperial and metric:", false);
                    printLine(gym.listMemberDetailsImperialAndMetric(), true);
                    break;
                case 9:
                    //Save to XML
                    printLine("Saving member details...", false);
                    try{
                    	saveFile();
                    	printLine("Save complete.", false);
                    }catch(Exception e) {
                    	printLine("Error writing to file: " + e, false);
                    }
                    break;
                case 10:
                    //Load from XML
                    printLine("Loading member details...", false);
                    try{
                    	loadFile();
                    	printLine("Load complete.", false);
                    }catch(Exception e) {
                    	printLine("Error loading from file: " + e, false);
                    }
                    break;
                default:
                    printLine("\nInvalid option entered: " + option, false);
                    break;
            }

            printLine("\nPress any key to continue...", false);
            input.nextLine();
            input.nextLine();
            System.out.println("\f");

            option = displayMainMenu();
        }

        //User has opted to end program, exiting
        printLine("\nExiting... bye.", false);
        System.exit(0);
    }
    
    /**
     * Saves information to XML
     * @throws IOException 
     */
	private void saveFile() throws IOException{
		XStream xstream = new XStream(new DomDriver()); 
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml")); 
		out.writeObject(gym.members);
		out.close();
	}
    
    /**
     * Loads information from given XML file
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings("unchecked")
	private void loadFile() throws IOException, ClassNotFoundException{
    	XStream xstream = new XStream(new DomDriver()); 
		ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml")); 
		gym.members = (ArrayList<Member>) is.readObject();
		is.close();
	}

}
