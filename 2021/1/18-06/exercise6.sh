# Gabriela Tomaz do Amaral Ribeiro - 20200543

# Faça um script que permita dizer se o arquivo passado pela linha de comando, caso
# exista, é maior do que 100 bytes. O comando stat pode ajudar na determinação do
# tamanho.


if [ -e $1 ];
    then
        bytes=$(stat --format=%s $1)
        if [ $bytes -gt 100 ];
            then   
                echo "O arquivo $1 tem mais que 100 bytes. Bytes: ($bytes)"
        else
            echo "O arquivo $1 tem menos que 100 bytes. Bytes: ($bytes)"
        fi
else
    echo "O arquivo $1 não existe."
fi


# Gabriela Tomaz do Amaral Ribeiro - 20200543