package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.CrudController;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelSubCategoria extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;

    private final SubCategoria subCategoria;
    private JTextField txtNome;
    private JButton btnEditar;
    private JButton btnExcluir;

    private CrudController<SubCategoria> controller;
    private JTextField txtCategoria;

    public PanelSubCategoria(SubCategoria subCategoria,
	    CrudController<SubCategoria> controller) {
	super();
	this.subCategoria = subCategoria;
	this.controller = controller;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "NOME", "CATEGORIA" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 200, 200 };

	return largura;
    }

    private void initComponents() {
	setBounds(227, 150, 485, 25);
	setLayout(null);

	// tipos padrões
	Color verde = new Color(204, 255, 204);
	Font fonte = new Font("Tahoma", Font.BOLD, 12);
	TitledBorder borda = new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null);
	//

	txtNome = new JTextField(" " + subCategoria.getNome());
	txtNome.setBackground(verde);
	txtNome.setBorder(borda);
	txtNome.setBounds(5, 0, 200, 25);
	txtNome.setFont(fonte);
	txtNome.setFocusable(false);
	txtNome.setColumns(10);
	add(txtNome);

	txtCategoria = new JTextField(" " + subCategoria.getCategoria());
	txtCategoria.setFont(fonte);
	txtCategoria.setFocusable(false);
	txtCategoria.setColumns(10);
	txtCategoria.setBorder(borda);
	txtCategoria.setBackground(verde);
	txtCategoria.setBounds(210, 0, 200, 25);
	add(txtCategoria);

	btnEditar = new JButton();
	btnEditar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnEditarActionPerformed(e);
	    }
	});
	btnEditar.setToolTipText("Editar SubCategoria.");
	btnEditar.setIcon(new ImageIcon(PanelSubCategoria.class
		.getResource("/br/com/vga/mymoney/images/edit_16x16.png")));
	btnEditar.setBounds(415, 0, 30, 25);
	add(btnEditar);

	btnExcluir = new JButton();
	btnExcluir.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setToolTipText("Excluir SubCategoria.");
	btnExcluir.setIcon(new ImageIcon(PanelSubCategoria.class
		.getResource("/br/com/vga/mymoney/images/delete_16x16.png")));
	btnExcluir.setBounds(450, 0, 30, 25);
	add(btnExcluir);
    }

    protected void btnEditarActionPerformed(ActionEvent e) {
	controller.alterar(subCategoria);
    }

    protected void btnExcluirActionPerformed(ActionEvent e) {
	controller.excluir(subCategoria);
    }
}
