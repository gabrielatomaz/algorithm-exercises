
#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    Escreva	um	algoritmo	que	leia	uma	quantidade	indeterminada	de	idades	de	pessoas (condição	de	
    término	será	a	digitação	de	um	valor	negativo).	Conte	e	mostre	quantas	destas	pessoas	ainda	não	
    podem	votar (idade <	16	anos).
*/
typedef enum { false = 0, true = !false } bool;

int main() {
    int counter = 0,
        i,
        size,
        age,
        totalNonVoters;
    int *ages = (int*)malloc(size * sizeof(int));
    bool isValid = true;

    do {
        counter++;
        printf("Digite a idade da pessoa %d: ", counter);
        scanf("%d", &age);

        if (age >= 0) ages[counter] = age;
        else isValid = false;

    } while (isValid);

    for (i = 1; i < counter; i++) {
        age = ages[i];
        printf("Idade %d: %d\n", i, age);
        
        if (age < 16) totalNonVoters++;
    }

    printf("Total de não votantes: %d\n", totalNonVoters);

    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/