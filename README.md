# 🚗 ConcessionariaPOO - Sistema de Gestão de Concessionária

> Sistema em Java desenvolvido para a disciplina de **Programação Orientada a Objetos (POO)**. O projeto simula o gerenciamento de compra, venda e controle de estoque de veículos de uma concessionária.

---

## 📌 Sumário
- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Conceitos de POO Aplicados](#-conceitos-de-poo-aplicados)
- [Padrões de Projeto](#-padrões-de-projeto)
- [Arquitetura do Projeto](#-arquitetura-do-projeto)
- [Como Executar](#-como-executar)

---

## 🎯 Sobre o Projeto

O **ConcessionariaPOO** é uma aplicação executada via terminal em Java puro que permite cadastrar e buscar veículos, realizar vendas associando clientes e vendedores, tratar exceções de estoque e persistir os dados em arquivo `.csv`.

---

## ⚡ Funcionalidades

- [x] **Cadastro de Veículos:** Suporte para Carros (portas, combustível) e Motos (cilindradas).
- [x] **Busca Dinâmica:** Pesquisa de veículos por ID no catálogo.
- [x] **Realização de Vendas:** Registro de vendas com cálculo automático do valor total.
- [x] **Persistência de Dados:** Salva e carrega informações em arquivo local (`veiculos.csv`).
- [x] **Tratamento de Erros:** Exceções customizadas para prevenção de inconsistências (ex: tentar vender veículo já vendido).

---

## 🛠️ Conceitos de POO Aplicados

- **Herança & Classes Abstratas:** 
  - `Pessoa` $\rightarrow$ `Cliente`, `Funcionario` $\rightarrow$ `Gerente`
  - `Veiculo` $\rightarrow$ `Carro`, `Moto`
- **Polimorfismo:** Sobrescrita do método `calcularImposto()` em cada subclasse de veículo e `calcularSalario()` em `Gerente`.
- **Encapsulamento:** Atributos privados com controle de acesso via *getters* e *setters*.
- **Collections:** Uso da API `java.util.List` para gerenciar coleções dinâmicas em memória.
- **Tratamento de Exceções:** 
  - *Checked Exception:* `VeiculoIndisponivelException`
  - *Unchecked Exception:* `VeiculoNaoEncontradoException`
- **Enumerações:** Utilização de `StatusVeiculo` e `TipoCombustivel`.

---

## 🎨 Padrões de Projeto (Design Patterns)

* **Factory Method (`VeiculoFactory`):** Centraliza a criação dos objetos `Carro` e `Moto` com base no tipo informado, encapsulando a lógica de instanciação.

---

## 📁 Arquitetura do Projeto

```text
src/
└── concessionaria/
    ├── exception/     # Exceções personalizadas
    ├── factory/       # Implementação do Padrão Factory
    ├── main/          # Classe principal e Menu do Terminal
    ├── model/         # Entidades de domínio, classes abstratas e Enums
    └── service/       # Lógica de negócio e leitura/gravação de arquivos