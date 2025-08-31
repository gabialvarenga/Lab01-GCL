package br.lab.service;

import br.lab.model.*;
import br.lab.enums.*;
import br.lab.util.FileUtil;

import java.util.*;

/**
 * Classe responsável por gerenciar a persistência do sistema
 */
public class SistemaService {
    private static final String ALUNOS_FILE = "alunos.dat";
    private static final String PROFESSORES_FILE = "professores.dat";
    private static final String DISCIPLINAS_FILE = "disciplinas.dat";
    private static final String CURSOS_FILE = "cursos.dat";
    private static final String CURRICULOS_FILE = "curriculos.dat";
    
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private List<Curso> cursos;
    private List<Curriculo> curriculos;
    
    private static SistemaService instance;
    
    /**
     * Construtor privado para implementar o padrão Singleton
     */
    private SistemaService() {
        carregarDados();
    }
    
    /**
     * Obtém a instância única do serviço
     * @return Instância do serviço
     */
    public static SistemaService getInstance() {
        if (instance == null) {
            instance = new SistemaService();
        }
        return instance;
    }
    
    /**
     * Carrega os dados do sistema dos arquivos
     */
    @SuppressWarnings("unchecked")
    private void carregarDados() {
        // Carrega alunos
        if (FileUtil.fileExists(ALUNOS_FILE)) {
            alunos = (List<Aluno>) FileUtil.loadObject(ALUNOS_FILE);
        } else {
            alunos = new ArrayList<>();
        }
        
        // Carrega professores
        if (FileUtil.fileExists(PROFESSORES_FILE)) {
            professores = (List<Professor>) FileUtil.loadObject(PROFESSORES_FILE);
        } else {
            professores = new ArrayList<>();
        }
        
        // Carrega disciplinas
        if (FileUtil.fileExists(DISCIPLINAS_FILE)) {
            disciplinas = (List<Disciplina>) FileUtil.loadObject(DISCIPLINAS_FILE);
        } else {
            disciplinas = new ArrayList<>();
        }
        
        // Carrega cursos
        if (FileUtil.fileExists(CURSOS_FILE)) {
            cursos = (List<Curso>) FileUtil.loadObject(CURSOS_FILE);
        } else {
            cursos = new ArrayList<>();
        }
        
        // Carrega currículos
        if (FileUtil.fileExists(CURRICULOS_FILE)) {
            curriculos = (List<Curriculo>) FileUtil.loadObject(CURRICULOS_FILE);
        } else {
            curriculos = new ArrayList<>();
        }
        
        // Restabelece as relações entre os objetos
        restabelecerRelacoes();
    }
    
    /**
     * Restabelece as relações entre os objetos após o carregamento
     */
    private void restabelecerRelacoes() {
        // Reinicializa as coleções transientes
        for (Aluno aluno : alunos) {
            aluno.setDisciplinasMatriculadasMap(new HashMap<>());
        }
        
        for (Professor professor : professores) {
            professor.setDisciplinas(new ArrayList<>());
        }
        
        for (Disciplina disciplina : disciplinas) {
            disciplina.setAlunos(new ArrayList<>());
        }
        
        for (Curriculo curriculo : curriculos) {
            curriculo.setDisciplinasOfertadas(new ArrayList<>());
        }
        
        // Reconstrói as relações
        for (Disciplina disciplina : disciplinas) {
            // Associa professor à disciplina
            for (Professor professor : professores) {
                if (professor.getRegistro().equals(disciplina.getProfessor() != null ? 
                        disciplina.getProfessor().getRegistro() : "")) {
                    disciplina.setProfessor(professor);
                    professor.adicionarDisciplina(disciplina);
                    break;
                }
            }
            
            // Adiciona disciplina ao currículo correspondente
            for (Curriculo curriculo : curriculos) {
                if (curriculo.getId() == disciplina.getCurriculoId()) {
                    curriculo.adicionarDisciplina(disciplina);
                    break;
                }
            }
        }
    }
    
    /**
     * Salva todos os dados do sistema em arquivos
     */
    public void salvarDados() {
        FileUtil.saveObject(alunos, ALUNOS_FILE);
        FileUtil.saveObject(professores, PROFESSORES_FILE);
        FileUtil.saveObject(disciplinas, DISCIPLINAS_FILE);
        FileUtil.saveObject(cursos, CURSOS_FILE);
        FileUtil.saveObject(curriculos, CURRICULOS_FILE);
    }
    
