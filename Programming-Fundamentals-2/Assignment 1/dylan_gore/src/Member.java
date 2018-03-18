/**
 *  This class is the template for a single member
 *  @version 1.0 (01 March 2018)
 *  @author Dylan Gore (20081224@mail.wit.ie)
 */
public class Member extends Object {

    private int memberId;
    private String memberName;
    private String memberAddress;
    private double height;
    private double startingWeight;
    private String gender = "";

    /**
     * Constuctor for objects in class Member
     * @param memberId The member's id is 6 digits long i.e. between 100000 (exclusive) and 999999 (inclusive). If any invalid member id is entered, set the member id to a default value of 100000.
     * @param memberName The member's name should be no more than 30 characters. If in entered name exceeds 30 characters, the extra characters will be truncated and only the first 30 characters will be retained.
     * @param memberAddress There is no validation on the member's address.
     * @param height The member's height is measured in metres. A minimum height of one metre (inclusive) is allowed and a maximum height of three metres (inclusive).
     * @param startingWeight The member's weight upon joining the gym (in kgs). A minimum weight of 35kg (inclusive) and a max of 250 kg (inclusive) is permitted in the gym.
     * @param gender The member's gender i.e. can be either "M" or "F". If not specified, default to unspecified.
     */
    public Member(int memberId, String memberName, String memberAddress, double height, double startingWeight, String gender) {
        setMemberId(memberId);
        setMemberName(memberName);
        setMemberAddress(memberAddress);
        setHeight(height);
        setStartingWeight(startingWeight);
        setGender(gender);
    }


    /**
     * The BMI value for the member. The formula used for BMI is weight divided by the square of the height.
     * @return the BMI value for the member. The number returned is truncated to two decimal places.
     */
    public double calculateBMI(){
        double bmi = startingWeight / Math.pow(height, 2);
        return toTwoDecimalPlaces(bmi);
    }

    /**
     * This method returns the member's height converted from metres to inches.
     * @return member height converted from metres to inches using the formula: metres multiplied by 39.37. The number returned is truncated to two decimal places.
     */
    public double convertHeightMetresToInches(){
        double inches = height * 39.37;
        return toTwoDecimalPlaces(inches);
    }

    /**
     * This method returns the member weight converted from kgs to pounds.
     * @return member weight converted from kgs to pounds. The number returned is truncated to two decimal places.
     */
    public double convertWeightKGtoPounds(){
        return startingWeight * 2.2;
    }

    /**
     * This method determines the BMI category that the member belongs to.
     * @return the member's BMI category.
     */
    public String determineBMICategory(){
        String category;
        double bmi = calculateBMI();

        if(bmi < 15.0){
            category = "VERY SEVERELY UNDERWEIGHT";
        }else if(bmi >= 15 && bmi < 16){
            category = "SEVERELY UNDERWEIGHT";
        }else if(bmi >= 16 && bmi < 18.5){
            category = "UNDERWEIGHT";
        }else if(bmi >= 18.5 && bmi < 25){
            category = "NORMAL";
        }else if(bmi >= 25 && bmi < 30){
            category = "OVERWEIGHT";
        }else if(bmi >= 30 && bmi < 35){
            category = "MODERATELY OBESE";
        }else if(bmi >= 35 && bmi < 40){
            category = "SEVERELY OBESE";
        }else if(bmi >= 40){
            category = "VERY SEVERELY OBESE";
        }else {
        	category = "Unknown";
        }

        return category.toUpperCase();
    }

    /**
     * Return's the member's height.
     * @return the member's height
     */
    public double getHeight(){
        return height;
    }

    /**
     * Returns the member's address.
     * @return the member's address
     */
    public String getMemberAddress(){
        return memberAddress;
    }

    /**
     * Returns the member's gender.
     * @return the member's gender
     */
    public String getMemberGender(){
        return gender;
    }

    /**
     * Returns the id for the member.
     * @return the member's id
     */
    public int getMemberId(){
        return memberId;
    }

    /**
     * Returns the member's name.
     * @return the member's name
     */
    public String getMemberName(){
        return memberName;
    }

    /**
     * Returns the member's starting weight.
     * @return the member's starting weight
     */
    public double getStartingWeight(){
        return startingWeight;
    }

