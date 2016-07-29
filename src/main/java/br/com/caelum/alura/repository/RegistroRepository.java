package br.com.caelum.alura.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.caelum.alura.model.Registro;

public interface RegistroRepository extends PagingAndSortingRepository<Registro, Long> {

}
