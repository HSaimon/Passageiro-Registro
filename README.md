# Passageiro-Registro

## Descrição
Este repositório contém um projeto Java, provavelmente uma aplicação para registro e gerenciamento de passageiros. A estrutura do projeto indica uma aplicação Java padrão, gerenciada pelo Maven, com foco em testes e cobertura de código (indicado por `jacoco.exec`).

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
- **JUnit**: Framework para testes unitários.
- **JaCoCo**: Ferramenta para relatórios de cobertura de código (indicado pelo arquivo `jacoco.exec`).

## Estrutura do Projeto
A estrutura do projeto é organizada da seguinte forma:
- `pom.xml`: Arquivo de configuração do Maven, definindo dependências e informações do projeto.
- `src/main/java/`: Contém o código-fonte principal da aplicação.
- `src/test/java/`: Contém os testes unitários da aplicação.
- `target/`: Diretório de saída para os artefatos de build, incluindo classes compiladas, relatórios de teste (`surefire-reports`) e relatórios de cobertura de código (`jacoco.exec`).
- `README.md`: Este arquivo.

## Instalação e Execução
Para configurar e executar o projeto localmente, siga os passos abaixo:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/HSaimon/Passageiro-Registro.git
   cd Passageiro-Registro
   ```

2. **Compile o projeto com Maven:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   Se houver uma classe principal configurada no `pom.xml`, você pode executá-la com:
   ```bash
   mvn exec:java
   ```
   Caso contrário, importe o projeto em uma IDE (como IntelliJ IDEA ou Eclipse) e execute a classe principal manualmente.

## Testes e Cobertura de Código
Para executar os testes unitários e gerar relatórios de cobertura de código, utilize o Maven:
```bash
mvn test
```
Após a execução dos testes, os relatórios de cobertura do JaCoCo estarão disponíveis no diretório `target/site/jacoco/` (se configurado no `pom.xml`).

## Contribuição
Contribuições são bem-vindas! Se você tiver sugestões ou encontrar algum problema, por favor, abra uma issue ou envie um pull request.

## Licença
Este projeto é de uso educacional. Sinta-se livre para adaptá-lo e utilizá-lo para fins de estudo.
