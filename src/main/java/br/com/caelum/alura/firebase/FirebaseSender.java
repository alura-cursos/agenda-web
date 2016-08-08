package br.com.caelum.alura.firebase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.caelum.alura.dto.AlunoSync;
import br.com.caelum.alura.model.Dispositivo;
import br.com.caelum.alura.service.DispositivoService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirebaseSender {

	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final String ERROR = "error";
	private static final String INVALID_REGISTRATION = "InvalidRegistration";
	private static final String NOT_REGISTERED = "NotRegistered";
	private OkHttpClient client = new OkHttpClient();
	private String URL;
	private String API_KEY;
	private ObjectMapper mapper = new ObjectMapper();
	private DispositivoService dispositivoService;

	public FirebaseSender(DispositivoService dispositivoService) throws IOException {
		this.dispositivoService = dispositivoService;
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

	public void envia(List<Dispositivo> dispositivos, AlunoSync alunoSync) throws IOException {
		List<Dispositivo> invalidos = new ArrayList<>();
		Mensagem mensagem = new Mensagem();
		mensagem.addData("alunoSync", alunoSync);

		mapper.setSerializationInclusion(Include.NON_NULL);
		for (Dispositivo dispositivo : dispositivos) {
			mensagem.setTo(dispositivo.getToken());
			String json = mapper.writeValueAsString(mensagem);
			Request request = createRequestForPOST(json);
			Response response = client.newCall(request).execute();
			String resposta = response.body().string();
			System.out.println(resposta);
			response.close();
			verificaErros(resposta, invalidos, dispositivo);
		}
		dispositivoService.deleta(invalidos);
	}

	@SuppressWarnings("unchecked")
	private void verificaErros(String resposta, List<Dispositivo> invalidos, Dispositivo dispositivo)
			throws IOException {
		List<LinkedHashMap<?, ?>> lista = (List<LinkedHashMap<?, ?>>) mapper.readValue(resposta, Map.class)
				.get("results");
		LinkedHashMap<?, ?> results = lista.get(0);
		if (results.containsKey(ERROR)) {
			String erro = (String) results.get(ERROR);
			if (erro.equals(NOT_REGISTERED) || erro.equals(INVALID_REGISTRATION)) {
				invalidos.add(dispositivo);
			}
		}
	}

}
