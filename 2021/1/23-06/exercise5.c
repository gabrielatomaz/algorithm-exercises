#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Faça uma função recursiva que receba um número inteiro positivo impar N e retorne o
    fatorial duplo desse número. O fatorial duplo é definido como o produto de todos os
    números naturais ímpares de 1 até algum número natural ímpar N.
    Por exemplo: o fatorial duplo de 5 é 5!! = 1 ∗ 3 ∗ 5 = 15
*/

int doubleFactorial(int number) {
    if (number == 1) {
        return 1;
    }
    
    return number * doubleFactorial(number - 2);
}
int main() {
    int result = doubleFactorial(5);

    printf("O resultado é: %d\n", result);

    return 0;
}

/*
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/