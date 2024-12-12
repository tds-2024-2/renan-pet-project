package org.example.entity;

import java.time.LocalDate;

public class Matricula {
  public enum Status {
    REGULAR, // cursando
    CANCELADA,
    APROVADO,
    REPROVADO
  }

  private int codigo;
  private Aluno aluno;
  private Status status;
  private LocalDate data;

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public int getCodigo() {
    return codigo;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }
}