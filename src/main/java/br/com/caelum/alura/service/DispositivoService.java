package br.com.caelum.alura.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.caelum.alura.firebase.FirebaseSender;
import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.model.Dispositivo;
import br.com.caelum.alura.repository.DispositivoRepository;

@Service
public class DispositivoService {

	private DispositivoRepository dispositivoRepository;

	@Autowired
	public DispositivoService(DispositivoRepository dispositivoRepository) {
		this.dispositivoRepository = dispositivoRepository;
	}

	public void salva(Dispositivo dispositivo) {
		dispositivoRepository.save(dispositivo);
	}

	@Async
	public void enviaNotificacao(Aluno aluno) {
		List<Dispositivo> dispositivos = (List<Dispositivo>) dispositivoRepository.findAll();
		try {
			FirebaseSender firebaseSender = new FirebaseSender();
			firebaseSender.envia(dispositivos, aluno);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
