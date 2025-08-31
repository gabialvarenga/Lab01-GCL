package br.lab.model;

import java.util.List;

import br.lab.enums.Role;

import java.util.ArrayList;

public class Professor extends Usuario {
    private String registro;
    private String especialidade;
    private transient List<Disciplina> disciplinas;
    
    public Professor() { 
        super();
        this.disciplinas = new ArrayList<>();
    }
    
    public Professor(int id, String nome, String email, String senha, String registro, String especialidade) {
        super(id, nome, email, senha, Role.PROFESSOR);
        this.registro = registro;
        this.especialidade = especialidade;
        this.disciplinas = new ArrayList<>();
    }
    
    public List<Aluno> visualizarListaAlunos(Disciplina disciplina) {
 
        if (disciplinas.contains(disciplina)) {
            return disciplina.listarAlunos();
        }
   
        return new ArrayList<>();
    }
    
    public String getRegistro() {
        return registro;
    }
    
    public void setRegistro(String registro) {
        this.registro = registro;
    }
    
    public String getEspecialidade() {
        return especialidade;
    }
    
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }
    
    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }
}