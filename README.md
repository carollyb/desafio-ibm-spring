# Desafio IBM

## API REST Java SpringBoot

O objetivo deste desafio é criar uma aplicação que permita que hóspedes façam reservas em uma casa de temporada através de uma API Rest. A aplicação deverá possibilitar o agendamento de estadias em datas específicas, com informações do hóspede e quantidade de pessoas

## Conteúdo

- [Especificação dos endpoints](#especificação-dos-endpoints)
  - [POST](#post)
    - [/reservas](#reservas)
  - [GET](#get)
    - [`/reservas`](#reservas-1)
    - [`/reservas/:id`](#reservasid)
  - [PUT](#put)
    - [/reservas/:id](#reservasid-1)
  - [DELETE](#delete)
    - [/reservas/:id/cancelar](#reservasidcancelar)
- [Desenvolvimento](#desenvolvimento)
  - [Testes](#testes)
  - [Padrões de desenvolvimento](#padrões-de-desenvolvimento)
  - [Tratamento de Erros](#tratamento-de-erros)
- [Tecnologias](#tecnologias)
- [Melhorias possíveis](#melhorias-possíveis)

## Especificação dos endpoints

### POST

#### `/reservas`

> Cria uma reserva e retorna um JSON com as informações

**Exemplo**

```
POST https://{host}/reservas
```

**Corpo da solicitação**

```json
{
  "nomeHospede": "Fulano de Tal",
  "dataInicio": "2023-08-10",
  "dataFim": "2023-08-15",
  "quantidadePessoas": 4
}
```

**Response**

```json
{
  "id": 1,
  "nomeHospede": "Fulano de Tal",
  "dataInicio": "2023-08-10",
  "dataFim": "2023-08-15",
  "quantidadePessoas": 4,
  "status": "CONFIRMADA"
}
```

### GET

#### `/reservas`

> Retorna uma array de todas as reservas cadastradas

**Exemplo**

```
GET http://{host}/reservas
```

**Resposta (JSON)**

```json
[
  {
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
  },
  {
    "id": 2,
    "nomeHospede": "Ciclano da Silva",
    "dataInicio": "2023-09-01",
    "dataFim": "2023-09-05",
    "quantidadePessoas": 2,
    "status": "PENDENTE"
  }
]
```

#### `/reservas/:id`

> Retorna um JSON com informações de uma reserva específica, pelo ID

**Exemplo**

```
GET http://{host}/reservas/1
```

**Resposta (JSON)**

```json
{
  "id": 1,
  "nomeHospede": "Fulano de Tal",
  "dataInicio": "2023-08-10",
  "dataFim": "2023-08-15",
  "quantidadePessoas": 4,
  "status": "CONFIRMADA"
}
```

### PUT

#### `/reservas/:id`

> Atualiza uma reserva existente

**Exemplo**

```
PUT https://{host}/reservas/1
```

**Corpo da solicitação**

```json
{
  "nomeHospede": "Fulano da Silva",
  "dataInicio": "2023-08-12",
  "dataFim": "2023-08-17",
  "quantidadePessoas": 5,
  "status": "PENDENTE"
}
```

**Resposta (JSON)**

```json
{
  "id": 1,
  "nomeHospede": "Fulano da Silva",
  "dataInicio": "2023-08-12",
  "dataFim": "2023-08-17",
  "quantidadePessoas": 5,
  "status": "PENDENTE"
}
```

### DELETE

#### `/reservas/:id/cancelar`

> Cancela uma reserva

**Exemplo**

```
DELETE https://{host}/reservas/1/cancelar
```

**Resposta (JSON)**

```json
{
  "id": 1,
  "nomeHospede": "Fulano da Silva",
  "dataInicio": "2023-08-12",
  "dataFim": "2023-08-17",
  "quantidadePessoas": 5,
  "status": "CANCELADA"
}
```

## Desenvolvimento

#### Testes

- [x] Testes da classe ReservaService com 87% de cobertura dos métodos e 85% de cobertura de linhas
- [x] Testes da classe ReservaController com 80% de cobertura dos métodos e 71% de cobertura de linhas

#### Padrões de desenvolvimento

- [x] Git Flow com leves mudanças (em vez de terminar a feature com `git flow feature finish -nome-`, finalizo apenas com publish para não deletar a branch. Prossigo fazendo um pull request para a develop, que no dia a dia da equipe seria acompanhado de um code review)
- [x] Conventional commits

#### Tratamento de Erros

- [x] Foi aplicado o desenvolvimento da classe ObjectNotFoundException, aprendida durante o Bootcamp, para lidar com a resposta vazia do método findById do repository
- [x] Foi criada a classe InvalidEnumValueException para lidar com casos onde o parâmetro para "status" não atende aos requisitos do Enum implementado, que aceita apenas "PENDENTE", "CONFIRMADA" e "CANCELADA"

## Tecnologias

- [x] Java 17, Spring Boot, H2 Database, Gerenciador de Pacotes Maven

## Melhorias possíveis

- [x] Melhoria da mensagem de erro na classe InvalidEnumValueException
