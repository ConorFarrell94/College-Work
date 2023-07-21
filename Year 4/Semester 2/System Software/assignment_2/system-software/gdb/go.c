#include <stdio.h>
#include <stdlib.h>
// gcc -g -o go go.c
// Note that we compiled this using the -g compiler flag. This prepares 
// the program to be used by the gdb debugger
// As an example, let’s change the code above to introduce 
// a bug that causes the program to crash. Let’s comment out line 12:

char *getWord(int maxsize)
{
  char *s;
//   s = calloc(sizeof(char), maxsize);
  printf("enter a string (max %d chars): ", maxsize);
  scanf("%s", s);
  return s;
}

void repeatWord(char *s, int n)
{
  int i;
  for (i=0; i<n; i++) {
    printf("%s\n", s);
  }
}

int *getVec(int vecsize)
{
  int *vec = malloc(sizeof(int)*vecsize);
  int i;
  for (i=0; i<vecsize; i++) {
    printf("enter value of vec[%d]:", i);
    scanf("%d", &vec[i]);
  }
  return vec;
}

void printVec(int *vec, int size)
{
  int i;
  printf("vec = {");
  for (i=0; i<size-1; i++) {
    printf("%d,", vec[i]);
  }
  printf("%d}\n", vec[size-1]);
}

int main(int argc, char *argv[])
{  
  char *myWord = getWord(256);
  repeatWord(myWord, 3);

  int *myVec = getVec(5);
  printVec(myVec, 5);

  free(myWord);
  free(myVec);

  return 0;
}   