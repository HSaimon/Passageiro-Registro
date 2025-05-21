💼 Atividade de Programação em Java: Sistema de Cadastro de Passageiros
🎯 Objetivo
Desenvolver um sistema simples de cadastro de passageiros utilizando Java, com funcionalidades de validação de CPF e e-mail, armazenamento em memória e testes automatizados.

📦 Requisitos do Sistema
1. Criar a Classe Passageiro

A classe deve conter os seguintes atributos:
int id
String nome
String cpf
String email

Inclua métodos para:

Validar o CPF (utilize uma lógica adequada para verificação de CPF).
Validar o e-mail (use regex ou métodos disponíveis para validação de formato).

2. Armazenamento
Utilize uma ArrayList<Passageiro> para armazenar os objetos da classe Passageiro.

Os passageiros cadastrados devem poder ser recuperados e listados posteriormente.3. Criar Menu Interativo (Console)

Desenvolva um menu simples no método main da classe MainApp, com as seguintes opções:
1 - Cadastrar passageiro
2 - Listar passageiros
3 - Sair

🧪 Testes Automatizados

Utilize JUnit 5 para criar os seguintes testes:
Verificação de CPF válido e inválido.
Verificação de e-mail válido e inválido.
Testar se a função de cadastro está inserindo corretamente os passageiros na lista.

🛠️ Tecnologias e Ferramentas

Linguagem: Java
Gerenciador de dependências: Maven
Framework de testes: JUnit 5
Cobertura de testes: JaCoCo
