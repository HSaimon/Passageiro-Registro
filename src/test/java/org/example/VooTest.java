package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VooTest {
    private Aviao aviao;
    private Voo voo;
    private LocalDateTime dataHora;
    private List<Reserva> reservas;
    
    @BeforeEach
    public void setUp() {
        aviao = new Aviao(1, "Boeing 737", 150, "Boeing");
        dataHora = LocalDateTime.of(2025, 6, 15, 10, 30);
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, aviao);
        reservas = new ArrayList<>();
    }
    
    @Test
    public void testCriacaoVooComValoresValidos() {
        Voo v = new Voo(2, "Brasília", "Salvador", dataHora, aviao);
        Assertions.assertEquals(2, v.getId());
        Assertions.assertEquals("Brasília", v.getOrigem());
        Assertions.assertEquals("Salvador", v.getDestino());
        Assertions.assertEquals(dataHora, v.getDataHora());
        Assertions.assertEquals(aviao, v.getAviao());
    }
    
    @Test
    public void testGetters() {
        Assertions.assertEquals(1, voo.getId());
        Assertions.assertEquals("São Paulo", voo.getOrigem());
        Assertions.assertEquals("Rio de Janeiro", voo.getDestino());
        Assertions.assertEquals(dataHora, voo.getDataHora());
        Assertions.assertEquals(aviao, voo.getAviao());
    }
    
    @Test
    public void testSetters() {
        LocalDateTime novaData = LocalDateTime.of(2025, 7, 20, 15, 45);
        Aviao novoAviao = new Aviao(2, "Airbus A320", 180, "Airbus");
        
        voo.setId(3);
        voo.setOrigem("Recife");
        voo.setDestino("Fortaleza");
        voo.setDataHora(novaData);
        voo.setAviao(novoAviao);
        
        Assertions.assertEquals(3, voo.getId());
        Assertions.assertEquals("Recife", voo.getOrigem());
        Assertions.assertEquals("Fortaleza", voo.getDestino());
        Assertions.assertEquals(novaData, voo.getDataHora());
        Assertions.assertEquals(novoAviao, voo.getAviao());
    }
    
    @Test
    public void testCalcularVagasDisponiveisSemReservas() {
        int vagasDisponiveis = voo.calcularVagasDisponiveis(reservas);
        Assertions.assertEquals(150, vagasDisponiveis);
    }
    
    @Test
    public void testCalcularVagasDisponiveisComReservas() {
        // Criar passageiros para as reservas
        Passageiro p1 = new Passageiro(1, "João", "52998224725", "joao@email.com");
        Passageiro p2 = new Passageiro(2, "Maria", "18987384322", "maria@email.com");
        
        // Criar reservas para o voo
        Reserva r1 = new Reserva(1, p1, voo, LocalDateTime.now());
        Reserva r2 = new Reserva(2, p2, voo, LocalDateTime.now());
        
        reservas.add(r1);
        reservas.add(r2);
        
        int vagasDisponiveis = voo.calcularVagasDisponiveis(reservas);
        Assertions.assertEquals(148, vagasDisponiveis);
    }
    
    @Test
    public void testCalcularVagasDisponiveisComReservasParaOutrosVoos() {
        // Criar outro voo
        Voo outroVoo = new Voo(2, "Curitiba", "Porto Alegre", dataHora, aviao);
        
        // Criar passageiros para as reservas
        Passageiro p1 = new Passageiro(1, "João", "52998224725", "joao@email.com");
        Passageiro p2 = new Passageiro(2, "Maria", "18987384322", "maria@email.com");
        
        // Criar reservas para o outro voo
        Reserva r1 = new Reserva(1, p1, outroVoo, LocalDateTime.now());
        Reserva r2 = new Reserva(2, p2, outroVoo, LocalDateTime.now());
        
        reservas.add(r1);
        reservas.add(r2);
        
        // As reservas são para outro voo, então não devem afetar as vagas disponíveis deste voo
        int vagasDisponiveis = voo.calcularVagasDisponiveis(reservas);
        Assertions.assertEquals(150, vagasDisponiveis);
    }
    
    @Test
    public void testCalcularVagasDisponiveisSemAviao() {
        Voo vooSemAviao = new Voo(3, "Manaus", "Belém", dataHora, null);
        int vagasDisponiveis = vooSemAviao.calcularVagasDisponiveis(reservas);
        Assertions.assertEquals(0, vagasDisponiveis);
    }
    
    @Test
    public void testToString() {
        String expected = voo.toString();
        Assertions.assertTrue(expected.contains("São Paulo"));
        Assertions.assertTrue(expected.contains("Rio de Janeiro"));
        Assertions.assertTrue(expected.contains("Boeing 737"));
    }
}
