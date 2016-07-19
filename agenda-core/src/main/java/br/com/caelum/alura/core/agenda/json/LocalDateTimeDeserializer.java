package br.com.caelum.alura.core.agenda.json;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;
	private static final String DATETIME_FIELD = "datetime";

	public LocalDateTimeDeserializer() {
		this(LocalDateTime.class);
	}

	protected LocalDateTimeDeserializer(Class<LocalDateTime> t) {
		super(t);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:MM:SS");
		TreeNode node = jsonParser.getCodec().readTree(jsonParser);
		return fmt.parseLocalDateTime(node.get(DATETIME_FIELD).toString().replaceAll("\"", ""));
	}

}
