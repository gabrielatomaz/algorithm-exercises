#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    1. O que é um vetor? Forneça um exemplo.
    2. Para criar um laço de repetição que auxilie no preenchimento de vetor. 
        Qual é o melhor comando (for, while ou do...while)?
*/

int main() {
    int i, // contador 
        size = 3, // tamanho do array
        array[size]; // declara o array com tamanho do valor da variável size (3)

    for (i = 0; i < size; i++) { // faz um looping de 0 a 2 e com auxílio do contador i
        array[i] = i + 1; // atribui um valor para cada posição do array
    }

    for (i = 0; i < size; i++) { // faz um looping de 0 a 2 e com auxílio do contador i
        printf("%d\n", array[i]); // apresenta na tela todos valores do array 
    }

    /* 
        o "for" abaixo cria um contador "j" e atribui o valor "0" a ele.
        a cada looping, é verificado se "j" é menor que "3" (tamanho do array), 
        assim, enquanto isso for verdade, o looping continua.
        além disso, a cada looping, o contador "j" é incrimentado.
        dessa forma, é possível acessar as posições do array
        com o auxílio do contador "j".
    */
    for (int j = 0; j < 3; j++) {
        array[j] = j + 1;
    }
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
