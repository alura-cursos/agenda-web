package br.com.caelum.alura.firebase;

public class FirebaseConfig {

	private String URL;
	private String apikey;

	public FirebaseConfig() {
	}

	public FirebaseConfig(String uRL, String apikey) {
		URL = uRL;
		this.apikey = apikey;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

}
