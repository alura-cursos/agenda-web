package br.com.caelum.alura.web.agenda.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.alura.core.agenda.model.Aluno;

@Controller
@RequestMapping("aluno")
public class AlunoController {

	@GetMapping("form")
	public ModelAndView form() {
		return new ModelAndView("aluno/cadastro", "aluno", new Aluno());
	}

	@PostMapping
	public String salvar(@ModelAttribute("aluno") Aluno aluno) {
		System.out.println(aluno.getNome());
		System.out.println(aluno.getEndereco());
		System.out.println(aluno.getTelefone());
		System.out.println(aluno.getSite());
		System.out.println(aluno.getNota());
		return "redirect:aluno";
	}

	@GetMapping
	public ModelAndView listar() {
		return new ModelAndView("aluno/lista", "alunos", new ArrayList<Aluno>());
	}

}
