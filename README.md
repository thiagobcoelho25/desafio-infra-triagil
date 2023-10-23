# Desafio Triágil

Olá! Nesse repositório você vai encontrar as instruções necessárias pra participar do desafio Triágil. O objetivo aqui é testar os seus conhecimentos de programação (e de Google), valendo uma vaga de estágio na equipe de Infraestrutura da Triágil.

O desafio é o seguinte: Você precisa desenvolver uma API na linguagem de programação que se sentir mais confortável (só não me venha com Java), pra montar times de Pokémons, pra isso, você vai usar a [pokeapi.co](https://pokeapi.co/). O usuário que utilizar sua API deve conseguir gerar um novo time, passando como parâmetro uma lista de pokémons e um nome de usuário. Você deve então ler essa lista, buscar pelos pokémons na pokeAPI e caso não encontre nenhum erro, salvar esse time da maneira que preferir (pode ser em memória, banco de dados, arquivo de texto...). Com o time salvo, você retorna ao usuário uma mensagem de validação e uma **id** unica, para identificar aquele time. Além disso, você deve disponibilizar duas rotas para leitura dos dados, uma que busque todos os times registrados e outra que busque um time pela ID unica.

Daí com sua API pronta, você só precisa gerar um Dockerfile e um compose! A ideia aqui é que qualquer pessoa com acesso ao seu código, consiga instanciar a sua API e realizar testes (e pode ter certeza que vamos testar).

### Requisitos

- Rotas

    - GET /api/teams - Deverá listar todos os times registrados
    - GET /api/teams/{id} - Busca um time registrado por ID
    - POST /api/teams - Rota para criação de um time, que recebe um JSON nesse formato [aqui](#exemplo-input)

* As rotas devem retornar erro quando o input contém algum erro (nome de pokémon inválido, etc...). A mensagem de erro fica a seu critério, desde que esteja claro para o usuário onde está o problema.

* Ao registrar o time, você deve guardar além do nome do Pokémon, alguns de seus dados disponíveis na PokeAPI. São eles: o ID do Pokémon (de acordo com a pokédex), sua altura e seu peso. Esses dados devem ser retornados junto do time quando buscado no sistema. Exemplo [aqui]()

### Avaliação

Você será avaliado com base na qualidade do código, na implementação dos requisitos e em sua capacidade de criar uma API funcional e bem documentada. Além disso, sua habilidade em configurar e compartilhar a aplicação em um container Docker será considerada, já que utilizaremos desse Docker para realizar os testes.

### Exemplos

#### Output /api/teams/{id}

```json
{
  "owner": "sleao",
  "pokemons": [
    {
      "id": 9,
      "name": "blastoise",
      "weight": 855,
      "height": 16
    },
    {
      "id": 25,
      "name": "pikachu",
      "weight": 60,
      "height": 4
    }
  ]
}
```

#### Output /api/teams

```json
{
  "1": {
    "owner": "sleao",
    "pokemons": [
      {
        "id": 9,
        "name": "blastoise",
        "weight": 855,
        "height": 16
      },
      {
        "id": 25,
        "name": "pikachu",
        "weight": 60,
        "height": 4
      }
    ]
  },
  "2": {
    "owner": "sleao",
    "pokemons": [
      {
        "id": 9,
        "name": "blastoise",
        "weight": 855,
        "height": 16
      },
      {
        "id": 25,
        "name": "pikachu",
        "weight": 60,
        "height": 4
      },
      {
        "id": 3,
        "name": "venusaur",
        "weight": 1000,
        "height": 20
      },
      {
        "id": 6,
        "name": "charizard",
        "weight": 905,
        "height": 17
      },
      {
        "id": 131,
        "name": "lapras",
        "weight": 2200,
        "height": 25
      },
      {
        "id": 54,
        "name": "psyduck",
        "weight": 196,
        "height": 8
      }
    ]
  }
}
```

#### Input

```json
{
  "user": "sleao",
  "team": [
    "blastoise",
    "pikachu",
    "charizard",
    "venusaur",
    "lapras",
    "dragonite"
  ]
}
```
## Resolução - Thiago Ribeiro
Para a resolução do desafio apresentado utilizei a linguaguem **Java** com a framework **Spring Boot**, utilizei banco em memoria **H2**. 
Como proposto, adicionei os arquivos ***DockFile*** e ***docker-compose.yml*** para a criação de um container para 
rodar a aplicação de maneira facilitada. Tambem adicionei a biblioteca **Swagger** para documentação das rotas.

### Pre-Requisitos

Como pre requisito para rodar a aplicação deve-se ter instalado no computador o programa **Docker**,
 assim como tambem um sistema de controle de versões *git* 

### Como Instalar

Deve-se acessar o github e fazer o clone do projeto:

```bash
# Clone co projeto
git clone https://github.com/thiagobcoelho25/desafio-infra-triagil.git
```
### Como rodar

Abra um terminal na pasta criada pelo clone do projeto e digite:

```bash
# Inicia projeto
docker-compose up
```

O docker ira baixar a imagem para o container inicial e ira criar um build do projeto para roda-lo
localmente nesse container com a porta *:8080* aberta.

### Rotas

- GET /api/teams:
    * Retorna todos os times em um formato de dicionario, onde o **ID** do time representa um atributo
do dicionario com seus respectivos dados
    

- GET /api/teams/{id}:
  * Retorna um time em especifico para o **ID** passado, caso o **ID** não exista retona um erro 400.

- POST /api/teams:
  * Rota que recebe um JSON com o nome do dono do time e uma lista de pokemons.
  * Esse rota não aceita o envio dos dados sem nome ou uma lista vazia, retornando um 400 coms as informações faltantes.
A mesma rota aceita apenas pokemons validos, retornando 404 caso não exista o pokemon na base do 'PókeAPI'. 
Tambem feitas são feitas verificações se existe +1 mesmo pokemon na lista, caso exista é retornando um erro
avisando que não são aceitos 2 pokemons iguais no time.

Para saber mais sobre as rotas e seus dados um endpoint foi aberto para documentação com **Swagger**
na rota http://localhost:8080/swagger-ui/index.html


### Autor

---

<a href="https://github.com/thiagobcoelho25">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/42743844?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Thiago Ribeiro</b></sub></a> <a href="https://github.com/thiagobcoelho25" title="Rocketseat">🚀</a>

Feito com ❤️ por Thiago Ribeiro 👋🏽 Entre em contato!

[![Linkedin Badge](https://img.shields.io/badge/-Thiago-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/thiagobcoelho25/)](https://www.linkedin.com/in/thiagobcoelho25/)
[![Gmail Badge](https://img.shields.io/badge/-thiagobcoelho25.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:thiagobcoelho25@gmail.com)](mailto:thiagobcoelho25@gmail.com)