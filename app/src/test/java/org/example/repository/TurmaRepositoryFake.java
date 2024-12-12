
package org.example.repository;

import java.util.List;
import java.util.Optional;

import org.example.entity.Aluno;
import org.example.entity.Disciplina;
import org.example.entity.Periodo;
import org.example.entity.Turma;

public class TurmaRepositoryFake implements TurmaRepository {

  @Override
  public Optional<Turma> findByCodigo(String codigoTurma) {
    if (codigoTurma.equals("tds-2024-2")) {
      Periodo p = new Periodo(2024, 2);
      Disciplina d = new Disciplina();
      d.setCargaHoraria(20);
      d.setCodigo("tds");
      d.setNome("Topicos em Desenvolvimento de Sistemas");
      Turma t = new Turma();
      t.setCodigo("tds-2024-2");
      t.setDisciplina(d);
      t.setPeriodo(p);
      t.setVagas(10);
      return Optional.of(t);
    }
    return Optional.empty();
  }

  @Override
  public List<Turma> findAllContainingAluno(Aluno aluno) {
    throw new UnsupportedOperationException("Unimplemented method 'findAllContainingAluno'");
  }
}