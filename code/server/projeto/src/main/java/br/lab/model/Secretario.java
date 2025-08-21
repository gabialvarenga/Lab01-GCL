package br.lab.model;

public class Secretario extends Usuario {
    
    public Secretario() {
        super();
    }
    
    public Secretario(String nome, String email, String senha) {
        super(nome, email, senha);
    }
    
    public void gerenciarCursos() {
        // Implementação do método de gerenciamento de cursos
        System.out.println("Gerenciando cursos...");
    }
    
    public void gerenciarDisciplinas() {
        // Implementação do método de gerenciamento de disciplinas
        System.out.println("Gerenciando disciplinas...");
    }
    
    public void gerenciarCurriculo() {
        // Implementação do método de gerenciamento de currículo
        System.out.println("Gerenciando currículo...");
    }
    
    public void gerenciarAluno() {
        // Implementação do método de gerenciamento de alunos
        System.out.println("Gerenciando alunos...");
    }
    
    public void cadastrarProfessor() {
        // Implementação do método de cadastro de professores
        System.out.println("Cadastrando professor...");
    }
}
