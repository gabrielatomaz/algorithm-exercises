
#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    Um	 pesquisador	 está	 investigando	 sobre	 mudanças	 climáticas	 e	 precisa	 calcular	 a	 média de	
    temperatura	e	pressão	atmosférica	de	um	mês	de	dados.	Escreva	um	programa	que	leia	30 valores	
    que	representem	dados	de	temperatura	e	pressão	atmosférica	diários	medidos.	Escreva	as	médias	
    de	temperatura	e	pressão	atmosférica	deste	mês	e	a	temperatura	do	dia	mais	quente.
*/

int main() {
    int i,
        counter = 30;
    float temperatures[counter],
        atmosphericPressure[counter],
        totalTemperatures,
        averageTemperatures,
        hottestDayTemperature,
        totalAtmosphericPressure,
        averageAtmosphericPressure;

    for(i = 0; i < counter; i++) {
        printf("Digite a temperatura %d: ", i + 1);
        scanf("%f", &temperatures[i]);
        
        printf("Digite a pressão atmosférica %d: ", i + 1);
        scanf("%f", &atmosphericPressure[i]);
    }


    for(i = 0; i < counter; i++) {
        totalTemperatures += temperatures[i];
        totalAtmosphericPressure += atmosphericPressure[i];
    }
    averageTemperatures = totalTemperatures / counter;
    averageAtmosphericPressure = totalAtmosphericPressure / counter;

    hottestDayTemperature = temperatures[0];
    for (i = 1; i < counter; i++)
      if (hottestDayTemperature < temperatures[i]) 
        hottestDayTemperature = temperatures[i];


    printf("Média de temperaturas: %f\n", averageTemperatures);
    printf("Média de pressões atmosféricas: %f\n", averageAtmosphericPressure);
    printf("Temperatura do dia mais quente: %f\n", hottestDayTemperature);

    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/