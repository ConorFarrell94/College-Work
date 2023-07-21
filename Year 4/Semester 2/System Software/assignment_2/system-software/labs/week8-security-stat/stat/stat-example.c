#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>


main(int argc, char *argv[])
{
   struct stat s;

   if (stat(argv[1], &s) == -1) {
        exit(EXIT_FAILURE);
   }

   printf("File type:");
   switch (s.st_mode & S_IFMT) {
    case S_IFBLK:  printf("block device\n"); break;
    case S_IFCHR:  printf("character device\n"); break;
    case S_IFDIR:  printf("directory\n"); break;
    case S_IFIFO:  printf("FIFO/pipe\n"); break;
    case S_IFLNK:  printf("symlink\n"); break;
    case S_IFREG:  printf("regular file\n"); break;
    case S_IFSOCK: printf("socket\n"); break;
    default: printf("unknown?\n"); break;
    }

   printf("I-node number: %ld\n", (long) s.st_ino);

   printf("Mode: %lo (octal)\n", (unsigned long) s.st_mode);

   printf("Link count: %ld\n", (long) s.st_nlink);
   printf("Ownership: UID=%ld   GID=%ld\n", (long) s.st_uid, (long) s.st_gid);

   printf("Preferred I/O block size: %ld bytes\n", (long) s.st_blksize);
   printf("File size: %lld bytes\n", (long long) s.st_size);
    printf("Blocks allocated: %lld\n", (long long) s.st_blocks);

   printf("Last status change: %s\n", ctime(&s.st_ctime));
   printf("Last file access: %s\n", ctime(&s.st_atime));
   printf("Last file modification: %s\n\n", ctime(&s.st_mtime));
 
   printf((s.st_mode & S_IRUSR)? "r":"-");

   printf("\n\n");

   exit(EXIT_SUCCESS);
}
