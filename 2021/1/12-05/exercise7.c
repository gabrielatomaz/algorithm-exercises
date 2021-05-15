#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    16.6.Ler uma matriz A 12 x 13 e divida todos os 13 elementos de cada uma das 12 linhas de A pelo valor
        do maior elemento daquela linha. Escrever a matriz A modificada.
*/

int main() {
    int i,
        j,
        biggestLineValue = 0,
        sizeLine = 12,
        sizeColumn = 13;
    float matrix[sizeLine][sizeColumn], result[sizeLine];

    for (i = 0; i < sizeLine; i++) {
        for (j = 0; j < sizeColumn; j++) {
            printf("Insira um valor para a linha %d e coluna %d: ", i + 1, j + 1);
            scanf("%f", &matrix[i][j]);
        }
    }

    for(i = 0; i < sizeLine; i++) {
        for (int j = 0; j < sizeColumn; j++) {
            if (matrix[i][j] > biggestLineValue) {
                biggestLineValue = matrix[i][j];
            }
        }
        result[i] = biggestLineValue;
        biggestLineValue = 0;
    }

    for (i = 0; i < sizeLine; i++) {
        for (j = 0; j < sizeColumn; j++) matrix[i][j] = matrix[i][j] / result[i];
    }
    
    
    for (i = 0; i < sizeLine; i++) {
        for (j = 0; j < sizeColumn; j++) printf("%f ", matrix[i][j]); 
        
        printf("\n");
    }
    
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
