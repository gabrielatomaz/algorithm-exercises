# Gabriela Tomaz do Amaral Ribeiro - 20200543

# Escrever um script que receba um valor inteiro, como parâmetro, e exiba a mensagem
# “POSITIVO”, “NEGATIVO” ou “NULO”, conforme o caso.

if [ $1 -gt 0 ];
    then
        echo "$1 é um valor positivo."
elif [ $1 -lt 0 ];
    then
        echo "$1 é um valor negativo."
else
    echo "$1 é um valor nulo."
fi

# Gabriela Tomaz do Amaral Ribeiro - 20200543