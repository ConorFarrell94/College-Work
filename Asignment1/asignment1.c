/*
assignment1
22 October 2019
Conor Farrell
Written in Atom editor and compiled using GCC for windows 10
Program to simulate a maths quiz game
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{

char choice, results;
int input, correct, incorrect, questions, roundcount;
int answerone, answertwo, answerthree, answerfour, answerfive;

srand(time(NULL));
int a, b, c, d, e, f, g, h, i, j = rand();

answerone= a+b;
answertwo= c*d;
answerthree=e-f;
answerfour=g/h;
answerfive=i+j;

roundcount=0;

printf("Welcome to my Mathematics Quiz Game!\n");
printf("Would you like to play? (y/n)\n");
scanf(" %c", &choice);

if(choice == 'n' || choice == 'N')
{
  printf("Goodbye!");
}

while(choice == 'y' || choice == 'Y')
{
    roundcount++;
    correct=0;
    incorrect=0;

    printf("\nRound: %d\n", roundcount);
    printf("How many questions would you like? (1 to 5)\n");
    scanf("%d", &questions);

    switch(questions)
      {
        case 1 :
                printf("What is %d + %d?\n", a,b);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerone);
                if(input == answerone)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }
                break;

        case 2 :
                printf("What is %d + %d?\n", a,b);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerone);
                if(input == answerone)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d x %d?\n", c,d);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answertwo);
                if(input == answertwo)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }
                break;

        case 3 :
                printf("What is %d + %d?\n", a,b);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerone);
                if(input == answerone)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", c,d);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answertwo);
                if(input == answertwo)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", e,f);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerthree);
                if(input == answerthree)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }
                break;

        case 4 :
                printf("What is %d + %d?\n", a,b);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerone);
                if(input == answerone)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", c,d);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answertwo);
                if(input == answertwo)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", e,f);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerthree);
                if(input == answerthree)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", g,h);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerfour);
                if(input == answerfour)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }
                break;

        case 5 :
                printf("What is %d + %d?\n", a,b);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerone);
                if(input == answerone)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", c,d);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answertwo);
                if(input == answertwo)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", e,f);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerthree);
                if(input == answerthree)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", g,h);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerfour);
                if(input == answerfour)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }

                printf("What is %d + %d?\n", i,j);
                scanf("%d", &input);
                printf("You entered: %d\nThe correct answer is: %d\n", input, answerfive);
                if(input == answerfive)
                  {
                    correct++;
                  }
                  else
                  {
                    incorrect++;
                  }
                break;

        default :
                printf("Not a valid option\nPlease try again\n");
                break;
      } //end switch

    printf("\nDisplay results? (y/n)\n");
    scanf(" %c", &results);

    if(results == 'y' || results == 'Y')
    {
      printf("You answered %d questions correctly and %d questions incorrectly\n", correct, incorrect);
      printf("Press Enter to continue . . .");
      getchar();
    }

    getchar();
    _flushall();
    system("cls"); /* this clears the screen after each round. I used this to reduce clutter
    for when the user wants to play multiple rounds*/

    printf("Would you like to play again? (y/n)\n");
    scanf(" %c", &choice); /* if the user enters Y here they will be reset to the question count
    but the round counter will be increased by 1*/

    if(choice == 'n' || choice == 'N')
    {
      printf("Goodbye!");
    }


}//end while

return 0;

}//end main
