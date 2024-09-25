package com.scrum;

import com.scrum.model.*;
import java.util.ArrayList;
import java.util.List;

public class ScrumBoard {
    private List<Sprint> sprints;
    private List<HistoriaUsuario> backlog;

    public ScrumBoard() {
        this.sprints = new ArrayList<>();
        this.backlog = new ArrayList<>();
    }

    public void adicionarSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void adicionarHistoriaAoBacklog(HistoriaUsuario historia) {
        backlog.add(historia);
    }

    public List<HistoriaUsuario> getBacklog() {
        return backlog;
    }

    public List<Tarefa> getTarefasNaoAtribuidas() {
        List<Tarefa> naoAtribuidas = new ArrayList<>();
        for (Sprint sprint : sprints) {
            for (HistoriaUsuario historia : sprint.getHistorias()) {
                for (Tarefa tarefa : historia.getTarefas()) {
                    if (tarefa.getResponsavel() == null) {
                        naoAtribuidas.add(tarefa);
                    }
                }
            }
        }
        return naoAtribuidas;
    }

    public List<Tarefa> getTarefasPorUsuario(Usuario usuario) {
        List<Tarefa> tarefasUsuario = new ArrayList<>();
        for (Sprint sprint : sprints) {
            for (HistoriaUsuario historia : sprint.getHistorias()) {
                for (Tarefa tarefa : historia.getTarefas()) {
                    if (usuario.equals(tarefa.getResponsavel())) {
                        tarefasUsuario.add(tarefa);
                    }
                }
            }
        }
        return tarefasUsuario;
    }
}
