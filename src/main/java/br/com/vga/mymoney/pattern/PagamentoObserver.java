package br.com.vga.mymoney.pattern;

import br.com.vga.mymoney.entity.Parcela;

public interface PagamentoObserver {

    public void incluirParcela(Parcela parcela);

    public void excluirParcela(Parcela parcela);

    public void calculaTotal();
}
