
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

## Implementação de Design Patterns

Foram utlizados os padroes Factory Method e Singleton no módulo de integração com banco de dados, aproveitando a implementação do SOLID, o Factory method tira a responsabilidade de criar instancias da classe base e passa essa função para as subclasses que extende da fabrica, também foi utilizado o Singleton para garantir o uso de uma única instância do produto relacionado ao banco de dados e assim torna o programa mais leve e performático.     

## Implementação do padrão MVC

Durante essa implementação, foi adicionado os pacotes de controllers, services e models, o código em si não recebeu novas atualizações, apesar algumas refatorações redirecionando trechos do código para novos módulos condizentes com a sua função.

## Implementação da API RESTFul

Ao longo dessa implementação foram criando alguns arquivos para migrar a aplicação para o modo API,
sendo eles:
- HttpServer, responsavel por criar um servido simples http, por meio de um servidor socket que fica aguardado as requisições;
- Routes, responsavel por receber a requisição do servidor socket, encaminhar para o controller fazer as ações e encaminhar a resposta da requisição por meio de um cliente socket

Além desses arquivos os controllers receberam métodos para executar as requisições

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
