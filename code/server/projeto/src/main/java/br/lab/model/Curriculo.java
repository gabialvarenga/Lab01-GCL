package br.lab.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Curriculo {
    private int id;
    private int ano;
    private int periodo;
    private LocalDate periodoMatriculaInicio;
    private LocalDate periodoMatriculaFim;
    private List<Disciplina> disciplinasOfertadas;
    
    public Curriculo() {
        this.disciplinasOfertadas = new ArrayList<>();
    }
    
    public Curriculo(int id, int ano, int periodo) {
        this.id = id;
        this.ano = ano;
        this.periodo = periodo;
        this.disciplinasOfertadas = new ArrayList<>();
    }
    
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplina.setCurriculoId(this.id);
        if (!disciplinasOfertadas.contains(disciplina)) {
            disciplinasOfertadas.add(disciplina);
        }
    }
    
    public void removerDisciplina(Disciplina disciplina) {
        disciplinasOfertadas.remove(disciplina);
    }
    
    public boolean isPeriodoMatriculaAberto() {
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(periodoMatriculaInicio) && 
               hoje.isBefore(periodoMatriculaFim);
    }
    
    public void abrirPeriodoMatricula() {
        this.periodoMatriculaInicio = LocalDate.now();
        this.periodoMatriculaFim = LocalDate.now().plusDays(15); // Exemplo: 15 dias para matrícula
        System.out.println("Período de matrícula aberto até " + periodoMatriculaFim);
    }
    
    public void fecharPeriodoMatricula() {
        this.periodoMatriculaFim = LocalDate.now();
        System.out.println("Período de matrícula fechado");
        verificarDisciplinasAtivas();
    }
    
    public void verificarDisciplinasAtivas() {
        for (Disciplina disciplina : disciplinasOfertadas) {
            boolean ativa = disciplina.verificarAtivacao();
            if (!ativa) {
                System.out.println("Disciplina " + disciplina.getNome() + 
                                  " cancelada por falta de alunos.");
            }
        }
    }
    
   
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getAno() {
        return ano;
    }
    
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public int getPeriodo() {
        return periodo;
    }
    
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
    public LocalDate getPeriodoMatriculaInicio() {
        return periodoMatriculaInicio;
    }
    
    public void setPeriodoMatriculaInicio(LocalDate periodoMatriculaInicio) {
        this.periodoMatriculaInicio = periodoMatriculaInicio;
    }
    
    public LocalDate getPeriodoMatriculaFim() {
        return periodoMatriculaFim;
    }
    
    public void setPeriodoMatriculaFim(LocalDate periodoMatriculaFim) {
        this.periodoMatriculaFim = periodoMatriculaFim;
    }
    
    public List<Disciplina> getDisciplinasOfertadas() {
        return disciplinasOfertadas;
    }
    
    public void setDisciplinasOfertadas(List<Disciplina> disciplinasOfertadas) {
        this.disciplinasOfertadas = disciplinasOfertadas;
    }
}