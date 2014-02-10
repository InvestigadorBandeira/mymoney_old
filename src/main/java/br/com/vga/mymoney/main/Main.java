package br.com.vga.mymoney.main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.controller.PrincipalController;
import br.com.vga.mymoney.dao.CategoriaDao;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.GrupoDao;
import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.dao.ReceitaDao;
import br.com.vga.mymoney.dao.SubCategoriaDao;
import br.com.vga.mymoney.dao.TituloDao;
import br.com.vga.mymoney.dao.TransferenciaDao;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.entity.Pagamento;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.Receita;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Conexao;

public class Main {

    private static StringBuilder print = new StringBuilder();

    public static void main(String[] args) {

	try {
	    // new TituloController(Conexao.getInstance());
	    new PrincipalController(Conexao.getInstance());
	    // criaRegistros(Conexao.getInstance());
	} catch (Exception e) {
	    System.out.println("ERRO:  " + e.getMessage());
	    Conexao.getInstance().getEntityManagerFactory().close();
	} finally {
	    Conexao.getInstance().getEntityManagerFactory().close();
	}

	System.out.println(print);
    }

    private static void criaRegistros(EntityManager em) {
	GrupoDao grupoDao = new GrupoDao(em);
	CategoriaDao categoriaDao = new CategoriaDao(em);
	ContaDao contaDao = new ContaDao(em);
	SubCategoriaDao subCategoriaDao = new SubCategoriaDao(em);
	TituloDao tituloDao = new TituloDao(em);
	ParcelaDao parcelaDao = new ParcelaDao(em);
	ReceitaDao receitaDao = new ReceitaDao(em);
	TransferenciaDao transferenciaDao = new TransferenciaDao(em);
	PagamentoDao pagamentoDao = new PagamentoDao(em);

	Grupo grupo = new Grupo();
	grupo.setNome("BANCOS");
	// grupoDao.save(grupo);

	Conta c1 = new Conta();
	c1.setNome("Santander");
	c1.setSaldoInicial(new BigDecimal("700.50"));
	c1.setGrupo(grupo);
	// contaDao.save(c1);

	Conta c2 = new Conta();
	c2.setNome("Bradesco");
	c2.setSaldoInicial(new BigDecimal("10.78"));
	c2.setGrupo(grupo);
	// contaDao.save(c2);

	Categoria categoria = new Categoria();
	categoria.setNome("INFORMÁTICA");
	// categoriaDao.save(categoria);

	SubCategoria sub1 = new SubCategoria();
	sub1.setNome("Periféricos");
	sub1.setCategoria(categoria);
	// subCategoriaDao.save(sub1);

	SubCategoria sub2 = new SubCategoria();
	sub2.setNome("Internet");
	sub2.setCategoria(categoria);
	// subCategoriaDao.save(sub2);

	Titulo titulo = new Titulo();
	titulo.setConta(contaDao.findById(1L));
	titulo.setData(Calendar.getInstance());
	titulo.setDescricao("Compra de 01 pendrive de 16Gb.");
	titulo.setValor(new BigDecimal("32.41"));
	// tituloDao.save(titulo);

	Parcela p1 = new Parcela();
	p1.setDataVencimento(Calendar.getInstance());
	p1.setValor(new BigDecimal("16.21"));
	p1.setSubCategoria(subCategoriaDao.findById(1L));
	p1.setObservacao("1");
	p1.setTitulo(titulo);
	// parcelaDao.save(p1);

	Parcela p2 = new Parcela();
	p2.setDataVencimento(Calendar.getInstance());
	p2.setValor(new BigDecimal("16.20"));
	p2.setSubCategoria(subCategoriaDao.findById(1L));
	p2.setObservacao("2");
	p2.setTitulo(titulo);
	// parcelaDao.save(p2);

	Receita receita = new Receita();
	receita.setConta(contaDao.findById(1L));
	receita.setData(Calendar.getInstance());
	receita.setDescricao("Salário");
	receita.setValor(new BigDecimal("698.00"));
	receita.setSubCategoria(subCategoriaDao.findById(2L));
	receita.setObservacao("Competência 12/2013");
	// receitaDao.save(receita);

	Transferencia transferencia = new Transferencia();
	transferencia.setContaOrigem(contaDao.findById(1L));
	transferencia.setContaDestino(contaDao.findById(2L));
	transferencia.setData(Calendar.getInstance());
	transferencia.setDescricao("Pagar fatura cartão Bradesco");
	transferencia.setValor(new BigDecimal("120.00"));
	transferencia.setObservacao("");
	// transferenciaDao.save(transferencia);

	Pagamento pagamento = new Pagamento();
	pagamento.setConta(contaDao.findById(1L));
	pagamento.setData(Calendar.getInstance());
	pagamento.setValorTotal(new BigDecimal("32.41"));
	// pagamentoDao.save(pagamento);

	p1 = parcelaDao.findById(1L);
	p1.setPagamento(pagamento);
	p1.setPaga(true);
	p2 = parcelaDao.findById(2L);
	p2.setPagamento(pagamento);
	p2.setPaga(true);
	// p1 = parcelaDao.update(p1);
	// p2 = parcelaDao.update(p2);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	print.append("\nContas\n");
	for (Grupo g : grupoDao.findAll()) {
	    print.append(" " + g.getId() + " - " + g.getNome() + "\n");

	    for (Conta c : g.getContas())
		print.append("     " + c.getId() + " - " + c.getNome() + " - "
			+ c.getSaldoInicial() + "\n");
	}

	print.append("\nCategorias\n");
	for (Categoria c : categoriaDao.findAll()) {
	    print.append(" " + c.getId() + " - " + c.getNome() + "\n");

	    for (SubCategoria s : c.getSubCategorias())
		print.append("     " + s.getId() + " - " + s.getNome() + "\n");
	}

	print.append("\nParcelas\n");
	for (Titulo t : tituloDao.findAll()) {
	    print.append(" " + t.getId() + " - " + t.getConta() + " - "
		    + sdf.format(t.getData().getTime()) + " - "
		    + t.getDescricao() + " - " + t.getValor() + "\n");

	    for (Parcela p : t.getParcelas())
		print.append("     " + p.getId() + " - "
			+ sdf.format(p.getDataVencimento().getTime()) + " - "
			+ p.getValor() + " - " + p.getSubCategoria() + " - "
			+ p.getObservacao() + "\n");
	}

	print.append("\nReceitas\n");
	for (Receita r : receitaDao.findAll())
	    print.append(" " + r.getId() + " - " + r.getConta() + " - "
		    + sdf.format(r.getData().getTime()) + " - "
		    + r.getDescricao() + " - " + r.getValor() + " - "
		    + r.getSubCategoria() + " - " + r.getObservacao() + "\n");

	print.append("\nTransferências\n");
	for (Transferencia t : transferenciaDao.findAll())
	    print.append(" " + t.getId() + " - " + t.getContaOrigem() + " - "
		    + t.getContaDestino() + " - "
		    + sdf.format(t.getData().getTime()) + " - "
		    + t.getDescricao() + " - " + t.getValor() + " - "
		    + t.getObservacao() + "\n");

	print.append("\nPagamentos\n");
	for (Pagamento p : pagamentoDao.findAll()) {
	    print.append(" " + p.getId() + " - " + p.getConta() + " - "
		    + sdf.format(p.getData().getTime()) + " - "
		    + p.getValorTotal() + "\n");

	    for (Parcela par : p.getParcelas())
		print.append("     " + par.getId() + " - "
			+ sdf.format(par.getDataVencimento().getTime()) + " - "
			+ par.getValor() + " - " + par.getSubCategoria()
			+ " - " + par.getObservacao() + "\n");
	}

	pagamento = pagamentoDao.findById(1L);
	p1 = parcelaDao.findById(1L);
	p1.setPagamento(null);
	p2 = parcelaDao.findById(2L);
	p2.setPagamento(null);
	// parcelaDao.update(p1);
	// parcelaDao.update(p2);

	// pagamentoDao.delete(pagamento);

    }
}
