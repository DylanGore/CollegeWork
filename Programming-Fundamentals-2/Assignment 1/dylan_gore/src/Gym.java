import java.util.ArrayList;

/**
 * This class handles a collection of Member classes.
 * @version 1.0 (01 Mar 2017)
 * @author Dylan Gore (20081224@mail.wit.ie)
 */

public class Gym extends Object {

    private String gymName;
    private String managerName;
    private String phoneNumber;

    ArrayList<Member> members = new ArrayList<>();

    /**
     * Constructor for objects of class Gym.
     * @param gymName The gym name must be no more than 30 characters. If the entered name exceeds 30 characters, the extra characters will be truncated and only thr first 30 characters will be retained.
     * @param managerName No validation is performed on the manager name field.
     */
    public Gym(String gymName, String managerName) {
        setGymName(gymName);
        setManagerName(managerName);
        setPhoneNumber("unknown");
    }

    /**
     * Constructor for objects of class Gym.
     * @param gymName The gym name must be no more than 30 characters. If the entered name exceeds 30 characters, the extra characters will be truncated and only thr first 30 characters will be retained.
     * @param managerName No validation is performed on the manager name field.
     * @param phoneNumber A check is done on the phone number to ensure that it is a number. If the phone number is not a number, it is set to "unknown".
     */
    public Gym(String gymName, String managerName, String phoneNumber) {
        setGymName(gymName);
        setManagerName(managerName);
        setPhoneNumber(phoneNumber);
    }

    /**
     * Adds a member to the gym collection.
     * @param member The member object that will be added to the gym collection
     */
    public void add(Member member){
        members.add(member);
    }

    /**
     * Returns the name of the gym.
     * @return the gym name
     */
    public String getGymName(){
        return this.gymName;
    }

    /**
     * Returns the name of the manager.
     * @return the manager name
     */
    public String getManagerName(){
        return this.managerName;
    }

    /**
     * Returns the phone number of the gym.
     * @return the gym phone number
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
     * List all members of a specific BMI category
     * @return The list of members whose BMI galls into the category passed as a parameter
     * @param category The category you wish to search members by
     */
    public String listBySpecificBMICategory(String category){
        String output = "";
        int catCount = 0;

        if(numberOfMembers() != 0){
            for(int i = 0; i < members.size(); i++){
                Member currentMember = members.get(i);
                String memberCategory = currentMember.determineBMICategory();

                //Convert category entered to uppercase as case should have no effect on output
                category = category.toUpperCase();

                if(category.toUpperCase().equals("UNDERWEIGHT") && memberCategory.contains("UNDERWEIGHT")){
                    //Keyword: UNDERWEIGHT
                    output = output + currentMember.toString() + "\n";
                    catCount++;
                }else if(category.toUpperCase().equals("OVERWEIGHT") && memberCategory.contains("OVERWEIGHT")){
                    //Keyword: OVERWEIGHT
                    output = output + currentMember.toString() + "\n";
                    catCount++;
                }else if(category.toUpperCase().equals("OBESE") && memberCategory.contains("OBESE")){
                    //Keyword: OBESE
                    output = output + currentMember.toString() + "\n";
                    catCount++;
                }else if(memberCategory.equals(category)){
                    //Exact category match
                    output = output + currentMember.toString() + "\n";
                    catCount++;
                }
            }
            //If category is empty
            if(catCount == 0){
                output = "There are no members in the gym in this BMI category";
            }
        }else{
            output = "There are no members in the gym";
        }

        return output;
    }

    /**
     * List all the members' weight and height both imperially and metrically.
     * @return Each member in the gym with a weight and height listed both imperially and metrically.
     */
    public String listMemberDetailsImperialAndMetric(){
        String output = "";
        if(members.size() != 0) {
        	for(int i = 0; i < members.size(); i++){
                Member currentMember = members.get(i);
                String name = currentMember.getMemberName();
                double imperialWeight = currentMember.convertWeightKGtoPounds();
                double imperialHeight = currentMember.convertHeightMetresToInches();
                output = output + name + ":     " + currentMember.getStartingWeight() + " kg (" + imperialWeight + " pounds)       " + currentMember.getHeight() + " metres (" + imperialHeight +" inches)\n";
            }
        }else {
        	output = "There are no members in the gym";
        }
        return output;
    }

