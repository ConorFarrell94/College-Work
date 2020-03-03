/*

Write a program to generate M random integers and put them in an array, then count the number of N
Random Integers that matches one of the numbers in the Array, using sequential search.  Run your
program for M= 10, 100, 1000 and N= 10, 100, 1000

Author: Conor Farrell
Written and compiled on ATOM for Windows 10
Date: 25/2/2020

*/

#include <stdio.h>
#include <stdlib.h> // Required for the rand() function

int main()
{
  int max = 1000;
  int m = 0;
  int n = 0;
  int i;
  int j;

  printf("Choose which amount of numbers to generate for first array\n");
  scanf("%d", &m);

  printf("Choose which amount of numbers to generate for second array\n");
  scanf("%d", &n);

  int randomNumbers1[m];
  int randomNumbers2[n];

  for ( i = 0; i < m; i++)
  {
    randomNumbers1[i] = rand()%max;
  }

  for ( i = 0; i < m; i++)
  {
    randomNumbers2[i] = rand()%max;
  }

  int matching = 0;

  for(i = 0; i < m; i++)
  {
    for(j = 0; j < n; j++)
    {
      if(*(randomNumbers1+i) == *(randomNumbers2+j))
      {
        matching = matching + 1; // Keeping track of how many numbers have matched
        printf("\n%d is matching\n", randomNumbers2[j]); // Displaying which numbers are a match
      } // End if
    } // End inner for loop
  } // End outer for loop

  printf("\nThere are %d matching numbers in the arrays\n", matching); // Print total amount of matching numbers

  getchar();
  return 0;

} // End main()
