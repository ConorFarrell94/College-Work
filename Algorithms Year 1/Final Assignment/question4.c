/*

Author: Conor Farrell
Editor: Atom for Windows 10

Implement each of the following operations on an array so that the time it
takes does not depend on the array’s size n
(i)	Delete the ith element of an array (1 ≤ i ≤ n)
(ii)	Delete the ith element of a sorted array

*/

#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define MAXIMUM 100

void insertionSort(int array[], int entries);

int i, j = 0;
double t;
double time_taken;

int main()
{

  srand(time(0));

  int location, entries;

  printf("How many entries would you like in the array?\n");
  scanf("%d", &entries);

  int array[entries];

  t = clock();

  for (i = 0; i < entries; i ++)
  {

    array[i] = rand()%MAXIMUM; // Assigns random values in the array

  }

  insertionSort(array, entries);

  printf("Array is generated\n");

  printf("Printing.\n\n");

  for (i = 0; i < entries; i ++)
  {

    printf("Element %d is: %d\n", i, array[i]);

  }

  printf("\nComplete.\n\n");

  printf("Which element would you like to delete?\n");
  scanf("%d", &location);

  for (i = location - 1; i < entries - 1; i ++)
  {

    array[i] = array[i + 1];

  }

  insertionSort(array, entries);

  printf("Final array:\n");

  for (i = 0; i < entries - 1; i ++)
  {

    printf("Element %d is: %d\n", i, array[i]);

  }

  t=clock() - t;
  
  time_taken = ((double)t)/CLOCKS_PER_SEC;

  printf("\nTime taken: %lf", time_taken);

} // End main()

void insertionSort(int array[], int entries)
{
    int key;

    for (i = 1; i < entries; i ++)
    {

        key = *(array + i);
        j = i - 1;

        while (j >= 0 && *(array + j) > key)
        {

          *(array + j + 1) = *(array + j);
          j = j - 1;

        }

        *(array + j + 1) = key;

    }

}
