#include <stdio.h>
#include <stdlib.h>

/*
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Considere o código abaixo, e:
    a) Crie uma função para preencher as 100 posições do vetor agenda.
    b) Crie uma função para imprimir o nome e o email de todas as pessoas que nasceram
        na data informada no programa principal.
*/

typedef struct{
    char nome[200];
    int diaNasc; //dia nascimento
    int mesNasc; //mês nascimento
    int anoNasc; //ano nascimento
    char email[100];
} Dados;

void preencher(Dados agenda[100]) {
    for (int i = 0; i < 100; i++) {
        Dados dado = agenda[i];

        printf("Informe o nome: \n");
        scanf("%s", dado.nome);

        printf("Informe o dia do nascimento: \n");
        scanf("%d", &dado.diaNasc);

        printf("Informe o mês do nascimento: \n");
        scanf("%d", &dado.mesNasc);

        printf("Informe o ano do nascimento: \n");
        scanf("%d", &dado.anoNasc);

        printf("Informe o email: \n");
        scanf("%s", dado.email);
    }
}

void listarPelaData(Dados agenda[100], int dia, int mes, int ano) {
    for (int i = 0; i < 100; i++) {
        Dados dado = agenda[i];
        if (dado.diaNasc == dia && dado.mesNasc == mes && dado.anoNasc == ano) {
            printf("Nome: %s\n", dado.nome);
            printf("Email: %s\n", dado.email);
        }
    }
}

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