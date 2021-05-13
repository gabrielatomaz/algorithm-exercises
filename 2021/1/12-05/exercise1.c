#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    Em aula, foi mostrado como imprimir um vetor de 10 posições (na ordem que foi digitado). 
    Faça um código em C que o usuário preencha um vetor de 10 posições e o seu programa imprima ao contrário 
    (ordem inversa ao digitado).
*/

int main() {
    int i,
        size = 10,
        array[size];

    for (i = 0; i < size; i++) { 
        printf("Atribua um valor ao vetor na posição %d: ", i);  
        scanf("%d", &array[i]); 
    }

    for (i = size - 1; i >= 0; i--) { 
        printf("%d\n", array[i]); 
    }
   
    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
