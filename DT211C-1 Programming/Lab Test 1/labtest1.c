/*
	Lab Test 1 - Check if inputted year is leap year

	Author: Dylan Gore
	Date: 24th October 2016
	OS Version: Windows
	Compiler: gcc 5.3.0
*/

#include <stdio.h>

int main(){

    int year = 0;
    float ans1, ans2, ans3;
    ans1  = ans2 = ans3 = 0;

    //Ensures the year is not negative
    do{
        //Get input from user for year
        printf("Please enter a year: ");
        scanf("%d", &year);
    }//end do
    while(year < 1);

    //Arithmetic logic behind checking if the year is a leap year or not
    ans1 = (float)year / 4;
    ans2 = (float)year / 100;
    ans3 = (float)year / 400;

    //Checks if the year is evenly divisile by 4, if it is, the int value of ans1 and the float value of ans1 will be equal (ie. it will have no fractional part)
    if(ans1 == (int)ans1){
        //Evenly divisible (Possible Leap Year)

        //Checks if the year is evenly divisible by 100 AND that it is NOT evenly divisible by 400
        if((ans2 == (int)ans2) && (ans3 != (int)ans3)){
            //Not Leap Year
            printf("%d is not a leap year.\n", year);
        }else{
            //Leap Year
            printf("%d is a leap year.\n", year);
        }//end inner else if

    }else{
        //Not Evenly Divisible(Not Leap Year)
        printf("%d is not a leap year.\n", year);
    }//end outer else if

	return(0);
}//end main()
