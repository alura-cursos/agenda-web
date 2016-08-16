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
@RequestMapping("api/aluno")
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
	public @ResponseBody AlunoSync insere(@RequestBody Aluno aluno,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		boolean temAtualizacao = alunoService.temAtualizacao(datahora);
		String id = alunoService.salva(aluno);
		Aluno salvo = alunoService.busca(id);
		return sincronizaDataHora(temAtualizacao, salvo, datahora);
	}

	@RequestMapping(value = "{id}", method = GET, produces = JSON)
	public @ResponseBody AlunoSync busca(@PathVariable("id") String id) {
		return new AlunoSync(alunoService.busca(id));
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public @ResponseBody ResponseEntity<AlunoSync> remove(@PathVariable("id") String id,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		boolean temAtualizacao = alunoService.temAtualizacao(datahora);
		if (alunoService.existe(id)) {
			alunoService.remove(id);
			return new ResponseEntity<AlunoSync>(sincronizaDataHora(temAtualizacao, alunoService.busca(id), datahora),
					HttpStatus.OK);
		}
		return new ResponseEntity<AlunoSync>(sincronizaDataHora(temAtualizacao, new Aluno(), datahora),
				HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "{id}", method = PUT, consumes = JSON, produces = JSON)
	public @ResponseBody AlunoSync insereOuAltera(@PathVariable("id") String id, @RequestBody Aluno aluno,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		boolean temAtualizacao = alunoService.temAtualizacao(datahora);
		aluno.setId(id);
		String idSalvo = alunoService.salva(aluno);
		List<Aluno> alunos = new ArrayList<>(Arrays.asList(alunoService.busca(idSalvo)));
		return sincronizaDataHora(temAtualizacao, alunos, datahora);
	}

	@RequestMapping(value = "lista", method = PUT, consumes = JSON, produces = JSON)
	public @ResponseBody ResponseEntity<AlunoSync> insereOuAlteraLista(@RequestBody List<Aluno> alunos,
			@RequestHeader(value = "datahora", required = false) String datahora) {
		boolean temAtualizacao = alunoService.temAtualizacao(datahora);
		List<Aluno> alunosSalvos = new ArrayList<>();
		try {
			alunosSalvos = alunoService.salva(alunos);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<AlunoSync>(new AlunoSync(alunosSalvos, datahora), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AlunoSync>(sincronizaDataHora(temAtualizacao, alunosSalvos, datahora), HttpStatus.OK);
	}

	@RequestMapping(value = "diff", method = GET, produces = JSON)
	public @ResponseBody AlunoSync alteracoes(@RequestHeader("datahora") String datahora) {
		return alunoService.novosRegistro(LocalDateTime.parse(datahora));
	}

	private AlunoSync sincronizaDataHora(boolean temAtualizacao, List<Aluno> alunos, String datahora) {
		return temAtualizacao ? new AlunoSync(alunos, datahora) : new AlunoSync(alunos);
	}

	private AlunoSync sincronizaDataHora(boolean temAtualizacao, Aluno aluno, String datahora) {
		return temAtualizacao ? new AlunoSync(aluno, datahora) : new AlunoSync(aluno);
	}

}
