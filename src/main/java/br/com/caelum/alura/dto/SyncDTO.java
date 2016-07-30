package br.com.caelum.alura.dto;

import java.util.List;

import org.joda.time.LocalDateTime;

public class SyncDTO {

	private List<AlunoDTO> dto;
	private String datahora;

	public SyncDTO() {
	}

	public SyncDTO(List<AlunoDTO> dto) {
		this.dto = dto;
		this.datahora = LocalDateTime.now().toString();
	}

	public List<AlunoDTO> getDto() {
		return dto;
	}

	public void setDto(List<AlunoDTO> dto) {
		this.dto = dto;
	}

	public String getDatahora() {
		return datahora;
	}

	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}

}
