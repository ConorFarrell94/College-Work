#include <stdio.h>

int main() {
	int i;
	char *weekday[7] = {
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday",
		"Sunday"	
	};

	for(i=0; i<7 ; i++) {
		printf("I love %s\n", weekday[i]);		
	}
	
	printf("I hope you liked this example");	

	return 0;
}
