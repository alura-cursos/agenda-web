package br.com.caelum.alura.repository;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.caelum.alura.model.Registro;

public interface RegistroRepository extends PagingAndSortingRepository<Registro, Long> {

	@Query("FROM Registro r WHERE r.dataHora > :datahora")
	List<Registro> getNovosRegistros(@Param("datahora") LocalDateTime datahora);

}
