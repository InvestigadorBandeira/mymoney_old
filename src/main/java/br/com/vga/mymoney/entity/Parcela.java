package br.com.vga.mymoney.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Parcela implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar dataVencimento;

    private BigDecimal valor;

    private BigDecimal acrescimo;

    private BigDecimal desconto;

    @ManyToOne
    private SubCategoria subCategoria;

    private String observacao;

    @ManyToOne
    private Titulo titulo;

    @Column(nullable = false)
    private Boolean paga = false;

    @ManyToOne
    private Pagamento pagamento;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Calendar getDataVencimento() {
	return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
	this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public BigDecimal getAcrescimo() {
	return acrescimo;
    }

    public void setAcrescimo(BigDecimal acrescimo) {
	this.acrescimo = acrescimo;
    }

    public BigDecimal getDesconto() {
	return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
	this.desconto = desconto;
    }

    public SubCategoria getSubCategoria() {
	return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
	this.subCategoria = subCategoria;
    }

    public String getObservacao() {
	return observacao;
    }

    public void setObservacao(String observacao) {
	this.observacao = observacao;
    }

    public Titulo getTitulo() {
	return titulo;
    }

    public void setTitulo(Titulo titulo) {
	this.titulo = titulo;
    }

    public Boolean getPaga() {
	return paga;
    }

    public void setPaga(Boolean paga) {
	this.paga = paga;
    }

    public Pagamento getPagamento() {
	return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
	this.pagamento = pagamento;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Parcela other = (Parcela) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