    /**
     * Returns a String representing all the members stored in the gym collection.
     * @return String representing all the members stored in the gym collection
     */
    public String listMembers(){
        String output = "";

        if(members.size() != 0){
            for(int i = 0; i < members.size(); i++){
                output = output + "\n " + i + ": " + members.get(i).toString();
            }
        }else{
            output = "There are no members in the gym";
        }

        return output;
    }

    /**
     * Lists all the members that have an ideal starting weight.
     * @return The list of members (i.e. use the toString method here) that have an ideal starting weight based on the devine method.
     */
    public String listMembersWithIdealWeight(){
        String output = "";
        int idealCount = 0;
        
        if(members.size() != 0){
            for(int i = 0; i < members.size(); i++){
                Member currentMember = members.get(i);
                if(currentMember.isIdealBodyWeight()){
                	idealCount++;
                    output = output + "\n\n " + i + ": " + currentMember.toString();
                }
            }
            
            if(idealCount == 0) {
            	//No members with ideal weights
            	output = "There are no members in the gym with an ideal weight";
            }
        }
        else{
        	//No members at all
            output = "There are no members in this gym";
        }

        return output;
    }

    /**
     * Returns the number of members stored in the gym collection.
     * @return The number of members currently stored in the gym collection.
     */
    public int numberOfMembers(){
        return members.size();
    }

    /**
     * Removes a member from the gym collection.
     * @param index The index number of the member object that will be removed from the gym collection
     */
    public void remove(int index){
    	//Ensures that index is greater than 0 and is less than the total size of the list (starting from 0)
        if(index >= 0 && index <= members.size() - 1) {
        	members.remove(index);
        }
    }

    /**
     * Updates the gym name field.
     * @param gymName The gym name must be no more than 30 characters. If the entered name exceeds 30 characters, the extra characters will be truncated and only thr first 30 characters will be retained.
     */
    public void setGymName(String gymName){
        if(gymName.length() <= 30){
            //Valid length
            this.gymName = gymName;
        }else{
        	this.gymName = gymName.substring(0, 30);
        }
    }

    /**
     * Updates the manager name field.
     * @param managerName No validation is performed on the manager name field
     */
    public void setManagerName(String managerName){
        this.managerName = managerName;
    }

    /**
     * Updates the gym phone number field.
     * @param phoneNumber A check is done on the phone number to ensure that it is a number. If the phone number is not a number, it is set to "unknown".
     */
    public void setPhoneNumber(String phoneNumber){
        if(isNumeric(this.phoneNumber) && !isNumeric(phoneNumber)) {
        	//If the stored phone number already contains a valid number, and the new number is invalid, do nothing.
        }else {
        	if(isNumeric(phoneNumber)) {
            	this.phoneNumber = phoneNumber;
            }else {	
            	//This should only ever run when a gym is first created
            	this.phoneNumber = "unknown";	
            }
        }
    }

    /**
     * Returns a human-readable String representation of the object state.
     * @return a String version of the Gym object.
     */
    @Override
    public String toString(){
        return "Gym name: " + gymName + ", Manager Name: " + managerName + ", Phone Number: " + phoneNumber + ". \n" +
                "List of members in the gym: \n" + listMembers();
    }
    
    
    /**
     * Checks if given string is numeric
     * @param s given string
     * @return true/false if string is numeric or not
     */
    private boolean isNumeric(String string) {
    	//Checks if the string has a value and contains only positive integers
    	if(string != null && string.matches("\\d+")) {
    		return true;
    	}else {
    		return false;
    	}  
    }  
}
