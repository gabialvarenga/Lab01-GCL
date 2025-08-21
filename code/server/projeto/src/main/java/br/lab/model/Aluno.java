package br.lab.model;

import java.util.List;
import java.util.ArrayList;

public class Aluno extends Usuario {
    private String matricula;
    
    public Aluno() {
        super();
    }
    
    public Aluno(String nome, String email, String senha, String matricula) {
        super(nome, email, senha);
        this.matricula = matricula;
    }
    
    public void realizarMatricula() {
        // Implementação do método de matrícula
        System.out.println("Matrícula realizada com sucesso.");
    }
    
    public List<Disciplina> visualizarDisciplinas() {
        // Corrigido o nome do método de "visualzarDisciplinas" para "visualizarDisciplinas"
        // Em um sistema real, isso consultaria um banco de dados
        return new ArrayList<>();
    }
    
    public void cancelarMatricula() {
        // Implementação do método de cancelamento de matrícula
        System.out.println("Matrícula cancelada com sucesso.");
    }
    
    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}