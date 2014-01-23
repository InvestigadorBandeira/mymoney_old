package br.com.vga.mymoney.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.dao.CategoriaDao;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.GrupoDao;
import br.com.vga.mymoney.dao.SubCategoriaDao;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.util.Conexao;

public class Main {

    public static void main(String[] args) {

	criaRegistros(Conexao.getInstance());

	Conexao.getInstance().getEntityManagerFactory().close();
    }

    private static void criaRegistros(EntityManager em) {
	GrupoDao grupoDao = new GrupoDao(em);
	CategoriaDao categoriaDao = new CategoriaDao(em);
	ContaDao contaDao = new ContaDao(em);
	SubCategoriaDao subCategoriaDao = new SubCategoriaDao(em);

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

	for (Grupo g : grupoDao.findAll()) {
	    System.out.println(g.getId() + " - " + g.getNome());

	    for (Conta c : g.getContas())
		System.out.println("     " + c.getId() + " - " + c.getNome()
			+ " - " + c.getSaldoInicial());
	}

	System.out.println();

	for (Categoria c : categoriaDao.findAll()) {
	    System.out.println(c.getId() + " - " + c.getNome());

	    for (SubCategoria s : c.getSubCategorias())
		System.out.println("     " + s.getId() + " - " + s.getNome());
	}

    }
}
