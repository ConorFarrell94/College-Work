/*

Author: Conor Farrell
Editor: Atom for Windows 10

Farthest pair in one dimension).
Write a program that, given an array a[] of N double values,
finds a farthest pair : two values whose difference is no smaller than
the difference of any other pair (in absolute value).

*/

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define ENTRIES 100
#define MAXIMUM 1000

void calculations(double[ENTRIES]);

int i, j = 0;
double lowest = 0;
double highest = 0;
double difference = ENTRIES;
double t;
double time_taken;

int main()
{

  printf("Farthest Pair:\n");

  double array[ENTRIES];

  srand(time(0));

  calculations(array);

  printf("\nArray values are:\n");

  for ( i = 0; i < ENTRIES; i ++)
  {

    printf("%.2f\n", array[i]);

  }

  printf("\nHighest value is: %.2f\nLowest value is: %.2f\nDifference is: %.2f", highest, lowest, difference);

  getchar();
  return 0;

} // End main()

void calculations(double array[])
{
  t = clock();

  for ( i = 0; i < ENTRIES; i ++)
  {

    array[i] = rand()%MAXIMUM; // Assigns random values in the array

  }

  // Determining the difference
  for(i = 0; i < ENTRIES; i ++)
  {

    for(j = 0; j < ENTRIES; j ++)
    {

      if(array[i] - array[j] > difference)
      {

        difference = array[i] - array[j];
        highest = array[i];
        lowest = array[j];

      }

      else if(array[j] - array[i] > difference)
      {

        difference = array[j] - array[i];
        highest = array[j];
        lowest = array[i];

      }

    }

  }

  t=clock() - t;

  time_taken = ((double)t)/CLOCKS_PER_SEC;

  printf("\nTime taken: %lf", time_taken);

} // End calculations()
