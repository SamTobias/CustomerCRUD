package br.com.samueltobias.customercrud.asynctask

interface Callback<T> {
    fun onFinish(success: T)
}