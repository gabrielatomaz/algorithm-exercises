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
        printf("%d - Informe o nome: ", i + 1);
        scanf("%s", agenda[i].nome);

        printf("%d - Informe o email: ", i + 1);
        scanf("%s", agenda[i].email);

        printf("%d - Informe o dia do nascimento: ", i + 1);
        scanf("%d", &agenda[i].diaNasc);

        printf("%d - Informe o mês do nascimento: ", i + 1);
        scanf("%d", &agenda[i].mesNasc);

        printf("%d - Informe o ano do nascimento: ", i + 1);
        scanf("%d", &agenda[i].anoNasc);

        printf("\n");
    }
}

void listarPelaData(Dados agenda[100], int dia, int mes, int ano) {
    printf("\nLista de pessoas que nasceram em %d/%d/%d\n", dia, mes, ano);
    for (int i = 0; i < 100; i++) {
        if (agenda[i].diaNasc == dia && agenda[i].mesNasc == mes && agenda[i].anoNasc == ano) {
            printf("%d - Nome: %s\n", i + 1, agenda[i].nome);
            printf("%d - Email: %s\n", i + 1, agenda[i].email);
        }

        printf("\n");
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