package br.com.caelum.alura.core.agenda.json;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;
	private static final String DATETIME_FIELD = "datetime";

	public LocalDateTimeSerializer() {
		this(LocalDateTime.class);
	}

	protected LocalDateTimeSerializer(Class<LocalDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy HH:MM:SS");
		gen.writeStringField(DATETIME_FIELD, dateTime.toString(fmt));
		gen.writeEndObject();
	}

}
