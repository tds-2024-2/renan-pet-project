package org.example.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThat;

import org.example.exception.NotFoundException;
import org.example.repository.AlunoRepository;
import org.example.repository.AlunoRepositoryFake;
import org.example.repository.TurmaRepository;
import org.example.repository.TurmaRepositoryFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatriculaServiceTest {

  MatriculaService matriculaService;
  AlunoRepository alunoRepository;
  TurmaRepository turmaRepository;

  @BeforeEach
  void Setup() {
    alunoRepository = new AlunoRepositoryFake();
    turmaRepository = new TurmaRepositoryFake();
    matriculaService = new MatriculaService(alunoRepository, turmaRepository);
  }

  @Test
  void TestVerdade() {
    assertThat(true).isTrue();
  }

  @Test
  void TestFalso() {
    assertThat(false).isFalse();
  }

  @Test
  void TestHaUmaInstanciaDeMatriculaServices() {
    assertThat(matriculaService).isNotNull();
  }

  @Test
  void TestAlunoNaoExisteLançaExceçãoNotFoundException() {
    assertThatThrownBy(() -> matriculaService.matricular(123, "tds-2024-2"))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Aluno 123 não encontrado");
  }

  @Test
  void TestTurmaNaoExisteLançaExceçãoNotFoundException() {
    assertThatThrownBy(() -> matriculaService.matricular(20221111, "tda-2024-2"))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Turma tda-2024-2 não encontrada");
  }

}