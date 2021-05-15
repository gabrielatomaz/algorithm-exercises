#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    17.1 Ler uma matriz M 6 x 6. Copiar para um vetor M o maior elemento de cada linha da matriz. Após o
    término da cópia imprimir o vetor M.
*/

int main() {
    int i,
        j,
        biggestLineValue = 0,
        size = 6,
        result[size],
        matrix[size][size];

    for (i = 0; i < size; i++) {
        for (j = 0; j < size; j++) {
            printf("Insira um valor para a linha %d e coluna %d: ", i + 1, j + 1);
            scanf("%d", &matrix[i][j]);
        }
    }

    for(i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (matrix[i][j] > biggestLineValue) {
                biggestLineValue = matrix[i][j];
            }
        }
        result[i] = biggestLineValue;
        biggestLineValue = 0;
    }

    for (i = 0; i < size; i++) printf("%d \n", result[i]);
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
