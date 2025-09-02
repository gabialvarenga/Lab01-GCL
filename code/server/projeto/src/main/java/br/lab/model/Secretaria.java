package br.lab.model;

import br.lab.enums.Role;
import br.lab.enums.Tipo;
import br.lab.service.SistemaService;
import java.util.List;
import java.util.ArrayList;

public class Secretaria extends Usuario {
    
    public Secretaria() {
        super();
    }
    
    public Secretaria(int id, String nome, String email, String senha) {
        super(id, nome, email, senha, Role.SECRETARIA);
    }
    
    /**
     * Lista todos os cursos
     */
    public List<Curso> listarCursos() {
        return SistemaService.getInstance().getCursos();
    }
    
    /**
     * Adiciona um novo curso
     */
    public boolean adicionarCurso(String nome, int creditos) {
        if (nome == null || nome.trim().isEmpty() || creditos <= 0) {
            return false;
        }
        
        Curso curso = new Curso(nome.trim(), creditos);
        SistemaService.getInstance().adicionarCurso(curso);
        return true;
    }
    
    /**
     * Lista todas as disciplinas
     */
    public List<Disciplina> listarDisciplinas() {
        return SistemaService.getInstance().getDisciplinas();
    }
    
    /**
     * Adiciona uma nova disciplina
     */
    public boolean adicionarDisciplina(String codigo, String nome, int creditos, int cargaHoraria, 
                                     Professor professor, Tipo tipo, int curriculoId) {
        if (codigo == null || codigo.trim().isEmpty() || 
            nome == null || nome.trim().isEmpty() || 
            creditos <= 0 || cargaHoraria <= 0 || 
            professor == null || tipo == null) {
            return false;
        }
        
        
        if (SistemaService.getInstance().buscarDisciplinaPorCodigo(codigo) != null) {
            return false;
        }
        
        Disciplina disciplina = new Disciplina(codigo.trim(), nome.trim(), creditos, cargaHoraria, professor, tipo);
        disciplina.setCurriculoId(curriculoId);
        
        SistemaService.getInstance().adicionarDisciplina(disciplina);
        
       
        professor.adicionarDisciplina(disciplina);
        
       
        Curriculo curriculo = SistemaService.getInstance().getCurriculos()
            .stream()
            .filter(c -> c.getId() == curriculoId)
            .findFirst()
            .orElse(null);
        
        if (curriculo != null) {
            curriculo.adicionarDisciplina(disciplina);
        }
        
        SistemaService.getInstance().salvarDados();
        return true;
    }
    
    /**
     * Lista todos os currículos
     */
    public List<Curriculo> listarCurriculos() {
        return SistemaService.getInstance().getCurriculos();
    }
    
    /**
     * Adiciona um novo currículo
     */
    public boolean adicionarCurriculo(int ano, int semestre) {
        if (ano <= 0 || (semestre != 1 && semestre != 2)) {
            return false;
        }
        
       
        if (SistemaService.getInstance().buscarCurriculo(ano, semestre) != null) {
            return false;
        }
        
        int id = SistemaService.getInstance().getCurriculos().size() + 1;
        Curriculo curriculo = new Curriculo(id, ano, semestre);
        
        SistemaService.getInstance().adicionarCurriculo(curriculo);
        return true;
    }
    
    /**
     * Abre período de matrícula para um currículo
     */
    public boolean abrirPeriodoMatricula(Curriculo curriculo) {
        if (curriculo == null) {
            return false;
        }
        
        curriculo.abrirPeriodoMatricula();
        SistemaService.getInstance().salvarDados();
        return true;
    }
    
    /**
     * Fecha período de matrícula para um currículo
     */
    public boolean fecharPeriodoMatricula(Curriculo curriculo) {
        if (curriculo == null) {
            return false;
        }
        
        curriculo.fecharPeriodoMatricula();
        SistemaService.getInstance().salvarDados();
        return true;
    }
    
