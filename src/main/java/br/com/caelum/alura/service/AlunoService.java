package br.com.caelum.alura.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.alura.dto.AlunoSync;
import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.repository.AlunoRepository;
import br.com.caelum.alura.utils.UUIDUtils;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;
	private DispositivoService dispositivoService;
	private static final Logger LOGGER = Logger.getLogger(AlunoService.class);
	private UUIDUtils uuidUtils;

	@Autowired
	public AlunoService(AlunoRepository alunoRepository, DispositivoService dispositivoService, UUIDUtils uuidUtils) {
		this.alunoRepository = alunoRepository;
		this.dispositivoService = dispositivoService;
		this.uuidUtils = uuidUtils;
	}

	public String salva(Aluno aluno) {
		aluno.modificado();
		geraIdSeForNuloOuInvalido(aluno);
		alunoRepository.save(aluno);
		LOGGER.info("aluno salvo " + aluno.getId());
		notificaAlteracao(aluno);
		return aluno.getId();
	}

	@Transactional
	public List<Aluno> salva(List<Aluno> alunos) {
		LOGGER.info("Validando ids dos alunos");
		if (alunos.stream().filter(a -> a.getId() == null).findAny().isPresent()) {
			LOGGER.error("Existe aluno sem id");
			throw new IllegalArgumentException("todos os alunos precisam de ids");
		}
		alunos.forEach(aluno -> salva(aluno));
		logaAlunos("alunosSalvos", alunos);
		return alunos;
	}

	public List<Aluno> getLista() {
		return alunoRepository.visiveis();
	}

	public long getTotal() {
		return alunoRepository.count();
	}

	@Transactional
	public void remove(String id) {
		Aluno aluno = busca(id);
		aluno.desativa();
		aluno.modificado();
		LOGGER.info("aluno removido " + id);
		salva(aluno);
	}

	public boolean existe(String id) {
		return alunoRepository.exists(id);
	}

	public Aluno busca(String id) {
		return alunoRepository.findOne(id);
	}

	public AlunoSync getSyncLista() {
		return new AlunoSync(alunoRepository.visiveis());
	}

	public AlunoSync novosRegistro(LocalDateTime datahora) {
		List<Aluno> alunos = alunoRepository.modificados(datahora);
		return new AlunoSync(alunos);
	}

	public boolean temAtualizacao(String datahora) {
		LOGGER.info("verificando se existe novas alterações");
		try {
			LocalDateTime datahoraRecebida = LocalDateTime.parse(datahora);
			return alunoRepository.existeAtualizacao(datahoraRecebida);
		} catch (IllegalArgumentException e) {
			LOGGER.error("data com formato inválido");
		} catch (NullPointerException e) {
			LOGGER.error("data nula");
		}
		return false;
	}

	private void logaAlunos(String mensagem, List<Aluno> alunos) {
		LOGGER.info(mensagem);
		alunos.forEach(aluno -> LOGGER.info(aluno.getId()));
	}

	private void notificaAlteracao(Aluno aluno) {
		List<Aluno> alunos = new ArrayList<>(Arrays.asList(aluno));
		dispositivoService.enviaNotificacao(alunos);
	}

	private void geraIdSeForNuloOuInvalido(Aluno aluno) {
		LOGGER.info("validando id");
		String uuid = aluno.getId();
		if (aluno.getId() == null || !uuidUtils.ehValido(uuid)) {
			LOGGER.info("gerando novo id");
			aluno.setId(uuidUtils.geraUUIDAleatorio());
			LOGGER.info("id gerado " + aluno.getId());
		}
	}

}
