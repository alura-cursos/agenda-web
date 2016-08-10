package br.com.caelum.alura.rest.controller;

import static br.com.caelum.alura.utils.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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

	@RequestMapping(method = POST, consumes = JSON, produces = JSON)
	public @ResponseBody AlunoSync insereNovo(@RequestBody Aluno aluno,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		String id = alunoService.salvar(aluno);
		return verificaAtualizacao(alunoService.busca(id), datahora);
	}

	@RequestMapping(value = "{id}", method = GET, produces = JSON)
	public @ResponseBody AlunoSync busca(@PathVariable("id") String id) {
		return new AlunoSync(alunoService.busca(id));
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public @ResponseBody ResponseEntity<AlunoSync> deleta(@PathVariable("id") String id,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		if (alunoService.existe(id)) {
			alunoService.deletar(id);
			return new ResponseEntity<AlunoSync>(verificaAtualizacao(alunoService.busca(id), datahora), HttpStatus.OK);
		}
		return new ResponseEntity<AlunoSync>(verificaAtualizacao(new ArrayList<>(), datahora), HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "{id}", method = PUT, consumes = JSON, produces = JSON)
	public @ResponseBody AlunoSync insereOuAltera(@PathVariable("id") String id, @RequestBody Aluno aluno,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		aluno.setId(id);
		String idSalvo = alunoService.salvar(aluno);
		List<Aluno> alunos = new ArrayList<>(Arrays.asList(alunoService.busca(idSalvo)));
		return verificaAtualizacao(alunos, datahora);
	}

	@RequestMapping(value = "lista", method = PUT, consumes = JSON, produces = JSON)
	public @ResponseBody AlunoSync insereOuAlteraLista(@RequestBody List<Aluno> alunos,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		List<Aluno> alunosSalvos = alunoService.salvar(alunos);
		return verificaAtualizacao(alunosSalvos, datahora);
	}

	@RequestMapping(value = "diff", method = GET, produces = JSON)
	public @ResponseBody AlunoSync alteracoes(@RequestHeader("datahora") String datahora) {
		return alunoService.novosRegistro(LocalDateTime.parse(datahora));
	}

	private AlunoSync verificaAtualizacao(Aluno aluno, String datahora) {
		if (datahora != null && alunoService.temAtualizacao(datahora)) {
			return new AlunoSync(aluno, datahora);
		} else {
			return new AlunoSync(aluno);
		}
	}

	private AlunoSync verificaAtualizacao(List<Aluno> alunos, String datahora) {
		if (datahora != null && alunoService.temAtualizacao(datahora)) {
			return new AlunoSync(alunos, datahora);
		} else {
			return new AlunoSync(alunos);
		}
	}

}
