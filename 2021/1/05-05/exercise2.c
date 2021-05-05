
#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    Escreva um programa que leia o salário de 30 indivíduos (aceitar somente valores positivos). Para
    cada valor lido calcule e escreva um aumento de salário segundo dados abaixo:
        3% p/ salário > =3000
        10% p/ salário entre 1000 e 3000
        15% p/ o menor <= 1000
*/

int main() {
    int i,
        counter = 30;
    float salaries[counter],
        validator,
        salary,
        increase;

    for(i = 0; i < counter; i++) {
        printf("Digite o salário %d: ", i + 1);
        scanf("%f", &validator);
        if (validator > 0) salaries[i] = validator;
        else i--;
    }


    for(i = 0; i < counter; i++) {
        salary = salaries[i];

        if (salary >= 3000) increase = .03;
        else if (salary > 1000 && salary < 3000) increase = .1;
        else if (salary <= 1000) increase = .15;

        salaries[i] = salary + (salary * increase);
    }

    for(i = 0; i < counter; i++)
        printf("Valor final do salário %d: %f \n", i, salaries[i]);

    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/