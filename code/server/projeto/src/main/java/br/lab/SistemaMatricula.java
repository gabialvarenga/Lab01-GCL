package br.lab;

import br.lab.model.*;
import br.lab.enums.*;
import br.lab.service.SistemaService;

import java.util.*;
import java.time.format.DateTimeFormatter;

/**
 * Classe principal do sistema de matrículas
 */
public class SistemaMatricula {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SistemaService service = SistemaService.getInstance();
    private static Usuario usuarioLogado = null;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * Método principal
     */
    public static void main(String[] args) {
        System.out.println("=== Sistema de Matrículas Universitárias ===");
        
        if (service.getAlunos().isEmpty() && service.getProfessores().isEmpty()) {
            System.out.println("Inicializando dados de exemplo...");
            service.inicializarDadosExemplo();
        }
        
        boolean executando = true;
        while (executando) {
            if (usuarioLogado == null) {
                executando = exibirMenuLogin();
            } else {
                executando = exibirMenuPrincipal();
            }
        }
        
        System.out.println("Sistema encerrado. Obrigado por utilizar!");
        scanner.close();
    }
    
    /**
     * Exibe o menu de login
     * @return true para continuar executando, false para encerrar
     */
    private static boolean exibirMenuLogin() {
        System.out.println("\n=== Menu de Login ===");
        System.out.println("1. Fazer login");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                fazerLogin();
                return true;
            case 2:
                return false;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                return true;
        }
    }
    
    /**
     * Realiza o login do usuário
     */
    private static void fazerLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        usuarioLogado = service.autenticarUsuario(email, senha);
        
        if (usuarioLogado == null) {
            System.out.println("Email ou senha inválidos. Tente novamente.");
        } else {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuarioLogado.getNome() + "!");
        }
    }
    
    /**
     * Exibe o menu principal do sistema
     * @return true para continuar executando, false para encerrar
     */
    private static boolean exibirMenuPrincipal() {
        if (usuarioLogado.getRole() == Role.ALUNO) {
            return exibirMenuAluno();
        } else if (usuarioLogado.getRole() == Role.PROFESSOR) {
            return exibirMenuProfessor();
        } else if (usuarioLogado.getRole() == Role.SECRETARIA) {
            return exibirMenuSecretaria();
        }
        
        return true;
    }
    
    /**
     * Exibe o menu do aluno
     * @return true para continuar executando, false para encerrar
     */
    private static boolean exibirMenuAluno() {
        Aluno aluno = (Aluno) usuarioLogado;
        
        System.out.println("\n=== Menu do Aluno ===");
        System.out.println("1. Ver disciplinas disponíveis");
        System.out.println("2. Realizar matrícula");
        System.out.println("3. Cancelar matrícula");
        System.out.println("4. Ver minhas disciplinas");
        System.out.println("5. Fazer logout");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                verDisciplinasDisponiveis();
                return true;
            case 2:
                realizarMatricula(aluno);
                return true;
            case 3:
                cancelarMatricula(aluno);
                return true;
            case 4:
                verMinhasDisciplinas(aluno);
                return true;
            case 5:
                System.out.println("Logout realizado com sucesso!");
                usuarioLogado = null;
                return true;
            case 6:
                return false;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                return true;
        }
    }
    
    /**
     * Exibe o menu do professor
     * @return true para continuar executando, false para encerrar
     */
    private static boolean exibirMenuProfessor() {
        Professor professor = (Professor) usuarioLogado;
        
        System.out.println("\n=== Menu do Professor ===");
        System.out.println("1. Ver minhas disciplinas");
        System.out.println("2. Ver alunos de uma disciplina");
        System.out.println("3. Fazer logout");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                verDisciplinasProfessor(professor);
                return true;
            case 2:
                verAlunosDisciplina(professor);
                return true;
            case 3:
                System.out.println("Logout realizado com sucesso!");
                usuarioLogado = null;
                return true;
            case 4:
                return false;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                return true;
        }
    }
    
    /**
     * Exibe o menu da secretaria
     * @return true para continuar executando, false para encerrar
     */
    private static boolean exibirMenuSecretaria() {
        Secretaria secretaria = (Secretaria) usuarioLogado;
        
        System.out.println("\n=== Menu da Secretaria ===");
        System.out.println("1. Gerenciar cursos");
        System.out.println("2. Gerenciar disciplinas");
        System.out.println("3. Gerenciar currículos");
        System.out.println("4. Gerenciar períodos de matrícula");
        System.out.println("5. Gerenciar professores");
        System.out.println("6. Ver todas as disciplinas");
        System.out.println("7. Ver todos os alunos");
        System.out.println("8. Ver todos os professores");
        System.out.println("9. Fazer logout");
        System.out.println("10. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                gerenciarCursos();
                return true;
            case 2:
                gerenciarDisciplinas();
                return true;
            case 3:
                gerenciarCurriculos();
                return true;
            case 4:
                gerenciarPeriodosMatricula();
                return true;
            case 5:
                gerenciarProfessores();
                return true;
            case 6:
                verTodasDisciplinas(secretaria);
                return true;
            case 7:
                verTodosAlunos(secretaria);
                return true;
            case 8:
                verTodosProfessores(secretaria);
                return true;
            case 9:
                System.out.println("Logout realizado com sucesso!");
                usuarioLogado = null;
                return true;
            case 10:
                return false;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                return true;
        }
    }
    
    /**
     * Exibe as disciplinas disponíveis para matrícula
     */
    private static void verDisciplinasDisponiveis() {
        List<Disciplina> disciplinas = service.getDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis.");
            return;
        }
        
        System.out.println("\n=== Disciplinas Disponíveis ===");
        System.out.println("Código | Nome | Tipo | Vagas | Ativa");
        
        for (Disciplina d : disciplinas) {
            if (d.podeAceitarMatricula()) {
                System.out.printf("%s | %s | %s | %d/%d | %s%n",
                    d.getCodigo(),
                    d.getNome(),
                    d.getTipo(),
                    d.getAlunosMatriculados(),
                    Disciplina.MAX_ALUNOS,
                    d.isAtivo() ? "Sim" : "Não"
                );
            }
        }
    }
    
    /**
     * Realiza a matrícula do aluno em uma disciplina
     * @param aluno Aluno que está realizando a matrícula
     */
    private static void realizarMatricula(Aluno aluno) {
        // Verifica se há algum currículo com período de matrícula aberto
        boolean periodoAberto = false;
        for (Curriculo c : service.getCurriculos()) {
            if (c.isPeriodoMatriculaAberto()) {
                periodoAberto = true;
                break;
            }
        }
        
        if (!periodoAberto) {
            System.out.println("Não há período de matrícula aberto no momento.");
            return;
        }
        
        // Lista disciplinas disponíveis
        verDisciplinasDisponiveis();
        
        System.out.print("\nDigite o código da disciplina: ");
        String codigo = scanner.nextLine();
        
        Disciplina disciplina = service.buscarDisciplinaPorCodigo(codigo);
        
        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }
        
        if (!disciplina.podeAceitarMatricula()) {
            System.out.println("Esta disciplina não está disponível para matrícula.");
            return;
        }
        
        System.out.println("Tipo da disciplina: " + disciplina.getTipo());
        
        // Confirma o tipo da matrícula
        System.out.print("Confirma matrícula como " + disciplina.getTipo() + "? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Matrícula cancelada.");
            return;
        }
        
        // Tenta realizar a matrícula
        boolean sucesso = aluno.realizarMatricula(disciplina, disciplina.getTipo());
        
        if (sucesso) {
            System.out.println("Matrícula realizada com sucesso!");
            service.salvarDados();
        } else {
            System.out.println("Não foi possível realizar a matrícula. Verifique se você já atingiu o limite de disciplinas deste tipo.");
        }
    }
    
    /**
     * Cancela a matrícula do aluno em uma disciplina
     * @param aluno Aluno que está cancelando a matrícula
     */
    private static void cancelarMatricula(Aluno aluno) {
        // Verifica se há algum currículo com período de matrícula aberto
        boolean periodoAberto = false;
        for (Curriculo c : service.getCurriculos()) {
            if (c.isPeriodoMatriculaAberto()) {
                periodoAberto = true;
                break;
            }
        }
        
        if (!periodoAberto) {
            System.out.println("Não há período de matrícula aberto no momento.");
            return;
        }
        
        // Lista disciplinas matriculadas
        List<Disciplina> disciplinasMatriculadas = aluno.getDisciplinasMatriculadas();
        
        if (disciplinasMatriculadas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== Minhas Disciplinas ===");
        System.out.println("Código | Nome | Tipo");
        
        for (int i = 0; i < disciplinasMatriculadas.size(); i++) {
            Disciplina d = disciplinasMatriculadas.get(i);
            System.out.printf("%d. %s | %s | %s%n",
                i + 1,
                d.getCodigo(),
                d.getNome(),
                aluno.getTipoDisciplina(d)
            );
        }
        
        System.out.print("\nDigite o número da disciplina que deseja cancelar: ");
        int opcao = lerInteiro();
        
        if (opcao < 1 || opcao > disciplinasMatriculadas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinasMatriculadas.get(opcao - 1);
        
        // Confirma o cancelamento
        System.out.print("Confirma o cancelamento da matrícula em " + disciplina.getNome() + "? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (!confirmacao.equalsIgnoreCase("S")) {
            System.out.println("Cancelamento cancelado.");
            return;
        }
        
        // Tenta cancelar a matrícula
        boolean sucesso = aluno.cancelarMatricula(disciplina);
        
        if (sucesso) {
            System.out.println("Matrícula cancelada com sucesso!");
            service.salvarDados();
        } else {
            System.out.println("Não foi possível cancelar a matrícula.");
        }
    }
    
    /**
     * Exibe as disciplinas em que o aluno está matriculado
     * @param aluno Aluno que deseja ver suas disciplinas
     */
    private static void verMinhasDisciplinas(Aluno aluno) {
        List<Disciplina> disciplinasObrigatorias = aluno.getDisciplinasPorTipo(Tipo.OBRIGATORIA);
        List<Disciplina> disciplinasOptativas = aluno.getDisciplinasPorTipo(Tipo.OPTATIVA);
        
        System.out.println("\n=== Minhas Disciplinas ===");
        
        if (disciplinasObrigatorias.isEmpty() && disciplinasOptativas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }
        
        if (!disciplinasObrigatorias.isEmpty()) {
            System.out.println("\nDisciplinas Obrigatórias:");
            System.out.println("Código | Nome | Professor");
            
            for (Disciplina d : disciplinasObrigatorias) {
                System.out.printf("%s | %s | %s%n",
                    d.getCodigo(),
                    d.getNome(),
                    d.getProfessor() != null ? d.getProfessor().getNome() : "Sem professor"
                );
            }
        }
        
        if (!disciplinasOptativas.isEmpty()) {
            System.out.println("\nDisciplinas Optativas:");
            System.out.println("Código | Nome | Professor");
            
            for (Disciplina d : disciplinasOptativas) {
                System.out.printf("%s | %s | %s%n",
                    d.getCodigo(),
                    d.getNome(),
                    d.getProfessor() != null ? d.getProfessor().getNome() : "Sem professor"
                );
            }
        }
    }
    
    /**
     * Exibe as disciplinas ministradas pelo professor
     * @param professor Professor que deseja ver suas disciplinas
     */
    private static void verDisciplinasProfessor(Professor professor) {
        List<Disciplina> disciplinas = professor.getDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Você não está ministrando nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== Minhas Disciplinas ===");
        System.out.println("Código | Nome | Tipo | Alunos Matriculados | Ativa");
        
        for (Disciplina d : disciplinas) {
            System.out.printf("%s | %s | %s | %d/%d | %s%n",
                d.getCodigo(),
                d.getNome(),
                d.getTipo(),
                d.getAlunosMatriculados(),
                Disciplina.MAX_ALUNOS,
                d.isAtivo() ? "Sim" : "Não"
            );
        }
    }
    
    /**
     * Exibe os alunos matriculados em uma disciplina do professor
     * @param professor Professor que deseja ver os alunos
     */
    private static void verAlunosDisciplina(Professor professor) {
        List<Disciplina> disciplinas = professor.getDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Você não está ministrando nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== Selecione uma Disciplina ===");
        
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, d.getCodigo(), d.getNome());
        }
        
        System.out.print("\nDigite o número da disciplina: ");
        int opcao = lerInteiro();
        
        if (opcao < 1 || opcao > disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinas.get(opcao - 1);
        List<Aluno> alunos = professor.visualizarListaAlunos(disciplina);
        
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos matriculados nesta disciplina.");
            return;
        }
        
        System.out.println("\n=== Alunos Matriculados em " + disciplina.getNome() + " ===");
        System.out.println("Matrícula | Nome | Email");
        
        for (Aluno a : alunos) {
            System.out.printf("%s | %s | %s%n",
                a.getMatricula(),
                a.getNome(),
                a.getEmail()
            );
        }
    }
    
    /**
     * Gerencia os cursos do sistema
     */
    private static void gerenciarCursos() {
        Secretaria secretaria = (Secretaria) usuarioLogado;
        
        System.out.println("\n=== Gerenciar Cursos ===");
        System.out.println("1. Listar cursos");
        System.out.println("2. Adicionar curso");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                listarCursos(secretaria);
                break;
            case 2:
                adicionarCurso(secretaria);
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    /**
     * Lista todos os cursos do sistema
     */
    private static void listarCursos(Secretaria secretaria) {
        List<Curso> cursos = secretaria.listarCursos();
        
        if (cursos.isEmpty()) {
            System.out.println("Não há cursos cadastrados.");
            return;
        }
        
        System.out.println("\n=== Cursos ===");
        System.out.println("Nome | Créditos");
        
        for (Curso c : cursos) {
            System.out.printf("%s | %d%n", c.getNome(), c.getTotalCreditos());
        }
    }
    
    /**
     * Adiciona um novo curso ao sistema
     */
    private static void adicionarCurso(Secretaria secretaria) {
        System.out.println("\n=== Adicionar Curso ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Total de Créditos: ");
        int creditos = lerInteiro();
        
        if (secretaria.adicionarCurso(nome, creditos)) {
            System.out.println("Curso adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar curso. Verifique os dados informados.");
        }
    }
    
    /**
     * Gerencia as disciplinas do sistema
     */
    private static void gerenciarDisciplinas() {
        Secretaria secretaria = (Secretaria) usuarioLogado;
        
        System.out.println("\n=== Gerenciar Disciplinas ===");
        System.out.println("1. Listar disciplinas");
        System.out.println("2. Adicionar disciplina");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                verTodasDisciplinas(secretaria);
                break;
            case 2:
                adicionarDisciplina(secretaria);
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    /**
     * Lista todas as disciplinas do sistema
     */
    private static void verTodasDisciplinas(Secretaria secretaria) {
        List<Disciplina> disciplinas = secretaria.listarDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas.");
            return;
        }
        
        System.out.println("\n=== Disciplinas ===");
        System.out.println("Código | Nome | Tipo | Professor | Alunos | Ativa");
        
        for (Disciplina d : disciplinas) {
            System.out.printf("%s | %s | %s | %s | %d/%d | %s%n",
                d.getCodigo(),
                d.getNome(),
                d.getTipo(),
                d.getProfessor() != null ? d.getProfessor().getNome() : "Sem professor",
                d.getAlunosMatriculados(),
                Disciplina.MAX_ALUNOS,
                d.isAtivo() ? "Sim" : "Não"
            );
        }
    }
    
    /**
     * Adiciona uma nova disciplina ao sistema
     */
    private static void adicionarDisciplina(Secretaria secretaria) {
        System.out.println("\n=== Adicionar Disciplina ===");
        
        // Verifica se há professores cadastrados
        List<Professor> professores = secretaria.listarProfessores();
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados. Impossível adicionar disciplina.");
            return;
        }
        
        // Verifica se há currículos cadastrados
        List<Curriculo> curriculos = secretaria.listarCurriculos();
        if (curriculos.isEmpty()) {
            System.out.println("Não há currículos cadastrados. Impossível adicionar disciplina.");
            return;
        }
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Créditos: ");
        int creditos = lerInteiro();
        
        System.out.print("Carga Horária: ");
        int cargaHoraria = lerInteiro();
        
        // Seleciona o tipo
        System.out.println("Tipo: ");
        System.out.println("1. Obrigatória");
        System.out.println("2. Optativa");
        System.out.print("Escolha o tipo: ");
        int tipoOpcao = lerInteiro();
        
        Tipo tipo;
        if (tipoOpcao == 1) {
            tipo = Tipo.OBRIGATORIA;
        } else if (tipoOpcao == 2) {
            tipo = Tipo.OPTATIVA;
        } else {
            System.out.println("Opção inválida. Usando Obrigatória como padrão.");
            tipo = Tipo.OBRIGATORIA;
        }
        
        // Lista professores para seleção
        System.out.println("\n=== Selecione um Professor ===");
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            System.out.printf("%d. %s (%s)%n", i + 1, p.getNome(), p.getRegistro());
        }
        
        System.out.print("Escolha o professor: ");
        int profOpcao = lerInteiro();
        
        if (profOpcao < 1 || profOpcao > professores.size()) {
            System.out.println("Opção inválida. Usando o primeiro professor como padrão.");
            profOpcao = 1;
        }
        
        Professor professor = professores.get(profOpcao - 1);
        
        // Lista currículos para seleção
        System.out.println("\n=== Selecione um Currículo ===");
        for (int i = 0; i < curriculos.size(); i++) {
            Curriculo c = curriculos.get(i);
            System.out.printf("%d. %d.%d%n", i + 1, c.getAno(), c.getSemestre());
        }
        
        System.out.print("Escolha o currículo: ");
        int currOpcao = lerInteiro();
        
        if (currOpcao < 1 || currOpcao > curriculos.size()) {
            System.out.println("Opção inválida. Usando o primeiro currículo como padrão.");
            currOpcao = 1;
        }
        
        Curriculo curriculo = curriculos.get(currOpcao - 1);
        
        // Adiciona a disciplina usando o método da secretaria
        if (secretaria.adicionarDisciplina(codigo, nome, creditos, cargaHoraria, professor, tipo, curriculo.getId())) {
            System.out.println("Disciplina adicionada com sucesso!");
        } else {
            System.out.println("Erro ao adicionar disciplina. Verifique se o código não está duplicado.");
        }
    }
    
    /**
     * Gerencia os currículos do sistema
     */
    private static void gerenciarCurriculos() {
        System.out.println("\n=== Gerenciar Currículos ===");
        System.out.println("1. Listar currículos");
        System.out.println("2. Adicionar currículo");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                listarCurriculos();
                break;
            case 2:
                adicionarCurriculo();
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    /**
     * Lista todos os currículos do sistema
     */
    private static void listarCurriculos() {
        List<Curriculo> curriculos = service.getCurriculos();
        
        if (curriculos.isEmpty()) {
            System.out.println("Não há currículos cadastrados.");
            return;
        }
        
        System.out.println("\n=== Currículos ===");
        System.out.println("ID | Ano | Semestre | Período de Matrícula | Disciplinas");
        
        for (Curriculo c : curriculos) {
            String periodoMatricula;
            if (c.isPeriodoMatriculaAberto()) {
                periodoMatricula = "Aberto (até " + c.getPeriodoMatriculaFim().format(formatter) + ")";
            } else if (c.getPeriodoMatriculaInicio() == null) {
                periodoMatricula = "Não iniciado";
            } else {
                periodoMatricula = "Fechado";
            }
            
            System.out.printf("%d | %d | %d | %s | %d disciplina(s)%n",
                c.getId(),
                c.getAno(),
                c.getSemestre(),
                periodoMatricula,
                c.getDisciplinasOfertadas().size()
            );
        }
    }
    
    /**
     * Adiciona um novo currículo ao sistema
     */
    private static void adicionarCurriculo() {
        System.out.println("\n=== Adicionar Currículo ===");
        
        System.out.print("Ano: ");
        int ano = lerInteiro();
        
        System.out.print("Semestre (1 ou 2): ");
        int semestre = lerInteiro();
        
        if (semestre != 1 && semestre != 2) {
            System.out.println("Semestre inválido. Usando 1 como padrão.");
            semestre = 1;
        }
        
        // Verifica se já existe um currículo para o ano/semestre
        Curriculo existente = service.buscarCurriculo(ano, semestre);
        if (existente != null) {
            System.out.println("Já existe um currículo para " + ano + "." + semestre);
            return;
        }
        
        // Gera um ID para o currículo
        int id = service.getCurriculos().size() + 1;
        
        Curriculo curriculo = new Curriculo(id, ano, semestre);
        service.adicionarCurriculo(curriculo);
        
        System.out.println("Currículo adicionado com sucesso!");
        service.salvarDados();
    }
    
    /**
     * Gerencia os períodos de matrícula do sistema
     */
    private static void gerenciarPeriodosMatricula() {
        System.out.println("\n=== Gerenciar Períodos de Matrícula ===");
        
        List<Curriculo> curriculos = service.getCurriculos();
        
        if (curriculos.isEmpty()) {
            System.out.println("Não há currículos cadastrados.");
            return;
        }
        
        System.out.println("Selecione um currículo:");
        
        for (int i = 0; i < curriculos.size(); i++) {
            Curriculo c = curriculos.get(i);
            
            String status;
            if (c.isPeriodoMatriculaAberto()) {
                status = "Aberto";
            } else if (c.getPeriodoMatriculaInicio() == null) {
                status = "Não iniciado";
            } else {
                status = "Fechado";
            }
            
            System.out.printf("%d. %d.%d (%s)%n", i + 1, c.getAno(), c.getSemestre(), status);
        }
        
        System.out.print("Escolha um currículo: ");
        int opcao = lerInteiro();
        
        if (opcao < 1 || opcao > curriculos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Curriculo curriculo = curriculos.get(opcao - 1);
        
        System.out.println("\n=== Período de Matrícula ===");
        System.out.println("1. Abrir período de matrícula");
        System.out.println("2. Fechar período de matrícula");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int acao = lerInteiro();
        
        switch (acao) {
            case 1:
                curriculo.abrirPeriodoMatricula();
                System.out.println("Período de matrícula aberto com sucesso!");
                service.salvarDados();
                break;
            case 2:
                curriculo.fecharPeriodoMatricula();
                System.out.println("Período de matrícula fechado com sucesso!");
                service.salvarDados();
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    /**
     * Lista todos os alunos do sistema
     */
    private static void verTodosAlunos(Secretaria secretaria) {
        List<Aluno> alunos = secretaria.listarAlunos();
        
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }
        
        System.out.println("\n=== Alunos ===");
        System.out.println("Matrícula | Nome | Email | Disciplinas Matriculadas");
        
        for (Aluno a : alunos) {
            System.out.printf("%s | %s | %s | %d disciplina(s)%n",
                a.getMatricula(),
                a.getNome(),
                a.getEmail(),
                a.getDisciplinasMatriculadas().size()
            );
        }
    }
    
    /**
     * Lista todos os professores do sistema
     */
    private static void verTodosProfessores(Secretaria secretaria) {
        List<Professor> professores = secretaria.listarProfessores();
        
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados.");
            return;
        }
        
        System.out.println("\n=== Professores ===");
        System.out.println("Registro | Nome | Email | Especialidade | Disciplinas");
        
        for (Professor p : professores) {
            System.out.printf("%s | %s | %s | %s | %d disciplina(s)%n",
                p.getRegistro(),
                p.getNome(),
                p.getEmail(),
                p.getEspecialidade(),
                p.getDisciplinas().size()
            );
        }
    }
    
    /**
     * Gerencia os professores do sistema
     */
    private static void gerenciarProfessores() {
        Secretaria secretaria = (Secretaria) usuarioLogado;
        
        System.out.println("\n=== Gerenciar Professores ===");
        System.out.println("1. Listar professores");
        System.out.println("2. Adicionar professor");
        System.out.println("3. Remover professor");
        System.out.println("4. Atribuir disciplina a professor");
        System.out.println("5. Remover disciplina de professor");
        System.out.println("6. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                verTodosProfessores(secretaria);
                break;
            case 2:
                adicionarProfessor(secretaria);
                break;
            case 3:
                removerProfessor(secretaria);
                break;
            case 4:
                atribuirDisciplinaProfessor(secretaria);
                break;
            case 5:
                removerDisciplinaProfessor(secretaria);
                break;
            case 6:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
    
    /**
     * Adiciona um novo professor ao sistema
     */
    private static void adicionarProfessor(Secretaria secretaria) {
        System.out.println("\n=== Adicionar Professor ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Registro: ");
        String registro = scanner.nextLine();
        
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        
        if (secretaria.adicionarProfessor(nome, email, senha, registro, especialidade)) {
            System.out.println("Professor adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar professor. Verifique se o email ou registro não estão duplicados.");
        }
    }
    
    /**
     * Remove um professor do sistema
     */
    private static void removerProfessor(Secretaria secretaria) {
        List<Professor> professores = secretaria.listarProfessores();
        
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados.");
            return;
        }
        
        System.out.println("\n=== Remover Professor ===");
        System.out.println("Professores cadastrados:");
        
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            System.out.printf("%d. %s (%s) - %d disciplina(s)%n", 
                i + 1, p.getNome(), p.getRegistro(), p.getDisciplinas().size());
        }
        
        System.out.print("\nDigite o número do professor a ser removido: ");
        int opcao = lerInteiro();
        
        if (opcao < 1 || opcao > professores.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Professor professor = professores.get(opcao - 1);
        
        System.out.print("Confirma a remoção do professor " + professor.getNome() + "? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("S")) {
            if (secretaria.removerProfessor(professor)) {
                System.out.println("Professor removido com sucesso!");
            } else {
                System.out.println("Este professor tem disciplinas atribuídas. Remova as disciplinas primeiro.");
            }
        } else {
            System.out.println("Remoção cancelada.");
        }
    }
    
    /**
     * Atribui uma disciplina a um professor
     */
    private static void atribuirDisciplinaProfessor(Secretaria secretaria) {
        List<Professor> professores = secretaria.listarProfessores();
        List<Disciplina> disciplinas = secretaria.listarDisciplinas();
        
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados.");
            return;
        }
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas.");
            return;
        }
        
        System.out.println("\n=== Atribuir Disciplina a Professor ===");
        
        // Seleciona o professor
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            System.out.printf("%d. %s (%s)%n", i + 1, p.getNome(), p.getRegistro());
        }
        
        System.out.print("Escolha o professor: ");
        int profOpcao = lerInteiro();
        
        if (profOpcao < 1 || profOpcao > professores.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Professor professor = professores.get(profOpcao - 1);
        
        // Filtra disciplinas sem professor ou com professor diferente
        List<Disciplina> disciplinasDisponiveis = new ArrayList<>();
        for (Disciplina d : disciplinas) {
            if (d.getProfessor() == null || !d.getProfessor().equals(professor)) {
                disciplinasDisponiveis.add(d);
            }
        }
        
        if (disciplinasDisponiveis.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis para atribuir a este professor.");
            return;
        }
        
        // Seleciona a disciplina
        System.out.println("\nDisciplinas disponíveis:");
        for (int i = 0; i < disciplinasDisponiveis.size(); i++) {
            Disciplina d = disciplinasDisponiveis.get(i);
            String profAtual = d.getProfessor() != null ? d.getProfessor().getNome() : "Sem professor";
            System.out.printf("%d. %s - %s (Atual: %s)%n", 
                i + 1, d.getCodigo(), d.getNome(), profAtual);
        }
        
        System.out.print("Escolha a disciplina: ");
        int discOpcao = lerInteiro();
        
        if (discOpcao < 1 || discOpcao > disciplinasDisponiveis.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinasDisponiveis.get(discOpcao - 1);
        
        if (secretaria.atribuirDisciplina(professor, disciplina)) {
            System.out.println("Disciplina " + disciplina.getNome() + " atribuída ao professor " + professor.getNome() + " com sucesso!");
        } else {
            System.out.println("Erro ao atribuir disciplina.");
        }
    }
    
    /**
     * Remove uma disciplina de um professor
     */
    private static void removerDisciplinaProfessor(Secretaria secretaria) {
        List<Professor> professores = secretaria.listarProfessores();
        
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados.");
            return;
        }
        
        System.out.println("\n=== Remover Disciplina de Professor ===");
        
        // Seleciona o professor
        System.out.println("Professores com disciplinas:");
        List<Professor> professoresComDisciplinas = new ArrayList<>();
        
        for (Professor p : professores) {
            if (!p.getDisciplinas().isEmpty()) {
                professoresComDisciplinas.add(p);
            }
        }
        
        if (professoresComDisciplinas.isEmpty()) {
            System.out.println("Nenhum professor tem disciplinas atribuídas.");
            return;
        }
        
        for (int i = 0; i < professoresComDisciplinas.size(); i++) {
            Professor p = professoresComDisciplinas.get(i);
            System.out.printf("%d. %s (%s) - %d disciplina(s)%n", 
                i + 1, p.getNome(), p.getRegistro(), p.getDisciplinas().size());
        }
        
        System.out.print("Escolha o professor: ");
        int profOpcao = lerInteiro();
        
        if (profOpcao < 1 || profOpcao > professoresComDisciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Professor professor = professoresComDisciplinas.get(profOpcao - 1);
        
        // Seleciona a disciplina
        List<Disciplina> disciplinasProfessor = professor.getDisciplinas();
        
        System.out.println("\nDisciplinas do professor " + professor.getNome() + ":");
        for (int i = 0; i < disciplinasProfessor.size(); i++) {
            Disciplina d = disciplinasProfessor.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, d.getCodigo(), d.getNome());
        }
        
        System.out.print("Escolha a disciplina a ser removida: ");
        int discOpcao = lerInteiro();
        
        if (discOpcao < 1 || discOpcao > disciplinasProfessor.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinasProfessor.get(discOpcao - 1);
        
        System.out.print("Confirma a remoção da disciplina " + disciplina.getNome() + " do professor " + professor.getNome() + "? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("S")) {
            if (secretaria.removerDisciplinaDeProfessor(professor, disciplina)) {
                System.out.println("Disciplina removida do professor com sucesso!");
            } else {
                System.out.println("Erro ao remover disciplina do professor.");
            }
        } else {
            System.out.println("Remoção cancelada.");
        }
    }
    
    /**
     * Lê um número inteiro da entrada padrão
     * @return Número inteiro lido
     */
    private static int lerInteiro() {
        try {
            int valor = Integer.parseInt(scanner.nextLine());
            return valor;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
