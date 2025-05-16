# Cadastro de Passageiros Refatorado (Conforme Exemplo)

Este projeto implementa um sistema simples de cadastro de passageiros utilizando Java, seguindo o padrão de um exemplo fornecido. Utiliza Maven para gerenciamento de dependências, JUnit 5 para testes automatizados e JaCoCo para relatório de cobertura de testes.

## Funcionalidades

- Cadastro de passageiros com ID, nome, CPF e e-mail.
- Métodos estáticos na classe `Passageiro` para validação de CPF (formato e dígitos verificadores) e formato de e-mail, retornando `boolean`.
- O construtor da classe `Passageiro` não lança exceções por dados inválidos, apenas atribui os valores. A validação ocorre antes da instanciação na lógica de cadastro.
- Armazenamento de passageiros em memória (ArrayList).
- Menu interativo no console para:
    - Cadastrar novo passageiro (com validação de CPF/e-mail e checagem de CPF duplicado antes de adicionar).
    - Listar passageiros cadastrados.
    - Sair do sistema.
- Testes automatizados com JUnit 5 para as classes `Passageiro` e `MainApp`.
- Relatório de cobertura de testes gerado pelo JaCoCo.

## Estrutura do Projeto

O projeto segue a estrutura padrão Maven, com o código fonte em `src/main/java/org/example/` e os testes em `src/test/java/org/example/`.

```
cadastro-passageiros-refatorado/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               ├── MainApp.java       (Menu interativo e lógica principal)
│   │               └── Passageiro.java    (Classe de entidade Passageiro com métodos de validação)
│   └── test/
│       └── java/
│           └── org/
│               └── example/
│                   ├── MainAppTest.java   (Testes para MainApp)
│                   └── PassageiroTest.java(Testes para a classe Passageiro)
└── target/ (Gerado pelo Maven)
    ├── classes/
    ├── test-classes/
    ├── surefire-reports/ (Resultados dos testes JUnit)
    ├── jacoco.exec       (Dados de cobertura JaCoCo)
    └── site/
        └── jacoco/         (Relatório de cobertura JaCoCo - abrir index.html)
    └── cadastro-passageiros-refatorado-1.0-SNAPSHOT.jar (Arquivo JAR executável)
```

## Pré-requisitos

- Java Development Kit (JDK) 11 ou superior instalado (o projeto foi configurado para Java 11 no `pom.xml`, mas o exemplo original mencionava Java 17. Se houver problemas, ajuste a versão no `pom.xml` para 17).
- Apache Maven instalado.

## Como Compilar e Executar

1.  **Clone o repositório ou descompacte o arquivo .zip do projeto.**
2.  **Navegue até o diretório raiz do projeto** (`cadastro-passageiros-refatorado`) usando o terminal.

3.  **Compile o projeto, execute os testes e gere o relatório de cobertura:**
    O comando `mvn clean install` fará tudo isso, pois o plugin JaCoCo está configurado para gerar o relatório na fase `prepare-package` (que é executada durante o `install`).
    ```bash
    mvn clean install
    ```
    Se preferir rodar apenas os testes e o relatório sem instalar o artefato no repositório local:
    ```bash
    mvn clean test jacoco:report
    ```
    (Nota: a configuração do JaCoCo no `pom.xml` está para a fase `prepare-package`, então `mvn clean package` ou `mvn clean install` são mais garantidos para acionar o relatório conforme o exemplo. `mvn clean test jacoco:report` também deve funcionar se o `jacoco.exec` for gerado corretamente pelos testes.)

4.  **Para executar a aplicação (menu interativo no console):**
    Após a compilação (`mvn clean install` ou `mvn clean package`), o arquivo JAR estará em `target/cadastro-passageiros-refatorado-1.0-SNAPSHOT.jar`.
    Execute o JAR com o seguinte comando:
    ```bash
    java -cp target/cadastro-passageiros-refatorado-1.0-SNAPSHOT.jar org.example.MainApp
    ```

## Como Visualizar o Relatório de Cobertura de Testes (JaCoCo)

1.  Após executar `mvn clean install` (ou outro comando que execute a fase `prepare-package` ou `test` e `jacoco:report`), o relatório será gerado.
2.  Abra o arquivo `target/site/jacoco/index.html` em seu navegador web para visualizar o relatório detalhado de cobertura.

## Tecnologias Utilizadas

- **Linguagem:** Java 11 (configurado no `pom.xml`)
- **Gerenciador de Dependências:** Apache Maven
- **Framework de Testes:** JUnit 5 (Jupiter API, Engine, Params - versão 5.12.2)
- **Cobertura de Testes:** JaCoCo (versão 0.8.13)

