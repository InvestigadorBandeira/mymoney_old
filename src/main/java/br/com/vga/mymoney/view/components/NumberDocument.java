package br.com.vga.mymoney.view.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class NumberDocument extends FixedLengthDocument {
    private static final long serialVersionUID = 1L;

    public NumberDocument(int maxlen) {
	super(maxlen);
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
	    throws BadLocationException {
	if (str == null)
	    return;

	try {
	    Integer.parseInt(str);
	} catch (Exception e) {
	    return;
	}

	super.insertString(offset, str, attr);
    }
}
