package br.com.caelum.alura.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.alura.firebase.FirebaseConfig;
import br.com.caelum.alura.service.FirebaseService;

@Controller
@RequestMapping("firebase")
public class FirebaseController {

	private FirebaseService firebaseService;

	@Autowired
	public FirebaseController(FirebaseService firebaseService) {
		this.firebaseService = firebaseService;
	}

	@RequestMapping(method = GET)
	public ModelAndView config() throws IOException {
		return new ModelAndView("firebase/configuracao", "config", FirebaseConfig.getInstance());
	}

	@RequestMapping(method = POST)
	public String config(@ModelAttribute("config") FirebaseConfig config, RedirectAttributes attributes)
			throws IOException {
		config.salvaProperties();
		if (firebaseService.tokenValido()) {
			attributes.addFlashAttribute("sucesso", "Firebase configurado");
		} else {
			attributes.addFlashAttribute("falha", "Firebase não configurado");
		}
		return "redirect:/firebase";
	}

}
