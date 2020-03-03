/*
Write an algorithm in C to merge the contents of two sorted lists.
Assume that both lists are represented using arrays.
The result array should be sorted.
*/

#include <stdio.h>
#include <time.h>

#define AMOUNT 5
#define MERGED 10

int i, j = 0;
double t;
double time_taken;

void insertionSort(int arr1[]);
void insertionSort(int arr2[]);

int arr1[AMOUNT];
int arr2[AMOUNT];
int arr3[MERGED];

int main()
{
  printf("Enter 5 numbers for first array:\n");
  for(i = 0; i < AMOUNT; i++)
  {
    scanf("%d", &arr1[i]);
  }

  insertionSort(arr1);

  printf("Enter 5 numbers for second array:\n");
  for(i = 0; i < AMOUNT; i++)
  {
    scanf("%d", &arr2[i]);
  }

  t = clock();

  insertionSort(arr2);

  printf("\nArray 1 sorted: ");
  for(i = 0; i < AMOUNT; i++)
  {
    printf("%d", arr1[i]);
  }

  printf("\nArray 2 sorted: ");
  for(i = 0; i < AMOUNT; i++)
  {
    printf("%d", arr2[i]);
  }

  for(i = 0; i < AMOUNT; i++)
  {
    arr3[i] = arr1[i];
  }

  for(i = 0, j = AMOUNT; j < MERGED && i < AMOUNT; i++, j++)
  {
    arr3[j] = arr2[i];
  }

  insertionSort(arr3);

  printf("\nArrays merged and sorted: ");
  for(i = 0; i < MERGED; i++)
  {
    printf("%d", arr3[i]);
  }

  t=clock() - t;
  time_taken = ((double)t)/CLOCKS_PER_SEC;

  printf("\nTime taken: %lf", time_taken);

} // End main()

void insertionSort(int array[])
{
    int key;

    for (i = 1; i < AMOUNT; i++)
    {
        key = *(array+i);
        j = i - 1;

        while (j >= 0 && *(array+j) > key)
        {
          *(array+j+1) = *(array+j);
          j = j - 1;
        }

        *(array+j+1) = key;
    }
}
