#include<stdio.h>

int main(){
    char str3[15];
    printf("Enter name & surname:\n");
    // use get for getting multiword string
    fgets(str3, 15, stdin);
    printf("%s", str3);
}