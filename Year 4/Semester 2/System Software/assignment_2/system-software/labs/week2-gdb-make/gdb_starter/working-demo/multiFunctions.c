#include <stdio.h>
void display1(char* message);
void display2(char* message);
void display3(char* message);

int main()
{
   printf("This in the main block\n");   
   char s[50] = "I live in program 1\n"; 
   display1(s);
   return 0;
}

void display1(char* message)
{
   int displaynum = 10;
   printf("This is function 1\n"); 
   printf("Message: %s\n", message);
   display2(message);
}

void display2(char* message)
{
   int displaynum = 20;	
   printf("This is function 2\n"); 
   printf("Message: %s\n", message);
   display3(message);
}

void display3(char* message)
{
   int displaynum = 30;
   printf("This is function 3\n"); 
   printf("Message: %s\n", message);
}
