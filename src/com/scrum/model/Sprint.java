package com.scrum.model;

import java.util.ArrayList;
import java.util.List;

public class Sprint {
    private String nome;
    private List<HistoriaUsuario> historias;

    public Sprint(String nome) {
        this.nome = nome;
        this.historias = new ArrayList<>();
    }

    public void adicionarHistoria(HistoriaUsuario historia) {
        historias.add(historia);
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public List<HistoriaUsuario> getHistorias() {
        return historias;
    }
}
