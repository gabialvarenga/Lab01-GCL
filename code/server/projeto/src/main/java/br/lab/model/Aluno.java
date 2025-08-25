package br.lab.model;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import br.lab.enums.Role;
import br.lab.enums.StatusMatricula;
import br.lab.enums.Tipo;

public class Aluno extends Usuario {
    private String matricula;
    private List<Matricula> matriculas;
     
    public Aluno() {
        super();
        this.matriculas = new ArrayList<>();
    }
    
    public Aluno(int id, String nome, String email, String senha, String matricula) {
        super(id, nome, email, senha, Role.ALUNO);
        this.matricula = matricula;
        this.matriculas = new ArrayList<>();
    }
    
    public boolean realizarMatricula(Disciplina disciplina, Tipo tipo) {
        if (!podeMatricular(tipo)) {
            return false;
        }
        
        if (!disciplina.podeAceitarMatricula()) {
            return false;
        }
        
        Matricula novaMatricula = new Matricula();
        novaMatricula.setAluno(this);
        novaMatricula.setDisciplina(disciplina);
        novaMatricula.setStatus(StatusMatricula.ATIVA);
        novaMatricula.setTipo(tipo);
        
        matriculas.add(novaMatricula);
        disciplina.adicionarAluno(this);
        
        System.out.println("Matrícula realizada com sucesso.");
        return true;
    }
    
    public boolean cancelarMatricula(Disciplina disciplina) {
        List<Matricula> matriculasNaDisciplina = matriculas.stream()
            .filter(m -> m.getDisciplina().equals(disciplina) && m.getStatus() == StatusMatricula.ATIVA)
            .collect(Collectors.toList());
            
        if (matriculasNaDisciplina.isEmpty()) {
            return false;
        }
        
        matriculasNaDisciplina.forEach(Matricula::cancelar);
        disciplina.removerAluno(this);
        
        System.out.println("Matrícula cancelada com sucesso.");
        return true;
    }
    
    public boolean podeMatricular(Tipo tipo) {
        int count = contarMatriculasPorTipo(tipo);
        if (tipo == Tipo.OBRIGATORIA) {
            return count < 4; 
        } else {
            return count < 2;
        }
    }
    
    public int contarMatriculasPorTipo(Tipo tipo) {
        return (int) matriculas.stream()
            .filter(m -> m.getStatus() == StatusMatricula.ATIVA && m.getTipo() == tipo)
            .count();
    }
    
    public List<Disciplina> getDisciplinasMatriculadas() {
        return matriculas.stream()
            .filter(m -> m.getStatus() == StatusMatricula.ATIVA)
            .map(Matricula::getDisciplina)
            .collect(Collectors.toList());
    }
    
    public boolean verificarLimitesMatricula(Tipo tipo) {
        return podeMatricular(tipo);
    }
    
    public List<Matricula> getMatriculas() {
        return matriculas;
    }
    
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}