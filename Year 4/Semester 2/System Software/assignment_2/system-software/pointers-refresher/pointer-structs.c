#include <stdio.h>
#include <stdlib.h>

// we define a struct that contains three int values: year, month and day. 
// We use typedef to name our new struct type date.
typedef struct {
  int year;
  int month;
  int day;
} date;

int main(void) {

  // we declare a new variable today to be a pointer to date. 
  date *today;
  // we use malloc() to allocate a block of memory (on the heap) to store one date struct.
  today = (date*)malloc(sizeof(date));

  // the explicit way of accessing fields of our struct
  // this uses explicit pointer syntax. S
  // for example the expression (*today).day means, dereference the today pointer and then access the day field 
  // of the thing you find there (which will be a date struct).
  (*today).day = 15;
  (*today).month = 6;
  (*today).year = 2022;

  printf("the longhand way of displaying date is %d/%d/%d\n", (*today).day, today->month, today->year);

  // the more readable shorthand way of doing it
  today->day = 15;
  today->month = 6;
  today->year = 2023;

  printf("the readable way of displaying date is %d/%d/%d\n", today->day, today->month, today->year);

  free(today);

  return 0;
}