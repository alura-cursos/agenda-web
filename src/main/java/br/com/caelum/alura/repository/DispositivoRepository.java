package br.com.caelum.alura.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.caelum.alura.model.Dispositivo;

public interface DispositivoRepository extends PagingAndSortingRepository<Dispositivo, String>{

}
