#include <stdio.h>

int main(void) {

   FILE *fp;
   char buff[255];
   int i;

   fp = fopen("temp.txt", "r");
   
   for (i = 0 ; i < 3 ; i++)
   {
      fgets(buff, 255, fp);
      printf("Line %d: %s\n", i , buff );
   }

   for (i = 0 ; i < 1000000 ; i++)
   {
      fgets(buff, 255, fp);
      printf("Line %d: %s\n", i , buff );
   }


   fclose(fp);
   return 0;

}
