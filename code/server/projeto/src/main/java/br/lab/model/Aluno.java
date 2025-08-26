package br.lab.model;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import br.lab.enums.Role;
import br.lab.enums.Tipo;

public class Aluno extends Usuario {
    private String matricula;
    private Map<Disciplina, Tipo> disciplinasMatriculadas;
    
    public Aluno() {
        super();
        this.disciplinasMatriculadas = new HashMap<>();
    }
    
    public Aluno(int id, String nome, String email, String senha, String matricula) {
        super(id, nome, email, senha, Role.ALUNO);
        this.matricula = matricula;
        this.disciplinasMatriculadas = new HashMap<>();
    }
    
    public boolean realizarMatricula(Disciplina disciplina, Tipo tipo) {
        if (!podeMatricular(tipo)) {
            return false;
        }
        
        if (!disciplina.podeAceitarMatricula()) {
            return false;
        }
        
        disciplinasMatriculadas.put(disciplina, tipo);
        disciplina.adicionarAluno(this);
        
        System.out.println("Matrícula realizada com sucesso.");
        return true;
    }
    
    public boolean cancelarMatricula(Disciplina disciplina) {
        if (!disciplinasMatriculadas.containsKey(disciplina)) {
            return false;
        }
        
        disciplinasMatriculadas.remove(disciplina);
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
        return (int) disciplinasMatriculadas.values()
            .stream()
            .filter(t -> t == tipo)
            .count();
    }
    
    public List<Disciplina> getDisciplinasMatriculadas() {
        return new ArrayList<>(disciplinasMatriculadas.keySet());
    }
    
    public List<Disciplina> getDisciplinasPorTipo(Tipo tipo) {
        return disciplinasMatriculadas.entrySet()
            .stream()
            .filter(entry -> entry.getValue() == tipo)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
    
    public boolean verificarLimitesMatricula(Tipo tipo) {
        return podeMatricular(tipo);
    }
    
    public Tipo getTipoDisciplina(Disciplina disciplina) {
        return disciplinasMatriculadas.get(disciplina);
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public Map<Disciplina, Tipo> getDisciplinasMatriculadasMap() {
        return disciplinasMatriculadas;
    }
}