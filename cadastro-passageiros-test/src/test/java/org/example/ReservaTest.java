package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaTest {
    private Passageiro passageiro;
    private Aviao aviao;
    private Voo voo;
    private Reserva reserva;
    private List<Reserva> reservas;
    private LocalDateTime dataHora;
    
    @BeforeEach
    public void setUp() {
        passageiro = new Passageiro(1, "Ana Silva", "52998224725", "ana.silva@email.com");
        aviao = new Aviao(1, "Boeing 737", 150, "Boeing");
        dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, aviao);
        reserva = new Reserva(1, passageiro, voo, LocalDateTime.now());
        reservas = new ArrayList<>();
    }
    
    @Test
    public void testCriacaoReservaComValoresValidos() {
        LocalDateTime dataReserva = LocalDateTime.now();
        Reserva r = new Reserva(2, passageiro, voo, dataReserva);
        
        Assertions.assertEquals(2, r.getId());
        Assertions.assertEquals(passageiro, r.getPassageiro());
        Assertions.assertEquals(voo, r.getVoo());
        Assertions.assertEquals(dataReserva, r.getDataReserva());
    }
    
    @Test
    public void testGetters() {
        Assertions.assertEquals(1, reserva.getId());
        Assertions.assertEquals(passageiro, reserva.getPassageiro());
        Assertions.assertEquals(voo, reserva.getVoo());
        Assertions.assertNotNull(reserva.getDataReserva());
    }
    
    @Test
    public void testSetters() {
        Passageiro novoPassageiro = new Passageiro(2, "Carlos Souza", "18987384322", "carlos@email.com");
        Aviao novoAviao = new Aviao(2, "Airbus A320", 180, "Airbus");
        Voo novoVoo = new Voo(2, "Brasília", "Salvador", dataHora, novoAviao);
        LocalDateTime novaData = LocalDateTime.now().plusDays(1);
        
        reserva.setId(3);
        reserva.setPassageiro(novoPassageiro);
        reserva.setVoo(novoVoo);
        reserva.setDataReserva(novaData);
        
        Assertions.assertEquals(3, reserva.getId());
        Assertions.assertEquals(novoPassageiro, reserva.getPassageiro());
        Assertions.assertEquals(novoVoo, reserva.getVoo());
        Assertions.assertEquals(novaData, reserva.getDataReserva());
    }
    
    @Test
    public void testVerificarDisponibilidadeComVagasDisponiveis() {
        Assertions.assertTrue(Reserva.verificarDisponibilidade(voo, reservas));
    }
    
    @Test
    public void testVerificarDisponibilidadeSemVagasDisponiveis() {
        // Criar passageiros para preencher todas as vagas
        for (int i = 0; i < aviao.getCapacidade(); i++) {
            Passageiro p = new Passageiro(i+10, "Passageiro " + i, "52998224725", "passageiro" + i + "@email.com");
            Reserva r = new Reserva(i+10, p, voo, LocalDateTime.now());
            reservas.add(r);
        }
        
        Assertions.assertFalse(Reserva.verificarDisponibilidade(voo, reservas));
    }
    
    @Test
    public void testVerificarDisponibilidadeComVooNulo() {
        Assertions.assertFalse(Reserva.verificarDisponibilidade(null, reservas));
    }
    
    @Test
    public void testVerificarDisponibilidadeComAviaoNulo() {
        Voo vooSemAviao = new Voo(2, "Curitiba", "Porto Alegre", dataHora, null);
        Assertions.assertFalse(Reserva.verificarDisponibilidade(vooSemAviao, reservas));
    }
    
    @Test
    public void testVerificarReservaDuplicadaComReservaNaoExistente() {
        Assertions.assertFalse(Reserva.verificarReservaDuplicada(passageiro, voo, reservas));
    }
    
    @Test
    public void testVerificarReservaDuplicadaComReservaExistente() {
        reservas.add(reserva);
        Assertions.assertTrue(Reserva.verificarReservaDuplicada(passageiro, voo, reservas));
    }
    
    @Test
    public void testVerificarReservaDuplicadaComOutroPassageiro() {
        reservas.add(reserva);
        
        Passageiro outroPassageiro = new Passageiro(2, "Maria Oliveira", "18987384322", "maria@email.com");
        Assertions.assertFalse(Reserva.verificarReservaDuplicada(outroPassageiro, voo, reservas));
    }
    
    @Test
    public void testVerificarReservaDuplicadaComOutroVoo() {
        reservas.add(reserva);
        
        Voo outroVoo = new Voo(2, "Brasília", "Salvador", dataHora, aviao);
        Assertions.assertFalse(Reserva.verificarReservaDuplicada(passageiro, outroVoo, reservas));
    }
    
    @Test
    public void testToString() {
        String expected = reserva.toString();
        Assertions.assertTrue(expected.contains("Ana Silva"));
        Assertions.assertTrue(expected.contains("São Paulo"));
        Assertions.assertTrue(expected.contains("Rio de Janeiro"));
    }
}
