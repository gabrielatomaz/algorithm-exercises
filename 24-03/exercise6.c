#include <stdio.h>
#include <stdlib.h>

/* 
  Escreva um algoritmo que leia o código de um
  aluno e suas três notas. Calcule a média
  ponderada do aluno, considerando que o peso
  para a maior nota seja 4 e para as duas restantes,
  3. Mostre o código do aluno, suas três notas, a
  média calculada e a mensagem "APROVADO"
  se a média for maior ou igual a 5 e
  "REPROVADO" caso contrário
*/

int main() {
    int i, indexToBeRemoved;
    float average, grades[3], bestGrade, rest[2];
    char code[10], *result;

    printf("Digite o código do aluno: ");
    scanf("%s", code);

    for (i = 0; i < 3; i++) {
      printf("Digite a nota %d: ", i);
      scanf("%f", &grades[i]);
    }

    bestGrade = grades[0];
    for (i = 1; i < 3; i++)
      if (bestGrade < grades[i]) 
        bestGrade = grades[i];

    for(i = 0; i < 3; i++)
    {
      if(grades[i] == bestGrade)
      {
        indexToBeRemoved = i;
        break;
      }
    }

    for(i = indexToBeRemoved; i < 2; i++)
      grades[i] = grades[i + 1];

    for(i = 0; i < 2; i++)
      rest[i] = grades[i];

    average = bestGrade * .4;

    for(i = 0; i < 2; i++) average += rest[i] * .3;

    if (average >= 5) result = "APROVADO";
    else result = "REPROVADO";

    printf("====================\n");
    printf("Código do aluno: %s\n", code);
    printf("====================\n");
    printf("Aluno %s \n", result);
    
    for (i = 0; i < 3; i++)
      printf("Nota %d: %f\n", i, grades[i]);

    printf("====================\n");
    printf("Média ponderada: %f\n", average);

    return 0;
}
