package br.com.vga.mymoney.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Formatador {

    public static String dataTexto(Calendar data) {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	return data != null ? sdf.format(data.getTime()) : "";
    }

}
