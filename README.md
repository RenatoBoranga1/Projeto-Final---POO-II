# RentFlow

Sistema acadêmico de locação de veículos em Java, desenvolvido para demonstrar fundamentos de programação orientada a objetos.

> O repositório permanece com o nome histórico `Projeto-Final---POO-II`. **RentFlow** é apenas a identidade editorial recomendada para facilitar a leitura do portfólio.

## Contexto

O projeto foi criado como trabalho final de um módulo de POO. Ele registra uma etapa da minha evolução em Java e não tenta simular uma arquitetura enterprise: toda a interação ocorre no console e os dados permanecem em memória.

## Problema modelado

Uma locadora precisa cadastrar clientes, veículos e agências, localizar registros e controlar o aluguel e a devolução de veículos de categorias diferentes.

## Funcionalidades implementadas

- cadastro e consulta de pessoas físicas e jurídicas
- cadastro, alteração, busca e listagem de veículos
- suporte a carros, motos e caminhões
- cadastro, alteração e listagem de agências
- aluguel e devolução de veículos
- cálculo do valor da locação por período e categoria
- descontos calculados conforme o tipo de cliente e os dias alugados
- menu interativo em console

## Fundamentos de POO demonstrados

- **abstração:** `Cliente` e `Veiculo` definem estado e comportamento comuns
- **herança:** `ClientePF`/`ClientePJ` e `Carro`/`Moto`/`Caminhao` especializam classes-base
- **polimorfismo:** cada tipo de veículo implementa sua diária e seu comportamento de aluguel/devolução
- **interfaces:** `ICliente<T>` define o contrato dos clientes
- **generics:** documentos de clientes e classes auxiliares usam tipos parametrizados
- **encapsulamento:** entidades expõem operações e acessores próprios
- **composição:** `LocadoraSantander` associa cliente, veículo, agência e datas da locação
- **coleções:** `Sistema` mantém os registros em listas e coordena as operações

## Estrutura principal

```text
.
├── Main.java                 # menu e entrada da aplicação
├── Sistema.java              # cadastros, consultas e locações
├── LocadoraSantander.java    # registro de aluguel e devolução
├── Cliente.java              # abstração genérica de cliente
├── ClientePF.java
├── ClientePJ.java
├── ICliente.java
├── Veiculo.java              # abstração de veículo
├── Carro.java
├── Moto.java
├── Caminhao.java
├── Agencia.java
└── Conta/                    # exercício auxiliar preservado no projeto
```

## Como executar

### Pré-requisito

- JDK 17 ou superior

No PowerShell:

```powershell
$sourceFiles = Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName
javac -encoding UTF-8 -d out $sourceFiles
java -cp out Main
```

Em Bash:

```bash
javac -encoding UTF-8 -d out *.java Conta/*.java
java -cp out Main
```

## Testes

O projeto não possui testes automatizados nem ferramenta de build. A validação disponível é a compilação com `javac` e a execução manual do menu.

## Limitações conhecidas

- persistência somente em memória
- entrada de dados e regras de aplicação concentradas no fluxo de console
- uso de `double` para valores monetários
- ausência de validação automatizada e tratamento estruturado de erros
- arquivos PDF e MP4 são materiais acadêmicos auxiliares e não fazem parte da execução

Essas limitações são mantidas transparentes porque o objetivo do projeto é evidenciar fundamentos de Java/POO, não apresentar uma aplicação de produção.

## Autor

Renato Boranga
