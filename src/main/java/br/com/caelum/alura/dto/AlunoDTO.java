package br.com.caelum.alura.dto;

import br.com.caelum.alura.model.Registro;

public class AlunoDTO {

	private Long id;
	private Acao acao;

	public AlunoDTO(Long id, Acao acao) {
		this.id = id;
		this.acao = acao;
	}

	public AlunoDTO() {

	}

	public AlunoDTO(Registro registro) {
		this.id = registro.getId();
		this.acao = registro.getAcao();
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