    /**
     * Adiciona um aluno ao sistema
     * @param aluno Aluno a ser adicionado
     */
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        salvarDados();
    }
    
    /**
     * Adiciona um professor ao sistema
     * @param professor Professor a ser adicionado
     */
    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        salvarDados();
    }
    
    /**
     * Adiciona uma disciplina ao sistema
     * @param disciplina Disciplina a ser adicionada
     */
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        salvarDados();
    }
    
    /**
     * Adiciona um curso ao sistema
     * @param curso Curso a ser adicionado
     */
    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
        salvarDados();
    }
    
    /**
     * Adiciona um currículo ao sistema
     * @param curriculo Currículo a ser adicionado
     */
    public void adicionarCurriculo(Curriculo curriculo) {
        curriculos.add(curriculo);
        salvarDados();
    }
    
    /**
     * Busca um aluno pelo número de matrícula
     * @param matricula Número de matrícula
     * @return Aluno encontrado ou null
     */
    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
    
    /**
     * Busca um aluno pelo email
     * @param email Email do aluno
     * @return Aluno encontrado ou null
     */
    public Aluno buscarAlunoPorEmail(String email) {
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().equals(email)) {
                return aluno;
            }
        }
        return null;
    }
    
    /**
     * Busca um professor pelo registro
     * @param registro Registro do professor
     * @return Professor encontrado ou null
     */
    public Professor buscarProfessorPorRegistro(String registro) {
        for (Professor professor : professores) {
            if (professor.getRegistro().equals(registro)) {
                return professor;
            }
        }
        return null;
    }
    
    /**
     * Busca um professor pelo email
     * @param email Email do professor
     * @return Professor encontrado ou null
     */
    public Professor buscarProfessorPorEmail(String email) {
        for (Professor professor : professores) {
            if (professor.getEmail().equals(email)) {
                return professor;
            }
        }
        return null;
    }
    
    /**
     * Busca uma disciplina pelo código
     * @param codigo Código da disciplina
     * @return Disciplina encontrada ou null
     */
    public Disciplina buscarDisciplinaPorCodigo(String codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }
    
    /**
     * Busca um curso pelo nome
     * @param nome Nome do curso
     * @return Curso encontrado ou null
     */
    public Curso buscarCursoPorNome(String nome) {
        for (Curso curso : cursos) {
            if (curso.getNome().equals(nome)) {
                return curso;
            }
        }
        return null;
    }
    
    /**
     * Busca um currículo pelo ano e semestre
     * @param ano Ano do currículo
     * @param semestre Semestre do currículo
     * @return Currículo encontrado ou null
     */
    public Curriculo buscarCurriculo(int ano, int semestre) {
        for (Curriculo curriculo : curriculos) {
            if (curriculo.getAno() == ano && curriculo.getSemestre() == semestre) {
                return curriculo;
            }
        }
        return null;
    }
    
    /**
     * Autentica um usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return Usuário autenticado ou null
     */
    public Usuario autenticarUsuario(String email, String senha) {
        // Tenta autenticar como aluno
        for (Aluno aluno : alunos) {
            if (aluno.getEmail().equals(email) && aluno.getSenha().equals(senha)) {
                return aluno;
            }
        }
        
        // Tenta autenticar como professor
        for (Professor professor : professores) {
            if (professor.getEmail().equals(email) && professor.getSenha().equals(senha)) {
                return professor;
            }
        }
        
        // Busca uma secretaria (considerando que só temos uma)
        for (Usuario usuario : getSecretarias()) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        
        return null;
    }
    
    /**
     * Obtém a lista de alunos
     * @return Lista de alunos
     */
    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    /**
     * Obtém a lista de professores
     * @return Lista de professores
     */
    public List<Professor> getProfessores() {
        return professores;
    }
    
    /**
     * Obtém a lista de disciplinas
     * @return Lista de disciplinas
     */
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    /**
     * Obtém a lista de cursos
     * @return Lista de cursos
     */
    public List<Curso> getCursos() {
        return cursos;
    }
    
    /**
     * Obtém a lista de currículos
     * @return Lista de currículos
     */
    public List<Curriculo> getCurriculos() {
        return curriculos;
    }
    
    /**
     * Obtém a lista de secretarias
     * @return Lista de secretarias
     */
    public List<Secretaria> getSecretarias() {
        List<Secretaria> secretarias = new ArrayList<>();
        
        // Verifica se já existe uma secretaria
        boolean encontrouSecretaria = false;
        for (Usuario usuario : obterTodosUsuarios()) {
            if (usuario instanceof Secretaria) {
                secretarias.add((Secretaria) usuario);
                encontrouSecretaria = true;
            }
        }
        
        // Se não existe, cria uma secretaria padrão
        if (!encontrouSecretaria) {
            Secretaria secretaria = new Secretaria(1, "Secretaria", "secretaria@universidade.com", "123456");
            secretarias.add(secretaria);
        }
        
        return secretarias;
    }
    
    /**
     * Obtém todos os usuários do sistema
     * @return Lista com todos os usuários
     */
    private List<Usuario> obterTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(alunos);
        usuarios.addAll(professores);
        return usuarios;
    }
    
    /**
     * Inicializa o sistema com dados de exemplo
     */
    public void inicializarDadosExemplo() {
        if (!alunos.isEmpty() || !professores.isEmpty() || !disciplinas.isEmpty() || 
            !cursos.isEmpty() || !curriculos.isEmpty()) {
            System.out.println("O sistema já contém dados. A inicialização foi cancelada.");
            return;
        }
        
        // Cria secretaria
        Secretaria secretaria = new Secretaria(1, "Secretaria", "secretaria@universidade.com", "123456");
        
        // Cria professores
        Professor prof1 = new Professor(2, "João Silva", "joao@universidade.com", "123456", "P001", "Computação");
        Professor prof2 = new Professor(3, "Maria Souza", "maria@universidade.com", "123456", "P002", "Matemática");
        professores.add(prof1);
        professores.add(prof2);
        
        // Cria alunos
        Aluno aluno1 = new Aluno(4, "Pedro Santos", "pedro@aluno.com", "123456", "A001");
        Aluno aluno2 = new Aluno(5, "Ana Oliveira", "ana@aluno.com", "123456", "A002");
        Aluno aluno3 = new Aluno(6, "Carlos Ferreira", "carlos@aluno.com", "123456", "A003");
        Aluno aluno4 = new Aluno(7, "Beatriz Lima", "beatriz@aluno.com", "123456", "A004");
        alunos.add(aluno1);
        alunos.add(aluno2);
        alunos.add(aluno3);
        alunos.add(aluno4);
        
        // Cria cursos
        Curso curso1 = new Curso("Ciência da Computação", 3000);
        Curso curso2 = new Curso("Engenharia de Software", 3600);
        cursos.add(curso1);
        cursos.add(curso2);
        
        // Cria currículo
        Curriculo curriculo = new Curriculo(1, 2025, 2);
        curriculos.add(curriculo);
        
        // Cria disciplinas
        Disciplina disc1 = new Disciplina("CC001", "Programação I", 60, 60, prof1, Tipo.OBRIGATORIA);
        Disciplina disc2 = new Disciplina("CC002", "Banco de Dados", 60, 60, prof2, Tipo.OBRIGATORIA);
        Disciplina disc3 = new Disciplina("CC003", "Redes de Computadores", 60, 60, prof1, Tipo.OPTATIVA);
        Disciplina disc4 = new Disciplina("CC004", "Inteligência Artificial", 60, 60, prof2, Tipo.OPTATIVA);
        Disciplina disc5 = new Disciplina("CC005", "Engenharia de Software", 60, 60, prof1, Tipo.OBRIGATORIA);
        Disciplina disc6 = new Disciplina("CC006", "Cálculo I", 60, 60, prof2, Tipo.OBRIGATORIA);
        Disciplina disc7 = new Disciplina("CC007", "Sistemas Operacionais", 60, 60, prof1, Tipo.OPTATIVA);
        Disciplina disc8 = new Disciplina("CC008", "Computação Gráfica", 60, 60, prof2, Tipo.OPTATIVA);
        
        disc1.setCurriculoId(curriculo.getId());
        disc2.setCurriculoId(curriculo.getId());
        disc3.setCurriculoId(curriculo.getId());
        disc4.setCurriculoId(curriculo.getId());
        disc5.setCurriculoId(curriculo.getId());
        disc6.setCurriculoId(curriculo.getId());
        disc7.setCurriculoId(curriculo.getId());
        disc8.setCurriculoId(curriculo.getId());
        
        disciplinas.add(disc1);
        disciplinas.add(disc2);
        disciplinas.add(disc3);
        disciplinas.add(disc4);
        disciplinas.add(disc5);
        disciplinas.add(disc6);
        disciplinas.add(disc7);
        disciplinas.add(disc8);
        
        // Adiciona disciplinas ao currículo
        curriculo.adicionarDisciplina(disc1);
        curriculo.adicionarDisciplina(disc2);
        curriculo.adicionarDisciplina(disc3);
        curriculo.adicionarDisciplina(disc4);
        curriculo.adicionarDisciplina(disc5);
        curriculo.adicionarDisciplina(disc6);
        curriculo.adicionarDisciplina(disc7);
        curriculo.adicionarDisciplina(disc8);
        
        // Adiciona disciplinas aos cursos
        curso1.adicionarDisciplina(disc1);
        curso1.adicionarDisciplina(disc2);
        curso1.adicionarDisciplina(disc3);
        curso1.adicionarDisciplina(disc4);
        
        curso2.adicionarDisciplina(disc5);
        curso2.adicionarDisciplina(disc6);
        curso2.adicionarDisciplina(disc7);
        curso2.adicionarDisciplina(disc8);
        
        // Adiciona disciplinas aos professores
        prof1.adicionarDisciplina(disc1);
        prof1.adicionarDisciplina(disc3);
        prof1.adicionarDisciplina(disc5);
        prof1.adicionarDisciplina(disc7);
        
        prof2.adicionarDisciplina(disc2);
        prof2.adicionarDisciplina(disc4);
        prof2.adicionarDisciplina(disc6);
        prof2.adicionarDisciplina(disc8);
        
        // Abre o período de matrícula
        curriculo.abrirPeriodoMatricula();
        
        // Salva os dados
        salvarDados();
        System.out.println("Dados de exemplo inicializados com sucesso!");
    }
}
