package br.com.vga.mymoney.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Titulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Conta conta;

    @Temporal(TemporalType.DATE)
    private Calendar data;

    private String descricao;

    private BigDecimal valor;

    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Parcela> parcelas;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Conta getConta() {
	return conta;
    }

    public void setConta(Conta conta) {
	this.conta = conta;
    }

    public Calendar getData() {
	return data;
    }

    public void setData(Calendar data) {
	this.data = data;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
    }

    public List<Parcela> getParcelas() {
	return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
	this.parcelas = parcelas;
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
	Titulo other = (Titulo) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
