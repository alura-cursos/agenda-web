package br.com.caelum.alura.converter;

import java.sql.Timestamp;
import java.util.TimeZone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		return (localDateTime == null ? null : new Timestamp(localDateTime.toDateTime().getMillis()));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
		return (sqlTimestamp == null ? null
				: new LocalDateTime(sqlTimestamp.getTime(), DateTimeZone.forTimeZone(TimeZone.getDefault())));
	}
}