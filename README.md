# Drone Feeder

O objetivo do projeto é desenvolver uma API utilizando princípios de Programação Orientada a Objetos (POO) de um CRUD para gerenciar uma startup de entregas utilizando drones. Isso será feito utilizando o banco de dados MySQL através do framework Spring Boot, gerenciador de dependências Maven e Docker.

## Para instalar e executar a aplicação

- Clone o repositório

~~~sh  
  git clone git@github.com:stonefullstm/acc-java-drone-feeder.git
~~~

- Na raiz do projeto execute o seguinte comando para instalar as dependências:

~~~sh
  mvn install
~~~

- Na raiz do projeto execute o comando abaixo para criar os conteineres:

~~~sh
  docker compose up -d
~~~

- Após os conteineres subirem a API estará executando e pode ser acessada no navegador digitando `http://localhost:8080/hello` na barra de endereços do navegador.
- Ainda na raiz do projeto, os testes podem ser executados como o comando:

~~~sh
  mvn test
~~~


## Funcionalidades

- Endpoint para criar um drone (POST `/drones`). O corpo da requisição deve estar no formato:
  
~~~json
{
  "nome": "Drone 01",
  "modelo": "L900 PRO",
}
~~~

- Endpoints para ler todos os drones (GET `/drones`) e para ler um único drone pelo id (GET `/drones/id`)
- Endpoint para remover um drone pelo id (DELETE `/drones/id`)
- Endpoint para atualizar os dados de um drone pelo id (PUT `/drones/id`). O corpo da requisição deve estar no formato:

~~~json
{
  "nome": "Drone 01",
  "modelo": "STM900 PRO",
}
~~~

- Endpoint para inserir uma entrega em um drone existente (POST `/drones/id/entrega`). O corpo da requisição deve estar no formato:

~~~json
{
  "latitude": 1.0,
  "longitude": -1.0,
}
~~~

A documentação completa da API se encontra [nesta página](http://localhost:8080/swagger-ui.html), a qual pode ser acessada após a execução da aplicação.

## Tecnologias utilizadas
 
<div display="inline-block">
<img width="" src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
<img width="" src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot">
<img width="" src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white">
<img width="" src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
<img width="" src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white">
</div>