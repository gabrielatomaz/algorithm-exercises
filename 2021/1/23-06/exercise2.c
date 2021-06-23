#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543

    Faça uma função que receba uma matriz 50 x 10 que se refere respostas de 10
    questões de múltipla escolha, referentes a 50 alunos. A função receberá também um
    vetor de 10 posições contendo o gabarito de respostas que pode ser ‘a’, ‘b’, ‘c’ ou ‘d’.
    A função deverá comparar as respostas de cada candidato com o gabarito e imprimir
    a sua respectiva nota (de 0 a 10).
*/

#define ROW 50
#define COL 10

void printGrades(char matrix[ROW][COL], char answers[COL]) {
    int grade = 0;
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            if (matrix[i][j] == answers[j]) {
                grade++;
            }
        }

        printf("Aluno %d = Nota %d\n", i + 1, grade);
        grade = 0;
    }
}

int main() {
    char matrix[ROW][COL],
        answers[COL];
    
    for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
            printf("Digite a resposta da questão %d para o aluno %d: ", j + 1, i + 1);
            scanf("%s", &matrix[i][j]);
        }
        
        printf("\n");
    }

    for (int i = 0; i < COL; i++) {
        printf("Insira o valor do gabarito da questão %d: ", i + 1);
        scanf("%s", &answers[i]);
    }

    printGrades(matrix, answers);

    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/