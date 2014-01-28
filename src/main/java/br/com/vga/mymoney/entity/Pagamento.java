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
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Conta conta;

    @Temporal(TemporalType.DATE)
    private Calendar data;

    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Parcela> parcelas;

    private BigDecimal valorTotal;

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

    public List<Parcela> getParcelas() {
	return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
	this.parcelas = parcelas;
    }

    public BigDecimal getValorTotal() {
	return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
	this.valorTotal = valorTotal;
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
	Pagamento other = (Pagamento) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
