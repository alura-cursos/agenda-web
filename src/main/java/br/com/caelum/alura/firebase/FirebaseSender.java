package br.com.caelum.alura.firebase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.caelum.alura.dto.AlunoDTO;
import br.com.caelum.alura.dto.SyncDTO;
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

	public void envia(List<Dispositivo> dispositivos, AlunoDTO alunoDTO) throws IOException {
		Mensagem mensagem = new Mensagem();
		ArrayList<AlunoDTO> dtos = new ArrayList<AlunoDTO>(Arrays.asList(alunoDTO));
		mensagem.addData("syncDTO", new SyncDTO(dtos));
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		for (Dispositivo dispositivo : dispositivos) {
			mensagem.setTo(dispositivo.getToken());
			String json = mapper.writeValueAsString(mensagem);
			Request request = createRequestForPOST(json);
			Response response = client.newCall(request).execute();
			response.close();
		}
	}

}
