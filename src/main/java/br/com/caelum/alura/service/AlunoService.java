package br.com.caelum.alura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.repository.AlunoRepository;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;

	@Autowired
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public void salvar(Aluno aluno) {
		alunoRepository.save(aluno);
	}

	public List<Aluno> getLista() {
		System.out.println("total " + getTotal());
		return (List<Aluno>) alunoRepository.findAll();
	}

	public long getTotal() {
		return alunoRepository.count();
	}

	public void deletar(Long id) {
		alunoRepository.delete(alunoRepository.findOne(id));
	}

	public boolean existe(Long id) {
		return alunoRepository.exists(id);
	}

}
