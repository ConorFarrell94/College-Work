#include <stdio.h>

int main (int argc, char *argv[])
{
  int age = 30;
  int *p;
  p = &age;
  printf("age=%d\n", age);
  printf("p=%p\n", p); // this is a memory address that points to where age is
  printf("*p=%d\n", *p); // we see how to access the value that a pointer points to
  printf("sizeof(p)=%ld\n", sizeof(p)); // we can see that our pointer p is 8 bytes.
  *p = 40; // this is called pointer dereferencing,  we can set the value of the variable pointed to by p to 40
  // Since p points to the age variable, we are setting the value of the address in memory corresponding to the age variable to 40


  printf("*p=%d\n", *p);
  printf("age=%d\n", age);
  return 0;
}
