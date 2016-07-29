package br.com.caelum.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.repository.AlunoRepository;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;
	private RegistroService registoService;
	private DispositivoService dispositivoService;

	@Autowired
	public AlunoService(AlunoRepository alunoRepository, RegistroService registoService,
			DispositivoService dispositivoService) {
		this.alunoRepository = alunoRepository;
		this.registoService = registoService;
		this.dispositivoService = dispositivoService;
	}

	public void salvar(Aluno aluno) {
		Long id = aluno.getId();
		if (id != null && existe(id)) {
			registoService.alteracao(id);
			alunoRepository.save(aluno);
			dispositivoService.notificaNovaAlteracao(id);
		} else {
			alunoRepository.save(aluno);
			registoService.insercao(aluno.getId());
			dispositivoService.notificaNovoRegistro(aluno.getId());
		}
	}

	public List<Aluno> getLista() {
		return (List<Aluno>) alunoRepository.findAll();
	}

	public long getTotal() {
		return alunoRepository.count();
	}

	public void deletar(Long id) {
		registoService.delecao(id);
		alunoRepository.delete(alunoRepository.findOne(id));
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

}
