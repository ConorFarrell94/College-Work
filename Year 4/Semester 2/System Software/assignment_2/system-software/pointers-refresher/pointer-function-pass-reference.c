#include <stdio.h>

void myFun(int *x) {
  *x = *x * 2;
}



rather a pointer to an int (hence the star * notation). Now in our main() function, on line 10, we pass to myFun() not the value of y as in the previous code example, but rather the address of y, using the & notation. Now when myFun() is called, it uses pointer dereferencing to multiply the value pointed to by its argument x, by 2. Of course x is simply the address of y, which we passed to myFun(), and so the value pointed to by x is the value that we assigned to y, which is 50. So myFun() multiplies that value by 2 and assigns it using pointer dereferencing to *x, which is the value associated with y.
int main(void) {
  int y = 50;
  printf("y=%d\n", y);
  myFun(&y);
  printf("y=%d\n", y);
  return 0;
}