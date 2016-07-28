package br.com.caelum.alura.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.caelum.alura.dto.Acao;
import br.com.caelum.alura.dto.AlunoDTO;
import br.com.caelum.alura.firebase.FirebaseSender;
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

	public void notificaNovoRegistro(Long id) {
		AlunoDTO alunoDto = new AlunoDTO(id, Acao.ADICIONA);
		enviaNotificacao(alunoDto);
	}

	public void notificaNovaAlteracao(Long id) {
		AlunoDTO alunoDto = new AlunoDTO(id, Acao.ALTERA);
		enviaNotificacao(alunoDto);
	}

	public void notificaNovaDelecao(Long id) {
		AlunoDTO alunoDto = new AlunoDTO(id, Acao.DELETA);
		enviaNotificacao(alunoDto);
	}

	@Async
	private void enviaNotificacao(AlunoDTO alunoDto) {
		List<Dispositivo> dispositivos = (List<Dispositivo>) dispositivoRepository.findAll();
		try {
			FirebaseSender firebaseSender = new FirebaseSender();
			firebaseSender.envia(dispositivos, alunoDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
