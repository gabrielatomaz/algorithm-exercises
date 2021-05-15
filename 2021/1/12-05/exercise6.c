#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    16.1 Ler uma matriz M 5 x 5, calcular e escrever as seguintes somas:
        a) da linha 3 de M
        b) da coluna 2 de M
        c) da diagonal principal
        d) da diagonal secundária
        e) de todos os elementos da matriz
*/

int main() {
    int i,
        j,
        size = 5,
        sumMatrixLineThree = 0,
        sumMatrixColumnTwo = 0,
        sumMatrix = 0,
        sumDiagonalPrimary = 0,
        sumDiagonalSecondary = 0,
        matrix[size][size];

    for (i = 0; i < size; i++) {
        for (j = 0; j < size; j++) {
            printf("Insira um valor para a linha %d e coluna %d: ", i + 1, j + 1);
            scanf("%d", &matrix[i][j]);
        }
    }

    for (i = 0; i < size; i++) {
        sumMatrixLineThree += matrix[3][i];
        sumMatrixColumnTwo += matrix[i][2];

        for (j = 0; j < size; j++) {
            sumMatrix += matrix[i][j];
            
            if (i == j) sumDiagonalPrimary += matrix[i][j];

            if ((i + j) == (size - 1)) sumDiagonalSecondary += matrix[i][j];
        }
    }

    printf("sumMatrixLineThree: %d \n", sumMatrixLineThree);
    printf("sumMatrixColumnTwo: %d \n", sumMatrixColumnTwo);
    printf("sumMatrix: %d \n", sumMatrix);
    printf("sumDiagonalPrimary: %d \n", sumDiagonalPrimary);
    printf("sumDiagonalSecondary: %d \n", sumDiagonalSecondary);
    
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
