#!/bin/bash

#Compilar todas as classes e interface
groovyc  ./src/Interfaces/IPessoa.groovy ./src/Classes/*.groovy

#Rodar os testes
cd ./src
groovy Main.groovy