package br.com.caelum.alura.controller;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {

	@RequestMapping("/")
	public String index() throws IOException {

		Resource resource = new ClassPathResource("/firebase.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		props.setProperty("firebase.apikey", "teste");
		System.out.println(props.containsKey("firebase.apikey"));
		System.out.println("value " + props.get("firebase.apikey"));
		return "index";
	}
}
