package br.com.vga.mymoney.main;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.dao.CategoriaDao;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.GrupoDao;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.dao.SubCategoriaDao;
import br.com.vga.mymoney.dao.TituloDao;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.util.Conexao;

public class Main {

    private static StringBuilder print = new StringBuilder();

    public static void main(String[] args) {

	try {
	    criaRegistros(Conexao.getInstance());
	    Conexao.getInstance().getEntityManagerFactory().close();
	} catch (Exception e) {
	    System.out.println("ERRO:  " + e.getMessage());
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

	// Titulo t1 = tituloDao.findById(1L);
	// tituloDao.delete(t1);
    }
}
