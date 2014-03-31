package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.SubCategoriaController;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.PanelSubCategoria;
import br.com.vga.mymoney.view.tables.PanelHearder;

public class SubCategoriaView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel pnCadastro;
    private JLabel lblNome;
    private JTextField txtNome;
    private JPanel pnListagem;
    private JLabel lblListagem;
    private JScrollPane scrollSubCategorias;
    private JButton btnSair;
    private JButton btnSalvarSubCategoria;
    private JPanel pnSubCategorias;
    private JLabel lblCategoria;
    private JComboBox<Categoria> cbCategoria;

    private Mensagem mensagem;
    private final SubCategoriaController controller;

    public SubCategoriaView(SubCategoriaController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Cadastro de SubCategorias", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(30, 20, 575, 489);
	setLayout(null);

	pnCadastro = new JPanel();
	pnCadastro.setBackground(new Color(250, 250, 210));
	pnCadastro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnCadastro.setBounds(10, 25, 554, 90);
	add(pnCadastro);
	pnCadastro.setLayout(null);

	lblNome = new JLabel("Nome");
	lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNome.setBounds(10, 11, 130, 25);
	pnCadastro.add(lblNome);

	txtNome = new JTextField();
	txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtNome.setColumns(10);
	txtNome.setBounds(150, 11, 320, 25);
	pnCadastro.add(txtNome);

	lblCategoria = new JLabel("Categoria");
	lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblCategoria.setBounds(10, 47, 130, 25);
	pnCadastro.add(lblCategoria);

	cbCategoria = new JComboBox<Categoria>();
	cbCategoria.setBounds(150, 48, 270, 25);
	pnCadastro.add(cbCategoria);

	btnSalvarSubCategoria = new JButton("Salvar SubCategoria");
	btnSalvarSubCategoria.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnSalvarSubCategoriaActionPerformed(e);
	    }
	});
	btnSalvarSubCategoria.setBounds(10, 126, 150, 25);
	add(btnSalvarSubCategoria);

	pnListagem = new JPanel();
	pnListagem.setLayout(null);
	pnListagem.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnListagem.setBackground(new Color(250, 250, 210));
	pnListagem.setBounds(10, 162, 554, 277);
	add(pnListagem);

	lblListagem = new JLabel("Listagem de SubCategorias Cadastradas");
	lblListagem.setHorizontalAlignment(SwingConstants.CENTER);
	lblListagem.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblListagem.setBounds(10, 11, 534, 20);
	pnListagem.add(lblListagem);

	scrollSubCategorias = new JScrollPane();
	scrollSubCategorias.setBounds(10, 42, 534, 224);
	pnListagem.add(scrollSubCategorias);

	pnSubCategorias = new JPanel();
	scrollSubCategorias.setViewportView(pnSubCategorias);

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
	mensagem = new Mensagem(this, "Cadastro de SubCategorias");
    }

    public void montaComboCategoria(List<Categoria> categorias) {
	for (Categoria categoria : categorias)
	    cbCategoria.addItem(categoria);
    }

    public void montaListagemSubCategorias(List<SubCategoria> subCategorias) {
	if (subCategorias == null || subCategorias.isEmpty()) {
	    pnSubCategorias.removeAll();
	    pnSubCategorias.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelSubCategoria> panelSubCategorias = new ArrayList<>();

	for (SubCategoria subCategoria : subCategorias)
	    panelSubCategorias.add(new PanelSubCategoria(subCategoria,
		    controller));

	for (int i = 0; i < panelSubCategorias.size(); i++)
	    layout.append("[25px]");

	pnSubCategorias.removeAll();

	// Define layout
	pnSubCategorias.setLayout(new MigLayout("", "[485px]", layout
		.toString()));

	pnSubCategorias.add(new PanelHearder(panelSubCategorias.get(0)),
		"cell 0 0,grow");

	for (int i = 0; i < panelSubCategorias.size(); i++)
	    pnSubCategorias.add(panelSubCategorias.get(i), "cell 0 " + (i + 1)
		    + ",grow");

	pnSubCategorias.updateUI();
    }

    public void atualizaCampos() {
	txtNome.setText("");
	txtNome.requestFocus();
    }

    protected void btnSalvarSubCategoriaActionPerformed(ActionEvent e) {
	String nome = txtNome.getText().trim();
	Categoria categoria = cbCategoria.getItemAt(cbCategoria
		.getSelectedIndex());

	if (nome.isEmpty()) {
	    mensagem.aviso("Nome é obrigatório.");
	    txtNome.requestFocus();
	    return;
	}

	if (categoria == null) {
	    mensagem.aviso("Categoria inválida.");
	    cbCategoria.requestFocus();
	    return;
	}

	SubCategoria subCategoria = new SubCategoria();
	subCategoria.setNome(nome);
	subCategoria.setCategoria(categoria);
	controller.salvar(subCategoria);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
