#include <stdio.h>
#include <stdlib.h>

void showVec(double vec[], int n) {
  int i;
  for (i=0; i<n; i++) {
    printf("vec[%d]=%.3f\n", i, vec[i]);
  }
  printf("\n");
}

int main(void) {

  double *vec = calloc(3, sizeof(double));

  vec[1] = 3.14;
  showVec(vec, 3);

  vec = realloc(vec, sizeof(double)*5);
  showVec(vec, 5);

  vec[3] = 7.77;
  showVec(vec, 5);

  free(vec);
  return 0;
}