package br.com.casaDoCodigo.livroOO.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class ConversoraData {

	public static Date converterData(String data) {
		
		Calendar dataAniversario = GregorianCalendar.getInstance();
		dataAniversario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data.split("/")[0]));
		dataAniversario.set(Calendar.MONTH, Integer.parseInt(data.split("/")[1]));
		dataAniversario.set(Calendar.YEAR, Integer.parseInt(data.split("/")[2]));
		dataAniversario.set(Calendar.HOUR, 0);
		dataAniversario.set(Calendar.MINUTE, 0);
		dataAniversario.set(Calendar.SECOND, 0);
		dataAniversario.set(Calendar.MILLISECOND, 0);	
		
		return dataAniversario.getTime();
	}
}
