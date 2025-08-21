package br.lab.model;

import java.time.LocalDate;

public class Matricula {
    private int id;
    private LocalDate data;
    private String status;
    private Aluno aluno;
    private Disciplina disciplina;
    
    public Matricula() {
    }
    
    public Matricula(int id, LocalDate data, String status, Aluno aluno, Disciplina disciplina) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }
    
    public void cancelar() {
        this.status = "CANCELADA";
        System.out.println("Matrícula cancelada com sucesso.");
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}