# Gabriela Tomaz do Amaral Ribeiro - 20200543

# Desenvolva um script que verifica se 
# o parâmetro $1 digitado pelo usuário é um
# arquivo ou um diretório

if [ -d $1 ];
    then
        echo "$1 é um diretório."
elif [ -f $1 ];
    then
        echo "$1 é um arquivo."
else
    echo "$1 não é um arquivo e nem um diretório."
fi

# Gabriela Tomaz do Amaral Ribeiro - 20200543