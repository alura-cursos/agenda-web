package br.com.caelum.alura.firebase;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mensagem {

	private Map<String, Object> data = new HashMap<>();
	private String to;

	public Map<String, Object> getData() {
		return Collections.unmodifiableMap(data);
	}

	public void addData(String key, Object value) {
		data.put(key, value);
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
