package br.com.caelum.alura.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.caelum.alura.model.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {

	Aluno findByNome(String nome);

	List<Aluno> findAllByOrderByIdDesc();
}
