package br.com.caelum.alura.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void salvar(Aluno aluno) {
		aluno.alunoModificado();
		alunoRepository.save(aluno);
		notificaAlteracao(aluno);
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

	public void deletar(Long id) {
		Aluno aluno = getAluno(id);
		aluno.setAtivo(0);
		salvar(aluno);
	}

	public boolean existe(Long id) {
		return alunoRepository.exists(id);
	}

	public Aluno getUltimo() {
		return alunoRepository.findAllByOrderByIdDesc().get(0);
	}

	public Aluno getAluno(Long id) {
		return alunoRepository.findOne(id);
	}

	public AlunoSync getSyncLista() {
		return new AlunoSync(alunoRepository.alunosVisiveis());
	}

	public AlunoSync novosRegistro(LocalDateTime datahora) {
		List<Aluno> alunos = alunoRepository.alunosModificados(datahora);
		return new AlunoSync(alunos);
	}

	public List<Aluno> salvaLista(List<Aluno> alunos) {
		List<Aluno> alunosSalvos = new ArrayList<>();
		for (Aluno aluno : alunos) {
			salvar(aluno);
			alunosSalvos.add(getUltimo());
		}
		return alunosSalvos;
	}

}
