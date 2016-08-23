package br.com.caelum.alura.service;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.caelum.alura.firebase.FirebaseClient;
import br.com.caelum.alura.firebase.FirebaseConfig;
import br.com.caelum.alura.repository.FirebaseConfigRepository;

@Service
public class FirebaseService {

	private FirebaseConfigRepository firebaseRepo;

	@Inject
	public FirebaseService(FirebaseConfigRepository firebaseRepo) {
		this.firebaseRepo = firebaseRepo;
	}

	public boolean tokenValido() throws IOException {
		return new FirebaseClient(getConfig()).validaAPIKey();
	}

	public void salva(FirebaseConfig config) {
		config.setId(1L);
		firebaseRepo.save(config);
	}

	public FirebaseConfig getConfig() {
		FirebaseConfig config = firebaseRepo.findOne(1L);
		if (config == null)
			return new FirebaseConfig("https://fcm.googleapis.com/fcm/send", "");
		return config;
	}
}
