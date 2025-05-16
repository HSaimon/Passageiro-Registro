package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MainApp {
    private static List<Passageiro> passageiros = new ArrayList<>();
    private static int proximoId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;
        do {
            exibirMenu();
            try {
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    opcao = 0; // Resetar opção para continuar no loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                opcao = 0; // Resetar opção para continuar no loop
            } finally {
                scanner.nextLine(); // Consumir nova linha restante para evitar problemas com leituras subsequentes
            }

            switch (opcao) {
                case 1:
                    cadastrarPassageiro();
                    break;
                case 2:
                    listarPassageiros();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    if (opcao != 0) { // Não mostrar para entrada inválida já tratada
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
            }
            System.out.println(); // Linha em branco para melhor formatação
        } while (opcao != 3);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("--- Menu de Cadastro de Passageiros ---");
        System.out.println("1 - Cadastrar passageiro");
        System.out.println("2 - Listar passageiros");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarPassageiro() {
        System.out.println("--- Cadastro de Novo Passageiro ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        String cpfInput;
        System.out.print("CPF (ex: 123.456.789-00): ");
        cpfInput = scanner.nextLine();

        String emailInput;
        System.out.print("E-mail: ");
        emailInput = scanner.nextLine();

        if (!Passageiro.validarCpf(cpfInput)) {
            System.out.println("Erro: CPF inválido. Cadastro não realizado.");
            return;
        }

        if (!Passageiro.validarEmail(emailInput)) {
            System.out.println("Erro: E-mail inválido. Cadastro não realizado.");
            return;
        }
        
        // Normaliza o CPF para armazenamento e verificação de duplicidade
        String cpfNormalizado = cpfInput.replaceAll("[^0-9]", "");

        for (Passageiro pExistente : passageiros) {
            if (pExistente.getCpf().replaceAll("[^0-9]", "").equals(cpfNormalizado)) {
                System.out.println("Erro: Passageiro com este CPF já cadastrado. Cadastro não realizado.");
                return;
            }
        }

        Passageiro novoPassageiro = new Passageiro(proximoId, nome, cpfInput, emailInput); // Armazena o CPF como foi digitado, se passou na validação
        passageiros.add(novoPassageiro);
        System.out.println("Passageiro cadastrado com sucesso! ID: " + proximoId);
        proximoId++;
    }

    private static void listarPassageiros() {
        System.out.println("--- Lista de Passageiros Cadastrados ---");
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado.");
        } else {
            for (Passageiro p : passageiros) {
                System.out.println(p);
            }
        }
    }

    // Métodos para testes
    public static List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public static void limparPassageirosParaTeste() {
        passageiros.clear();
        proximoId = 1;
    }

    public static boolean adicionarPassageiroParaTeste(Passageiro p) {
        if (p == null || p.getCpf() == null || p.getEmail() == null) return false;

        if (!Passageiro.validarCpf(p.getCpf())) {
            return false; 
        }
        if (!Passageiro.validarEmail(p.getEmail())) {
            return false;
        }

        String cpfNormalizadoNovo = p.getCpf().replaceAll("[^0-9]", "");
        for (Passageiro existente : passageiros) {
            if (existente.getCpf().replaceAll("[^0-9]", "").equals(cpfNormalizadoNovo)) {
                return false; // CPF duplicado
            }
        }
        // Se o ID do passageiro de teste não foi definido (ex: 0 ou <1), atribui um novo
        // No exemplo, o ID é passado no construtor, então vamos assumir que o objeto p já tem um ID.
        // Se o ID do objeto 'p' for maior ou igual ao proximoId, atualizamos proximoId.
        if (p.getId() >= proximoId) {
            proximoId = p.getId() + 1;
        }
        passageiros.add(p);
        return true;
    }
}

