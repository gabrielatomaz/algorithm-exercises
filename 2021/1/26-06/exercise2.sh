#!/bin/bash

echo "Vou buscar os dados do sistema. Posso continuar? [s/n] "
scanf RESPOSTA
test "$RESPOSTA" = "n" && exit
echo "Data e Horário:"
date
echo
echo "Uso do disco:"
df
echo
echo "Usuários conectados:"
w