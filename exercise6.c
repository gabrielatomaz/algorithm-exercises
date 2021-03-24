#include <stdio.h>
#include <stdlib.h>

int main() {
    int i, indexToBeRemoved;
    float average, grades[3], largest, rest[2];
    char code[10], *result;

    printf("Digite o código do aluno: ");
    scanf("%s", code);

    for (i = 0; i < 3; i++) {
      printf("Digite a nota %d: ", i);
      scanf("%f", &grades[i]);
    }

    largest = grades[0];
    for (i = 1; i < 3; i++)
      if (largest < grades[i]) 
        largest = grades[i];

    for(i = 0; i < 3; i++)
    {
      if(grades[i] == largest)
      {
        indexToBeRemoved = i;
        break;
      }
    }

    for(i = indexToBeRemoved; i < 2; i++)
      grades[i] = grades[i + 1];

    for(i = 0; i < 2; i++)
      rest[i] = grades[i];

    average = largest * .4;

    for(i = 0; i < 2; i++) average += rest[i] * .3;

    if (average >= 5) result = "APROVADO";
    else result = "REPROVADO";

    printf("====================\n");
    printf("Código do aluno: %s\n", code);
    printf("====================\n");
    printf("Aluno %s \n", result);
    
    for (i = 0; i < 3; i++) {
      printf("Nota %s: %f\n", code, grades[i]);
    }

    printf("====================\n");
    printf("Média ponderada: %f\n", average);
}
