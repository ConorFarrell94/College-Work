#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
 
#define NUM_THREADS 3 
  
pthread_mutex_t lock_x; 

// function to run in the thread 
void *functionThread(void *arg) {
  printf("hello from the thread function, thread id: %d\n", pthread_self());
  
  // get access to the lock
  pthread_mutex_lock(&lock_x);

  printf("Do Something here \n");

  // release the lock
  pthread_mutex_unlock(&lock_x);
  
  // kill the thread
  pthread_exit(NULL);
}
 
int main(int argc, char **argv) {
  // init an array of threads
  pthread_t thr[NUM_THREADS];
  int rc;
 
  /* create the lock */
  pthread_mutex_init(&lock_x, NULL);
 
  /* create threads */
  if ((rc = pthread_create(&thr[0], NULL, functionThread, NULL))) {
    printf("Error creating thread");
    return EXIT_FAILURE;
  }
  if ((rc = pthread_create(&thr[1], NULL, functionThread, NULL))) {
    printf("Error creating thread");
    return EXIT_FAILURE;
  }
  if ((rc = pthread_create(&thr[2], NULL, functionThread, NULL))) {
    printf("Error creating thread");
    return EXIT_FAILURE;
  }

  pthread_join(thr[0], NULL);
  pthread_join(thr[1], NULL);
  pthread_join(thr[2], NULL);
 
  return EXIT_SUCCESS;
}

