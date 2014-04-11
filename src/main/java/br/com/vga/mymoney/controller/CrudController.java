package br.com.vga.mymoney.controller;

public interface CrudController<T> {

    public void salvar(T object);

    public void alterar(T object);

    public void excluir(T object);

    public void visualizar(T object);
}
