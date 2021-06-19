# Gabriela Tomaz do Amaral Ribeiro - 20200543

# Escrever um script que recebe uma temperatura em graus Centígrados, como
# parâmetro, e a exiba convertida em graus Fahrenheit. A fórmula de conversão é
# F = (9C + 160) / 5
# onde F é a temperatura em Fahrenheit e C é a temperatura em Centígrados.

f=$(((9 * $1 + 160) / 5))

echo $f

# Gabriela Tomaz do Amaral Ribeiro - 20200543