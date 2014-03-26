package br.com.vga.mymoney.controller;

public interface CrudController<T> {

    public void salvar(T object);

    public void atualizar(T object);

    public void excluir(T object);
}
