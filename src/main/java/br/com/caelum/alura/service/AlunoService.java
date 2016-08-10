package br.com.caelum.alura.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.alura.dto.AlunoSync;
import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.repository.AlunoRepository;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;
	private DispositivoService dispositivoService;

	@Autowired
	public AlunoService(AlunoRepository alunoRepository, DispositivoService dispositivoService) {
		this.alunoRepository = alunoRepository;
		this.dispositivoService = dispositivoService;
	}

	public String salvar(Aluno aluno) {
		aluno.alunoModificado();
		verificaId(aluno);
		alunoRepository.save(aluno);
		notificaAlteracao(aluno);
		return aluno.getId();
	}

	private void verificaId(Aluno aluno) {
		if (aluno.getId() == null) {
			aluno.setId(UUID.randomUUID().toString());
		}
	}

	@Transactional
	public List<Aluno> salvar(List<Aluno> alunos) {
		List<Aluno> alunosSalvos = new ArrayList<>();
		for (Aluno aluno : alunos) {
			if (aluno.getId() != null) {
				String id = salvar(aluno);
				alunosSalvos.add(busca(id));
			}
		}
		return alunosSalvos;
	}

	private void notificaAlteracao(Aluno aluno) {
		List<Aluno> alunos = new ArrayList<>(Arrays.asList(aluno));
		dispositivoService.enviaNotificacao(alunos);
	}

	public List<Aluno> getLista() {
		return alunoRepository.alunosVisiveis();
	}

	public long getTotal() {
		return alunoRepository.count();
	}

	@Transactional
	public void deletar(String id) {
		Aluno aluno = busca(id);
		aluno.setDesativado(1);
		aluno.alunoModificado();
		salvar(aluno);
	}

	public boolean existe(String id) {
		return alunoRepository.exists(id);
	}

	public Aluno busca(String id) {
		return alunoRepository.findOne(id);
	}

	public AlunoSync getSyncLista() {
		return new AlunoSync(alunoRepository.alunosVisiveis());
	}

	public AlunoSync novosRegistro(LocalDateTime datahora) {
		List<Aluno> alunos = alunoRepository.alunosModificados(datahora);
		return new AlunoSync(alunos);
	}

	public boolean temAtualizacao(String datahora) {
		try {
			LocalDateTime localDateTime = LocalDateTime.parse(datahora);
			return alunoRepository.existeAtualizacao(localDateTime);
		} catch (IllegalFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

}
