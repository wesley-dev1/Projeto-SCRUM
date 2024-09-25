package com.scrum.model;

import java.util.ArrayList;
import java.util.List;

public class HistoriaUsuario {
    private String titulo;
    private String descricao;
    private List<Tarefa> tarefas;

    public HistoriaUsuario(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
}
