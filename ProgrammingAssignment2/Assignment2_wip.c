/*

Assignment2.c

Author: Conor Farrell
Text Editor: Atom

Lotto game program:
This program will allow a user to
enter their 6 selected numbers and give them a set of options, each performing a
specific requirement. It will store the 6 numbers in a 1-Dimensional array.
It will be modularised (i.e. use functions) and each task will be dealt in a separate
function. This program will display a simple menu to the user and each option in
the menu will be implemented by calling a separate function. The program will
only use pointer notation to access array elements.

*/

#include <stdio.h>
#include <math.h>
#include <windows.h>

#define MAXENTRIES 6 // Amount of numbers the user must enter
#define MAXTOTAL 42; // Maximun valid number

int i, j = 0; // Used in for loops
int winningNumbers[] = {1,3,5,7,9,11}; // Winning lotto numbers
int enteredNumbers[MAXENTRIES]; // Array for user selected numbers

void showNumbers(); // Displays the numbers chosen by the user
void insertionSort(); // Sort numbers in ascending order

int main()
{
  int initialSelection = 0;
  int exit = 0;

  while (exit == 0)
  {
    // Main menu
    printf("1. Select your numbers\n2. Show numbers\n");
    printf("3. Compare to winning numbers\n4. Frequency of numbers\n");
    printf("5. Sort your numbers\n6. Exit\n");
    scanf("%d", &initialSelection);

    switch(initialSelection)
    {
    // 1. Select your numbers
      case 1:
      {
        system("cls");
        printf("Please enter your 6 numbers:\n");
        for(i = 0; i < MAXENTRIES; i++)
        {
          scanf("%d", &*(enteredNumbers+i));

          // Error checking to prevent duplicates being added
          for(j = 0; j < i; j++)
          {
            if(*(enteredNumbers+i) == *(enteredNumbers+j))
            {
              printf("Select a number not already used\n");
              i--;
            } // End if
          } // End for loop that prevents duplicates

          // Error checking for valid integer entry by user
          if(*(enteredNumbers+i) <= 0 || *(enteredNumbers+i) > 42) // NEED TO ADD ERROR CHECKING FOR DECIMALS
          {
            printf("Invalid entry, please try again\n");
            getchar(); // Prevents invalid entry from continuously showing
            i--; // Removes the invalid entry
          } // End error checking loop

        } // End user input for loop

      system("cls");
      break;

      } // End case 1

    // 2. Show your numbers
      case 2:
      {
        system("cls");

        showNumbers();

        Sleep(1500);
        system("cls");

        break;
      } // End case 2

    // 3. Compare to winning numbers
      case 3:
      {
        system("cls");

        int matching = 0;

        for(i = 0; i < MAXENTRIES; i++)
        {
          for(j = 0; j < MAXENTRIES; j++)
          {
            if(*(enteredNumbers+i) == *(winningNumbers+j))
            {
              matching = matching + 1;
            }
          }
        }

        if(matching == 6)
        {
          printf("You have 6 matching numbers\n");
          printf("Congratulations, you've won the jackpot!\n");
        }
        if(matching == 5)
        {
          printf("You have 5 matching numbers\n");
          printf("Congratulations, you've won a new car\n");
        }
        if(matching == 4)
        {
          printf("You have 4 matching numbers\n");
          printf("You've won a weekend away!\n");
        }
        if(matching == 3)
        {
          printf("You have 3 matching numbers\n");
          printf("You've won a cinema pass!\n");
        }
        if(matching < 3)
        {
          printf("You have less than 3 matching numbers\n");
          printf("Tough luck\n");
        }

        Sleep(1500);
        system("cls");

        break;
      } // End case 3

    // 4. Frequency of numbers
      case 4:
      {

      } // End case 4

    // 5. Sort your numbers
      case 5:
      {
        insertionSort(enteredNumbers);

        system("cls");

        printf("Your numbers in ascending order are: ");

        for(i = 0; i < MAXENTRIES; i++)
        {
          printf(" %d ", *(enteredNumbers+i));
        }

        Sleep(1500);
        system("cls");

        break;
      } // End case 5

    // 6. Exit
      case 6:
      {
        exit = 1;
        break;
      } // End case 6

    default:
    {
      getchar();
      system("cls");
      printf("Not a valid option. Please try again\n");
      Sleep(1500);
      system("cls");
      break;
    }

    } // End switch

  } // End while exit == 0

} // End main()

void showNumbers()
{
  printf("Your chosen numbers are:\n");
  for(i = 0; i<MAXENTRIES; i++)
  {
    printf("%d ", *(enteredNumbers+i));
  }
}

void insertionSort(int enteredNumbers[])
{
    int key;

    for (i = 1; i < MAXENTRIES; i++)
    {
        key = *(enteredNumbers+i);
        j = i - 1;

        while (j >= 0 && *(enteredNumbers+j) > key)
        {
          *(enteredNumbers+j+1) = *(enteredNumbers+j);
          j = j - 1;
        }

        *(enteredNumbers+j+1) = key;
    }
}
