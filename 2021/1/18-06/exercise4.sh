# Escrever um script que receba um valor 
# inteiro positivo, como parâmetro, e exiba
# \todos os valores anteriores do numero até chegar no 0 (zero)

if [ $1 -lt 0 ];
    then
        echo "$1 não é um valor válido"
        exit
fi

i=$1
while [ $i -ge 0 ]; do
    echo $i
    let i--
done
