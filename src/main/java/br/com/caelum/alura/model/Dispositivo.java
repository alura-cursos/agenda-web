package br.com.caelum.alura.model;

public class Dispositivo {

	private String token;
	private Aluno aluno;

	public Dispositivo(String token, Aluno aluno) {
		this.token = token;
		this.aluno = aluno;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
