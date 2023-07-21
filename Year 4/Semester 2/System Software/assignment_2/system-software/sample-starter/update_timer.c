#include <syslog.h>
#include <stdio.h>
#include <time.h>


// again you can use this if you want to update it to the next day
void update_timer(struct tm *due_date) {
	printf("CURRENT DAY IS %d; CHANGING TIMER TO TOMMOROW'S DAY", due_date -> tm_mday);
	due_date -> tm_mday += 1;
	//mktime will convert the struct due_date into normal date format
	//i.e. if day is greater than 31 will make it day 1
  	mktime(due_date);	
	printf("TIMER UPDATED, DUE NEXT DAY, %d", due_date -> tm_mday);
}