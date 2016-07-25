package br.com.caelum.alura.firebase;

import java.util.Collections;
import java.util.Map;

public class Mensagem {

	private Notification notification;
	private Map<String, Object> data;

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Map<String, Object> getData() {
		return Collections.unmodifiableMap(data);
	}

	public void addData(String key, Object value){
		data.put(key, value);
	}

}