    /**
     * This method returns a boolean to indicate if the member has an ideal body weight based on the Devine formula.
     * @return Returns true if the result of the Devine formula is with in 2 kgs (inclusive) of the starting weight; false if it is outside this range.
     */
    public boolean isIdealBodyWeight(){

        double imperialHeight =  convertHeightMetresToInches();
        double idealWeight;

        //Calculated ideal weight based on height and gender
        if(imperialHeight <= 60){
            if(gender.equals("M")){
                idealWeight = 50;
            }else{
                idealWeight = 45.5;
            }
        }else{
            if(gender.equals("M")){
                idealWeight = 50 + ((imperialHeight - 60) * 2.3);
            }else{
                idealWeight = 45.5 + ((imperialHeight - 60) * 2.3);
            }
        }

        //Compares ideal weight to starting weight
        if (startingWeight >= idealWeight - 2 && startingWeight < idealWeight + 2) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Updates the member's gender field.
     * @param gender The member's gender i.e. can be either "M" or "F". All other values are ignored.
     */
    public void setGender(String gender){
    	gender = gender.toUpperCase();
    	
    	if(isValidGender(this.gender) && !isValidGender(gender)) {
    		//Do nothing as stored value is valid, new value is not
    	}else {
    		if(isValidGender(gender)){
                this.gender = gender;
            }else{
            	this.gender = "Unspecified";
            }
    	}
        
    }

    /**
     * Updates the member's height field.
     * @param height The member's height is measured in metres. A minimum height of one metre is allowed and a maximum height of three metres.
     */
    public void setHeight(double height){
    	if(isValidHeight(this.height) && !isValidHeight(height)) {
    		//Do nothing as stored value is valid, new value is not
    	}else {
    		if(isValidHeight(height)){
                this.height = height;
            }else{
                //Invalid height
                this.height = 0;
            }
    	}
    }

    /**
     * Updates the member's address field.
     * @param memberAddress There is no validation on the member's address.
     */
    public void setMemberAddress(String memberAddress){
        this.memberAddress = memberAddress;
    }

    /**
     * Updates the member's id field.
     * @param memberId The member's id is 6 digits long i.e. between 100000 (exclusive) and 999999 (inclusive).
     */
    public void setMemberId(int memberId){
        if(isValidId(this.memberId) && !isValidId(memberId)) {
        	//Do nothing as stored value is valid, new value is not
        }else {
        	if(isValidId(memberId)){
                //Valid id
                this.memberId = memberId;
            }
        	else{
                //Invalid id
                this.memberId = 100000;
            }
        }
    }

    /**
     * Updates the member's name field.
     * @param memberName The member's name should be no more than 30 characters. If the entered name exceeds 30 characters, it will be truncated and only the first 30 characters will be retained.
     */
    public void setMemberName(String memberName){
        if(memberName.length() <= 30){
            //Valid name
            this.memberName = memberName;
        }else{
            this.memberName = memberName.substring(0, 30);
        }
    }

    /**
     * Update the member's starting weight field.
     * @param startingWeight The member's weight upon joining the gym (in kgs). A minimum weight of 35 kg and a max of 250 kg is permitted in the gym.
     */
    public void setStartingWeight(double startingWeight){
        if(isValidWeight(this.startingWeight) && !isValidWeight(startingWeight)) {
        	//Do nothing as stored value is valid, new value is not
        }else {
        	if(isValidWeight(startingWeight)) {
                //Valid weight
                this.startingWeight = startingWeight;
            }else{
                this.startingWeight = 0;
            }
        }
    }

    /**
     * Returns a human-readable String representation of the object state.
     * @return a string version of the Member object.
     */
    @Override
    public String toString(){
        return "Member Id: " + memberId + ", Name: " + memberName + ", Address: " + memberAddress + "\n" +
                " Height: " + height + ", Starting Weight: " + startingWeight + " BMI of " + calculateBMI() + " (" + determineBMICategory() + ").";
    }

    /**
     * Helper method for truncating double to two decimal places
     * @param num number to truncated
     * @return the same number that has been input with 2 decimal places
     */
    private double toTwoDecimalPlaces(double num){
        return (int) (num *100 ) /100.0;
    }
    
    /**
     * Helper method to check if member id is valid
     * @param memberId id to check 
     * @return true/false if id is valid or not
     */
    private boolean isValidId(int memberId) {
    	if(memberId > 100000 && memberId <= 999999) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * Helper method to check if height is valid
     * @param height height to check
     * @return true/false if height is valid or not
     */
    private boolean isValidHeight(double height) {
    	if(height >= 1 && height <= 3.0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * Helper method to check if weight is valid
     * @param startingWeight weight to check
     * @return true/false if height is valid or not
     */
    private boolean isValidWeight(double startingWeight) {
    	if(startingWeight >= 35 && startingWeight <= 250) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * Helper method to check if gender is valid
     * @param gender gender to check
     * @return true/false if gender is valid or not
     */
    private boolean isValidGender(String gender) {
    	gender = gender.toUpperCase();
    	if(gender.equals("M") || gender.equals("F")) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
