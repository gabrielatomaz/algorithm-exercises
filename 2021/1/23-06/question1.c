#include <stdio.h>
#include <stdlib.h>

/*  
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Escreva um programa, que crie e preencha um vetor com 1000 números inteiros
    aleatórios (variando de 0 até 3000, inclusive).
    Ainda nesse programa, crie um menu com as seguintes opções (utilize uma função
    para cada opção):
        Opção 1 - Imprima o maior número e sua respectiva posição no vetor.
        Opção 2 - Imprima todos os números pares do vetor (caso não encontre, informe o usuário).
        Opção 3 – Inverter os valores no próprio vetor (Obs.: Só armazenar na ordem inversa! Não
        imprima o vetor!). Após inverter, imprima para o usuário: “O conteúdo do vetor foi invertido”
        Opção 4 – Imprimir o vetor.
        Opção 5 - Sair.
*/

#define SIZE 1000

void printBiggestValue(int array[]) {
    int biggestValue = array[0];
    int indexOfBiggestValue = 0;
    for (int i = 1; i < SIZE; i++) {
        if (biggestValue < array[i]) {
            biggestValue = array[i];    
            indexOfBiggestValue = i;
        }
    }

    printf("Maior número: %d\n", biggestValue);
    printf("Posição no vetor: %d\n", indexOfBiggestValue);
}

void printEvenNumbers(int array[]) {
    int hasEvenNumber = 0;

    for (int i = 0; i < SIZE; i++) {
        if (array[i] % 2 == 0) {
            hasEvenNumber = 1;

            printf("%d\n", array[i]);
        }
    }

    if (hasEvenNumber == 0) {
        printf("Nenhum valor par foi encontrado no vetor.\n");
    }
}

int* reverseArray(int array[]) {
    for (int low = 0, high = SIZE - 1; low < high; low++, high--) {
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }

    printf("O conteúdo do vetor foi invertido!\n");

    return array;
}

void printArray(int array[]) { 
    printf("Valores do vetor: \n");

    for (int i = 0; i < SIZE; i++) {
        printf("%d\n", array[i]);
    }
}

int main() {
    int *array,
        menu = 0;

    for (int i = 0; i < SIZE; i++) {
        array[i] = rand() % 3001;
    }

    while (menu != 5) {
        printf("Opção 1 - Imprima o maior número e sua respectiva posição no vetor.\n");
        printf("Opção 2 - Imprima todos os números pares do vetor.\n");
        printf("Opção 3 – Inverter os valores no próprio vetor. \n");
        printf("Opção 4 – Imprimir o vetor. \n");
        printf("Opção 5 - Sair. \n");
        scanf("%d", &menu);

        switch (menu) {
            case 1:
                printBiggestValue(array);
                
                break;
            case 2:
                printEvenNumbers(array);

                break;
            case 3:
                array = reverseArray(array);

                break; 
            case 4:
                printArray(array);
                break;
            case 5: 
                break;
            default:
                printf("Opção inválida! Tente novamente.\n");

                break;
        }
    }

    return 0;
}


/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/