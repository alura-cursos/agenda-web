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

	@Autowired
	public AlunoService(AlunoRepository alunoRepository, RegistroService registoService) {
		this.alunoRepository = alunoRepository;
		this.registoService = registoService;
	}

	public void salvar(Aluno aluno) {
		Long id = aluno.getId();
		if (id != null) {
			registoService.alteracao(id);
			alunoRepository.save(aluno);
		} else {
			alunoRepository.save(aluno);
			registoService.insercao(aluno.getId());
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
