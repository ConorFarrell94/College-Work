#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

void *print_message_function( void *ptr );

main()
{
     pthread_t thread1, thread2, thread3;
     const char *message1 = "\nHello from Thread 1\nGoodbye From Thread 1";
     const char *message2 = "\nHello From Thread 2\nGoodbye From Thread 2";
     const char *message3 = "\nHello From Thread 3\nGoodbye From Thread 3";

     int  iret1, iret2, iret3;

     // create the threads
     iret1 = pthread_create( &thread1, NULL, print_message_function, (void*) message1);
     if(iret1)
     {
         fprintf(stderr,"Error - pthread_create() return code: %d\n",iret1);
         exit(EXIT_FAILURE);
     } 
     // pthread_join( thread1, NULL);
     
     iret2 = pthread_create( &thread2, NULL, print_message_function, (void*) message2);
     if(iret2)
     {
         fprintf(stderr,"Error - pthread_create() return code: %d\n",iret2);
         exit(EXIT_FAILURE);
     }

     iret3 = pthread_create( &thread3, NULL, print_message_function, (void*) message3);
     if(iret3)
     {
         fprintf(stderr,"Error - pthread_create() return code: %d\n",iret3);
         exit(EXIT_FAILURE);
     }


    // wait for threads
     pthread_join( thread1, NULL); 
     pthread_join( thread2, NULL); 
     pthread_join( thread3, NULL);
     
     exit(EXIT_SUCCESS);
}

void *print_message_function( void *ptr )
{
     char *message;
     message = (char *) ptr;
     printf("%s \n", message);
     // kill the thread
     pthread_exit(NULL);
}
