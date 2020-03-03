/*

Assignment2.c

Author: Conor Farrell
Text Editor: Atom
Compiler: GCC for Windows 10

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
#include <windows.h>

#define MAXENTRIES 6 // Amount of numbers the user must enter

int i, j = 0; // Used in for loops
int winningNumbers[] = {1,3,5,7,9,11}; // Winning lotto numbers
int enteredNumbers[MAXENTRIES]; // Array for user selected numbers
int checkingNumbers[42]; // Array for frequency of numbers

void showNumbers(); // Displays the numbers chosen by the user
void insertionSort(int enteredNumbers[]); // Sort numbers in ascending order
void userInput(); // Get users selection of numbers
void matchingNumbers(); // Checks users numbers compare to winning numbers

int main()
{
  int initialSelection;
  int exit = 0;

  while (exit == 0)
  {
    int flag; // Enables the ability to check whether option 1 has been selected

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
        flag = 1;
        system("cls");

        userInput(); // Calling function userInput to get users selection of numbers

        system("cls");
        break;

      } // End case 1

    // 2. Show your numbers
      case 2:
      {
        if(flag == 1)
        {
          system("cls");

          showNumbers(); // Function to display users chosen numbers

          Sleep(1500);
          system("cls");

          break;
        }
        else
        {
          system("cls");
          printf("You must select option 1 first\n");

          Sleep(1500);
          system("cls");
          break;
        }
      } // End case 2

    // 3. Compare to winning numbers
      case 3:
      {
        if(flag == 1)
        {
          system("cls");

          matchingNumbers(); // Checks users numbers in comparison to winning numbers

          Sleep(1500);
          system("cls");

          break;
        }
        else
        {
          system("cls");
          printf("You must select option 1 first\n");

          Sleep(1500);
          system("cls");
          break;
        }
      } // End case 3

    // 4. Frequency of numbers
      case 4:
      {
        if(flag == 1)
        {
          for(i = 0; i < 42; i++)
          {
            if(*(checkingNumbers+i) != 0)
            {
              printf("%d has been entered %d times\n", i, *(checkingNumbers+i));
            }
          }
        Sleep(3000);
        system("cls");
        break;
        }
        else
        {
          system("cls");
          printf("You must select option 1 first\n");

          Sleep(1500);
          system("cls");
          break;
        }
      } // End case 4

    // 5. Sort your numbers
      case 5:
      {
        if(flag == 1)
        {
          insertionSort(enteredNumbers); // Call function to sort numbers in ascending order

          system("cls");

          printf("Your numbers in ascending order are: ");

          for(i = 0; i < MAXENTRIES; i++)
          {
            printf(" %d ", *(enteredNumbers+i));
          }

          Sleep(1500);
          system("cls");

          break;
        }
        else
        {
          system("cls");
          printf("You must select option 1 first\n");

          Sleep(1500);
          system("cls");
          break;
        }
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

  getchar();
  return 0;

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

void userInput()
{
  printf("Please enter your 6 numbers:\n");
  for(i = 0; i < MAXENTRIES; i++)
  {
    scanf("%d", &*(enteredNumbers+i));
    // If a decimal is entered this getchar and flushall disregards the decimal value
    getchar();
    _flushall();

    // Error checking to prevent duplicates being added
    for(j = 0; j < i; j++)
    {
      if(*(enteredNumbers+i) == *(enteredNumbers+j))
      {
        printf("Select a number not already used\n");
        i--; // Removes the duplicate entry
      } // End if
    } // End for loop that prevents duplicates

    // Error checking for valid integer entry by user
    if(*(enteredNumbers+i) <= 0 || *(enteredNumbers+i) > 42) // NEED TO ADD ERROR CHECKING FOR DECIMALS
    {
      printf("Invalid entry, please try again\n");
      getchar(); // Prevents invalid entry from continuously showing
      i--; // Removes the invalid entry
      *(checkingNumbers+*(enteredNumbers+i)) = *(checkingNumbers+*(enteredNumbers+i)) - 1;
    } // End error checking loop
    else
    {
       *(checkingNumbers+*(enteredNumbers+i)) = *(checkingNumbers+*(enteredNumbers+i)) + 1;
    }

  } // End user input for loop
}

void matchingNumbers()
{
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
    printf("Tough luck!\n");
  }
}
