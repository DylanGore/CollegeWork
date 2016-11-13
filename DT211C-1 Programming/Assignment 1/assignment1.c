/*
	Programming Assignment 1

	Author: Dylan Gore
	Date: 13th November 2016
	OS Version: Windows
	Compiler: gcc 5.3.0
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

//Checks what OS the program is running on
#if defined(_WIN32) || defined(_WIN64)
    int os = 1; //Windows
#else
    int os = 0; //UNIX
#endif


//Initialize all variables setting the default PIN to 1234
int pin = 1234;
int timesIncorrect = 0;
int timesCorrect = 0;
int enteredPin = 0;
int newPin = 0;
int menuOption = 0;

//Function that when called clears the input buffer allowing scanf() statements to work as intended
void clearInputBuffer(){
    while(getchar() != '\n'){}//end while
}//end clearInputBuffer

//Function that when called clears the screen
void clearScreen(){
    if(os == 1){
        //Windows
        system("cls");
    }//end if
    else{
        //UNIX
        system("clear");
    }//end else
}//end clearScreen

//Function that when called shows the menu
void showMenu(){
    clearScreen(); //clears the screen

    printf("1. Enter your PIN and verify that it is correct\n");
    printf("2. Change your PIN\n");
    printf("3. Display the number of times the PIN was entered correctly and incorrectly\n");
    printf("4. Exit Program\n");

    printf("\nChoose Option: ");

    scanf("%d", &menuOption);
    clearInputBuffer();

    printf("\n");

    //runs a specific set of instructions based on what was entered
    switch(menuOption){
        case 1: //Verify correct PIN
        {
            printf("Please enter your PIN: ");
            scanf("%d", &enteredPin);
            if(enteredPin == pin){
                printf("Valid PIN\n");
                timesCorrect++; //Increment times PIN has been entered correctly for use in option 3
                sleep(2); //wait for 2 seconds
            }//end if
            else{
                printf("Invalid PIN!\n");
                timesIncorrect++; //Increment times PIN has been entered incorrectly for use in option 3
                sleep(2);//wait for 2 seconds
            }//end else
            showMenu(); //show the menu
            break;
        }//end case 1 (Verify PIN)
        case 2:
        {
            printf("Please enter your new 4 digit PIN: ");
            scanf("%d", &enteredPin); //User inputs PIN
            clearInputBuffer();

            //If PIN is not 4 digits long, ask the user to re-enter it until it is
            while(enteredPin > 9999 || enteredPin < 1000){
                printf("The PIN you have entered is not 4 digits long, please try again.\n");
                printf("\nPlease enter your new 4 digit PIN: ");
                scanf("%d", &enteredPin);
                clearInputBuffer();
            }//end while

            printf("Please enter your new PIN again to confirm: ");
            scanf("%d", &newPin);
            if(enteredPin == newPin){
                pin = newPin;
                printf("PIN changed successfuly!");
            }//end if
            else{
                //If the confirmation PIN is not 4 digits it will not match and will give the error below also
                printf("The PIN you have entered does not match, you will now be returned to the menu.");
            }//end else

            clearInputBuffer();
            sleep(3); //Wait for 3 seconds to allow the user to read the text on the screen
            showMenu();
            break;

        }//end case 2 (Change PIN)
        case 3:
        {
            printf("You have entered your PIN: \n");
            printf("1. Correctly: %d times\n", timesCorrect);
            printf("2. Incorrectly: %d times\n", timesIncorrect);
            sleep(3);
            showMenu();
            break;
        }//end case 3 (Show Correct/Incorrect)
        case 4:
        {
            printf("Goodbye\n");
            sleep(1);//Waits for 1 second
            clearScreen();
            exit(0); //Gracefully ends the program
            break;
        }//end case 4 (Exit)
        default:
        {
            printf("Invalid Option!\n");
            printf("Please enter a number between 1 and 4\n");
            sleep(2);
            showMenu();
            break;
        }//end default
    }//end switch
}

//Main funtion, runs when program starts, displays the menu
int main(){
    showMenu();
	return(0);
}//end main
