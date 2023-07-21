#include <stdio.h>

int main() {
  int i;
  for (i=1; i<=10; i++) {
    if ((i % 2) != 0) {
      printf("%d is odd\n", i);
    }
    else {
      printf("%d is even\n", i);
    }
  }
}