#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {

   uid_t uid = geteuid();
   gid_t gid = getegid();

   printf("This process is associated with UID: %d and GID: %d", uid, gid);

}
