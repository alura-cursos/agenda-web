package br.com.caelum.alura.rest.controller;

import static br.com.caelum.alura.utils.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.alura.dto.AlunoSync;
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
	public @ResponseBody AlunoSync lista() {
		return alunoService.getSyncLista();
	}

	@RequestMapping(value = "{id}", method = GET, produces = JSON)
	public @ResponseBody AlunoSync busca(@PathVariable("id") Long id) {
		return new AlunoSync(alunoService.getAluno(id));
	}

	@RequestMapping(method = POST, consumes = JSON, produces = JSON)
	public @ResponseBody AlunoSync insere(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		return new AlunoSync(alunoService.getUltimo());
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public @ResponseBody ResponseEntity<AlunoSync> deleta(@PathVariable("id") Long id) {
		if (alunoService.existe(id)) {
			alunoService.deletar(id);
			return new ResponseEntity<AlunoSync>(new AlunoSync(alunoService.getAluno(id)), HttpStatus.OK);
		}
		return new ResponseEntity<AlunoSync>(new AlunoSync(new ArrayList<>()), HttpStatus.FORBIDDEN);
	}

	@RequestMapping(method = PATCH, consumes = JSON, produces = JSON)
	public @ResponseBody AlunoSync altera(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		List<Aluno> alunos = new ArrayList<>(Arrays.asList(alunoService.getAluno(aluno.getId())));
		return new AlunoSync(alunos);
	}

	@RequestMapping(value = "diff", method = GET, produces = JSON)
	public @ResponseBody AlunoSync alteracoes(@RequestHeader("datahora") String datahora) {
		return alunoService.novosRegistro(LocalDateTime.parse(datahora));
	}

}
