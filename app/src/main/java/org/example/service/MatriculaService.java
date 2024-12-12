package org.example.service;

import java.time.LocalDate;
import java.util.List;

import org.example.entity.Aluno;
import org.example.entity.Disciplina;
import org.example.entity.Matricula;
import org.example.entity.Matricula.Status;
import org.example.entity.Turma;
import org.example.exception.DomainException;
import org.example.exception.NotFoundException;
import org.example.repository.AlunoRepository;
import org.example.repository.TurmaRepository;

public class MatriculaService {

  private static int codigo = 1;

  private final AlunoRepository alunoRepository;
  private final TurmaRepository turmaRepository;

  public MatriculaService(AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
    this.alunoRepository = alunoRepository;
    this.turmaRepository = turmaRepository;
  }

  public Matricula matricular(final int numeroMatricula, final String codigoTurma) {

    final Aluno aluno = alunoRepository
        .findByNumeroMatricula(numeroMatricula)
        .orElseThrow(
            () -> new NotFoundException("Aluno " + numeroMatricula + " não encontrado"));

    final Turma turma = turmaRepository
        .findByCodigo(codigoTurma)
        .orElseThrow(() -> new NotFoundException("Turma " + codigoTurma + " não encontrada"));

    if (turma.isAlunoMatriculado(aluno)) { // teste faltando
      throw new DomainException(
          "Aluno " + numeroMatricula + " já matriculado na turma " + turma.getCodigo());
    }

    final Disciplina disciplina = turma.getDisciplina();

    if (turma.getNumeroMatriculas() >= turma.getVagas()) {
      final List<Turma> historico = turmaRepository.findAllContainingAluno(aluno);

      final List<Turma> historicoDisciplina = historico.stream().filter(t -> t.getDisciplina().equals(disciplina))
          .toList();

      if (historicoDisciplina.stream().anyMatch(t -> t.getMatriculas().stream() // teste faltando
          .anyMatch(m -> Status.APROVADO.equals(m.getStatus())))) {

        throw new DomainException("Aluno %d já foi aprovado em disciplina %s"
            .formatted(numeroMatricula, disciplina.getCodigo()));
      }

      if (historicoDisciplina.stream() // teste faltando
          .noneMatch(t -> t.getMatriculas().stream()
              .anyMatch(m -> Status.REPROVADO.equals(m.getStatus())))) {

        throw new DomainException("Não há vagas na turma " + turma.getCodigo());
      }
    }

    final Matricula mat = new Matricula();
    mat.setCodigo(codigo++);
    mat.setAluno(aluno);
    mat.setData(LocalDate.now());
    mat.setStatus(Status.REGULAR);

    return mat;
  }
}