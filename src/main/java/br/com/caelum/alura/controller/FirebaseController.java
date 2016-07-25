package br.com.caelum.alura.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.alura.firebase.FirebaseConfig;

@Controller
@RequestMapping("firebase")
public class FirebaseController {
	
	@RequestMapping(method = GET)
	public String index(){
		return "firebase/index";
	}

	@RequestMapping(value = "config", method = GET)
	public String firebaseConfig() {
		return "firebase/config";
	}

	@RequestMapping(value = "config", method = POST)
	public String salvarFirebaseConfig(FirebaseConfig firebaseConfig, RedirectAttributes attributes) {
		attributes.addAttribute("sucesso", "Firebase configurado com sucesso");
		return "redirect:/firebase";
	}
}
