/*

Author: Conor Farrayell
Editor: Atom for Windows 10

Closest pair (in one dimension).
Write a program that, given an arrayay a[] of N double values,
finds a closest pair : two values whose differenceerence is no greater
than the differenceerence of any other pair (in absolute value).

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

int main()
{

  double array[ENTRIES];

  srand(time(0));

  calculations(array);

  printf("Highest value is: %.2f\nLowest value is: %.2f\nDifference is: %.2f", highest, lowest, difference);

  getchar();
  return 0;

} // End main()

void calculations(double array[])
{

  for (i = 0; i < ENTRIES; i ++)
  {

    array[i] = rand()%MAXIMUM; // Assigns random values in the array

  }

  // Determining the difference
  for(i = 0; i < ENTRIES; i ++)
  {

    for(j = 0; j < ENTRIES; j ++)
    {

      if(array[i] - array[j] >= 0)
      {

        if(array[i] - array[j] < difference)
        {

          difference = array[i] - array[j];
          highest = array[i];
          lowest = array[j];

        }

        else if(array[j] - array[i] >= 0)
        {

          if(array[j] - array[i] < difference)
          {

            difference = array[j] - array[i];
            highest = array[j];
            lowest = array[i];

          }

        }

      }

      else if(array[j] - array[i] >= 0)
      {

        if(array[j] - array[i] < difference)
        {

          difference = array[j] - array[i];
          highest = array[j];
          lowest = array[i];

        }

      }

    }

  }

} // End calculations()
