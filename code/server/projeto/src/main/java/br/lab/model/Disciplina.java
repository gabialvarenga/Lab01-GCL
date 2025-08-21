package br.lab.model;

import java.util.List;
import java.util.ArrayList;

public class Disciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private List<Aluno> alunos;
    
    public Disciplina() {
        this.alunos = new ArrayList<>();
    }
    
    public Disciplina(String codigo, String nome, int cargaHoraria, Professor professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }
    
    public void adicionarAluno(Aluno aluno) {
        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }
    
    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }
    
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }
    
    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
