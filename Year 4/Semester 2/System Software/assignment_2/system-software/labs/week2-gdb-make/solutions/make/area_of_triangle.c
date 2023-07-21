#include <math.h>

double area_of_triangle( double a, double b, double c )
{
   double s, area;
 
   s = (a+b+c)/2;
 
   area = sqrt(s*(s-a)*(s-b)*(s-c));
 
   return area;
}
