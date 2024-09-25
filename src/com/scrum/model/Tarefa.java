package com.scrum.model;

public class Tarefa {
    private String descricao;
    private Usuario responsavel;
    private StatusTarefa status;

    public Tarefa(String descricao, Usuario responsavel) {
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.status = StatusTarefa.TODO;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
}
