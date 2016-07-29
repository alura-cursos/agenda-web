package br.com.caelum.alura.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.joda.time.LocalDateTime;

import br.com.caelum.alura.converter.LocalDateTimeConverter;
import br.com.caelum.alura.dto.Acao;

@Entity
public class Registro {

	@Id
	private Long id;
	@Enumerated(EnumType.STRING)
	private Acao acao;
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dataHora;

	public Registro() {

	}

	public Registro(Long id, Acao acao) {
		this.id = id;
		this.acao = acao;
		this.dataHora = LocalDateTime.now();
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", acao=" + acao + ", dataHora=" + dataHora + "]";
	}

}
