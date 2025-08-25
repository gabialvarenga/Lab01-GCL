package br.lab.model;

import br.lab.enums.Role;

public class Secretaria extends Usuario {
    
    public Secretaria() {
        super();
    }
    
    public Secretaria(int id, String nome, String email, String senha) {
        super(id, nome, email, senha, Role.SECRETARIA);
    }
    
    public void gerenciarCursos() {
       
        System.out.println("Gerenciando cursos...");
    }
    
    public void gerenciarDisciplinas() {
        
        System.out.println("Gerenciando disciplinas...");
    }
    
    public Curriculo gerarCurriculo(int ano, int periodo) {

        System.out.println("Gerando currículo para " + ano + "." + periodo);
        return new Curriculo(0, ano, periodo);
    }
    
    public void gerenciarAluno() {
 
        System.out.println("Gerenciando alunos...");
    }
    
    public void cadastrarProfessor() {
        
        System.out.println("Cadastrando professor...");
    }
}