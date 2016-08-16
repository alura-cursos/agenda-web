package br.com.caelum.alura.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.com.caelum.alura.firebase.FirebaseClient;

@Service
public class FirebaseService {
	public boolean tokenValido() throws IOException {
		return new FirebaseClient().validaAPIKey();
	}
}
