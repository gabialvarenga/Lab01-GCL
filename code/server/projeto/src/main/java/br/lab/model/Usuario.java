package br.lab.model;

import br.lab.enums.Role;
import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String email;
    private String senha;
    private Role role;
    
    public Usuario() {
    }
    
    public Usuario(int id, String nome, String email, String senha, Role role) {
        this.id = id;
        this.nome = nome;
        this.email = email; 
        this.senha = senha;
        this.role = role;
    }
    
    public boolean fazerLogin() {
        return true;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
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
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}