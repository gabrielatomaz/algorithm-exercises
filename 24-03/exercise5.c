#include <stdio.h>
#include <stdlib.h>

int main() {
    int swimmerAge;
    char *category;

    printf("Digite a idade do nadador: ");  
    scanf("%d", &swimmerAge); 

    if (swimmerAge < 5) {
        printf("Crianças com menos de 5 anos não podem nadar!");
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
