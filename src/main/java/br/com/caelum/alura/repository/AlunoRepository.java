package br.com.caelum.alura.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.caelum.alura.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	Aluno findByNome(String nome);
}
