#include <stdio.h>
#include <stdlib.h>

/* 
  Elabore um algoritmo que, dada a idade de um
  nadador classifica-o em uma das seguintes
  categorias:
    infantil	A	=	5	- 7	anos	
    infantil	B	=	8-10	anos	
    juvenil	A	=	11-13	anos	
    juvenil	B	=	14-17	anos	
    adulto	=	maiores	de	18	anos
*/

int main() {
  int swimmerAge;
  char *category;

  printf("Digite a idade do nadador: ");  
  scanf("%d", &swimmerAge); 

  if (swimmerAge < 5) {
    printf("Por favor, insira uma idade válida!");
    return 0;
  }
  else if (swimmerAge >=5 && swimmerAge <=7) category = "Infantil A";
  else if (swimmerAge >=8 && swimmerAge <=10) category = "Infantil B";
  else if (swimmerAge >=11 && swimmerAge <=13) category = "Juvenil A";
  else if (swimmerAge >=14 && swimmerAge <=17) category = "Juvenil B";
  else category = "Adulto";
  

  printf("Categoria de acordo com a idade: %s", category);
  
  return 0;
}
