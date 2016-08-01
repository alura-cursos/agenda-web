package br.com.caelum.alura.repository;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.caelum.alura.model.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {

	List<Aluno> findAllByOrderByIdDesc();

	@Query("UPDATE Aluno a SET a.removido = true WHERE a.id = :id")
	void deletaAluno(@Param("id") Long id);

	@Query("FROM Aluno a WHERE a.modificacao > :datahora")
	List<Aluno> alunosModificados(@Param("datahora") LocalDateTime datahora);

	@Query("FROM Aluno a WHERE a.removido = false")
	List<Aluno> alunosVisiveis();
}
