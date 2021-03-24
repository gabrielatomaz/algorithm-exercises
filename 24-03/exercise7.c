#include <stdio.h>
#include <stdlib.h>

int main() {
    int number;

    printf("Digite um número inteiro: ");  
    scanf("%d", &number); 
  
    if (number % 2 == 0) printf("%d é um número PAR\n", number);
    else printf("%d é um número IMPAR\n", number);

    if (number == 0) printf("Número igual a ZERO\n");
    else if (number > 0) printf("Número POSITIVO\n");
    else if (number < 0) printf("Número NEGATIVO\n");
}
