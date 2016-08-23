package br.com.caelum.alura.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.caelum.alura.firebase.FirebaseConfig;

public interface FirebaseConfigRepository extends CrudRepository<FirebaseConfig, Long> {

}
