package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Reserva {
    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private LocalDateTime dataReserva;

    public Reserva(int id, Passageiro passageiro, Voo voo, LocalDateTime dataReserva) {
        this.id = id;
        this.passageiro = passageiro;
        this.voo = voo;
        this.dataReserva = dataReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public static boolean verificarDisponibilidade(Voo voo, List<Reserva> reservas) {
        if (voo == null || voo.getAviao() == null) {
            return false;
        }
        
        int vagasDisponiveis = voo.calcularVagasDisponiveis(reservas);
        return vagasDisponiveis > 0;
    }
    
    public static boolean verificarReservaDuplicada(Passageiro passageiro, Voo voo, List<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            if (reserva.getPassageiro().getId() == passageiro.getId() && 
                reserva.getVoo().getId() == voo.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Reserva{" +
                "id=" + id +
                ", passageiro=" + (passageiro != null ? passageiro.getNome() + " (ID: " + passageiro.getId() + ")" : "não definido") +
                ", voo=" + (voo != null ? voo.getOrigem() + " -> " + voo.getDestino() + " (ID: " + voo.getId() + ")" : "não definido") +
                ", dataReserva=" + dataReserva.format(formatter) +
                '}';
    }
}
