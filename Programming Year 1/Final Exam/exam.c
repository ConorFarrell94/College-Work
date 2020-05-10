/*

Author: Conor Farrell
Last changes made on: 30/04/20
Written in Atom for Windows 10 and compiled using GCC

This is a program designed to allow the end user to decide whether to manually
enter a four digit code or have the program randomly generate one. This code will then
be compared to a fixed authorised access code. The program will also enable
the user to encrypt and decrypt the code.

*/

#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <windows.h>

// Setting the max elements for the codes
#define MAX 4

void code_entry(int code_selection[]);
void random_code(int code_selection[]);
void code_encryption(int code_selection[]);
void code_decryption(int code_selection[]);
void checking_code(int*, int*);

// Counting correct and incorrect attempts
struct code_counter {int correct_code; int wrong_code;};
struct code_counter counting;

// Used to check if code has been encrypted
int encryption = 0;
// Used to make sure user enters/makes a code first
int code_entered = 0;
// Exit program decision
char exit_choice;

int main()
{

  int exit = 0;

  while(exit == 0)
  {
    int initialchoice;
    int code_selection[4];
    int access_code[4] = {4, 5, 2, 3};

    // Enabling the ability to randomly generate numbers
    srand(time(0));

    printf("Please choose an option from the list:\n\n");
    printf("1. Select your own code\n2. Randomly generate the code\n3. Encrypt your code\n");
    printf("4. Decrypt your code\n5. Check your code against the access code\n");
    printf("6. View number of attempts\n7. Exit the program\n");

    scanf("%d", &initialchoice);

    switch(initialchoice)
    {
      // Select your own code
      case 1:
        code_entry(code_selection);
        break;
      // Randomly generating the code
      case 2:
        random_code(code_selection);
        break;
      // Encrypt your code
      case 3:
        if(code_entered == 1)
        {
          code_encryption(code_selection);
        }
        if(code_entered == 0)
        {
          system("cls");

          printf("Please enter or generate a code first");

          Sleep(2500);
          system("cls");
        }
        break;
      // Decrypt your code
      case 4:
        if(code_entered == 1)
        {
          if(encryption == 0)
          {
            system("cls");

            printf("Please encrypt your code first\n");

            Sleep(2500);
            system("cls");
            break;
          }
          if(encryption == 1)
          {
            code_decryption(code_selection);
            break;
          }
        }
      if(code_entered == 0)
      {
        system("cls");

        printf("Please enter or generate a code first");

        Sleep(2500);
        system("cls");
      }
      // Check your code against the access code
      case 5:
        if(code_entered == 1)
        {
          if(encryption == 1)
          {
            system("cls");
            checking_code(code_selection, access_code);
            Sleep(2500);
            system("cls");
            break;
          }

          if(encryption == 0)
          {
            system("cls");

            printf("Please encrypt your code first\n");

            Sleep(2500);
            system("cls");
            break;
          }
        }
        if(code_entered == 0)
        {
          system("cls");

          printf("Please enter or generate a code first");

          Sleep(2500);
          system("cls");
        }
        break;
      // View number of attempts
      case 6:
            system("cls");

            printf("Succesfull attempts: %d\nFailed attempts: %d\n", counting.correct_code, counting.wrong_code);

            Sleep(2500);
            system("cls");

        break;
      // Exit the program
      case 7:
          system("cls"); // For some reason this clear screen wasn't working for me and I can't figure out why

          printf("Are you sure? (y/n)\n");
          scanf("%1s", &exit_choice);

          if(exit_choice == 'y' || exit_choice == 'Y')
          {
            exit = 1;
            break;
          }
          else
          {
            system("cls");
            break;
          }
      default:
          _flushall(); // The underscore was neccessary as it wouldn't work properly otherwise with my compiler
          system("cls");
        break;

    } // End switch(initialchoice)
  } // End while(exit == 0)
} // End main()

void code_entry(int *code_selection)
{
  int i = 0;

  system("cls");

  printf("Please enter 4 numbers\nTheses numbers must be between 0 and 9\n");
  for (i = 0; i < MAX; i ++)
  {
    scanf("%d", &*(code_selection + i));

    // Error checking for valid entry and removing the entry if it isnt valid
    if(*(code_selection + i) < 0 || *(code_selection + i) > 9)
    {
      system("cls");
      printf("Please choose numbers between 0 and 9.\n");
      Sleep(2500);
      system("cls");
      i --;
    }
  }

  system("cls");

  printf("Your code is:\n");
  for (i = 0; i < MAX; i ++)
  {
    printf("%d ", *(code_selection + i));
  }

  code_entered = 1; // Unlocking other options on main menu

  Sleep(2500);
  system("cls");
}

void random_code(int *code_selection)
{
  int i = 0;

  system("cls");

  for(i = 0; i < MAX; i ++)
  {
    *(code_selection+i) = rand()%MAX;
  }

  printf("Your code is:\n");
  for (i = 0; i < MAX; i ++)
  {
    printf("%d ", *(code_selection + i));
  }

  code_entered = 1; // Unlocking other options on main menu

  Sleep(2500);
  system("cls");
}

void code_encryption(int *code_selection)
{
  int i = 0;
  int temp_element;

  // Changing the 1st element with the 3rd
  temp_element = *(code_selection + 2);
  *(code_selection + 2) = *(code_selection + 0);
  *(code_selection + 0) = temp_element;

  // Changing the 2nd element with the 4th
  temp_element = *(code_selection + 3);
  *(code_selection + 3) = *(code_selection + 1);
  *(code_selection + 1) = temp_element;

  // Switching 10s to 0 and also adding 1 to every element
  for(i = 0; i < MAX; i ++)
  {
    (*(code_selection + i)) ++;

    if((*(code_selection + i)) == 10)
    {
      (*(code_selection + i)) = 0;
    }

  }

  system("cls");

  printf("Your encrypted code is:\n");
  for(i = 0; i < MAX; i ++)
  {
    printf("%d ", *(code_selection+i));
  }

  Sleep(2500);
  system("cls");

  encryption = 1; // Unlocking other options on main menu (encryption is complete)
}

void code_decryption(int *code_selection)
{
  int i = 0;
  int temp_element;

  // Taking one away from all elements and changing any -1 entries to 9
  for(i = 0; i < MAX; i ++)
  {
    (*(code_selection + i)) --;

    if((*(code_selection+i)) == -1)
    {
      (*(code_selection+i)) = 9;
    }
  }

  // Changing the 1st element with the 3rd
  temp_element = *(code_selection + 2);
  *(code_selection + 2) = *(code_selection + 0);
  *(code_selection + 0) = temp_element;

  // Changing the 2nd element with the 4th
  temp_element = *(code_selection + 3);
  *(code_selection + 3) = *(code_selection + 1);
  *(code_selection + 1) = temp_element;

  system("cls");

  printf("Your decrypted code is:\n");
  for(i = 0; i < MAX; i ++)
  {
    printf("%d ", *(code_selection+i));
  }

  Sleep(2500);
  system("cls");

  encryption = 0; // Setting encryption status to not complete
}

void checking_code(int *code_selection, int *access_code)
{
  int i = 0;
  int matching = 0;

  for(i = 0; i < MAX; i ++)
  {
    if((*(code_selection + i)) == (*(access_code + i)))
    {
      matching = matching + 1;
    }
  }

  if(matching == 4)
  {
    printf("Correct Code entered.\n");
    counting.correct_code ++; // Incrementing the amount of successful entries
  }
  if(matching != 4)
  {
    printf("Wrong Code entered.\n");
    counting.wrong_code ++; // Incrementing the amount of unsuccessful entries
  }
}
