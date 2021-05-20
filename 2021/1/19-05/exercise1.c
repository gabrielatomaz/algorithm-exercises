#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    Veja o slide 18 e faça um programa que leia uma matriz 5x5 de números inteiros, 
    logo após, imprima os números que estão acima da diagonal principal, 
    incluindo a diagonal (imprima um X nas outras posições).
*/

int main() {
    int i,
        j,
        size = 5,
        matrix[size][size];

    for (i = 0; i < size; i++) {
        for (j = 0; j < size; j++) {
            printf("Insira um valor para a linha %d e coluna %d: ", i + 1, j + 1);
            scanf("%d", &matrix[i][j]);
        }
    }

    for (i = 0; i < size; i++) {
        for (j = 0; j < size; j++) {
            if (i < j || i == j) printf(" %d ", matrix[i][j]);
            else printf(" x ");
        }
        printf("\n");
    }
    
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
