#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    15.2 Ler um vetor R de 5 elementos contendo o gabarito da LOTO. A seguir ler um vetor A de 10
        elementos contendo uma aposta. A seguir imprima quantos pontos fez o apostador.
*/

int main() {
    int i,
        j,
        points,
        result[5],
        bet[10];

    for (i = 0; i < 5; i++) { 
        printf("Valor %d do gabarito da LOTO: ", i + 1);  
        scanf("%d", &result[i]); 
    }

    for (j = 0; j < 10; j++) { 
        printf("Valor %d da aposta: ", j + 1);  
        scanf("%d", &bet[j]); 
    }

    for (i = 0; i < 10; i++) 
        for (j = 0; j < 5; j++) 
            if (bet[i] == result[j]) points++;

    printf("%d pontos", points);

   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
