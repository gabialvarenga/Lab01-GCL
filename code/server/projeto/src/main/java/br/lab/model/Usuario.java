package br.lab.model;

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    
    public Usuario() {
    }
    
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public boolean fazerLogin() {
        // Implementação do método de autenticação
        // Em um sistema real, isso verificaria as credenciais contra um banco de dados
        return true;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
}