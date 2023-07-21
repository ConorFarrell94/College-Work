#include <stdio.h>

long factorial(int);

int main()
{
   long f1;
   int f_num = 5;
   
   f1 = factorial(f_num);
}

long factorial(int f) {
   if(f == 0) {
      return f;
   } else {
      return f*factorial(f-1);
   }
}
