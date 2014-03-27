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
import br.com.vga.mymoney.controller.GrupoController;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.PanelGrupo;
import br.com.vga.mymoney.view.tables.PanelHearder;

public class GrupoView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel pnCadastro;
    private JLabel lblNome;
    private JTextField txtNome;
    private JPanel pnListagem;
    private JLabel lblListagem;
    private JScrollPane scrollGrupos;

    private Mensagem mensagem;

    private final GrupoController controller;
    private JButton btnSair;
    private JButton btnSalvarGrupo;
    private JPanel pnGrupos;

    public GrupoView(GrupoController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Cadastro de Grupos", TitledBorder.LEADING, TitledBorder.TOP,
		null, null));
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

	btnSalvarGrupo = new JButton("Salvar Grupo");
	btnSalvarGrupo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnSalvarGrupoActionPerformed(e);
	    }
	});
	btnSalvarGrupo.setBounds(10, 106, 150, 25);
	add(btnSalvarGrupo);

	pnListagem = new JPanel();
	pnListagem.setLayout(null);
	pnListagem.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnListagem.setBackground(new Color(250, 250, 210));
	pnListagem.setBounds(10, 142, 554, 297);
	add(pnListagem);

	lblListagem = new JLabel("Listagem de Grupos Cadastrados");
	lblListagem.setHorizontalAlignment(SwingConstants.CENTER);
	lblListagem.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblListagem.setBounds(10, 11, 534, 20);
	pnListagem.add(lblListagem);

	scrollGrupos = new JScrollPane();
	scrollGrupos.setBounds(10, 42, 534, 244);
	pnListagem.add(scrollGrupos);

	pnGrupos = new JPanel();
	scrollGrupos.setViewportView(pnGrupos);

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
	mensagem = new Mensagem(this, "Cadastro de Grupos");
    }

    public void montaListagemGrupos(List<Grupo> grupos) {
	if (grupos == null || grupos.isEmpty()) {
	    pnGrupos.removeAll();
	    pnGrupos.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelGrupo> panelGrupos = new ArrayList<>();

	for (Grupo grupo : grupos)
	    panelGrupos.add(new PanelGrupo(grupo, controller));

	for (int i = 0; i < panelGrupos.size(); i++)
	    layout.append("[25px]");

	pnGrupos.removeAll();

	// Define layout
	pnGrupos.setLayout(new MigLayout("", "[375px]", layout.toString()));

	pnGrupos.add(new PanelHearder(panelGrupos.get(0)), "cell 0 0,grow");

	for (int i = 0; i < panelGrupos.size(); i++)
	    pnGrupos.add(panelGrupos.get(i), "cell 0 " + (i + 1) + ",grow");

	pnGrupos.updateUI();
    }

    public void atualizaCampos() {
	txtNome.setText("");
	txtNome.requestFocus();
    }

    protected void btnSalvarGrupoActionPerformed(ActionEvent e) {
	String nome = txtNome.getText().trim();

	if (nome.isEmpty()) {
	    mensagem.aviso("Nome é obrigatório.");
	    txtNome.requestFocus();
	    return;
	}

	Grupo grupo = new Grupo();
	grupo.setNome(nome);
	controller.salvar(grupo);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
