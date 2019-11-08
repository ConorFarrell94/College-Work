/*
assignment1.c

Date Assigned:  22 October 2019
Last Modified:  8 November 2019
Author:         Conor Farrell

Written in Atom editor and compiled using GCC in CMD for Windows 10

Program to simulate a maths quiz game with a maximum of 5 questions per round.
User will only have the ability to display results once a round has been completed.
This game will run until the user has decided he/she has played enough rounds.
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{

  int a, b, actual_answer, provided_answer, choice, results, input, correct, incorrect, questions;
  int roundcount = 1;
  int max = 50; // This is the maximum number that can be randomly generated

  srand(time(NULL)); // Uses the local time as seed

  printf("Welcome to my Mathematics Quiz Game!\n");
  printf("1. Play game\n");
  printf("2. Exit\n");
  scanf("%d", &choice);

  // Keep doing rounds until we say otherwise
  while(choice == 1)
  {

      correct = 0;
      incorrect = 0;

      printf("\nRound: %d\n", roundcount);
      printf("How many questions would you like? (1 to 5)\n");
      scanf("%d", &questions);

      // Check for the valid number of questions to be provided
      if (questions < 1 || questions > 5)
        {
          printf("\nPlease enter a valid question between 1 and 5.\n");
        }

      // Round has started as valid number of questions have been entered
      roundcount++;

      // Ask each question
      for(int i = 0; i < questions; i++)
      {

        printf("\nQuestion %d\n", i+1);

        // Get two random integers
        a = rand()%max;
        b = rand()%max;

        // Calculate the answers
        actual_answer = a + b; // Couldnt figure out how to randomise the operators

        printf("What is %d + %d?\n", a, b);
        scanf("%d", &provided_answer);

        printf("\nYou entered %d\n", provided_answer);

        if (actual_answer == provided_answer)// Checks the answers
            {
              printf("Correct! The answer is %d\n", actual_answer);
              correct++;
            }
        else
            {
              printf("Incorrect! The answer is %d\n", actual_answer);
              incorrect++;
            }

        printf("\n======================\n");// Cosmetic line break

      }// End for

      printf("\n1. Display results?\n2. Continue\n");/* The option to display
      results will only ever be unlocked once the user has completed a round */
      scanf("%d", &results);

      if(results == 1)
        {
          printf("You answered %d questions correctly and %d questions incorrectly\n", correct, incorrect);
          printf("Press enter to continue . . .\n");// Keeps the results on-screen until user decides to continue
          _flushall();
        }

      getchar();

      system("cls"); /* This clears the screen after each round. I used this to reduce clutter
      for when the user wants to play multiple rounds. "cls" ONLY works on windows, for linux
      you need "clear" */

      printf("Welcome to my Mathematics Quiz Game!\n");
      printf("1. Play next round\n2. Exit\n");
      scanf("%d", &choice); /* If the user enters 1 here they will be reset to the question count
      but the round counter will be increased by 1 */
      getchar();

  }// End while

  printf("Goodbye!\n");
  _flushall();// This prevent the terminal from automatically closing
  getchar(); // Until the user enters any additional input
  return 0;

}// End main
