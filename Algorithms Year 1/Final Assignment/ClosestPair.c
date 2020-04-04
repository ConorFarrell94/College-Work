/*

Author: Conor Farrell
Editor: Atom for Windows 10

Closest pair (in one dimension).
Write a program that, given an array a[] of N double values,
finds a closest pair : two values whose difference is no greater
than the difference of any other pair (in absolute value).

*/


#include <stdio.h>

int number = 5;
double d = 0;
int counter = 0;
int i, j;

double calculations();

int main()
{

  double a[number];

  printf("Enter %d number(s):\n", number);

  for (i = 0; i < number; i ++)
  {
      scanf("%lf", &a[i]);
  }

  double calculations(double a[]);

  printf("Smallest difference is between %2.2f and %2.2lf and it is %2.2lf",a[counter+1], a[counter], a[counter+1] - a[counter]);

} // End main()

double calculations(double a[])
{

  for (i = 0; i < number; i ++)
  {
      for (j = i + 1; j < number; j ++) {
          if (a[i] > a[j])
          {
              d = a[i];
              a[i] = a[j];
              a[j] = d;
          }
      }
  };

  if (((a[4] - a[3]) < (a[3] - a[2])) && ((a[4] - a[3]) < (a[2] - a[1])) && ((a[4] - a[3]) < (a[1] - a[0])))
  {
      counter = 3;
  }

  else if (((a[4] - a[3]) > (a[3] - a[2])) && ((a[3] - a[2]) < (a[2] - a[1])) && ((a[3] - a[1]) < (a[1] - a[0])))
  {
      counter = 2;
  }

  else if (((a[4] - a[3]) > (a[2] - a[1])) && ((a[4] - a[3]) > (a[2] - a[1])) && ((a[2] - a[1]) < (a[1] - a[0])))
  {
      counter = 1;
  }

  else if (((a[1] - a[0]) < (a[3] - a[2])) && ((a[1] - a[0]) < (a[2] - a[1])) && ((a[4] - a[3]) > (a[1] - a[0])))
  {
      counter = 0;
  }

  printf("Smallest difference is between %lf and %lf and it is %lf\n",a[counter+1], a[counter], a[counter+1] - a[counter]);

} // End calculations()
