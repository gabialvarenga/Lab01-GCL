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
        System.out.println("\n=== Menu da Secretaria ===");
        System.out.println("1. Gerenciar cursos");
        System.out.println("2. Gerenciar disciplinas");
        System.out.println("3. Gerenciar currículos");
        System.out.println("4. Gerenciar períodos de matrícula");
        System.out.println("5. Ver todas as disciplinas");
        System.out.println("6. Ver todos os alunos");
        System.out.println("7. Ver todos os professores");
        System.out.println("8. Fazer logout");
        System.out.println("9. Sair");
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
                verTodasDisciplinas();
                return true;
            case 6:
                verTodosAlunos();
                return true;
            case 7:
                verTodosProfessores();
                return true;
            case 8:
                System.out.println("Logout realizado com sucesso!");
                usuarioLogado = null;
                return true;
            case 9:
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
        System.out.println("\n=== Gerenciar Cursos ===");
        System.out.println("1. Listar cursos");
        System.out.println("2. Adicionar curso");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                listarCursos();
                break;
            case 2:
                adicionarCurso();
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
    private static void listarCursos() {
        List<Curso> cursos = service.getCursos();
        
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
    private static void adicionarCurso() {
        System.out.println("\n=== Adicionar Curso ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Total de Créditos: ");
        int creditos = lerInteiro();
        
        Curso curso = new Curso(nome, creditos);
        service.adicionarCurso(curso);
        
        System.out.println("Curso adicionado com sucesso!");
    }
    
    /**
     * Gerencia as disciplinas do sistema
     */
    private static void gerenciarDisciplinas() {
        System.out.println("\n=== Gerenciar Disciplinas ===");
        System.out.println("1. Listar disciplinas");
        System.out.println("2. Adicionar disciplina");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerInteiro();
        
        switch (opcao) {
            case 1:
                verTodasDisciplinas();
                break;
            case 2:
                adicionarDisciplina();
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
    private static void verTodasDisciplinas() {
        List<Disciplina> disciplinas = service.getDisciplinas();
        
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
    private static void adicionarDisciplina() {
        System.out.println("\n=== Adicionar Disciplina ===");
        
        // Verifica se há professores cadastrados
        List<Professor> professores = service.getProfessores();
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados. Impossível adicionar disciplina.");
            return;
        }
        
        // Verifica se há currículos cadastrados
        List<Curriculo> curriculos = service.getCurriculos();
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
        
        // Cria a disciplina
        Disciplina disciplina = new Disciplina(codigo, nome, creditos, cargaHoraria, professor, tipo);
        disciplina.setCurriculoId(curriculo.getId());
        
        // Adiciona a disciplina
        service.adicionarDisciplina(disciplina);
        curriculo.adicionarDisciplina(disciplina);
        professor.adicionarDisciplina(disciplina);
        
        System.out.println("Disciplina adicionada com sucesso!");
        service.salvarDados();
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
    private static void verTodosAlunos() {
        List<Aluno> alunos = service.getAlunos();
        
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
    private static void verTodosProfessores() {
        List<Professor> professores = service.getProfessores();
        
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
