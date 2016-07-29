package br.com.caelum.alura.dto;

import br.com.caelum.alura.model.Aluno;

public class AlunoDTO {

	private Acao acao;
	private Aluno aluno;

	public AlunoDTO(Aluno aluno, Acao acao) {
		this.aluno = aluno;
		this.acao = acao;
	}

	public AlunoDTO() {

	}

	public AlunoDTO(Long id, Acao acao) {
		this.acao = acao;
		Aluno aluno = new Aluno();
		aluno.setId(id);
		this.aluno = aluno;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
