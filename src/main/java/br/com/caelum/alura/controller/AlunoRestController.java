package br.com.caelum.alura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.service.AlunoService;
import static br.com.caelum.alura.utils.HTTPValues.JSON;

@RestController
@RequestMapping("v1/aluno")
public class AlunoRestController {

	private AlunoService alunoService;

	@Autowired
	public AlunoRestController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Aluno> alunos() {
		return alunoService.getLista();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = JSON)
	public ResponseEntity<String> inserir(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		return new ResponseEntity<>("aluno cadastrado", HttpStatus.OK);
	}

}
