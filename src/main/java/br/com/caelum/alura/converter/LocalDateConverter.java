package br.com.caelum.alura.converter;

import java.sql.Date;
import java.util.TimeZone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	public Date convertToDatabaseColumn(LocalDate attribute) {
		return attribute == null ? null : new Date(attribute.toDate().getTime());
	}

	public LocalDate convertToEntityAttribute(Date dbData) {
		return dbData == null ? null : new LocalDate(dbData.getTime(), DateTimeZone.forTimeZone(TimeZone.getDefault()));
	}

}
