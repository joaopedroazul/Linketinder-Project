#!/bin/bash

#Compilar todas as classes e interface
groovyc  ./src/Interfaces/IPessoa.groovy ./src/Classes/*.groovy

#Rodar os testes
groovy -cp  "./lib/spock-core-2.4-M1-groovy-4.0.jar" ./src/test/groovy/EmpresaTest.groovy
groovy -cp  "./lib/spock-core-2.4-M1-groovy-4.0.jar" ./src/test/groovy/CandidatoTest.groovy