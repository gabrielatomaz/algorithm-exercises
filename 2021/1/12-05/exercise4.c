#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    14.3 Preencher um vetor B de 10 elementos com 10 se o índice do elemento for ímpar, e 20 se for par.
    Escrever o vetor B após o seu total preenchimento.
*/

int main() {
    int i,
        size = 10,
        array[size];

    for (i = 0; i < size; i++) {
        if (i % 2 == 0) array[i] = 20;
        else array[i] = 10;
    }

    for (i = 0; i < size; i++) printf("%d\n", array[i]);
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
