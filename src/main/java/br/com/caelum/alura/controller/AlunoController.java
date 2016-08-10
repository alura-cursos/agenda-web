package br.com.caelum.alura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.service.AlunoService;
import br.com.caelum.alura.utils.AlunoUtils;

@Controller
@RequestMapping("aluno")
public class AlunoController {

	private AlunoService alunoService;

	@Autowired
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@RequestMapping("form")
	public ModelAndView form(Aluno aluno) {
		return prepararAlunoPraForm(aluno);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView alterar(@PathVariable("id") String id) {
		if (alunoService.existe(id)) {
			Aluno aluno = alunoService.busca(id);
			return prepararAlunoPraForm(aluno);
		}
		return new ModelAndView("redirect:aluno");
	}

	private ModelAndView prepararAlunoPraForm(Aluno aluno) {
		ModelAndView mav = new ModelAndView("aluno/formulario");
		mav.addObject("aluno", aluno);
		mav.addObject("notas", AlunoUtils.POSSIVEIS_NOTAS);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("aluno") Aluno aluno, RedirectAttributes attributes) {
		alunoService.salvar(aluno);
		attributes.addFlashAttribute("info", "aluno salvo");
		return "redirect:aluno";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		return new ModelAndView("aluno/lista", "alunos", alunoService.getLista());
	}

}
