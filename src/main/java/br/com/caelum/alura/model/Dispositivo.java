package br.com.caelum.alura.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dispositivo {

	@Id
	private String token;

	public Dispositivo(String token) {
		this.token = token;
	}
	
	public Dispositivo (){
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
