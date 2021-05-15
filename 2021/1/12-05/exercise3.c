#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    14.2 Preencher um vetor A de 10 elementos(índices de 0 a 9) com os números inteiros
    10,20,30,40,50,...,100. Escrever o vetor A após o seu total preenchimento.
*/

int main() {
    int i,
        size = 10,
        array[size];

    for (i = 0; i < size; i++) array[i] = (i + 1) * 10;

    for (i = 0; i < size; i++) printf("%d\n", array[i]);
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
