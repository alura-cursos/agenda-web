package br.com.caelum.alura.firebase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.caelum.alura.dto.AlunoSync;
import br.com.caelum.alura.model.Dispositivo;
import br.com.caelum.alura.service.DispositivoService;
import okhttp3.Request;
import okhttp3.Response;

public class FirebaseSender extends FirebaseClient {

	private DispositivoService dispositivoService;
	private final static Logger LOGGER = Logger.getLogger(FirebaseSender.class);

	public FirebaseSender(DispositivoService dispositivoService) throws IOException {
		super();
		this.dispositivoService = dispositivoService;
	}

	public void envia(List<Dispositivo> dispositivos, AlunoSync alunoSync) throws IOException {
		List<Dispositivo> invalidos = new ArrayList<>();
		Mensagem mensagem = new Mensagem();
		mensagem.put("alunoSync", alunoSync);
		mapper.setSerializationInclusion(Include.NON_NULL);
		for (Dispositivo dispositivo : dispositivos) {
			mensagem.setTo(dispositivo.getToken());
			String json = mapper.writeValueAsString(mensagem);
			Request request = criaRequisicaoParaPost(json);
			Response response = client.newCall(request).execute();
			if (ehInvalido(response))
				invalidos.add(dispositivo);
			else
				LOGGER.info("resposta do firebase " + response.body().string());
			response.close();
		}

		dispositivoService.deleta(invalidos);
	}

	@SuppressWarnings("unchecked")
	private boolean ehInvalido(Response response) throws IOException {
		if (response.code() != 401) {
			String resposta = response.body().string();
			List<LinkedHashMap<?, ?>> lista = (List<LinkedHashMap<?, ?>>) mapper.readValue(resposta, Map.class)
					.get("results");
			Map<?, ?> results = lista.get(0);
			if (results.containsKey(ERROR)) {
				String erro = (String) results.get(ERROR);
				if (erro.equals(NOT_REGISTERED) || erro.equals(INVALID_REGISTRATION)) {
					return true;
				}
			}
		}
		return false;
	}

}
