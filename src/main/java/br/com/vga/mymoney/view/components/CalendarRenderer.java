package br.com.vga.mymoney.view.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.table.DefaultTableCellRenderer;

public class CalendarRenderer extends DefaultTableCellRenderer {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public CalendarRenderer() {
	super();
	setHorizontalAlignment(CENTER);
    }

    @Override
    protected void setValue(Object value) {
	if (value instanceof Calendar) {
	    setText(value == null ? "" : sdf.format(((Calendar) value)
		    .getTime()));
	}

    }

}
