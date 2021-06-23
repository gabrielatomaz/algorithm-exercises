
/*
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Considere o código abaixo, e:
    a) C rie uma função para preencher as 100 posições do vetor agenda.
    b) C rie uma função para imprimir o nome e o email de todas as pessoas que nasceram
        na data informada no programa principal.
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct{
    char nome[200];
    int diaNasc; //dia nascimento
    int mesNasc; //mês nascimento
    int anoNasc; //ano nascimento
    char email[100];
} Dados;

int main() {
    Dados agenda[100];
    int dia, mes, ano;

    preencher(agenda);

    printf("Informe uma data.\nDigite o dia:");
    scanf("%d", &dia);

    printf("\nDigite o mes:");
    scanf("%d", &mes);

    printf("\nDigite o ano:");
    scanf("%d", &ano);

    listarPelaData(agenda, dia, mes, ano);

    return 0;
}


/*
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/