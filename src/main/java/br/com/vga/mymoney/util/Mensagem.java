package br.com.vga.mymoney.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Mensagem {

    private Component component;
    private String titulo;

    public Mensagem(Component component, String titulo) {
	this.component = component;
	this.titulo = titulo;
    }

    public void info(String mensagem) {
	JOptionPane.showMessageDialog(component, mensagem, titulo,
		JOptionPane.INFORMATION_MESSAGE);
    }

    public void erro(String mensagem) {
	JOptionPane.showMessageDialog(component, mensagem, titulo,
		JOptionPane.ERROR_MESSAGE);
    }

    public void pergunta(String mensagem) {
	JOptionPane.showMessageDialog(component, mensagem, titulo,
		JOptionPane.QUESTION_MESSAGE);
    }

    public void aviso(String mensagem) {
	JOptionPane.showMessageDialog(component, mensagem, titulo,
		JOptionPane.WARNING_MESSAGE);
    }

}
