package br.com.vga.mymoney.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.ReceitaDao;
import br.com.vga.mymoney.dao.SubCategoriaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Receita;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.pattern.SaldoObserver;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ReceitaView;

public class ReceitaController implements CrudController<Receita> {

    private final ReceitaDao dao;
    private ReceitaView view;
    private Mensagem mensagem;

    private final ContaDao contaDao;
    private final SubCategoriaDao subCategoriaDao;

    private final SaldoObserver observer;
    private final JPanel telas;

    public ReceitaController(EntityManager em, SaldoObserver observer,
	    JPanel telas) {
	dao = new ReceitaDao(em);
	contaDao = new ContaDao(em);
	subCategoriaDao = new SubCategoriaDao(em);

	this.observer = observer;
	this.telas = telas;
    }

    public void exibeView() {
	view = new ReceitaView(this);
	montaComboConta();
	montaComboSubCategoria();
	view.atualizaCampos();
	filtraPorTodas();
	mensagem = new Mensagem(view, "Cadastro de Receitas");

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.setVisible(true);
    }

    private void montaComboConta() {
	List<Conta> contas = contaDao.findAll();

	if (contas == null || contas.isEmpty())
	    mensagem.aviso("É necessário cadastrar Contas primeiro.");
	else
	    view.montaComboConta(contas);
    }

    public void montaComboSubCategoria() {
	List<SubCategoria> subCategorias = subCategoriaDao.findAll();

	if (subCategorias == null || subCategorias.isEmpty())
	    mensagem.aviso("É necessário cadastrar SubCategorias primeiro.");
	else
	    view.montaComboSubCategoria(subCategorias);
    }

    @Override
    public void salvar(Receita receita) {
	dao.save(receita);
	view.atualizaCampos();
	filtraPorTodas();
	observer.atualizaSaldoContas();
    }

    @Override
    public void atualizar(Receita receita) {
	mensagem.info("Funcionalidade não implementada.");
	// observer.atualizaSaldoContas();
    }

    @Override
    public void excluir(Receita receita) {

	int confirma = mensagem.confirma(String.format(
		"Deseja excluir a Receita:\nConta: %s\nData: %s\nDescrição: "
			+ "%s\nValor: %s\nSubCategoria: %s\nObservação: %s",
		receita.getConta(), Formatador.dataTexto(receita.getData()),
		receita.getDescricao(),
		Formatador.valorTexto(receita.getValor()),
		receita.getSubCategoria(), receita.getObservacao()));

	if (confirma == 0) {
	    dao.delete(receita);
	    view.montaListagemReceitas(dao.findAll());
	    observer.atualizaSaldoContas();
	}
    }

    // implementar filtro data
    public void filtraPorTodas() {
	view.montaListagemReceitas(dao.findAll());
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }
}
