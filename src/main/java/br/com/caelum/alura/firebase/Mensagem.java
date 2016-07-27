package br.com.caelum.alura.firebase;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mensagem {

	private Notification notification;
	private Map<String, Object> data = new HashMap<>();
	private String to;

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

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
