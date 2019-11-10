package br.com.sulamerica.desafio_sas.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DateToStringConverter extends StdConverter<Date, String>{

	@Override
	public String convert(Date value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(value);
	}
}