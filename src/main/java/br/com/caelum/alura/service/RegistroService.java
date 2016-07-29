package br.com.caelum.alura.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.alura.dto.Acao;
import br.com.caelum.alura.dto.AlunoDTO;
import br.com.caelum.alura.model.Registro;
import br.com.caelum.alura.repository.RegistroRepository;

@Service
public class RegistroService {

	private RegistroRepository registroRepository;

	@Autowired
	public RegistroService(RegistroRepository registroRepository) {
		this.registroRepository = registroRepository;
	}

	public void insercao(Long id) {
		registrar(new Registro(id, Acao.ADICIONA));
	}

	public void alteracao(Long id) {
		registrar(new Registro(id, Acao.ALTERA));
	}

	public void delecao(Long id) {
		registrar(new Registro(id, Acao.DELETA));
	}

	private void registrar(Registro registro) {
		registroRepository.save(registro);
	}

	public List<Registro> getLista() {
		return (List<Registro>) registroRepository.findAll();
	}

	public List<AlunoDTO> novosRegistro(LocalDateTime datahora) {
		List<AlunoDTO> dtos = new ArrayList<>();
		for (Registro registro : novosRegistros(datahora)) {
			dtos.add(new AlunoDTO(registro));
		}
		return dtos;
	}

	private List<Registro> novosRegistros(LocalDateTime datahora) {
		return registroRepository.getNovosRegistros(datahora);
	}
}
