package br.com.caelum.alura.web.agenda.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.alura.core.agenda.model.Aluno;
import br.com.caelum.alura.core.agenda.util.AlunoUtils;
import br.com.caelum.alura.web.agenda.service.AlunoService;

@Controller
@RequestMapping("aluno")
public class AlunoController {

	private AlunoService alunoService;

	@Inject
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@RequestMapping("form")
	public ModelAndView form(Aluno aluno) {
		ModelAndView mav = new ModelAndView("aluno/cadastro");
		mav.addObject("aluno", new Aluno());
		mav.addObject("notas", AlunoUtils.POSSIVEIS_NOTAS);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("aluno") Aluno aluno) {
		alunoService.salvar(aluno);
		return "redirect:aluno";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		return new ModelAndView("aluno/lista", "alunos", alunoService.getLista());
	}

}
