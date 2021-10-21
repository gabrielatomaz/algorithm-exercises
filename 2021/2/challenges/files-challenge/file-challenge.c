#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define LINE_SIZE 100
#define FILE_NAME_SIZE 100
#define FIRST_TXT "90052.txt"
#define NEXT_TEXT "Next nothing is"

typedef enum { false = 0, true = !false } bool;

int main() {
    FILE *file;
    char *line = (char*) malloc(LINE_SIZE), 
        *newLine,
        fileName[FILE_NAME_SIZE] = FIRST_TXT;
    bool search = true;

    while (search) {
        file = fopen(fileName, "r");
        newLine = fgets(line, LINE_SIZE, file);

        while (*newLine) {
            if (isdigit(*newLine)) sprintf(fileName, "%ld.txt", strtol(newLine, &newLine, 10));
            else newLine++;
        }

        search = strstr(line, NEXT_TEXT) != NULL;
    }

    printf("Arquivo: %s\n%s\n", fileName, line);

    return 0;
}