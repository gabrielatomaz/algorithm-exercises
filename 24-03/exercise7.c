#include <stdio.h>
#include <stdlib.h>

int main() {
    int number;

    printf("Digite um número inteiro: ");  
    scanf("%d", &number); 
  
    if (number % 2 == 0) printf("%d é um número PAR e ", number);
    else printf("%d é um número IMPAR e ", number);

    if (number == 0) printf("igual a ZERO");
    else if (number > 0) printf("POSITIVO");
    else if (number < 0) printf("NEGATIVO");

    return 0;
}
