package br.com.caelum.alura.controller;

import static br.com.caelum.alura.utils.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.service.AlunoService;

@RestController
@RequestMapping("v1/aluno")
public class AlunoRestController {

	private AlunoService alunoService;

	@Autowired
	public AlunoRestController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@RequestMapping(method = GET)
	public @ResponseBody List<Aluno> alunos() {
		return alunoService.getLista();
	}

	@RequestMapping(method = POST, consumes = JSON)
	public ResponseEntity<String> inserir(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		return new ResponseEntity<String>("aluno cadastrado", HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = DELETE, consumes = JSON)
	public ResponseEntity<String> inserir(@PathVariable("id") Long id) {
		if (alunoService.existe(id)) {
			alunoService.deletar(id);
			return new ResponseEntity<String>("aluno deletado", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("aluno inexistente", HttpStatus.FORBIDDEN);
		}
	}

}
