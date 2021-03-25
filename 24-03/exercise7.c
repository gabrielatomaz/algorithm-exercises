#include <stdio.h>
#include <stdlib.h>

/* 
	Faça um algoritmo que leia um número inteiro e
	mostre uma mensagem indicando se este
	número é par ou ímpar, e se é positivo, negativo
	ou zero. 
*/

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
