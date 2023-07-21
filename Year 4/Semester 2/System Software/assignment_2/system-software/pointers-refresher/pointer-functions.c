#include <stdio.h>

int add( int a, int b ) {
  return a + b;
}

int subtract( int a, int b ) {
  return a - b;
}

int multiply( int a, int b ) {
  return a * b;
}

// we define a function doMath which returns nothing (hence void) and which takes three input arguments.
// The first argument is a pointer to a function. It’s actually more specific than that. It’s a pointer to a 
// specific kind of function: a function that returns an int, and that takes two int values as inputs.
// The (*fn) says this is a pointer to a function, and we shall refer to that function as fn. 
// The preceding int says, it’s a function that returns an int. 
// The subsequent (int a, int b) says it’s a function that takes two int arguments as inputs.
void doMath( int (*fn)(int a, int b), int a, int b ) {
  int result = fn(a, b);
  printf("result = %d\n", result);
}

int main(void) {

  int a = 2;
  int b = 3;

  doMath(add, a, b);
  doMath(subtract, a, b);
  doMath(multiply, a, b);

  return 0;
}