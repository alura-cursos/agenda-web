package br.com.caelum.alura.firebase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class FirebaseConfig {

	private String URL;
	private String apikey;

	public FirebaseConfig() {
	}

	public FirebaseConfig(String URL, String apikey) {
		this.URL = URL;
		this.apikey = apikey;
	}

	public String getURL() throws IOException {
		Resource resource = new ClassPathResource("/firebase.properties");
		Properties prop = PropertiesLoaderUtils.loadProperties(resource);
		return (String) prop.getProperty("firebase.url");
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getApikey() throws IOException {
		Resource resource = new ClassPathResource("/firebase.properties");
		Properties prop = PropertiesLoaderUtils.loadProperties(resource);
		return (String) prop.getProperty("firebase.apikey");
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public static FirebaseConfig getInstance() {
		FirebaseConfig firebaseConfig = new FirebaseConfig();
		try {
			Resource resource = new ClassPathResource("/firebase.properties");
			Properties prop = PropertiesLoaderUtils.loadProperties(resource);
			firebaseConfig.setApikey(prop.getProperty("firebase.apikey"));
			firebaseConfig.setURL(prop.getProperty("firebase.url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return firebaseConfig;
	}

	public void salvarProperties() throws IOException {
		Resource resource = new ClassPathResource("/firebase.properties");
		Properties prop = PropertiesLoaderUtils.loadProperties(resource);
		prop.put("firebase.apikey", apikey);
		prop.put("firebase.url", URL);
		prop.store(new FileOutputStream(resource.getFile()), "");
	}

}
