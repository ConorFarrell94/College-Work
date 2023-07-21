#include <stdio.h>
#include <stdlib.h>

typedef struct {
  int year;
  int month;
  int day;
} date;

int main(void) {

  date *mylist = malloc(sizeof(date) * 10);

  mylist[0].year = 2012;
  mylist[0].month = 1;
  mylist[0].day = 15;

  int i;
  for (i=1; i<10; i++) {
    mylist[i].year = 2012-i;
    mylist[i].month = 1 + i;
    mylist[i].day = 15 + i;
  }

  for (i=0; i<10; i++) {
    printf("mylist[%d] = %d/%d/%d\n", i, mylist[i].day, mylist[i].month, mylist[i].year);
  }

  free(mylist);
  return 0;
}