package org.example.repository;

import java.util.List;
import java.util.Optional;

import org.example.entity.Aluno;
import org.example.entity.Turma;

public interface TurmaRepository {

  Optional<Turma> findByCodigo(String codigoTurma);

  List<Turma> findAllContainingAluno(Aluno aluno);
}