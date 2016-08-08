package br.com.caelum.alura.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.caelum.alura.dto.AlunoSync;
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
	public void enviaNotificacao(List<Aluno> alunos) {

		List<Dispositivo> dispositivos = (List<Dispositivo>) dispositivoRepository.findAll();
		try {
			Thread.sleep(1000L);
			FirebaseSender firebaseSender = new FirebaseSender(this);
			firebaseSender.envia(dispositivos, new AlunoSync(alunos));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void deleta(List<Dispositivo> invalidos) {
		dispositivoRepository.delete(invalidos);
	}

}
