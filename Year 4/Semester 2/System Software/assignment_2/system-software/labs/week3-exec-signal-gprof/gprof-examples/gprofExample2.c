#include<stdio.h>
#include <unistd.h>

void func4(void)
{
    printf("\n Inside func4() \n");
    int count;
    for(count=0;count<=65535;count++);
}

void func3(void)
{
    printf("\n Inside func3() \n");
    int count;
    for(count=0;count<=2000000000;count++);
	/*{
		printf("Iteration: %d\n", count);
	}*/
}

void func2(void)
{
    printf("\n Inside func2() \n");
    int count = 0;
    for(count=0;count<=4095;count++);

    func3();
}

void func1(void)
{
    printf("\n Inside func1() \n");
    int count;
    for(count=0;count<=16777215;count++);

    func2();
}

int main(void)
{
    printf("\n main() starts...\n");
    int count;
    for(count=0;count<=2000000000;count++);

    func1();
    func4();
    printf("\n main() ends...\n");

    return 0;
}


