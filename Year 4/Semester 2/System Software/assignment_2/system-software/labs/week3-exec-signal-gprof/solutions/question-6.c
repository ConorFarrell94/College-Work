#include <stdio.h>

int main(void) {

    FILE *fp;
    char buf[260];// or char *buf, then use malloc - make index size appropriate length for anticipated line len.
    fp = fopen("temp.txt", "r");
    while(fgets(buf, sizeof(buf), fp)) //where sizeof(buf) is the length of line you anticipate reading
    {
        printf("%s", buf);
    }
    fclose(fp);  

   return 0;

}
