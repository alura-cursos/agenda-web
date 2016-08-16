package br.com.caelum.alura.firebase;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirebaseClient {

	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	protected static final String ERROR = "error";
	protected static final String INVALID_REGISTRATION = "InvalidRegistration";
	protected static final String NOT_REGISTERED = "NotRegistered";
	protected OkHttpClient client = new OkHttpClient();
	protected ObjectMapper mapper = new ObjectMapper();
	private String URL;
	private String API_KEY;

	public FirebaseClient() throws IOException {
		FirebaseConfig firebaseConfig = FirebaseConfig.getInstance();
		URL = firebaseConfig.getURL();
		API_KEY = firebaseConfig.getApikey();
	}

	protected Request criaRequisicaoParaPost(String json) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(URL).addHeader("Authorization", "key=" + API_KEY).post(body)
				.build();
		return request;
	}

	public boolean validaAPIKey() throws IOException {
		Request request = criaRequisicaoParaPost(jsonTeste());
		Response response = client.newCall(request).execute();
		boolean sucesso = response.isSuccessful();
		response.close();
		return sucesso;
	}

	private String jsonTeste() throws JsonProcessingException {
		Mensagem mensagem = new Mensagem();
		mensagem.setTo("teste");
		String jsonTeste = mapper.writeValueAsString(mensagem);
		return jsonTeste;
	}
}
