package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Voo {
    private int id;
    private String origem;
    private String destino;
    private LocalDateTime dataHora;
    private Aviao aviao;

    public Voo(int id, String origem, String destino, LocalDateTime dataHora, Aviao aviao) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.aviao = aviao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public int calcularVagasDisponiveis(List<Reserva> reservas) {
        if (aviao == null) {
            return 0;
        }
        
        int reservasParaEsteVoo = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getVoo().getId() == this.id) {
                reservasParaEsteVoo++;
            }
        }
        
        return aviao.getCapacidade() - reservasParaEsteVoo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Voo{" +
                "id=" + id +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", dataHora=" + dataHora.format(formatter) +
                ", aviao=" + (aviao != null ? aviao.getModelo() + " (ID: " + aviao.getId() + ")" : "não definido") +
                '}';
    }
}
