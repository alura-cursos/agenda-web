package br.com.caelum.alura.firebase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirebaseConfig {

	@Id
	private Long id;
	private String URL;
	private String apikey;

	public FirebaseConfig() {
	}

	public FirebaseConfig(String URL, String apikey) {
		this.URL = URL;
		this.apikey = apikey;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getURL() {
		return URL;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
