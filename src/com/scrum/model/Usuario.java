package com.scrum.model;

public class Usuario {
    private String nome;
    private String senha;
    private PapelUsuario papel;

    public Usuario(String nome, String senha, PapelUsuario papel) {
        this.nome = nome;
        this.senha = senha;
        this.papel = papel;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public PapelUsuario getPapel() {
        return papel;
    }
}
