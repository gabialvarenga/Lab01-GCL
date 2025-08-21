package br.lab.model;

import java.util.List;
import java.util.ArrayList;

public class Curso {
    private String id;
    private String nome;
    private String descricao;
    private int duracao;
    private List<Disciplina> disciplinas;
    
    public Curso() {
        this.disciplinas = new ArrayList<>();
    }
    
    public Curso(String id, String nome, String descricao, int duracao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
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
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getDuracao() {
        return duracao;
    }
    
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}