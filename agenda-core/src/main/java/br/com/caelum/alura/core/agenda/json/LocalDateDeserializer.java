package br.com.caelum.alura.core.agenda.json;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
	
    private static final long serialVersionUID = 1L;
	private static final String DATE_FIELD = "data";

    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    protected LocalDateDeserializer(Class<LocalDate> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        TreeNode node = p.getCodec().readTree(p);
        return fmt.parseLocalDate(node.get(DATE_FIELD).toString().replaceAll("\"", ""));
    }
}