package br.lab.model;

import java.util.List;
import java.util.ArrayList;

public class Professor extends Usuario {
    private String registro;
    private String especialidade;
    
    public Professor() {
        super();
    }
    
    public Professor(String nome, String email, String senha, String registro, String especialidade) {
        super(nome, email, senha);
        this.registro = registro;
        this.especialidade = especialidade;
    }
    
    public List<Aluno> visualizarListaDeAlunos() {
        // Em um sistema real, isso consultaria um banco de dados
        // Retornando uma lista vazia para demonstração
        return new ArrayList<>();
    }
    
    // Getters e Setters
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
}