/*

Author: Conor Farrell
Editor: Atom for Windows 10

Farthest pair in one dimension).
Write a program that, given an array a[] of N double values,
finds a farthest pair : two values whose difference is no smaller than
the difference of any other pair (in absolute value).

*/

#include <stdio.h>

int number = 5;
int i, j;
double d = 0;

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

  printf("The biggest difference is between %2.2lf and %2.2lf and it is %2.2lf\n",a[0], a[number-1], a[number-1] - a[0]);

} // End main()

double calculations(double a[])
{
  for (i = 0; i < number; i ++)
  {
      for (j = i + 1; j < number; j ++)
      {
          if (a[i] > a[j])
          {
              d = a[i];
              a[i] = a[j];
              a[j] = d;
          }
      }
  }
}
