package br.com.caelum.alura.firebase;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.caelum.alura.model.Aluno;
import br.com.caelum.alura.model.Dispositivo;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirebaseSender {

	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private OkHttpClient client = new OkHttpClient();
	private String URL;
	private String API_KEY;

	public FirebaseSender() throws IOException {
		FirebaseConfig firebaseConfig = FirebaseConfig.getInstance();
		URL = firebaseConfig.getURL();
		API_KEY = firebaseConfig.getApikey();
	}

	private Request createRequestForPOST(String json) {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(URL).addHeader("Authorization", "key=" + API_KEY).post(body)
				.build();
		return request;
	}

	public void enviarAluno(List<Dispositivo> dispositivos, Aluno aluno) throws IOException {
		Mensagem mensagem = new Mensagem();
		mensagem.addData("aluno", aluno);
		ObjectMapper objectMapper = new ObjectMapper();
		for (Dispositivo dispositivo : dispositivos) {
			mensagem.setTo(dispositivo.getToken());
			String json = objectMapper.writeValueAsString(mensagem);
			Request request = createRequestForPOST(json);
			Response response = client.newCall(request).execute();
			response.close();
		}
	}

}
