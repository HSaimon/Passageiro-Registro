package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class MainAppTest {
    
    @BeforeEach
    public void setUp() {
        MainApp.limparDadosParaTeste();
    }
    
    // Testes para Passageiros
    @Test
    public void testValidarCpfValido() {
        Assertions.assertTrue(Passageiro.validarCpf("52998224725"), "CPF válido deve retornar true");
    }
    
    @Test
    public void testValidarCpfInvalido() {
        Assertions.assertFalse(Passageiro.validarCpf("12345678900"), "CPF inválido deve retornar false");
    }
    
    @Test
    public void testValidarEmailValido() {
        Assertions.assertTrue(Passageiro.validarEmail("ana.souza@email.com"), "Email válido deve retornar true");
    }
    
    @Test
    public void testValidarEmailInvalido() {
        Assertions.assertFalse(Passageiro.validarEmail("ana.souza@com"), "Email inválido deve retornar false");
    }
    
    @Test
    public void testCadastrarPassageiroComDadosValidos() {
        Passageiro p = new Passageiro(1, "Ana Silva", "52998224725", "ana.silva@email.com");
        Assertions.assertTrue(MainApp.adicionarPassageiroParaTeste(p), "Deve adicionar passageiro com dados válidos");
        
        List<Passageiro> passageiros = MainApp.getPassageiros();
        Assertions.assertEquals(1, passageiros.size(), "Lista deve conter 1 passageiro");
        Assertions.assertEquals("Ana Silva", passageiros.get(0).getNome(), "Nome deve ser igual ao cadastrado");
    }
    
    @Test
    public void testCadastrarPassageiroComCpfInvalido() {
        Passageiro p = new Passageiro(1, "Ana Silva", "12345678900", "ana.silva@email.com");
        Assertions.assertFalse(MainApp.adicionarPassageiroParaTeste(p), "Não deve adicionar passageiro com CPF inválido");
        
        List<Passageiro> passageiros = MainApp.getPassageiros();
        Assertions.assertTrue(passageiros.isEmpty(), "Lista deve estar vazia");
    }
    
    // Testes para Aviões
    @Test
    public void testCadastrarAviaoComDadosValidos() {
        Aviao a = new Aviao(1, "Boeing 737", 150, "Boeing");
        Assertions.assertTrue(MainApp.adicionarAviaoParaTeste(a), "Deve adicionar avião com dados válidos");
        
        List<Aviao> avioes = MainApp.getAvioes();
        Assertions.assertEquals(1, avioes.size(), "Lista deve conter 1 avião");
        Assertions.assertEquals("Boeing 737", avioes.get(0).getModelo(), "Modelo deve ser igual ao cadastrado");
    }
    
    @Test
    public void testCadastrarAviaoComCapacidadeZero() {
        Aviao a = new Aviao(1, "Boeing 737", 0, "Boeing");
        Assertions.assertFalse(MainApp.adicionarAviaoParaTeste(a), "Não deve adicionar avião com capacidade zero");
        
        List<Aviao> avioes = MainApp.getAvioes();
        Assertions.assertTrue(avioes.isEmpty(), "Lista deve estar vazia");
    }
    
    @Test
    public void testCadastrarAviaoComCapacidadeNegativa() {
        Aviao a = new Aviao(1, "Boeing 737", -10, "Boeing");
        Assertions.assertFalse(MainApp.adicionarAviaoParaTeste(a), "Não deve adicionar avião com capacidade negativa");
        
        List<Aviao> avioes = MainApp.getAvioes();
        Assertions.assertTrue(avioes.isEmpty(), "Lista deve estar vazia");
    }
    
    // Testes para Voos
    @Test
    public void testCadastrarVooComDadosValidos() {
        Aviao a = new Aviao(1, "Boeing 737", 150, "Boeing");
        MainApp.adicionarAviaoParaTeste(a);
        
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, a);
        
        Assertions.assertTrue(MainApp.adicionarVooParaTeste(v), "Deve adicionar voo com dados válidos");
        
        List<Voo> voos = MainApp.getVoos();
        Assertions.assertEquals(1, voos.size(), "Lista deve conter 1 voo");
        Assertions.assertEquals("São Paulo", voos.get(0).getOrigem(), "Origem deve ser igual à cadastrada");
    }
    
    @Test
    public void testCadastrarVooSemAviao() {
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, null);
        
        Assertions.assertFalse(MainApp.adicionarVooParaTeste(v), "Não deve adicionar voo sem avião associado");
        
        List<Voo> voos = MainApp.getVoos();
        Assertions.assertTrue(voos.isEmpty(), "Lista deve estar vazia");
    }
    
    // Testes para Reservas
    @Test
    public void testReservarPassagemComVagasDisponiveis() {
        // Cadastrar passageiro
        Passageiro p = new Passageiro(1, "Ana Silva", "52998224725", "ana.silva@email.com");
        MainApp.adicionarPassageiroParaTeste(p);
        
        // Cadastrar avião
        Aviao a = new Aviao(1, "Boeing 737", 150, "Boeing");
        MainApp.adicionarAviaoParaTeste(a);
        
        // Cadastrar voo
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, a);
        MainApp.adicionarVooParaTeste(v);
        
        // Fazer reserva
        Reserva r = new Reserva(1, p, v, LocalDateTime.now());
        
        Assertions.assertTrue(MainApp.adicionarReservaParaTeste(r), "Deve adicionar reserva com vagas disponíveis");
        
        List<Reserva> reservas = MainApp.getReservas();
        Assertions.assertEquals(1, reservas.size(), "Lista deve conter 1 reserva");
    }
    
    @Test
    public void testReservarPassagemSemVagasDisponiveis() {
        // Cadastrar avião com capacidade 1
        Aviao a = new Aviao(1, "Pequeno Avião", 1, "Fabricante");
        MainApp.adicionarAviaoParaTeste(a);
        
        // Cadastrar voo
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, a);
        MainApp.adicionarVooParaTeste(v);
        
        // Cadastrar passageiros
        Passageiro p1 = new Passageiro(1, "Ana Silva", "52998224725", "ana.silva@email.com");
        Passageiro p2 = new Passageiro(2, "Carlos Souza", "18987384322", "carlos@email.com");
        MainApp.adicionarPassageiroParaTeste(p1);
        MainApp.adicionarPassageiroParaTeste(p2);
        
        // Fazer primeira reserva (ocupa a única vaga)
        Reserva r1 = new Reserva(1, p1, v, LocalDateTime.now());
        MainApp.adicionarReservaParaTeste(r1);
        
        // Tentar fazer segunda reserva (não deve ser possível)
        Reserva r2 = new Reserva(2, p2, v, LocalDateTime.now());
        
        Assertions.assertFalse(MainApp.adicionarReservaParaTeste(r2), "Não deve adicionar reserva sem vagas disponíveis");
        
        List<Reserva> reservas = MainApp.getReservas();
        Assertions.assertEquals(1, reservas.size(), "Lista deve conter apenas 1 reserva");
    }
    
    @Test
    public void testReservarPassagemDuplicada() {
        // Cadastrar passageiro
        Passageiro p = new Passageiro(1, "Ana Silva", "52998224725", "ana.silva@email.com");
        MainApp.adicionarPassageiroParaTeste(p);
        
        // Cadastrar avião
        Aviao a = new Aviao(1, "Boeing 737", 150, "Boeing");
        MainApp.adicionarAviaoParaTeste(a);
        
        // Cadastrar voo
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, a);
        MainApp.adicionarVooParaTeste(v);
        
        // Fazer primeira reserva
        Reserva r1 = new Reserva(1, p, v, LocalDateTime.now());
        MainApp.adicionarReservaParaTeste(r1);
        
        // Tentar fazer reserva duplicada
        Reserva r2 = new Reserva(2, p, v, LocalDateTime.now());
        
        Assertions.assertFalse(MainApp.adicionarReservaParaTeste(r2), "Não deve adicionar reserva duplicada");
        
        List<Reserva> reservas = MainApp.getReservas();
        Assertions.assertEquals(1, reservas.size(), "Lista deve conter apenas 1 reserva");
    }
    
    // Testes de Listagem
    @Test
    public void testListarPassageirosAposTresCadastros() {
        Passageiro p1 = new Passageiro(1, "Ana Silva", "52998224725", "ana@email.com");
        Passageiro p2 = new Passageiro(2, "Carlos Souza", "18987384322", "carlos@email.com");
        Passageiro p3 = new Passageiro(3, "Maria Oliveira", "03394014866", "maria@email.com");
        
        MainApp.adicionarPassageiroParaTeste(p1);
        MainApp.adicionarPassageiroParaTeste(p2);
        MainApp.adicionarPassageiroParaTeste(p3);
        
        List<Passageiro> passageiros = MainApp.getPassageiros();
        Assertions.assertEquals(3, passageiros.size(), "Lista deve conter 3 passageiros");
    }
    
    @Test
    public void testListarAvioesAposDoisCadastros() {
        Aviao a1 = new Aviao(1, "Boeing 737", 150, "Boeing");
        Aviao a2 = new Aviao(2, "Airbus A320", 180, "Airbus");
        
        MainApp.adicionarAviaoParaTeste(a1);
        MainApp.adicionarAviaoParaTeste(a2);
        
        List<Aviao> avioes = MainApp.getAvioes();
        Assertions.assertEquals(2, avioes.size(), "Lista deve conter 2 aviões");
    }
    
    @Test
    public void testListarVoosAposUmCadastro() {
        Aviao a = new Aviao(1, "Boeing 737", 150, "Boeing");
        MainApp.adicionarAviaoParaTeste(a);
        
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, a);
        MainApp.adicionarVooParaTeste(v);
        
        List<Voo> voos = MainApp.getVoos();
        Assertions.assertEquals(1, voos.size(), "Lista deve conter 1 voo");
        Assertions.assertNotNull(voos.get(0).getAviao(), "Voo deve ter avião associado");
    }
    
    @Test
    public void testListarReservasAposDoisRegistros() {
        // Cadastrar passageiros
        Passageiro p1 = new Passageiro(1, "Ana Silva", "52998224725", "ana@email.com");
        Passageiro p2 = new Passageiro(2, "Carlos Souza", "18987384322", "carlos@email.com");
        MainApp.adicionarPassageiroParaTeste(p1);
        MainApp.adicionarPassageiroParaTeste(p2);
        
        // Cadastrar avião
        Aviao a = new Aviao(1, "Boeing 737", 150, "Boeing");
        MainApp.adicionarAviaoParaTeste(a);
        
        // Cadastrar voo
        LocalDateTime dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        Voo v = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, a);
        MainApp.adicionarVooParaTeste(v);
        
        // Fazer reservas
        Reserva r1 = new Reserva(1, p1, v, LocalDateTime.now());
        Reserva r2 = new Reserva(2, p2, v, LocalDateTime.now());
        MainApp.adicionarReservaParaTeste(r1);
        MainApp.adicionarReservaParaTeste(r2);
        
        List<Reserva> reservas = MainApp.getReservas();
        Assertions.assertEquals(2, reservas.size(), "Lista deve conter 2 reservas");
        Assertions.assertNotNull(reservas.get(0).getPassageiro(), "Reserva deve ter passageiro associado");
        Assertions.assertNotNull(reservas.get(0).getVoo(), "Reserva deve ter voo associado");
    }
}
