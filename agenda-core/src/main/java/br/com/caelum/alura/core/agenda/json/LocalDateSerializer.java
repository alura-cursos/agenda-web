package br.com.caelum.alura.core.agenda.json;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1L;
	private static final String DATE_FIELD = "data";

	public LocalDateSerializer() {
		this(LocalDate.class);
	}

	protected LocalDateSerializer(Class<LocalDate> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
		gen.writeStringField(DATE_FIELD, value.toString(fmt));
		gen.writeEndObject();
	}
}