#include <stdio.h>

int main() {
   
	int age, i;
   
	printf("Enter your age:\n");

	scanf("%d", &age);
   
	for(i=0; i<5 ; i++) {	
		printf("You are %d years old\n", age);
	}

	return 0;
 
}
