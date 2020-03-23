package br.com.samueltobias.customercrud.asynctask;

public interface RetornoAcaoDao<T> {

    void quandoTermina(T sucesso);
}
