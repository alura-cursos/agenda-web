package br.com.caelum.alura.web.agenda.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.caelum.alura.core.agenda.model.Aluno;
import br.com.caelum.alura.db.agenda.repository.AlunoRepository;

@Service
public class AlunoService {

	private AlunoRepository alunoRepository;

	@Inject
	public AlunoService (AlunoRepository alunoRepository){
		this.alunoRepository = alunoRepository;
	}
	
	public void salvar(Aluno aluno){
		alunoRepository.save(aluno);
	}

	public List<Aluno> getLista() {
		return (List<Aluno>) alunoRepository.findAll();
	}
	
	
}
