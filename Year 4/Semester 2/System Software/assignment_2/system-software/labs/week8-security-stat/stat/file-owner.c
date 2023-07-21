#include <stdio.h>
#include <sys/stat.h>

int main(int argc, char* argv[]) {
   const char* const filename = argv[1];
   struct stat buf;

   stat (filename, &buf);
   
   if (buf.st_mode & S_IRUSR) { 
      printf("Owner can read %s\n", filename);
   }

   if (buf.st_mode & S_IWUSR) { 
      printf("Owner can modify %s\n", filename);
   }

   if (buf.st_mode & S_IXUSR) { 
      printf("Owner can execute %s\n", filename);
   }

   return 0;
}
