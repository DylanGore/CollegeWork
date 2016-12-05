/*
	Lab Test 2 - Multi-Dimensional Arrays and setting their values

	Author: Dylan Gore
	Date: 5th December 2016
	OS Version: Windows
	Compiler: gcc 5.3.0
*/

#include <stdio.h>

#define ARRAY_LENGTH 5
#define ARRAY_WIDTH 5

int main(){

    int multi_array[ARRAY_LENGTH][ARRAY_WIDTH];
    int start_pos = 0; //start position for diagonal
    int end_pos = ARRAY_WIDTH - 1; //end position for diagonal
    int middle = ARRAY_WIDTH / 2;
    int new_line = 0;

    int counter0, counter1, counter2, counter5;
    counter0 = counter1 = counter2 = counter5 = 0;

    //Initialize the array with 5
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            multi_array[i][j] = 5;
        }
    }

    //Print the contents of the array
    printf("Initialize array to contain 5 in every position\n");
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            if(new_line == 5){
                printf("\n");
                new_line = 0;
            }//end if
            printf(" %d ", multi_array[i][j]);
            new_line++;
        }//end inner for
    }//end for

    //Set diagonals to 0
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            if(i == start_pos && j == start_pos){
                multi_array[start_pos][start_pos] = 0;
                multi_array[end_pos][start_pos] = 0;
                start_pos++;
                end_pos--;
            }//end if
        }//end inner for
    }//end for

    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            start_pos = 0;

        }//end inner for
    }//end for

    printf("\n\nSet diagonals to 0");
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            if(new_line == 5){
                printf("\n");
                new_line = 0;
            }//end if
            printf(" %d ", multi_array[i][j]);
            new_line++;
        }//end inner for
    }//end for

    //Set middle row and column to 1
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            multi_array[i][middle] = 1;
            multi_array[middle][j] = 1;
        }//end inner for
    }//end for


    //Print the contents of the array
    printf("\n\nSet both middle rows to 1");
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            if(new_line == 5){
                printf("\n");
                new_line = 0;
            }//end if
            printf(" %d ", multi_array[i][j]);
            new_line++;
        }//end inner for
    }//end for

    start_pos = 0;
    end_pos = ARRAY_LENGTH - 1;

    //Set corner elements to 2
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            multi_array[start_pos][end_pos] = 2;
            multi_array[end_pos][start_pos] = 2;

            multi_array[start_pos][start_pos] = 2;
            multi_array[end_pos][end_pos] = 2;
        }//end inner for
    }//end for


    //Print the contents of the array
    printf("\n\nSet corners to 2");
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int j = 0; j < ARRAY_WIDTH; j++){
            if(new_line == 5){
                printf("\n");
                new_line = 0;
            }//end if
            printf(" %d ", multi_array[i][j]);
            new_line++;
        }//end inner for
    }//end for


    //Begin counting
    for(int i = 0; i < ARRAY_LENGTH; i++){
        for(int  j = 0; j < ARRAY_WIDTH; j++){
            switch(multi_array[i][j]){
                case 0:
                {
                    counter0++;
                    break;
                }//end case 0
                case 1:
                {
                    counter1++;
                    break;
                }//end case 1
                case 2:
                {
                    counter2++;
                    break;
                }//end case 2
                case 5:
                {
                    counter5++;
                    break;
                }//end case 5
                default:
                {
                    printf("Error!");
                    break;
                }//end default
            }//end switch
        }//end inner for
    }//end for

    //Print counters
    printf("\n\n");
    printf("Number of 0s: %d\n", counter0);
    printf("Number of 1s: %d\n", counter1);
    printf("Number of 2s: %d\n", counter2);
    printf("Number of 5s: %d\n", counter5);

}//end main
