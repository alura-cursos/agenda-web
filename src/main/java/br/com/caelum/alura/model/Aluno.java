package br.com.caelum.alura.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.caelum.alura.converter.LocalDateTimeConverter;

@Entity
public class Aluno {

	@Id
	@GenericGenerator(name = "id", strategy = "uuid2")
	private String id;
	private String nome;
	private String endereco;
	private String telefone;
	private String site;
	private Double nota;
	private String caminhoFoto;
	private int desativado;
	@JsonIgnore
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modificacao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	public void modificado() {
		this.modificacao = LocalDateTime.now();
	}

	public LocalDateTime getModificacao() {
		return modificacao;
	}

	public int getDesativado() {
		return desativado;
	}

	public void desativa() {
		this.desativado = 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Aluno aluno = (Aluno) obj;
		if (aluno.id == this.id)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", site="
				+ site + ", nota=" + nota + ", caminhoFoto=" + caminhoFoto + ", desativado=" + desativado
				+ ", modificacao=" + modificacao + "]";
	}

}
