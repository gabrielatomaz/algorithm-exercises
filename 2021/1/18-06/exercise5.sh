# Gabriela Tomaz do Amaral Ribeiro - 20200543

# Escreva um script que testa se determinado 
# arquivo existe ou não. O nome do arquivo
# será passado via linha de comando.

if [ -e $1 ];
    then
        echo "O arquivo $1 existe."
else
    echo "O arquivo $1 não existe."
fi

# Gabriela Tomaz do Amaral Ribeiro - 20200543