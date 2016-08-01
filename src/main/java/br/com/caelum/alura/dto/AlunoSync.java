package br.com.caelum.alura.dto;

import java.util.List;

import org.joda.time.LocalDateTime;

import br.com.caelum.alura.model.Aluno;

public class AlunoSync {

	private final List<Aluno> alunos;
	private final String datahora;

	public AlunoSync(List<Aluno> alunos) {
		this.alunos = alunos;
		this.datahora = LocalDateTime.now().toString();
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public String getDatahora() {
		return datahora;
	}

}
