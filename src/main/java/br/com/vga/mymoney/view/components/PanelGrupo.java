package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelGrupo extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;

    private final Grupo grupo;
    private JTextField txtNome;
    private JButton btnEditar;
    private JButton btnExcluir;

    public PanelGrupo(Grupo grupo) {
	super();
	this.grupo = grupo;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "NOME" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 295 };

	return largura;
    }

    private void initComponents() {
	setBounds(227, 150, 375, 25);
	setLayout(null);

	txtNome = new JTextField(" " + grupo.getNome());
	txtNome.setBackground(new Color(204, 255, 204));
	txtNome.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtNome.setBounds(5, 0, 295, 25);
	txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtNome.setFocusable(false);
	add(txtNome);
	txtNome.setColumns(10);

	btnEditar = new JButton();
	btnEditar.setToolTipText("Editar Grupo.");
	btnEditar.setIcon(new ImageIcon(PanelGrupo.class
		.getResource("/br/com/vga/mymoney/images/edit_16x16.png")));
	btnEditar.setBounds(305, 0, 30, 25);
	add(btnEditar);

	btnExcluir = new JButton();
	btnExcluir.setToolTipText("Excluir Grupo.");
	btnExcluir.setIcon(new ImageIcon(PanelGrupo.class
		.getResource("/br/com/vga/mymoney/images/delete_16x16.png")));
	btnExcluir.setBounds(340, 0, 30, 25);
	add(btnExcluir);
    }
}
