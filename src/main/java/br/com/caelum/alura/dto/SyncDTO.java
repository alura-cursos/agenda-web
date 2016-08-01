package br.com.caelum.alura.dto;

import java.util.List;

import org.joda.time.LocalDateTime;

public class SyncDTO {

	private final List<AlunoDTO> dto;
	private final String datahora;

	public SyncDTO(List<AlunoDTO> dto) {
		this.dto = dto;
		this.datahora = LocalDateTime.now().toString();
	}

	public List<AlunoDTO> getDto() {
		return dto;
	}

	public String getDatahora() {
		return datahora;
	}

}
