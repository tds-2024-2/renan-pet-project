package org.example.service;

import static org.junit.Assert.assertThat;

import org.example.entity.Turma;
import org.example.exception.NotFoundException;
import org.example.repository.AlunoRepository;
import org.example.repository.AlunoRepositoryFake;
import org.example.repository.TurmaRepository;
import org.example.repository.TurmaRepositoryFake;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

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

  @Test
  void TestMatricularAlunoEmTurmaExistente() {
    matriculaService.matricular(20221111, "tds-2024-2");
    // Simula a chamada para verificar se a turma foi atualizada corretamente após a
    // matrícula.
    Turma turma = turmaRepository.findByCodigo("tds-2024-2").orElseThrow();
    assertThat(turma.getVagas()).isEqualTo(9);
  }

  @Test
  void TestMatricularAlunoEmTurmaComVagasEsgotadas() {
    Turma turma = turmaRepository.findByCodigo("tds-2024-2").orElseThrow();
    turma.setVagas(0);

    assertThatThrownBy(() -> matriculaService.matricular(20221111, "tds-2024-2"))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Turma tds-2024-2 não possui mais vagas disponíveis");
  }

  @Test
  void TestMatricularAlunoDuasVezesNaMesmaTurma() {
    matriculaService.matricular(20221111, "tds-2024-2");
    assertThatThrownBy(() -> matriculaService.matricular(20221111, "tds-2024-2"))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Aluno 20221111 já está matriculado na turma tds-2024-2");
  }

  @Test
  void TestMatricularAlunoComDadosInvalidos() {
    assertThatThrownBy(() -> matriculaService.matricular(-1, "invalid-turma"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Dados da matrícula são inválidos");
  }

  @Test
  void TestMatricularAlunoEmTurmaComVagasAtualizacao() {
    Turma turma = turmaRepository.findByCodigo("tds-2024-2").orElseThrow();
    turma.setVagas(5);

    matriculaService.matricular(20221111, "tds-2024-2");
    assertThat(turma.getVagas()).isEqualTo(4);
  }

}