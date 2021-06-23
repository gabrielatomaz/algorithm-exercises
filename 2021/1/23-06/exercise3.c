#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Faça uma função que receba uma string contendo uma frase. A função deverá
    contar a quantidade de palavras existentes nessa frase. (Obs.: A frase pode conter
    mais espaços que o normal).
*/

int countWords(char phrase[]) {
    int counter = 1;

    for (int i = 0; phrase[i] != '\0'; i++) {
        if (phrase[i] == ' ' && phrase[i + 1] != ' ') {
            counter++;    
        }
    }

    return counter;
}

int main() {
    int totalWords = countWords("Essa é uma frase!");

    printf("Número de palavras: %d\n", totalWords);

    totalWords = countWords("Essa     é uma    frase!");

    printf("Número de palavras: %d\n", totalWords);

    return 0;
}

/*
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/