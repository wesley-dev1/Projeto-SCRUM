Sistema Scrum em Java 17

Este é um sistema simples desenvolvido em Java 17 que simula um ambiente Scrum, permitindo que diferentes usuários interajam com o sistema de acordo com seus papéis (Product Owner, Scrum Master e Desenvolvedor). O sistema é executado inteiramente em memória, sem necessidade de banco de dados ou ferramentas externas.
Índice

    Funcionalidades
    Requisitos
    Configuração do Projeto
    Como Executar
    Contas de Usuário
    Papéis e Permissões
    Exemplos de Uso
        Product Owner
        Scrum Master
        Desenvolvedor
    Estrutura do Projeto
    Contribuição
    Licença

Funcionalidades

    Product Owner:
        Criar e priorizar histórias de usuário e tarefas.
        Visualizar as tarefas dos desenvolvedores.

    Scrum Master:
        Gerenciar sprints e adicionar histórias do backlog.
        Remover impedimentos (funcionalidade básica).
        Visualizar as tarefas dos desenvolvedores.

    Desenvolvedor:
        Pegar tarefas do backlog e assumir responsabilidade.
        Atualizar o status das tarefas atribuídas.

Requisitos

    Java 17 ou superior.
    IDE: IntelliJ IDEA (recomendado) ou qualquer outra de sua preferência.

Configuração do Projeto

    Clone o repositório:

    bash

    git clone https://github.com/seu-usuario/sistema-scrum-java.git

    Importe o projeto na IDE:
        Abra o IntelliJ IDEA.
        Selecione "Open" e navegue até o diretório do projeto clonado.
        Certifique-se de que o SDK do projeto está configurado para Java 17.

    Crie a estrutura de pacotes:
        No painel Project, clique com o botão direito em "src".
        Selecione "New" > "Package" e crie o pacote com.scrum.
        Dentro do pacote com.scrum, crie outro pacote chamado model.

    Adicione as classes ao projeto:
        Crie as classes conforme a Estrutura do Projeto e copie o código-fonte correspondente.

    Compile o projeto:
        Vá em "Build" > "Build Project" ou pressione Ctrl+F9.

Como Executar

    Execute a classe Main:
        No painel Project, navegue até src/com.scrum/Main.java.
        Clique com o botão direito na classe Main e selecione "Run 'Main.main()'".

    Interaja com o sistema via console:
        O console será exibido, permitindo que você faça login e utilize o sistema conforme descrito nos Exemplos de Uso.

Contas de Usuário

O sistema possui três usuários pré-definidos, cada um com um papel específico:
Nome de Usuário	Senha	Papel
productowner	senha1	Product Owner
scrummaster	senha2	Scrum Master
desenvolvedor	senha3	Desenvolvedor
Papéis e Permissões
Product Owner

    Criar História de Usuário: Adicionar novas histórias ao backlog, incluindo tarefas associadas.
    Priorizar Backlog: Reordenar as histórias no backlog para refletir as prioridades do negócio.
    Visualizar Tarefas dos Desenvolvedores: Acompanhar o progresso das tarefas atribuídas aos desenvolvedores.

Scrum Master

    Gerenciar Sprint: Criar novas sprints e adicionar histórias do backlog.
    Remover Impedimentos: Identificar e remover obstáculos que possam afetar o progresso da equipe.
    Visualizar Tarefas dos Desenvolvedores: Monitorar as tarefas em andamento e o status de cada uma.

Desenvolvedor

    Pegar Tarefa do Backlog: Assumir responsabilidade por tarefas não atribuídas.
    Atualizar Status da Tarefa: Atualizar o status das tarefas atribuídas (TODO, IN_PROGRESS, DONE).
