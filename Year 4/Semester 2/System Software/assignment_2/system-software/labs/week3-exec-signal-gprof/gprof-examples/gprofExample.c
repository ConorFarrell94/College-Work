//test_gprof.c
#include<stdio.h>

void new_func1(void)
{
    printf("\n Inside new_func1()\n");
    float i = 0;

    for(i=0;i<500000;i++) { printf("new_func1 %f\n", i); };

    return;
}

void func1(void)
{
    printf("\n Inside func1 \n");
    float i = 0;

    for(i=0;i<5000;i++) { printf("func1 %f\n", i); }
    new_func1();

    return;
}

static void func2(void)
{
    printf("\n Inside func2 \n");
    float i = 0;

    for(i=0;i<5000;i++) { printf("func2 %f\n", i); }
    return;
}

int main(void)
{
    printf("\n Inside main()\n");
    float i = 0;

    for(i=0;i<5000;i++) { printf("func main %f\n", i); }
    func1();
    func2();

    return 0;
}
