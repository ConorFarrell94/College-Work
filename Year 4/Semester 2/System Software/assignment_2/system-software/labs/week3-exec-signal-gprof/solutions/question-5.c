#include <stdio.h>
#include <stdlib.h>

int main()
{
   char *name[50];
   FILE *fptr;
   fptr = fopen("temp.txt","a+");

   if(fptr == NULL)
   {
      printf("Error!");   
      exit(1);             
   }

   printf("Enter name: ");
   scanf("%s",&name);

   fprintf(fptr,"%s",name);
   fclose(fptr);

   return 0;
}