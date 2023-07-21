#include <stdio.h>

main() {
   FILE *fp;

   fp = fopen("temp.txt", "w+");
   fprintf(fp, "Writing with fprintf...\n");
   fputs("Writing with fputs...\n", fp);
   fclose(fp);
}
