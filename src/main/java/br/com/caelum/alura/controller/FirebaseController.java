package br.com.caelum.alura.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.alura.firebase.FirebaseConfig;

@Controller
@RequestMapping("firebase")
public class FirebaseController {

	@RequestMapping(method = GET)
	public ModelAndView firebaseConfig() throws IOException {
		return new ModelAndView("firebase/configuracao", "firebaseConfig", FirebaseConfig.getInstance());
	}

	@RequestMapping(method = POST)
	public String salvarFirebaseConfig(@ModelAttribute("firebaseConfig") FirebaseConfig firebaseConfig,
			RedirectAttributes attributes) throws IOException {
		firebaseConfig.salvarProperties();
		attributes.addFlashAttribute("info", "Firebase configurado");
		return "redirect:/firebase";
	}

}
