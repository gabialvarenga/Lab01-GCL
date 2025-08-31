package br.lab.model;

import java.util.List;
import java.io.Serializable;
import br.lab.enums.Tipo;
import java.util.ArrayList;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nome;
    private int creditos;
    private int cargaHoraria;
    private int alunosMatriculados;
    private boolean ativo;
    private Tipo tipo;
    private int curriculoId;
    private transient Professor professor;
    private transient List<Aluno> alunos;
    
    public static final int MIN_ALUNOS = 3;
    public static final int MAX_ALUNOS = 60;
    
    public Disciplina() {
        this.alunos = new ArrayList<>();
        this.alunosMatriculados = 0;
        this.ativo = true;
    }
    
    public Disciplina(String codigo, String nome, int creditos, int cargaHoraria, Professor professor, Tipo tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.creditos = creditos;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.tipo = tipo;
        this.alunos = new ArrayList<>();
        this.alunosMatriculados = 0;
        this.ativo = true;
    }
    
    public boolean podeAceitarMatricula() {

        return this.ativo && this.alunosMatriculados < MAX_ALUNOS;
    }
    
    public boolean verificarAtivacao() {

        this.ativo = this.alunosMatriculados >= MIN_ALUNOS;
        return this.ativo;
    }
    
    public int getVagasDisponiveis() {
        return MAX_ALUNOS - alunosMatriculados;
    }
    
    public void adicionarAluno(Aluno aluno) {
        if (!alunos.contains(aluno) && alunosMatriculados < MAX_ALUNOS) {
            alunos.add(aluno);
            alunosMatriculados++;
        }
    }
    
    public void removerAluno(Aluno aluno) {
        if (alunos.remove(aluno)) {
            alunosMatriculados--;
        }
    }
    
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCreditos() {
        return creditos;
    }
    
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    public int getCargaHoraria() {
        return cargaHoraria;
    }
    
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public int getAlunosMatriculados() {
        return alunosMatriculados;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public int getCurriculoId() {
        return curriculoId;
    }
    
    public void setCurriculoId(int curriculoId) {
        this.curriculoId = curriculoId;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}