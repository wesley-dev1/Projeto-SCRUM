package com.scrum;

import com.scrum.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaScrum {
    private List<Usuario> usuarios;
    private ScrumBoard board;

    public SistemaScrum() {
        this.usuarios = new ArrayList<>();
        this.board = new ScrumBoard();
        inicializarUsuarios();
    }

    private void inicializarUsuarios() {
        usuarios.add(new Usuario("productowner", "senha1", PapelUsuario.PRODUCT_OWNER));
        usuarios.add(new Usuario("scrummaster", "senha2", PapelUsuario.SCRUM_MASTER));
        usuarios.add(new Usuario("desenvolvedor", "senha3", PapelUsuario.DESENVOLVEDOR));
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean sistemaAtivo = true;
        while (sistemaAtivo) {
            System.out.println("\nBem-vindo ao Sistema Scrum!");
            System.out.print("Nome de usuário: ");
            String nomeUsuario = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Usuario usuarioLogado = autenticarUsuario(nomeUsuario, senha);
            if (usuarioLogado != null) {
                System.out.println("Login bem-sucedido! Papel: " + usuarioLogado.getPapel());
                exibirMenu(usuarioLogado, scanner);
            } else {
                System.out.println("Credenciais inválidas.");
            }

            System.out.print("\nDeseja fazer login com outro usuário? (s/n): ");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                sistemaAtivo = false;
                System.out.println("Encerrando o sistema. Até logo!");
            }
        }
        scanner.close();
    }

    private Usuario autenticarUsuario(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    private void exibirMenu(Usuario usuario, Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\nSelecione uma opção:");
            switch (usuario.getPapel()) {
                case PRODUCT_OWNER:
                    System.out.println("1. Criar História de Usuário");
                    System.out.println("2. Priorizar Backlog");
                    System.out.println("3. Visualizar Tarefas dos Desenvolvedores"); // Nova opção
                    System.out.println("4. Sair");
                    int opcaoPO = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    switch (opcaoPO) {
                        case 1:
                            criarHistoriaUsuario(scanner);
                            break;
                        case 2:
                            priorizarBacklog(scanner);
                            break;
                        case 3:
                            visualizarTarefasDesenvolvedores(scanner);
                            break;
                        case 4:
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;
                case SCRUM_MASTER:
                    System.out.println("1. Gerenciar Sprint");
                    System.out.println("2. Remover Impedimentos");
                    System.out.println("3. Visualizar Tarefas dos Desenvolvedores"); // Nova opção
                    System.out.println("4. Sair");
                    int opcaoSM = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcaoSM) {
                        case 1:
                            gerenciarSprint(scanner);
                            break;
                        case 2:
                            removerImpedimentos(scanner);
                            break;
                        case 3:
                            visualizarTarefasDesenvolvedores(scanner);
                            break;
                        case 4:
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;
                case DESENVOLVEDOR:
                    System.out.println("1. Pegar Tarefa do Backlog");
                    System.out.println("2. Atualizar Status da Tarefa");
                    System.out.println("3. Sair");
                    int opcaoDev = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcaoDev) {
                        case 1:
                            pegarTarefaBacklog(usuario, scanner);
                            break;
                        case 2:
                            atualizarStatusTarefa(usuario, scanner);
                            break;
                        case 3:
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break;
                default:
                    System.out.println("Papel não reconhecido.");
                    sair = true;
            }
        }
        System.out.println("Logout realizado com sucesso.");
    }

    // Métodos específicos para cada papel

    // Product Owner
    private void criarHistoriaUsuario(Scanner scanner) {
        System.out.print("Título da História: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição da História: ");
        String descricao = scanner.nextLine();
        HistoriaUsuario historia = new HistoriaUsuario(titulo, descricao);

        // Adicionando tarefas à história
        boolean adicionarMaisTarefas = true;
        while (adicionarMaisTarefas) {
            System.out.print("Descrição da Tarefa: ");
            String descricaoTarefa = scanner.nextLine();
            Tarefa tarefa = new Tarefa(descricaoTarefa, null);
            historia.adicionarTarefa(tarefa);

            System.out.print("Deseja adicionar outra tarefa? (s/n): ");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                adicionarMaisTarefas = false;
            }
        }

        board.adicionarHistoriaAoBacklog(historia);
        System.out.println("História adicionada ao backlog.");
    }

    private void priorizarBacklog(Scanner scanner) {
        System.out.println("Backlog atual:");
        List<HistoriaUsuario> backlog = board.getBacklog();
        for (int i = 0; i < backlog.size(); i++) {
            System.out.println((i + 1) + ". " + backlog.get(i).getTitulo());
        }
        System.out.print("Digite o número da história para priorizar: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();
        if (indice >= 0 && indice < backlog.size()) {
            HistoriaUsuario historia = backlog.remove(indice);
            backlog.add(0, historia);
            System.out.println("História priorizada com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // Scrum Master
    private void gerenciarSprint(Scanner scanner) {
        System.out.print("Nome da Sprint: ");
        String nomeSprint = scanner.nextLine();
        Sprint sprint = new Sprint(nomeSprint);
        System.out.println("Adicionando histórias ao sprint...");
        List<HistoriaUsuario> backlog = board.getBacklog();
        if (backlog.isEmpty()) {
            System.out.println("Backlog vazio.");
            return;
        }
        for (int i = 0; i < backlog.size(); i++) {
            System.out.println((i + 1) + ". " + backlog.get(i).getTitulo());
        }
        System.out.print("Digite os números das histórias para adicionar ao sprint (separados por vírgula): ");
        String[] indices = scanner.nextLine().split(",");
        for (String indiceStr : indices) {
            int indice = Integer.parseInt(indiceStr.trim()) - 1;
            if (indice >= 0 && indice < backlog.size()) {
                sprint.adicionarHistoria(backlog.get(indice));
            }
        }
        board.adicionarSprint(sprint);
        System.out.println("Sprint criado com sucesso.");
    }

    private void removerImpedimentos(Scanner scanner) {
        System.out.println("Listando impedimentos...");
        // Implementação fictícia, pois impedimentos não foram definidos
        System.out.println("Nenhum impedimento encontrado.");
    }

    // Novo método para visualizar as tarefas dos desenvolvedores
    private void visualizarTarefasDesenvolvedores(Scanner scanner) {
        System.out.println("Desenvolvedores:");
        List<Usuario> desenvolvedores = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getPapel() == PapelUsuario.DESENVOLVEDOR) {
                desenvolvedores.add(usuario);
            }
        }
        for (int i = 0; i < desenvolvedores.size(); i++) {
            System.out.println((i + 1) + ". " + desenvolvedores.get(i).getNome());
        }
        System.out.print("Selecione o desenvolvedor para ver as tarefas: ");
        int indiceDev = scanner.nextInt() - 1;
        scanner.nextLine();
        if (indiceDev >= 0 && indiceDev < desenvolvedores.size()) {
            Usuario devSelecionado = desenvolvedores.get(indiceDev);
            List<Tarefa> tarefasDev = board.getTarefasPorUsuario(devSelecionado);
            if (tarefasDev.isEmpty()) {
                System.out.println("O desenvolvedor não possui tarefas atribuídas.");
            } else {
                System.out.println("Tarefas do desenvolvedor " + devSelecionado.getNome() + ":");
                for (Tarefa tarefa : tarefasDev) {
                    System.out.println("- " + tarefa.getDescricao() + " [" + tarefa.getStatus() + "]");
                }
            }
        } else {
            System.out.println("Índice de desenvolvedor inválido.");
        }
    }

    // Desenvolvedor
    private void pegarTarefaBacklog(Usuario usuario, Scanner scanner) {
        System.out.println("Tarefas disponíveis:");
        List<Tarefa> tarefasDisponiveis = board.getTarefasNaoAtribuidas();
        if (tarefasDisponiveis.isEmpty()) {
            System.out.println("Nenhuma tarefa disponível.");
            return;
        }
        for (int i = 0; i < tarefasDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + tarefasDisponiveis.get(i).getDescricao());
        }
        System.out.print("Digite o número da tarefa para assumir: ");
        int indiceTarefa = scanner.nextInt() - 1;
        scanner.nextLine();
        if (indiceTarefa >= 0 && indiceTarefa < tarefasDisponiveis.size()) {
            Tarefa tarefa = tarefasDisponiveis.get(indiceTarefa);
            tarefa.setResponsavel(usuario);
            tarefa.setStatus(StatusTarefa.IN_PROGRESS);
            System.out.println("Tarefa atribuída com sucesso.");
        } else {
            System.out.println("Índice de tarefa inválido.");
        }
    }

    private void atualizarStatusTarefa(Usuario usuario, Scanner scanner) {
        System.out.println("Suas tarefas:");
        List<Tarefa> tarefas = board.getTarefasPorUsuario(usuario);
        if (tarefas.isEmpty()) {
            System.out.println("Você não possui tarefas atribuídas.");
            return;
        }
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ". " + tarefas.get(i).getDescricao() + " - " + tarefas.get(i).getStatus());
        }
        System.out.print("Digite o número da tarefa para atualizar o status: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();
        if (indice >= 0 && indice < tarefas.size()) {
            Tarefa tarefa = tarefas.get(indice);
            System.out.println("Status atuais: ");
            for (StatusTarefa status : StatusTarefa.values()) {
                System.out.println(status);
            }
            System.out.print("Digite o novo status: ");
            String novoStatus = scanner.nextLine();
            try {
                tarefa.setStatus(StatusTarefa.valueOf(novoStatus));
                System.out.println("Status atualizado com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido.");
            }
        } else {
            System.out.println("Índice de tarefa inválido.");
        }
    }
}
