
/*
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Considere o código abaixo, e:
    a) Crie uma função para preencher as 100 posições do vetor agenda.
    b) Crie uma função para imprimir o nome e o email de todas as pessoas que nasceram
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

void preencher(Dados agenda[]) {
    for (int i = 0; i < 2; i++) {
        printf("Informe o nome: \n");
        scanf("%s", agenda[i].nome);

        printf("Informe o dia do nascimento: \n");
        scanf("%d", &agenda[i].diaNasc);

        printf("Informe o mês do nascimento: \n");
        scanf("%d", &agenda[i].mesNasc);

        printf("Informe o ano do nascimento: \n");
        scanf("%d", &agenda[i].anoNasc);

        printf("Informe o email: \n");
        scanf("%s", agenda[i].email);
    }
}

void listarPelaData(Dados agenda[], int dia, int mes, int ano) {
    for (int i = 0; i < 2; i++) {
        if (agenda[i].diaNasc == dia && agenda[i].mesNasc == mes && agenda[i].anoNasc == ano) {
            printf("Nome: %s\n", agenda[i].nome);
            printf("Email: %s\n", agenda[i].email);
        }
    }
}

int main() {
    Dados agenda[2];
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