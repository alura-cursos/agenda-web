package br.com.caelum.alura.rest.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.alura.model.Dispositivo;
import br.com.caelum.alura.service.DispositivoService;

@Controller
@RequestMapping("api/firebase")
public class FirebaseRestController {

	private DispositivoService dispositivoService;

	@Autowired
	public FirebaseRestController(DispositivoService dispositivoService) {
		this.dispositivoService = dispositivoService;
	}

	@RequestMapping(value = "dispositivo", method = POST)
	public ResponseEntity<String> cadastraDispositivo(@RequestHeader("token") String token) {
		dispositivoService.salva(new Dispositivo(token));
		return new ResponseEntity<String>("dispositivo cadastrado", HttpStatus.OK);
	}
}
