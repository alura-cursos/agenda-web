package br.com.caelum.alura.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UUIDUtils {

	public String geraUUIDAleatorio() {
		return UUID.randomUUID().toString();
	}

	public boolean ehValido(String uuid) {
		return uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
	}

}
