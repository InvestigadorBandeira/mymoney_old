package br.com.vga.mymoney.view.components;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.table.DefaultTableCellRenderer;

public class DecimalRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    DecimalFormat df = new DecimalFormat("#,##0.00;-#,##0.00");

    public DecimalRenderer() {
	super();
	setHorizontalAlignment(RIGHT);
    }

    @Override
    protected void setValue(Object value) {
	if (value instanceof BigDecimal)
	    setText(value == null ? "0,00" : df.format((value)));
    }
}
