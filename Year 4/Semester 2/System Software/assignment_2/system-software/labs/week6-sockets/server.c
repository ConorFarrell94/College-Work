#include<stdio.h>      // for IO
#include<string.h>     //for strlen
#include<sys/socket.h> // for socket
#include<arpa/inet.h>  //for inet_addr
#include<unistd.h>     //for write
 
int main(int argc , char *argv[])
{
    int s; // socket descriptor
    int cs; // Client Socket
    int connSize; // Size of struct 
    int READSIZE;  // Size of sockaddr_in for client connection

    struct sockaddr_in server , client;
    char message[500];
     
    //Create socket
    s = socket(AF_INET , SOCK_STREAM , 0);
    if (s == -1)
    {
        printf("Could not create socket");
    } else {
    	printf("Socket Successfully Created!!");
    } 

    // set sockaddr_in variables
    server.sin_port = htons( 8082 ); // Set the prot for communication
    server.sin_family = AF_INET; // Use IPV4 protocol
    server.sin_addr.s_addr = INADDR_ANY; 
    // When INADDR_ANY is specified in the bind call, the  socket will  be bound to all local interfaces. 
    
     
    //Bind
    if( bind(s,(struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("Bind issue!!");
        return 1;
    } else {
    	printf("Bind Complete!!");
    }
     
    //Listen for a conection
    listen(s,3); 
    //Accept and incoming connection
    printf("Waiting for incoming connection from Client>>");
    connSize = sizeof(struct sockaddr_in);
     
    //accept connection from an incoming client
    cs = accept(s, (struct sockaddr *)&client, (socklen_t*)&connSize);
    if (cs < 0)
    {
        perror("Can't establish connection");
        return 1;
    } else {
    	printf("Connection from client accepted!!");
    }
     
    //Receive message from client
    /*while( (READSIZE = recv(cs , message , 1999 , 0)) > 0 )
    {
        //Send the message back to client
        write(cs , message , strlen(message));
    }*/
    
    while(1) {
        memset(message, 0, 500);
	//READSIZE = read(cs,message,500);
	READSIZE = recv(cs , message , 2000 , 0);
        printf("Client said: %s\n", message);
        //puts(message);
	write(cs , "What ??" , strlen("What ??"));
    }
 
    if(READSIZE == 0)
    {
        puts("Client disconnected");
        fflush(stdout);
    }
    else if(READSIZE == -1)
    {
        perror("read error");
    }
     
    return 0;
}
