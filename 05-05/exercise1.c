#include <stdio.h>
#include <stdlib.h>

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/

/* 
    Escreva um algoritmo que leia 50 valores que representem preços de produtos. Escreva o valor do
    produto mais caro e dê um desconto de 12% para o produto mais barato. 
*/

int main() {
    int i,
        counter = 50;
    float productsPrice[counter],
        expensivestProduct, 
        cheapestProduct,
        discount;

    for(i = 0; i < counter; i++) {
        printf("Digite o valor do produto %d: ", i + 1);
        scanf("%f", &productsPrice[i]);
    }

    expensivestProduct = productsPrice[0];
    for (i = 1; i < counter; i++)
      if (expensivestProduct < productsPrice[i]) 
        expensivestProduct = productsPrice[i];

    cheapestProduct = productsPrice[0];
    for (i = 1; i < counter; i++)
        if (cheapestProduct > productsPrice[i]) 
            cheapestProduct = productsPrice[i];


    printf("Produto mais caro: %f\n", expensivestProduct);
    printf("Produto mais barato: %f\n", cheapestProduct);
    
    discount = cheapestProduct - (cheapestProduct * .12);
    printf("Valor final com desconto do produto mais barato: %f\n", discount);

    return 0;
}

/* 
    Gabriela Tomaz do Amaral Ribeiro - 20200543
*/
