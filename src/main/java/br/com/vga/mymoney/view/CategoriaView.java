package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.CategoriaController;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.PanelCategoria;
import br.com.vga.mymoney.view.tables.PanelHearder;

public class CategoriaView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel pnCadastro;
    private JLabel lblNome;
    private JTextField txtNome;
    private JPanel pnListagem;
    private JLabel lblListagem;
    private JScrollPane scrollCategorias;

    private Mensagem mensagem;

    private final CategoriaController controller;
    private JButton btnSair;
    private JButton btnSalvarCategoria;
    private JPanel pnCategorias;

    public CategoriaView(CategoriaController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Cadastro de Categorias", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(30, 20, 575, 489);
	setLayout(null);

	pnCadastro = new JPanel();
	pnCadastro.setBackground(new Color(250, 250, 210));
	pnCadastro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnCadastro.setBounds(10, 25, 554, 70);
	add(pnCadastro);
	pnCadastro.setLayout(null);

	lblNome = new JLabel("Nome");
	lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNome.setBounds(10, 11, 90, 25);
	pnCadastro.add(lblNome);

	txtNome = new JTextField();
	txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtNome.setColumns(10);
	txtNome.setBounds(110, 11, 350, 25);
	pnCadastro.add(txtNome);

	btnSalvarCategoria = new JButton("Salvar Categoria");
	btnSalvarCategoria.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnSalvarCategoriaActionPerformed(e);
	    }
	});
	btnSalvarCategoria.setBounds(10, 106, 150, 25);
	add(btnSalvarCategoria);

	pnListagem = new JPanel();
	pnListagem.setLayout(null);
	pnListagem.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnListagem.setBackground(new Color(250, 250, 210));
	pnListagem.setBounds(10, 142, 554, 297);
	add(pnListagem);

	lblListagem = new JLabel("Listagem de Categorias Cadastradas");
	lblListagem.setHorizontalAlignment(SwingConstants.CENTER);
	lblListagem.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblListagem.setBounds(10, 11, 534, 20);
	pnListagem.add(lblListagem);

	scrollCategorias = new JScrollPane();
	scrollCategorias.setBounds(10, 42, 534, 244);
	pnListagem.add(scrollCategorias);

	pnCategorias = new JPanel();
	scrollCategorias.setViewportView(pnCategorias);

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(464, 450, 100, 25);
	add(btnSair);

	//
	mensagem = new Mensagem(this, "Cadastro de Categorias");
    }

    public void montaListagemCategorias(List<Categoria> categorias) {
	if (categorias == null || categorias.isEmpty()) {
	    pnCategorias.removeAll();
	    pnCategorias.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelCategoria> panelCategorias = new ArrayList<>();

	for (Categoria categoria : categorias)
	    panelCategorias.add(new PanelCategoria(categoria, controller));

	for (int i = 0; i < panelCategorias.size(); i++)
	    layout.append("[25px]");

	pnCategorias.removeAll();

	// Define layout
	pnCategorias.setLayout(new MigLayout("", "[375px]", layout.toString()));

	pnCategorias.add(new PanelHearder(panelCategorias.get(0)),
		"cell 0 0,grow");

	for (int i = 0; i < panelCategorias.size(); i++)
	    pnCategorias.add(panelCategorias.get(i), "cell 0 " + (i + 1)
		    + ",grow");

	pnCategorias.updateUI();
    }

    public void atualizaCampos() {
	txtNome.setText("");
	txtNome.requestFocus();
    }

    protected void btnSalvarCategoriaActionPerformed(ActionEvent e) {
	String nome = txtNome.getText().trim();

	if (nome.isEmpty()) {
	    mensagem.aviso("Nome é obrigatório.");
	    txtNome.requestFocus();
	    return;
	}

	Categoria categoria = new Categoria();
	categoria.setNome(nome);
	controller.salvar(categoria);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
