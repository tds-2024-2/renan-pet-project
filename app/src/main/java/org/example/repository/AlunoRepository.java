package org.example.repository;

import java.util.List;
import java.util.Optional;
import org.example.entity.Aluno;
import org.example.entity.Turma;

public interface AlunoRepository {

  Optional<Aluno> findByNumeroMatricula(int numeroMatricula);

  List<Turma> findAllTurmas();
}