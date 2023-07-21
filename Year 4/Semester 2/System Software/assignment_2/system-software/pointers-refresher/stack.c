#include <stdio.h>

double multiplyByTwo (double input) {
  // twice is pushed onto stack when called  
  double twice = input * 2.0;
  // as soon as twice returns it is popped off stack and gone forever
  return twice;
}

int main (int argc, char *argv[])
{

  // These three variables are pushed onto the stack 
  int age = 30;
  double salary = 12345.67;
  double myList[3] = {1.2, 2.3, 3.4};

  printf("double your salary is %.3f\n", multiplyByTwo(salary));

  // when the main function exists, the 3 variables we added are popped off the stack
  return 0;
}