package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MainApp {
    private static List<Passageiro> passageiros = new ArrayList<>();
    private static List<Aviao> avioes = new ArrayList<>();
    private static List<Voo> voos = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();
    private static int proximoIdPassageiro = 1;
    private static int proximoIdAviao = 1;
    private static int proximoIdVoo = 1;
    private static int proximoIdReserva = 1;
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
                    cadastrarAviao();
                    break;
                case 4:
                    listarAvioes();
                    break;
                case 5:
                    cadastrarVoo();
                    break;
                case 6:
                    listarVoos();
                    break;
                case 7:
                    reservarPassagem();
                    break;
                case 8:
                    listarReservas();
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    if (opcao != 0) { // Não mostrar para entrada inválida já tratada
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
            }
            System.out.println(); // Linha em branco para melhor formatação
        } while (opcao != 9);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("--- Menu do Sistema de Passagens Aéreas ---");
        System.out.println("1 - Cadastrar passageiro");
        System.out.println("2 - Listar passageiros");
        System.out.println("3 - Cadastrar avião");
        System.out.println("4 - Listar aviões");
        System.out.println("5 - Cadastrar voo");
        System.out.println("6 - Listar voos");
        System.out.println("7 - Reservar passagem");
        System.out.println("8 - Listar reservas");
        System.out.println("9 - Sair");
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

        Passageiro novoPassageiro = new Passageiro(proximoIdPassageiro, nome, cpfInput, emailInput);
        passageiros.add(novoPassageiro);
        System.out.println("Passageiro cadastrado com sucesso! ID: " + proximoIdPassageiro);
        proximoIdPassageiro++;
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

    private static void cadastrarAviao() {
        System.out.println("--- Cadastro de Novo Avião ---");
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();

        int capacidade = 0;
        boolean capacidadeValida = false;
        while (!capacidadeValida) {
            System.out.print("Capacidade: ");
            try {
                capacidade = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha
                
                if (!Aviao.validarCapacidade(capacidade)) {
                    System.out.println("Erro: A capacidade deve ser maior que zero.");
                } else {
                    capacidadeValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, insira um número válido para a capacidade.");
                scanner.nextLine(); // Consumir entrada inválida
            }
        }

        Aviao novoAviao = new Aviao(proximoIdAviao, modelo, capacidade, fabricante);
        avioes.add(novoAviao);
        System.out.println("Avião cadastrado com sucesso! ID: " + proximoIdAviao);
        proximoIdAviao++;
    }

    private static void listarAvioes() {
        System.out.println("--- Lista de Aviões Cadastrados ---");
        if (avioes.isEmpty()) {
            System.out.println("Nenhum avião cadastrado.");
        } else {
            for (Aviao a : avioes) {
                System.out.println(a);
            }
        }
    }

    private static void cadastrarVoo() {
        System.out.println("--- Cadastro de Novo Voo ---");
        
        if (avioes.isEmpty()) {
            System.out.println("Erro: É necessário cadastrar pelo menos um avião antes de cadastrar um voo.");
            return;
        }
        
        System.out.print("Origem: ");
        String origem = scanner.nextLine();

        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        System.out.println("Data e Hora (formato DD/MM/AAAA HH:MM): ");
        String dataHoraStr = scanner.nextLine();
        
        LocalDateTime dataHora;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dataHora = LocalDateTime.parse(dataHoraStr, formatter);
        } catch (Exception e) {
            System.out.println("Erro: Formato de data e hora inválido. Use o formato DD/MM/AAAA HH:MM.");
            return;
        }

        System.out.println("Aviões disponíveis:");
        for (Aviao a : avioes) {
            System.out.println(a.getId() + " - " + a.getModelo() + " (" + a.getCapacidade() + " lugares)");
        }
        
        System.out.print("Selecione o ID do avião: ");
        int aviaoId;
        try {
            aviaoId = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha
        } catch (InputMismatchException e) {
            System.out.println("Erro: ID inválido.");
            scanner.nextLine(); // Consumir entrada inválida
            return;
        }
        
        Aviao aviaoSelecionado = null;
        for (Aviao a : avioes) {
            if (a.getId() == aviaoId) {
                aviaoSelecionado = a;
                break;
            }
        }
        
        if (aviaoSelecionado == null) {
            System.out.println("Erro: Avião não encontrado.");
            return;
        }
        
        Voo novoVoo = new Voo(proximoIdVoo, origem, destino, dataHora, aviaoSelecionado);
        voos.add(novoVoo);
        System.out.println("Voo cadastrado com sucesso! ID: " + proximoIdVoo);
        proximoIdVoo++;
    }

    private static void listarVoos() {
        System.out.println("--- Lista de Voos Cadastrados ---");
        if (voos.isEmpty()) {
            System.out.println("Nenhum voo cadastrado.");
        } else {
            for (Voo v : voos) {
                int vagasDisponiveis = v.calcularVagasDisponiveis(reservas);
                System.out.println(v + " - Vagas disponíveis: " + vagasDisponiveis);
            }
        }
    }

    private static void reservarPassagem() {
        System.out.println("--- Reserva de Passagem ---");
        
        if (passageiros.isEmpty()) {
            System.out.println("Erro: É necessário cadastrar pelo menos um passageiro antes de fazer uma reserva.");
            return;
        }
        
        if (voos.isEmpty()) {
            System.out.println("Erro: É necessário cadastrar pelo menos um voo antes de fazer uma reserva.");
            return;
        }
        
        System.out.println("Passageiros disponíveis:");
        for (Passageiro p : passageiros) {
            System.out.println(p.getId() + " - " + p.getNome() + " (CPF: " + p.getCpf() + ")");
        }
        
        System.out.print("Selecione o ID do passageiro: ");
        int passageiroId;
        try {
            passageiroId = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha
        } catch (InputMismatchException e) {
            System.out.println("Erro: ID inválido.");
            scanner.nextLine(); // Consumir entrada inválida
            return;
        }
        
        Passageiro passageiroSelecionado = null;
        for (Passageiro p : passageiros) {
            if (p.getId() == passageiroId) {
                passageiroSelecionado = p;
                break;
            }
        }
        
        if (passageiroSelecionado == null) {
            System.out.println("Erro: Passageiro não encontrado.");
            return;
        }
        
        System.out.println("Voos disponíveis:");
        for (Voo v : voos) {
            int vagasDisponiveis = v.calcularVagasDisponiveis(reservas);
            System.out.println(v.getId() + " - " + v.getOrigem() + " -> " + v.getDestino() + 
                    " (Data: " + v.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + 
                    ", Vagas: " + vagasDisponiveis + ")");
        }
        
        System.out.print("Selecione o ID do voo: ");
        int vooId;
        try {
            vooId = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha
        } catch (InputMismatchException e) {
            System.out.println("Erro: ID inválido.");
            scanner.nextLine(); // Consumir entrada inválida
            return;
        }
        
        Voo vooSelecionado = null;
        for (Voo v : voos) {
            if (v.getId() == vooId) {
                vooSelecionado = v;
                break;
            }
        }
        
        if (vooSelecionado == null) {
            System.out.println("Erro: Voo não encontrado.");
            return;
        }
        
        // Verificar se já existe uma reserva para este passageiro neste voo
        if (Reserva.verificarReservaDuplicada(passageiroSelecionado, vooSelecionado, reservas)) {
            System.out.println("Erro: Este passageiro já possui uma reserva para este voo.");
            return;
        }
        
        // Verificar disponibilidade de vagas
        if (!Reserva.verificarDisponibilidade(vooSelecionado, reservas)) {
            System.out.println("Erro: Não há vagas disponíveis para este voo.");
            return;
        }
        
        Reserva novaReserva = new Reserva(proximoIdReserva, passageiroSelecionado, vooSelecionado, LocalDateTime.now());
        reservas.add(novaReserva);
        System.out.println("Reserva realizada com sucesso! ID: " + proximoIdReserva);
        proximoIdReserva++;
    }

    private static void listarReservas() {
        System.out.println("--- Lista de Reservas ---");
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    // Métodos para testes
    public static List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public static List<Aviao> getAvioes() {
        return avioes;
    }

    public static List<Voo> getVoos() {
        return voos;
    }

    public static List<Reserva> getReservas() {
        return reservas;
    }

    public static void limparDadosParaTeste() {
        passageiros.clear();
        avioes.clear();
        voos.clear();
        reservas.clear();
        proximoIdPassageiro = 1;
        proximoIdAviao = 1;
        proximoIdVoo = 1;
        proximoIdReserva = 1;
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
        
        if (p.getId() >= proximoIdPassageiro) {
            proximoIdPassageiro = p.getId() + 1;
        }
        passageiros.add(p);
        return true;
    }

    public static boolean adicionarAviaoParaTeste(Aviao a) {
        if (a == null) return false;
        
        if (!Aviao.validarCapacidade(a.getCapacidade())) {
            return false;
        }
        
        if (a.getId() >= proximoIdAviao) {
            proximoIdAviao = a.getId() + 1;
        }
        avioes.add(a);
        return true;
    }

    public static boolean adicionarVooParaTeste(Voo v) {
        if (v == null || v.getAviao() == null) return false;
        
        if (v.getId() >= proximoIdVoo) {
            proximoIdVoo = v.getId() + 1;
        }
        voos.add(v);
        return true;
    }

    public static boolean adicionarReservaParaTeste(Reserva r) {
        if (r == null || r.getPassageiro() == null || r.getVoo() == null) return false;
        
        // Verificar se já existe uma reserva para este passageiro neste voo
        if (Reserva.verificarReservaDuplicada(r.getPassageiro(), r.getVoo(), reservas)) {
            return false;
        }
        
        // Verificar disponibilidade de vagas
        if (!Reserva.verificarDisponibilidade(r.getVoo(), reservas)) {
            return false;
        }
        
        if (r.getId() >= proximoIdReserva) {
            proximoIdReserva = r.getId() + 1;
        }
        reservas.add(r);
        return true;
    }
}
