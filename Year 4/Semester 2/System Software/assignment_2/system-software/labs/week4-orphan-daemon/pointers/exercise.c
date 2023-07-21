#include <stdio.h>
#include <stdlib.h>
 
typedef struct {
  double *data;
  int nrows;
  int ncols;
} Matrix;

void printmat(Matrix *M);
void matrixmult(Matrix *A, Matrix *B, Matrix *C);
Matrix *createMatrix(int nrows, int ncols);
void destroyMatrix(Matrix *M);

int main(int argc, char *argv[])
{
  
  Matrix *A = createMatrix(3, 2);
  //Uncomment the following code when you implement createMatrix
  //otherwise it will give a segmention fault if createMatrix
  //is not implemented correctly 
  /*
  A->data[0] = 1.2;
  A->data[1] = 2.3;
  A->data[2] = 3.4;
  A->data[3] = 4.5;
  A->data[4] = 5.6;
  A->data[5] = 6.7;
  printmat(A);
  

  Matrix *B = createMatrix(2, 3);
  B->data[0] = 5.5;
  B->data[1] = 6.6;
  B->data[2] = 7.7;
  B->data[3] = 1.2;
  B->data[4] = 2.1;
  B->data[5] = 3.3;
  printmat(B);

  Matrix *C = createMatrix(3, 3);
  matrixmult(A, B, C);
  printmat(C);

  destroyMatrix(A);
  destroyMatrix(B);
  destroyMatrix(C);
  */
  return 0;
}

// your code goes below...


Matrix *createMatrix(int nrows, int ncols)
{
  // fill in the code here
}

void destroyMatrix(Matrix *M)
{
  // fill in the code here
}

void printmat(Matrix *M)
{
  // fill in the code here
  printf("so far printmat does nothing\n");
}

void matrixmult(Matrix *A, Matrix *B, Matrix *C)
{
  // fill in the code here
  printf("so far matrixmult does nothing\n");
}