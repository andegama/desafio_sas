package br.com.sulamerica.desafio_sas.converter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StringToDateConverter extends StdDeserializer<Date>{

	public StringToDateConverter() {
		this(null);
	}

	protected StringToDateConverter(Class<?> clazz) {
		super(clazz);
	}

	private static final long serialVersionUID = 9061522422176874684L;

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		String date = p.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
