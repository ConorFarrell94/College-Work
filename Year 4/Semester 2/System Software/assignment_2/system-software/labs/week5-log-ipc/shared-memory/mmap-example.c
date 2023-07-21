#include <stdio.h>
#include <sys/mman.h>

// We allocate memory using mmap. Here we used PROT_READ | PROT_WRITE protection for reading and writing 
// to the mapped region. 
// We used the MAP_PRIVATE | MAP_ANONYMOUS flag. MAP_PRIVATE is used because the mapping region is not 
// shared with other processes, and MAP_ANONYMOUS is used because here, we have not mapped any file. 
// For the same reason, the file descriptor and the offset value is set to 0.

int main(){

int N=5;
int *ptr = mmap ( NULL, N*sizeof(int),
 PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, 0, 0 );

if(ptr == MAP_FAILED){
    printf("Mapping Failed\n");
    return 1;
}

for(int i=0; i<N; i++)
    ptr[i] = i*10;

for(int i=0; i<N; i++)
    printf("[%d] ",ptr[i]);

printf("\n");
// we free the shared memory
int err = munmap(ptr, 10*sizeof(int));
if(err != 0){
    printf("UnMapping Failed\n");
    return 1;
}

return 0;
}