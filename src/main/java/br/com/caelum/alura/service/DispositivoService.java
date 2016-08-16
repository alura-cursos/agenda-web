package br.com.caelum.alura.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
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

	private final DispositivoRepository dispositivoRepository;
	private static final Logger LOGGER = Logger.getLogger(AlunoService.class);

	@Autowired
	public DispositivoService(DispositivoRepository dispositivoRepository) {
		this.dispositivoRepository = dispositivoRepository;
	}

	public void salva(Dispositivo dispositivo) {
		dispositivoRepository.save(dispositivo);
		LOGGER.info("dispositivo salvo " + dispositivo.getToken());
	}

	@Async
	public void enviaNotificacao(List<Aluno> alunos) {
		logaAlunos("enviando alunos", alunos);

		List<Dispositivo> dispositivos = (List<Dispositivo>) dispositivoRepository.findAll();
		try {
			FirebaseSender firebaseSender = new FirebaseSender(this);
			firebaseSender.envia(dispositivos, new AlunoSync(alunos));

		} catch (IOException e) {
			e.printStackTrace();
		}

		logaAlunos("alunos enviados com sucesso", alunos);
	}

	public void deleta(List<Dispositivo> invalidos) {
		if (!invalidos.isEmpty()) {
			dispositivoRepository.delete(invalidos);
			logaDispositivos("dispositivos excluídos", invalidos);
		}
	}

	private void logaAlunos(String mensagem, List<Aluno> alunos) {
		LOGGER.info(mensagem);
		alunos.forEach(aluno -> LOGGER.info(aluno.getId()));
	}

	private void logaDispositivos(String mensagem, List<Dispositivo> invalidos) {
		LOGGER.info(mensagem);
		invalidos.forEach(dispositivo -> LOGGER.info(dispositivo.getToken()));
	}

}
