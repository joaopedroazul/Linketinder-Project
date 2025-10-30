
# Linketinder-Project

## Autor

 Joao Pedro Silva de Andrade
 
## Descrição
	
Projeto desenvolvido durante o ACZG8 na trilha Groovy para demostrar os conhecimentos adquiridos ao longo da trilha.

## Modelagem MER e Banco de dados

    Para a criação do diagrama de Entidade Relacionamento foi gerado por meio do site dbdiagram.io e o SQL esqueleto 
    foi gerado manualmente e testado no PostgreSQL por meio da interface PgAdmin 4.

## Refatoração do Clean Code

	Foi implementado no Back-end modificações voltadas para a criação de nomes de variávies mais expressivos, ajuste no tamanho e funcionalidade de cada função e implementação de testes de integração

## Implementação dos Principios SOLID

    Foram utlizados os principios OCP(princípio aberto/fechado) e DIP(princípio da inversão de dependência), como o código no geral estava bem estruturado pós refatoração do clean code, esses principios foram utilizados na classes relacionadas a conexão com o banco de dados, criando uma
    abstração na forma de conectar, por meio de uma interface, e permitindo expandir para aceitar qualquer banco de dados relacional.


## Pre-requisitos

### Groovy e Java

É necessário ter o Groovy e JDK  instalado na maquina para rodar o programa


## Execução

### Programa principal

Para rodar o  back-end do linketinder, clone o projeto, abra um terminal e execute os seguintes comandos

    ./run.sh

Caso o executável não rode, de permissão para ele


    chmod +x ./run.sh

execute novamente

### Testes

Para executar os testes , rode o seguinte comando

    ./test.sh

Caso o executável não rode, de permissão para ele


    chmod +x ./test.sh

execute novamente
