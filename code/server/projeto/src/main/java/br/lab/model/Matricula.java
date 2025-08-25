package br.lab.model;

import java.time.LocalDate;

import br.lab.enums.StatusMatricula;
import br.lab.enums.Tipo;

public class Matricula {
    private int id;
    private LocalDate data;
    private StatusMatricula status;
    private Tipo tipo;
    private Aluno aluno;
    private Disciplina disciplina; 
    
    public Matricula() {
        this.data = LocalDate.now();
        this.status = StatusMatricula.ATIVA;
    }
    
    public Matricula(int id, Aluno aluno, Disciplina disciplina, Tipo tipo) {
        this.id = id;
        this.data = LocalDate.now();
        this.status = StatusMatricula.ATIVA;
        this.tipo = tipo;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }
    
    public void cancelar() {
        this.status = StatusMatricula.CANCELADA;
        System.out.println("Matrícula cancelada com sucesso.");
    }
    
    public boolean isAtiva() {
        return this.status == StatusMatricula.ATIVA;
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
    
    public StatusMatricula getStatus() {
        return status;
    }
    
    public void setStatus(StatusMatricula status) {
        this.status = status;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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