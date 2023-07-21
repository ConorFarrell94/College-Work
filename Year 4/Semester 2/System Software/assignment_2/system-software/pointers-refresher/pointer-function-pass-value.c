#include <stdio.h>

void myFun(int x) {
  x = x * 2;
}

int main(void) {
  // We assign the value 50 to the variable y
  int y = 50;
  printf("y=%d\n", y);
  // This is passing by value since we are handing over to myFun() the value of y (50)
  // Within myFun() we multiply the argument passed to it by 2 and exit. In main() when we print the value of y, after the function call to myFun(), it is still 50 (not 100).
  myFun(y);
  printf("y=%d\n", y);
  return 0;
}