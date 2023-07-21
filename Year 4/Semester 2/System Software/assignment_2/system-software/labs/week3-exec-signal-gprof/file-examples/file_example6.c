#include <stdio.h>
#include <string.h>

int main () {

   FILE *input;
   char message[] = "Hello there!";
   char ptr [50];
   int len = strlen(message)+1;

   // since one character is 1 byte long, the size of each
   // element that needs to be read is 1
   int size = 1;
   
   // Opening file for reading and writing
   input = fopen("myfile.txt", "w+");
   fwrite(message, len, 1, input);

   // Determining the starting point of the file
   fseek(input, 0, SEEK_SET);
   
   // Reading data from file to ptr
   fread(ptr, len, size, input);
   printf("%s\n", ptr);
   fclose(input);
   
   return(0);
}