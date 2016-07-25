package br.com.caelum.alura.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class FirebaseSender {

	private static final String ERROR = "error";
	private static final String INVALID_REGISTRATION = "InvalidRegistration";
	private static final String NOT_REGISTERED = "NotRegistered";
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private Mensagem message;
	private OkHttpClient client = new OkHttpClient();
	private String URL = "https://fcm.googleapis.com/fcm/send";
	private ObjectMapper mapper = new ObjectMapper();
	
}
