package br.com.samueltobias.customercrud.asynctask;

public interface Callback<T> {

    void onFinish(T success);
}
