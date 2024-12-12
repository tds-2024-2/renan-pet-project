package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Turma {

  private String codigo; // tds-2024-2
  private Disciplina disciplina;
  private Periodo periodo;
  private int vagas;
  private List<Matricula> matriculas = new ArrayList<>();

  public List<Matricula> getMatriculas() {
    return matriculas;
  }

  public void setMatriculas(List<Matricula> matriculas) {
    this.matriculas = matriculas;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  public Periodo getPeriodo() {
    return periodo;
  }

  public void setPeriodo(Periodo periodo) {
    this.periodo = periodo;
  }

  public int getVagas() {
    return vagas;
  }

  public void setVagas(int vagas) {
    this.vagas = vagas;
  }

  public int getNumeroMatriculas() {
    return this.matriculas.size();
  }

  public boolean isAlunoMatriculado(Aluno aluno) {
    return this.getMatriculas().stream().anyMatch(m -> m.getAluno().equals(aluno));
  }
  
}