package br.com.caelum.alura.rest.controller;

import static br.com.caelum.alura.utils.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
	public @ResponseBody AlunoSync alunos() {
		return alunoService.getSyncLista();
	}

	@RequestMapping(value = "{id}", method = GET, produces = JSON)
	public @ResponseBody Aluno aluno(@PathVariable("id") Long id) {
		return alunoService.getAluno(id);
	}

	@RequestMapping(method = POST, consumes = JSON, produces = JSON)
	public @ResponseBody Aluno inserir(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		return alunoService.getUltimo();
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
		if (alunoService.existe(id)) {
			alunoService.deletar(id);
			return new ResponseEntity<String>("aluno deletado", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("aluno inexistente", HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = PATCH, consumes = JSON, produces = JSON)
	public @ResponseBody Aluno alterar(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		return alunoService.getAluno(aluno.getId());
	}

	@RequestMapping(value = "diff", method = GET, produces = JSON)
	public @ResponseBody AlunoSync alteracoesAlunos(@RequestHeader("datahora") String datahora) {
		return alunoService.novosRegistro(LocalDateTime.parse(datahora));
	}

}
