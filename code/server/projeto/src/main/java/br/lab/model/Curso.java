package br.lab.model;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private int totalCreditos;
    private List<Disciplina> disciplinas;
    
    public Curso() {
        this.disciplinas = new ArrayList<>();
    }
    
    public Curso(String nome, int totalCreditos) {
        this.nome = nome;
        this.totalCreditos = totalCreditos;
        this.disciplinas = new ArrayList<>();
    }
    
    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }
    
    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }
    
    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinas);
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getTotalCreditos() {
        return totalCreditos;
    }
    
    public void setTotalCreditos(int totalCreditos) {
        this.totalCreditos = totalCreditos;
    }
}