package br.com.caelum.alura.db.agenda.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.caelum.alura.core.agenda.model.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {

}