    /**
     * Lista todos os professores
     */
    public List<Professor> listarProfessores() {
        return SistemaService.getInstance().getProfessores();
    }
    
    /**
     * Adiciona um novo professor
     */
    public boolean adicionarProfessor(String nome, String email, String senha, String registro, String especialidade) {
        if (nome == null || nome.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty() ||
            registro == null || registro.trim().isEmpty() ||
            especialidade == null || especialidade.trim().isEmpty()) {
            return false;
        }
        

        if (SistemaService.getInstance().buscarProfessorPorEmail(email) != null) {
            return false;
        }
        
        
        if (SistemaService.getInstance().buscarProfessorPorRegistro(registro) != null) {
            return false;
        }
        
        int id = SistemaService.getInstance().getProfessores().size() + 
                 SistemaService.getInstance().getAlunos().size() + 10;
        
        Professor professor = new Professor(id, nome.trim(), email.trim(), senha.trim(), registro.trim(), especialidade.trim());
        SistemaService.getInstance().adicionarProfessor(professor);
        return true;
    }
    
    /**
     * Remove um professor
     */
    public boolean removerProfessor(Professor professor) {
        if (professor == null) {
            return false;
        }
        
       
        if (!professor.getDisciplinas().isEmpty()) {
            return false;
        }
        
        SistemaService.getInstance().getProfessores().remove(professor);
        SistemaService.getInstance().salvarDados();
        return true;
    }
    
    /**
     * Atribui uma disciplina a um professor
     */
    public boolean atribuirDisciplina(Professor professor, Disciplina disciplina) {
        if (professor == null || disciplina == null) {
            return false;
        }
        
    
        if (disciplina.getProfessor() != null) {
            disciplina.getProfessor().removerDisciplina(disciplina);
        }
        
        
        disciplina.setProfessor(professor);
        professor.adicionarDisciplina(disciplina);
        
        SistemaService.getInstance().salvarDados();
        return true;
    }
    
    /**
     * Remove uma disciplina de um professor
     */
    public boolean removerDisciplinaDeProfessor(Professor professor, Disciplina disciplina) {
        if (professor == null || disciplina == null) {
            return false;
        }
        
        if (!professor.getDisciplinas().contains(disciplina)) {
            return false;
        }
        
        professor.removerDisciplina(disciplina);
        disciplina.setProfessor(null);
        
        SistemaService.getInstance().salvarDados();
        return true;
    }
    
    /**
     * Lista todos os alunos
     */
    public List<Aluno> listarAlunos() {
        return SistemaService.getInstance().getAlunos();
    }
    
    /**
     * Adiciona um novo aluno
     */
    public boolean adicionarAluno(String nome, String email, String senha, String matricula) {
        if (nome == null || nome.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            senha == null || senha.trim().isEmpty() ||
            matricula == null || matricula.trim().isEmpty()) {
            return false;
        }
        
        // Verifica se o email já existe
        if (SistemaService.getInstance().buscarAlunoPorEmail(email) != null) {
            return false;
        }
        
        // Verifica se a matrícula já existe
        if (SistemaService.getInstance().buscarAlunoPorMatricula(matricula) != null) {
            return false;
        }
        
        int id = SistemaService.getInstance().getProfessores().size() + 
                 SistemaService.getInstance().getAlunos().size() + 20;
        
        Aluno aluno = new Aluno(id, nome.trim(), email.trim(), senha.trim(), matricula.trim());
        SistemaService.getInstance().adicionarAluno(aluno);
        return true;
    }
    
    /**
     * Remove um aluno
     */
    public boolean removerAluno(Aluno aluno) {
        if (aluno == null) {
            return false;
        }
        
        // Verifica se o aluno tem disciplinas matriculadas
        if (!aluno.getDisciplinasMatriculadas().isEmpty()) {
            return false;
        }
        
        SistemaService.getInstance().getAlunos().remove(aluno);
        SistemaService.getInstance().salvarDados();
        return true;
    }
}